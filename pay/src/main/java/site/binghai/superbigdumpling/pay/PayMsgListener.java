package site.binghai.superbigdumpling.pay;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.mns.client.CloudAccount;
import com.aliyun.mns.client.CloudQueue;
import com.aliyun.mns.client.MNSClient;
import com.aliyun.mns.model.Message;
import com.aliyun.mns.model.RawTopicMessage;
import com.aliyun.mns.model.TopicMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Created by IceSea on 2017/11/19.
 * GitHub: https://github.com/IceSeaOnly
 */
@RestController
@RequestMapping("/notify")
public class PayMsgListener implements InitializingBean {
    private final Logger logger = LoggerFactory.getLogger(PayMsgListener.class);

    @Autowired
    private TopicParam topicParam;
    private CloudAccount account = null;
    private MNSClient client = null;
    private CloudQueue queue = null;


    @Override
    public void afterPropertiesSet() throws Exception {
        account = new CloudAccount(topicParam.getAccessId(), topicParam.getAccessKey(), topicParam.getEndPoint());
        client = account.getMNSClient();
        queue = client.getQueueRef(topicParam.getQueueName());
    }

    private void postTopicMsg(String msgText) {
        try {
            Message msg = new Message();
            msg.setMessageBody(msgText);
            msg = queue.putMessage(msg);
            logger.info("msgText:{},msgId:{},msgMD5:{}", msgText, msg.getMessageId(), msg.getMessageBodyMD5());
        } catch (Exception e) {
            logger.error("postTopicMsg Error! msgText:{}", msgText, e);
        }
    }

    @RequestMapping("api")
    public String api(){
        postTopicMsg(JSONObject.toJSONString(topicParam));
        return "ok";
    }

}
