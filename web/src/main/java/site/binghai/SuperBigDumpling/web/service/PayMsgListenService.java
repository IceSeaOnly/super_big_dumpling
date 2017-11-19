package site.binghai.SuperBigDumpling.web.service;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.mns.client.CloudAccount;
import com.aliyun.mns.client.CloudQueue;
import com.aliyun.mns.client.MNSClient;
import com.aliyun.mns.model.Message;
import com.aliyun.mns.model.QueueMeta;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.binghai.SuperBigDumpling.web.entity.TopicParam;

/**
 * Created by IceSea on 2017/11/19.
 * GitHub: https://github.com/IceSeaOnly
 */
@Service
public class PayMsgListenService implements InitializingBean {
    private final Logger logger = LoggerFactory.getLogger(PayMsgListenService.class);

    @Autowired
    private TopicParam topicParam;
    private MNSClient client;
    private CloudQueue queue;

    private CloudAccount account = null;

    @Override
    public void afterPropertiesSet() throws Exception {
        account = new CloudAccount(topicParam.getAccessId(), topicParam.getAccessKey(), topicParam.getEndPoint());
        client = account.getMNSClient();

        QueueMeta queueMeta = new QueueMeta();
        queueMeta.setQueueName(topicParam.getQueueName());
        queue = client.createQueue(queueMeta);

        new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {
                        consumeMsg();
                    } catch (Exception e) {
                        logger.error("consumer pay msg error !", e);
                    }
                }
            }
        }.start();
    }

    private void consumeMsg() {
        Message msg = queue.popMessage(30);
        if (msg != null) {
            logger.info("pay msg got:{}",msg);
            queue.deleteMessage(msg.getReceiptHandle());
        }
    }
}
