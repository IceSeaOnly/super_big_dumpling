package site.binghai.superbigdumpling.pay;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.mns.client.CloudAccount;
import com.aliyun.mns.client.CloudQueue;
import com.aliyun.mns.client.MNSClient;
import com.aliyun.mns.model.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import site.binghai.SuperBigDumpling.common.facades.PayRespPo;
import site.binghai.SuperBigDumpling.common.utils.WxPayUtil;

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
    public String api(@RequestBody String data) throws DocumentException {
        logger.info("Pay Info:{}",data);

        HashMap map = new HashMap();
        InputStream in = new ByteArrayInputStream(data.getBytes());
        // 读取输入流
        SAXReader reader = new SAXReader();
        Document document = reader.read(in);
        // 得到xml根元素
        Element root = document.getRootElement();
        // 得到根元素的所有子节点
        List<Element> elementList = root.elements();
        for (Element element : elementList) {
            map.put(element.getName(), element.getText());
        }
        String receiveSign = String.valueOf(map.remove("sign"));

        String prestr = WxPayUtil.createLinkString(map); // 把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串
        String key = "&key=" + topicParam.getAppPaySecret(); // 商户支付密钥
        //MD5运算生成签名
        String mysign = WxPayUtil.sign(prestr, key, "utf-8").toUpperCase();
        if(!mysign.equals(receiveSign)){
            logger.error("签名不正确:{}",data);
            return "<xml><return_code><![CDATA[FAIL]]></return_code><return_msg><![CDATA[签名不正确]]></return_msg></xml>";
        }

        PayRespPo payRespPo = JSONObject.parseObject(JSONObject.toJSONString(map), PayRespPo.class);
        if(payRespPo != null && "SUCCESS".equals(payRespPo.getResult_code())){
            postTopicMsg(JSONObject.toJSONString(map));
            logger.info("Json Msg : {}");
        }else{
            logger.error("Pay Info Error:{}",data);
        }

        return "<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>";
    }

}
