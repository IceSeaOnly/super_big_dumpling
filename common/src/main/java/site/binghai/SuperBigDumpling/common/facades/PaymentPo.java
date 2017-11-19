package site.binghai.SuperBigDumpling.common.facades;

import lombok.Builder;
import lombok.Data;

/**
 * Created by binghai on 2017/11/12.
 *
 * @ MoGuJie
 */
@Data
@Builder
public class PaymentPo {
    private String appid;//小程序ID
    private String mch_id;//商户号
    private String device_info;//设备号
    private String nonce_str;//随机字符串
    private String sign;//签名
    private String body;//商品描述
    private String detail;//商品详情
    private String attach;//附加数据
    private String out_trade_no;//商户订单号
    private String fee_type;//货币类型
    private String spbill_create_ip;//终端IP
    private String goods_tag;//商品标记
    private String total_fee;//总金额
    private String notify_url;//通知地址
    private String trade_type;//交易类型
    private String limit_pay;//指定支付方式
    private String openid;//用户标识
}
