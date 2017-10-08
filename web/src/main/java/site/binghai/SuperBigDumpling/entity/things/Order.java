package site.binghai.SuperBigDumpling.entity.things;

import lombok.Data;
import site.binghai.SuperBigDumpling.entity.people.User;
import site.binghai.SuperBigDumpling.enums.OrderStatusEnum;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Arrays;

/**
 * Created by binghai on 2017/9/30.
 *
 * @ MoGuJie
 */
@Entity(name = "orders")
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
    private String remarks; //备注信息
    private String address;
    private String recipients; //收件人
    private String recipientsPhone; //收件人手机

    public OrderStatusEnum getStatusEnum() {
        return OrderStatusEnum.of(status);
    }

    public void setAddress(Address... adds) {
        StringBuilder sb = new StringBuilder();
        Arrays.stream(adds).forEach(v -> sb.append(v.getName()));
        address = sb.toString();
    }
}
