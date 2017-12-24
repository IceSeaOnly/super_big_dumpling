package site.binghai.SuperBigDumpling.common.facades;

import com.alibaba.fastjson.JSONArray;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import site.binghai.SuperBigDumpling.common.entity.things.Express;
import site.binghai.SuperBigDumpling.common.entity.people.Order;
import site.binghai.SuperBigDumpling.common.entity.people.OrderAddress;

/**
 * Created by IceSea on 2017/10/29.
 * GitHub: https://github.com/IceSeaOnly
 */
@Data
@AllArgsConstructor
@Builder
public class OrderFacade extends BaseFacade<Order> {
    private String orderNum;
    private String orderStatus;
    private int oid;
    private String img;
    private String name;
    private JSONArray goodsProp;
    private int goodsNum;
    private double gprice;
    private double totalPrice;
    private OrderAddress address;
    private String createTime;
    private String groupTime;
    private String deliveryTime;
    private String completeTime;
    private int groupNum;
    private Express express;

    public OrderFacade() {
    }

    @Override
    public BaseFacade asObj(Order obj) {
        OrderFacade orderFacade = OrderFacade.builder()
                .orderNum(obj.getOrderNum())
                .orderStatus(obj.getOrderStatus())
                .oid(obj.getId())
                .img(obj.getImg())
                .name(obj.getName())
                .goodsProp(JSONArray.parseArray(obj.getProperties()))
                .goodsNum(obj.getGoodsNum())
                .gprice(obj.getPrice() / 100.0)
                .totalPrice(obj.getTotalPrice() / 100.0)
                .address(obj.getAddress())
                .createTime(obj.getCreateTime())
                .groupTime(obj.getGroupTime())
                .deliveryTime(obj.getDeliveryTime())
                .completeTime(obj.getCompleteTime())
                .express(obj.getExpress())
                .groupNum(obj.getGroupNum())
                .build();
        return orderFacade;
    }
}
