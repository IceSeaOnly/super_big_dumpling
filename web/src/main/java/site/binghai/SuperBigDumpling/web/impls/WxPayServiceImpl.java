package site.binghai.SuperBigDumpling.web.impls;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.collections4.MapUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.binghai.SuperBigDumpling.api.services.WxPayApi;
import site.binghai.SuperBigDumpling.common.utils.WxPayUtil;
import site.binghai.SuperBigDumpling.common.entity.people.Order;
import site.binghai.SuperBigDumpling.common.entity.WxParams;
import site.binghai.SuperBigDumpling.common.facades.PaymentPo;
import site.binghai.SuperBigDumpling.web.service.OrderService;
import site.binghai.SuperBigDumpling.common.utils.HttpRequestUtils;
import site.binghai.SuperBigDumpling.common.utils.MD5;
import site.binghai.SuperBigDumpling.common.utils.MessageUtil;
import org.apache.commons.lang.StringUtils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by binghai on 2017/11/12.
 *
 * @ MoGuJie
 */
@Service
public class WxPayServiceImpl implements WxPayApi, InitializingBean {
    private final Logger logger = LoggerFactory.getLogger(WxPayServiceImpl.class);

    @Autowired
    WxParams wxParams;
    private String this_ip;

    @Autowired
    private OrderService orderService;

    @Override
    public JSONObject getPayInfomation(String outTradeNo) {
        Order order = null;
        if(StringUtils.isNumeric(outTradeNo)){
            order = orderService.findById(Integer.parseInt(outTradeNo));
        }else{
            order = orderService.getByOutTradeNo(outTradeNo);
        }
        if (order == null) {
            logger.error("生成微信交易订单错误-1,outTradeNo:{}", outTradeNo);
            return null;
        }

        PaymentPo paymentPo = PaymentPo.builder()
                .appid(wxParams.getAppId())
                .mch_id(wxParams.getMch_id())
                .nonce_str(MD5.encryption(System.nanoTime() + this_ip + wxParams.getBaseNonceStr()))
                .body(order.getName() + "x" + order.getGoodsNum())
                .out_trade_no(order.getOrderNum())
                .total_fee(order.getTotalPrice() + "")
                .spbill_create_ip(this_ip)
                .notify_url(wxParams.getNotify_url())
                .trade_type("JSAPI")
                .openid(order.getOpenId())
                .attach("附加数据")
                .detail(order.getName())
                .device_info("wx-server")
                .fee_type("CNY")
                .goods_tag("")
                .limit_pay("no_credit") //不支持信用卡支付
                .goods_tag("") //商品标记
                .build();
        try {
            return getWxPayInfo(paymentPo);
        } catch (DocumentException e) {
            logger.error("生成微信交易订单错误-2", e);
        }
        return null;
    }


    private JSONObject getWxPayInfo(PaymentPo paymentPo) throws DocumentException {
        Map sParaTemp = JSONObject.parseObject(JSONObject.toJSONString(paymentPo));
        // 除去数组中的空值和签名参数
        Map sPara = WxPayUtil.paraFilter(sParaTemp);
        String prestr = WxPayUtil.createLinkString(sPara); // 把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串
        String key = "&key=" + wxParams.getPayAppSecret(); // 商户支付密钥
        //MD5运算生成签名
        String mysign = WxPayUtil.sign(prestr, key, "utf-8").toUpperCase();
        paymentPo.setSign(mysign);
        //打包要发送的xml
        String respXml = MessageUtil.messageToXML(paymentPo);
        // 打印respXml发现，得到的xml中有“__”不对，应该替换成“_”
        respXml = respXml.replace("__", "_");
        String url = "https://api.mch.weixin.qq.com/pay/unifiedorder";//统一下单API接口链接
        String result = HttpRequestUtils.sendPost(url, respXml);
        // 将解析结果存储在HashMap中
        Map map = new HashMap();
        InputStream in = new ByteArrayInputStream(result.getBytes());
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
        // 返回信息
        String return_code = MapUtils.getString(map, "return_code");//返回状态码
        String return_msg = MapUtils.getString(map, "return_msg");//返回信息

        logger.warn("微信支付返回信息,order:{},code:{},msg:{}", JSONObject.toJSONString(paymentPo), return_code, return_msg);

        JSONObject resp = new JSONObject();
        if (return_code == "SUCCESS" || return_code.equals(return_code)) {
            // 业务结果
            String prepay_id = MapUtils.getString(map, "prepay_id");//返回的预付单信息
            String nonceStr = MD5.encryption(System.nanoTime() + this_ip + wxParams.getBaseNonceStr());
            resp.put("nonceStr", nonceStr);
            resp.put("package", "prepay_id=" + prepay_id);
            Long timeStamp = System.currentTimeMillis() / 1000;
            resp.put("timeStamp", timeStamp + "");
            String stringSignTemp = "appId=" + wxParams.getAppId() + "&nonceStr=" + nonceStr + "&package=prepay_id=" + prepay_id + "&signType=MD5&timeStamp=" + timeStamp;
            //再次签名
            String paySign = WxPayUtil.sign(stringSignTemp, "&key=" + wxParams.getPayAppSecret(), "utf-8").toUpperCase();
            resp.put("paySign", paySign);
        }
        return resp;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this_ip = InetAddress.getLocalHost().getHostAddress().toString();
    }
}
