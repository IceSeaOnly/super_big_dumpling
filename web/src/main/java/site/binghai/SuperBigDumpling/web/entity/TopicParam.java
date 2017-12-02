package site.binghai.SuperBigDumpling.web.entity;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * Created by IceSea on 2017/11/19.
 * GitHub: https://github.com/IceSeaOnly
 */
@Component
@ConfigurationProperties(prefix = "ali")
@PropertySource("classpath:topic.properties")
@Data
public class TopicParam {
    private String accessId;
    private String accessKey;
    private String endPoint;
    private String queueName;
    private String tuanInfoQueue; //团状态变换消息队列
}
