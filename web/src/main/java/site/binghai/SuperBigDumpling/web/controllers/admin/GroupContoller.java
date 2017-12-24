package site.binghai.SuperBigDumpling.web.controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.binghai.SuperBigDumpling.api.enums.OrderStatusEnum;
import site.binghai.SuperBigDumpling.common.definations.ApiRequestMapping;
import site.binghai.SuperBigDumpling.common.entity.people.Order;
import site.binghai.SuperBigDumpling.common.entity.people.User;
import site.binghai.SuperBigDumpling.common.entity.things.Group;
import site.binghai.SuperBigDumpling.common.facades.GroupFacade;
import site.binghai.SuperBigDumpling.common.facades.OrderFacade;
import site.binghai.SuperBigDumpling.web.controllers.MultiController;
import site.binghai.SuperBigDumpling.dao.service.GroupService;
import site.binghai.SuperBigDumpling.dao.service.OrderService;

import java.util.List;
import java.util.Map;

/**
 * Created by binghai on 2017/12/2.
 *
 * @ super_big_dumpling
 */
@RestController
@RequestMapping("admin/group")
public class GroupContoller extends MultiController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private GroupService groupService;
    @Autowired
    private OrderController orderController;

    private OrderFacade orderFacade = new OrderFacade();


    @ApiRequestMapping("group-detail")
    public Object groupDetail(Map params) throws Exception {
        String oid = getString(params, "oid");
        Order order = orderService.getByOutTradeNo(oid);
        if (order == null) {
            return illegalRequest(params);
        }
        User thisUser = getUserByWxCode(params);
        Group group = groupService.findById(order.getGroupId());

        Order thisUserOrderOfThisGroup = orderService.findByUserAndGroup(thisUser, group);
        List<User> groupMembers = userService.findByIds(group.getGroupMemberIds());
        return new GroupFacade(group, thisUserOrderOfThisGroup, groupMembers, order.getTradeItem());
    }

    @ApiRequestMapping("group-list")
    public Object groupList(Map params) throws Exception {
        int cid = getInt(params, "cid");
        int page = getPage(params);
        User user = getUserByWxCode(params);
        List<Order> rs;
        switch (cid) {
            case 0:
                rs = orderService.findAllGroupOrdersByUser(user, page);
                break;
            case 1:
                rs = orderService.findAllByUserAndStatus(user, OrderStatusEnum.WAITING_GROUP_BUILED, page);
                break;
            case 2:
                rs = orderService.findAllByUserAndStatus(user, OrderStatusEnum.GROUP_BUILED, page);
                break;
            case 3:
                rs = orderService.findAllByUserAndStatus(user, OrderStatusEnum.GROUP_FAILED, page);
                break;
            default:
                rs = orderService.findAllGroupOrdersByUser(user, page);
        }

        return orderFacade.asList(rs);
    }
}
