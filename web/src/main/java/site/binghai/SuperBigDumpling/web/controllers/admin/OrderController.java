package site.binghai.SuperBigDumpling.web.controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import site.binghai.SuperBigDumpling.web.controllers.MultiController;
import site.binghai.SuperBigDumpling.common.entity.people.Order;
import site.binghai.SuperBigDumpling.common.entity.people.OrderAddress;
import site.binghai.SuperBigDumpling.common.entity.people.User;
import site.binghai.SuperBigDumpling.common.entity.things.TradeItem;
import site.binghai.SuperBigDumpling.api.enums.OrderStatusEnum;
import site.binghai.SuperBigDumpling.common.facades.OrderFacade;
import site.binghai.SuperBigDumpling.web.service.TradeItemService;
import site.binghai.SuperBigDumpling.common.utils.OrderUtils;
import site.binghai.SuperBigDumpling.common.utils.UserUtils;
import site.binghai.SuperBigDumpling.web.service.OrderService;
import site.binghai.SuperBigDumpling.common.utils.TimeFormatter;
import site.binghai.SuperBigDumpling.common.definations.ApiRequestMapping;
import site.binghai.SuperBigDumpling.common.system.ErrorList;

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
    @Autowired
    private OrderService orderService;
    @Autowired
    private TradeItemService tradeItemService;
    private OrderFacade facade = new OrderFacade();

    @ApiRequestMapping("create-orders")
    public Object createOrder(Map params) throws Exception {
        TradeItem tradeItem = simpleDataService.findById(getInt(params, "gid"), TradeItem.class);
        tradeItem = tradeItemService.getOneStock(tradeItem);
        if(tradeItem == null){
            return error(ErrorList.EMPTY_STOCK,null,null);
        }
        User user = getUserByWxCode(params);

        OrderAddress orderAddress = simpleDataService.save(OrderAddress.ofJson(getString(params, "address")));
        UserUtils.userInit(orderAddress, user);

        Order order = Order.builder()
                .goodsNum(getInt(params, "goodsNum"))
                .price(tradeItem.getPrice())
                .totalPrice(getMoneyY2F(params, "totalPrice"))
                .properties(getString(params, "goodsProp"))
                .groupOrder(getInt(params, "isGroup") == 1)
                .address(orderAddress)
                .tradeItem(tradeItem)
                .img(tradeItem.getImgUrl())
                .name(tradeItem.getName())
                .createTime(TimeFormatter.now())
                .openId(user.getOpenId())
                .build();
        if (!order.isGroupOrder()) { // 非拼团订单立即成团
            order.groupSuccess();
        }

        UserUtils.userInit(order, user);
        OrderUtils.orderStatusUpdate(order, OrderStatusEnum.WAITING_PAY);
        OrderUtils.makeOrderNo(order);
        order = simpleDataService.save(order);
        return order.getOrderNum();
    }

    @ApiRequestMapping("orders-list")
    public Object orderList(Map params) throws Exception {
        int sid = getInt(params, "sid");
        int page = getPage(params);
        User user = getUserByWxCode(params);
        List<Order> rs;
        switch (sid) {
            case 0:
                rs = orderService.findAllByUser(user, page);
                break;
            case 1:
                rs = orderService.findAllByUserAndStatus(user, OrderStatusEnum.WAITING_PAY, page);
                break;
            case 2:
                rs = orderService.findAllByUserAndStatus(user, OrderStatusEnum.WAITING_GROUP_BUILED, page);
                break;
            case 3:
                rs = orderService.findAllByUserAndStatus(user, OrderStatusEnum.GROUP_BUILED, page);
                break;
            case 4:
                rs = orderService.findAllByUserAndStatus(user, OrderStatusEnum.DISTRIBUTOIN, page);
                break;
            case 5:
                rs = orderService.findAllByUserAndStatus(user, OrderStatusEnum.COMPLETED, page);
                break;
            default:
                rs = orderService.findAllByUser(user, page);
        }

        return facade.asList(rs);
    }

    @ApiRequestMapping("orders-detail")
    public Object orderDetail(Map params) throws Exception {
        int orderId = getInt(params,"id");
        Order order = simpleDataService.findById(orderId,Order.class);
        User user = getUserByWxCode(params);
        if(order == null || order.getUserId() != user.getId()){
            return illegalRequest(params);
        }

        return facade.asObj(order);
    }
}
