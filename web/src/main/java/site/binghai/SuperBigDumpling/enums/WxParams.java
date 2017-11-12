package site.binghai.SuperBigDumpling.enums;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * Created by IceSea on 2017/10/27.
 * GitHub: https://github.com/IceSeaOnly
 */
@Component
@ConfigurationProperties(prefix = "wx")
@PropertySource("classpath:wxpay.properties")
@Data
public class WxParams {
    private String appId;
    private String secret; // appSecret
    private String mch_id;
    private String baseNonceStr;
    private String paySecret; // 商户支付密钥
    private String notify_url; // 微信支付回调

}
