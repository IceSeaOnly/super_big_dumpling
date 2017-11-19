package site.binghai.SuperBigDumpling.common.entity.people;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import site.binghai.SuperBigDumpling.common.entity.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by binghai on 2017/10/16.
 * 订单地址
 * @ MoGuJie
 */
@Data
@Entity
public class OrderAddress extends AbstractEntity {
    @Id
    @GeneratedValue
    private int id;
    private String userName;
    private String provinceName;
    private String cityName;
    private String countyName;
    private String telNumber;
    private String detailInfo;

    public OrderAddress() {
    }

    public OrderAddress(UserAddress userAddress) {

    }

    public static OrderAddress ofJson(String json){
        return JSONObject.parseObject(json,OrderAddress.class);
    }
}
