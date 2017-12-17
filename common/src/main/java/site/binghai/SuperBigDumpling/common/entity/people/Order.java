package site.binghai.SuperBigDumpling.common.entity.people;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import site.binghai.SuperBigDumpling.api.enums.OrderStatusEnum;
import site.binghai.SuperBigDumpling.common.entity.AbstractEntity;
import site.binghai.SuperBigDumpling.common.entity.things.Express;
import site.binghai.SuperBigDumpling.common.entity.things.Group;
import site.binghai.SuperBigDumpling.common.entity.things.TradeItem;
import site.binghai.SuperBigDumpling.common.utils.TimeFormatter;

import javax.persistence.*;

/**
 * Created by binghai on 2017/10/16.
 *
 * @ MoGuJie
 */
@Entity(name = "orders")
@Data
@Builder
@AllArgsConstructor
public class Order extends AbstractEntity {
    @Id
    @GeneratedValue
    private int id;
    @OneToOne//(fetch = FetchType.EAGER)
    private TradeItem tradeItem;
    private int price;
    private int goodsNum; //购买数量
    private int totalPrice;
    @OneToOne
    private Group whichGroup; //所属团，非拼团为null
    private String openId;
    private String properties; //购买属性 json
    private String img;
    private String orderNum; //商户订单号
    private OrderStatusEnum status;
    private String orderStatus;
    private String name;
    @OneToOne//(fetch = FetchType.EAGER)
    private OrderAddress address;
    private String groupTime; //成团时间
    private long groupTimeTs; //成团时间
    private String deliveryTime; //发货时间
    private long deliveryTimeTs; //发货时间戳
    private String completeTime; //成交时间
    private long completeTimeTs; //成交时间戳
    private String createTime;
    private boolean groupOrder; //是否是拼团订单
    @OneToOne//(fetch = FetchType.EAGER)
    private Express express;
    private boolean hasPay = false; // 是否已经支付
    private long payTime;

    public void setHasPay(boolean hasPay) {
        this.hasPay = hasPay;
        this.payTime = System.currentTimeMillis();
    }

    // 设置成团
    public void groupSuccess() {
        this.groupTimeTs = System.currentTimeMillis();
        this.groupTime = TimeFormatter.format(this.groupTimeTs);
    }

    public Order() {
        createTime = TimeFormatter.format(System.currentTimeMillis());
    }

    public void setStatus(OrderStatusEnum status) {
        this.status = status;
        this.orderStatus = status.getStatus();
    }
}
