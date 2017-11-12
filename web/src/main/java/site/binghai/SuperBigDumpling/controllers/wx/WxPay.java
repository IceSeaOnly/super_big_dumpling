package site.binghai.SuperBigDumpling.controllers.wx;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import services.WxPayApi;
import site.binghai.SuperBigDumpling.controllers.MultiController;
import site.binghai.SuperBigDumpling.impls.WxPayServiceImpl;
import site.binghai.SuperDumpling.common.definations.ApiRequestMapping;
import site.binghai.SuperDumpling.common.system.ErrorList;

import java.util.Map;

/**
 * Created by IceSea on 2017/10/26.
 * GitHub: https://github.com/IceSeaOnly
 */
@Controller
public class WxPay extends MultiController {

    @Autowired
    private WxPayApi wxPayApi;

    @ApiRequestMapping("wx-pay")
    public Object wxPay(Map params) {
        String outTradeNo = getString(params, "oid");
        Object resp = wxPayApi.getPayInfomation(outTradeNo);
        return resp == null ? error(ErrorList.ORDER_EXCEPTION, "订单异常", null) : resp;
    }
}
