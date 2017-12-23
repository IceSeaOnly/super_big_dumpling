package site.binghai.SuperBigDumpling.common.entity.things;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by binghai on 2017/12/23.
 * 配置项
 * @ super_big_dumpling
 */
@Data
@Entity
public class Configs {
    @Id
    @GeneratedValue
    private int id = 0;
    /**
     * 默认头像
     * */
    private String defaultAvatar = "http://image.binghai.site/data/f_38388299.jpg";
    /**
     * 默认用户名
     * */
    private String defaultUserNickName = "二狗";
    /**
     * 默认成团人数
     * */
    private int defaultGroupSize = 2;
    /**
     * 默认成团时间：秒
     * */
    private int defaultGroupTimeOut = 1800;
}
