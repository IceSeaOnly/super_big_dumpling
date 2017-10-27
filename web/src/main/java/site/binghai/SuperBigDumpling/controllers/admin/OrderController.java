package site.binghai.SuperBigDumpling.controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import site.binghai.SuperBigDumpling.controllers.MultiController;
import site.binghai.SuperBigDumpling.entity.people.Order;
import site.binghai.SuperBigDumpling.entity.people.OrderAddress;
import site.binghai.SuperBigDumpling.entity.things.TradeItem;
import site.binghai.SuperBigDumpling.enums.OrderStatusEnum;
import site.binghai.SuperBigDumpling.utils.OrderNoUtils;
import site.binghai.SuperBigDumpling.utils.UserUtils;
import site.binghai.SuperDumpling.common.definations.ApiRequestMapping;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by binghai on 2017/10/16.
 *
 * @ MoGuJie
 */
@Controller
@RequestMapping("admin/order")
public class OrderController extends MultiController {
    @ApiRequestMapping("create-orders")
    public Object createOrder(Map params) {
        TradeItem tradeItem = simpleDataService.findById(getInt(params, "gid"), TradeItem.class);
        OrderAddress orderAddress = simpleDataService.save(OrderAddress.ofJson(getString(params, "address")));

        Order order = Order.builder()
                .goodsNum(getInt(params, "goodsNum"))
                .totalPrice(getMoneyY2F(params, "totalPrice"))
                .properties(getString(params, "goodsProp"))
                .groupOrder(getInt(params, "isGroup") == 1)
                .address(orderAddress)
                .tradeItem(tradeItem)
                .img(tradeItem.getImgUrl())
                .name(tradeItem.getName())
                .build();
        if (!order.isGroupOrder()) {
            order.groupSuccess();
        }
        UserUtils.userInit(order, getUserByWxCode(params));
        order.setOrderStatus(OrderStatusEnum.WAITING_PAY.getStatus());
        order = simpleDataService.save(order);

        OrderNoUtils.makeOrderNo(order);

        return order.getOrderNum();
    }

    @ApiRequestMapping("orders-list")
    public Object orderList(Map params) {
        return null;
    }
}
