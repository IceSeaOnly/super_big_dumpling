package site.binghai.SuperBigDumpling.web.services;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.mns.client.CloudAccount;
import com.aliyun.mns.client.CloudQueue;
import com.aliyun.mns.client.MNSClient;
import com.aliyun.mns.model.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;
import site.binghai.SuperBigDumpling.api.enums.GroupStatusEnum;
import site.binghai.SuperBigDumpling.api.enums.OrderStatusEnum;
import site.binghai.SuperBigDumpling.common.entity.people.Order;
import site.binghai.SuperBigDumpling.common.entity.things.Group;
import site.binghai.SuperBigDumpling.common.facades.PayRespPo;
import site.binghai.SuperBigDumpling.dao.service.GroupService;
import site.binghai.SuperBigDumpling.dao.service.OrderService;
import site.binghai.SuperBigDumpling.web.entity.TopicParam;

import java.util.List;

/**
 * Created by IceSea on 2017/11/19.
 * GitHub: https://github.com/IceSeaOnly
 */
@Service
public class PayMsgListenService implements ApplicationListener<ContextRefreshedEvent> {
    private final Logger logger = LoggerFactory.getLogger(PayMsgListenService.class);

    @Autowired
    private OrderService orderService;
    @Autowired
    private TopicParam topicParam;
    @Autowired
    private GroupService groupService;

    private Boolean initFlag = Boolean.FALSE;
    private MNSClient client;
    private CloudQueue queue;
    private CloudQueue tuaninfoQueue;


    private CloudAccount account = null;

    public void intiListener() throws Exception {
        account = new CloudAccount(topicParam.getAccessId(), topicParam.getAccessKey(), topicParam.getEndPoint());
        client = account.getMNSClient();
        queue = client.getQueueRef(topicParam.getQueueName());
        tuaninfoQueue = client.getQueueRef(topicParam.getTuanInfoQueue());

        new Thread() {
            @Override
            public void run() {
                while (true) {
                    Message msg = null;
                    try {
                        msg = queue.popMessage(10);
                        if (msg == null) {
                            continue;
                        }
                        logger.info("新支付消息到达:{}", msg.getMessageBody());
                        consumeMsg(msg);
                    } catch (Exception e) {
                        logger.error("消费支付信息出错 !msg:{}", msg, e);
                    }
                }
            }
        }.start();
        new Thread() {
            @Override
            public void run() {
                while (true) {
                    Message msg = null;
                    try {
                        msg = tuaninfoQueue.popMessage(10);
                        if (msg == null) {
                            continue;
                        }
                        consumeTuanInfoMsg(msg);
                    } catch (Exception e) {
                        logger.error("消费团状态变更消息出错 !msg:{}", msg, e);
                    }
                }
            }
        }.start();

        logger.warn("消息监听启动。。。");
    }

    private void consumeMsg(Message msg) {
        PayRespPo data = JSONObject.parseObject(msg.getMessageBody(), PayRespPo.class);
        if (data != null) {
            Order order = orderService.getByOutTradeNo(data.getOut_trade_no());
            if (order != null && order.getOpenId().equals(data.getOpenid())) {
                order.setStatus(order.isGroupOrder() ? OrderStatusEnum.WAITING_GROUP_BUILED : OrderStatusEnum.GROUP_BUILED);
                order.setHasPay(true);
                orderService.update(order);
                putTuanInfoMessage(order);
                logger.info("订单已支付,orderId:{}", order.getId());
            } else {
                logger.error("支付消息反查询订单失败!消息:{}", msg);
            }
        } else {
            logger.error("支付消息反序列化失败!消息:{}", msg);
        }
        queue.deleteMessage(msg.getReceiptHandle());
    }

    /**
     * 订单变化发送到队列中
     * 单线程处理每个订单状态转移，保证及时成团
     */
    private void putTuanInfoMessage(Order order) {
        Message msg = new Message();
        msg.setMessageBody(String.valueOf(order.getId()));
        tuaninfoQueue.putMessage(msg);
    }

    /**
     * 监听每个团状态变更
     *
     * @param msg
     */
    private void consumeTuanInfoMsg(Message msg) {
        Integer orderId = Integer.valueOf(msg.getMessageBody());
        Order order = orderService.findById(orderId);
        if (order == null) {
            logger.error("团状态变更消息查询不到订单!", msg);
        } else {
            if (order.isGroupOrder()) {
               checkTuanBuild(order.getGroupId());
            } else {
                singleOrderBuilded(order);
            }
        }
        tuaninfoQueue.deleteMessage(msg.getReceiptHandle());
    }

    /**
     * 单个订单支付成功后逻辑
     * */
    private void singleOrderBuilded(Order order) {
        order.setStatus(OrderStatusEnum.GINGEL_ORDER_BUILED);
        orderService.update(order);
        // todo 推送通知
    }

    /**
     * 检查团内所有订单完成
     * */
    private void checkTuanBuild(int groupId) {
        Group group = groupService.findById(groupId);
        List<Order> orders = orderService.findByIds(group.getOrders());
        boolean allHasPay = true;
        for (Order o : orders){
            if(!o.isHasPay()){
                allHasPay = false;
                break;
            }
        }

        if(allHasPay){
            for (Order o : orders){
                o.setStatus(OrderStatusEnum.GROUP_BUILED);
                // todo 推送通知
                orderService.update(o);
            }
            group.setStatus(GroupStatusEnum.GROUP_SUCCESS);
            groupService.update(group);
        }
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (event.getApplicationContext().getParent() == null) {
            try {
                if (!initFlag) {
                    intiListener();
                    initFlag = Boolean.TRUE;
                }
            } catch (Exception e) {
                logger.error("消息监听器启动失败!", e);
            }
        }
    }
}
