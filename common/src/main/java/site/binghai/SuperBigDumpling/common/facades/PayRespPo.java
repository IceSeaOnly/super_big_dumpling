package site.binghai.SuperBigDumpling.common.facades;

import lombok.Data;

/**
 * Created by binghai on 2017/11/21.
 *
 * @ super_big_dumpling
 */
@Data
public class PayRespPo {
    private String appid;//小程序ID
    private String mch_id;//商户号
    private String nonce_str;//随机字符串
    private String sign;//签名
    private String result_code; //
    private String openid; //
    private String trade_type; // 交易类型,货币类型默认人民币：CNY
    private String bank_type; // 银行类型
    private int total_fee; //
    private int cash_fee; // 现金支付金额订单现金支付金额
    private String transaction_id; // 微信支付订单号
    private String out_trade_no; // 商户系统的订单号
    private String time_end; // 支付完成时间，格式为yyyyMMddHHmmss
    private String attach; // 商家数据包，原样返回
}
