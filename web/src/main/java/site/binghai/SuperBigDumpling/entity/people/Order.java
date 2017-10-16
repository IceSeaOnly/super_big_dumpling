package site.binghai.SuperBigDumpling.entity.people;

import lombok.Data;
import site.binghai.SuperBigDumpling.entity.AbstractEntity;
import site.binghai.SuperBigDumpling.entity.things.Express;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;
import java.util.Map;

/**
 * Created by binghai on 2017/10/16.
 *
 * @ MoGuJie
 */
@Entity
@Data
public class Order extends AbstractEntity {
    @Id
    @GeneratedValue
    private int id;
    private int price;
    private int goodsNum; //购买数量
    private int totalPrice;
    @ElementCollection
    private Map<String,List<String>> properties; //购买属性
    private String img;
    private String orderNum; //订单编号
    private String orderStatus;
    private String name;
    private OrderAddress address;
    private String groupTime; //成团时间
    private long groupTimeTs; //成团时间
    private String deliveryTime; //发货时间
    private long deliveryTimeTs; //发货时间戳
    private String completeTime; //成交时间
    private long completeTimeTs; //成交时间戳
    private String createTime;
    private long createTimeTs;
    private Express express;
}