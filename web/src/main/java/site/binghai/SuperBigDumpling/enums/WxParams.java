package site.binghai.SuperBigDumpling.enums;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by IceSea on 2017/10/27.
 * GitHub: https://github.com/IceSeaOnly
 */
@Component
@ConfigurationProperties(prefix = "wx")
@Data
public class WxParams {
    private String appId;
    private String secret;
}
