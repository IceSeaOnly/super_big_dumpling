package site.binghai.SuperBigDumpling.entity.things;

import lombok.Data;
import site.binghai.SuperBigDumpling.entity.people.User;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by binghai on 2017/9/30.
 *
 * @ MoGuJie
 */
@Entity
@Data
public class Order extends AbstractEntity {
    @Id
    @GeneratedValue
    private int id;
    private int gid; //团id
    private User user;
    private TradeItem tradeItem;
    private int oprice;//原价
    private int price; //成交价
    private boolean hasPay;
    private int status; //状态参考OrderStatusEnum
    private String payId;
    private String refundId;
    private long payTime;
    private long refundTime;
}
