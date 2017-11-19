package site.binghai.SuperBigDumpling.api.services;

import com.alibaba.fastjson.JSONObject;


/**
 * Created by binghai on 2017/11/12.
 *
 * @ MoGuJie
 */
public interface WxPayApi {
    JSONObject getPayInfomation(String outTradeNo);
}
