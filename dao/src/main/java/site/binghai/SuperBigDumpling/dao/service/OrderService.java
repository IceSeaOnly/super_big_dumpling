package site.binghai.SuperBigDumpling.dao.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import site.binghai.SuperBigDumpling.api.enums.GroupStatusEnum;
import site.binghai.SuperBigDumpling.common.entity.things.Group;
import site.binghai.SuperBigDumpling.dao.OrderDao;
import site.binghai.SuperBigDumpling.common.entity.people.Order;
import site.binghai.SuperBigDumpling.common.entity.people.User;
import site.binghai.SuperBigDumpling.api.enums.OrderStatusEnum;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IceSea on 2017/10/29.
 * GitHub: https://github.com/IceSeaOnly
 */
@Service
public class OrderService extends BaseService<Order> {
    private final Logger logger = LoggerFactory.getLogger(OrderService.class);

    @Autowired
    private OrderDao orderDao;

    public List<Order> findAllByUser(User user, int page) {
        return orderDao.findByUserIdAndAvailableOrderByCreatedDesc(user.getId(), true, new PageRequest(page, 10));
    }

    public List<Order> findAllByUserAndStatus(User user, OrderStatusEnum statusEnum, int page) {
        return orderDao.findByUserIdAndStatusAndAvailableOrderByCreatedDesc(user.getId(), statusEnum, true, new PageRequest(page, 10));
    }

    public Order getByOutTradeNo(String outTradeNo) {
        List<Order> rs = orderDao.findByOrderNum(outTradeNo);
        if (rs == null || rs.size() != 1) {
            logger.error("根据商户订单号查询异常!rs={}", outTradeNo);
            return null;
        }
        return rs.get(0);
    }

    @Override
    JpaRepository<Order, Integer> getDao() {
        return orderDao;
    }

    public Order findByUserAndGroup(User user, Group group) {
        List<Order> orders = orderDao.findByUserIdAndGroupId(user.getId(), group.getId());
        return CollectionUtils.isEmpty(orders) ? null : orders.get(0);
    }

    public List<Order> findAllGroupOrdersByUser(User user, int page) {
        return orderDao.findByUserIdAndGroupOrderAndAvailableOrderByCreatedDesc(user.getId(), true, true, new PageRequest(page, 10));
    }

    /**
     * 查询所有未成团订单
     */
    public List<Order> findAllGroupNotBuildOrders() {
        List<Order> orders = new ArrayList<>();
        int page = 0;
        int size = 0;
        do {
            size = orders.size();
            orders.addAll(orderDao.findAllByStatusAndAvailable(OrderStatusEnum.WAITING_GROUP_BUILED, true, new PageRequest(page++, 100)));
        } while (orders.size() > size);
        return orders;
    }

    /**
     * 把该团的订单置为拼团失败
     */
    @Transactional
    public void groupFailed(Group group) {
        List<Order> orders = orderDao.findByGroupId(group.getId());
        orders.forEach(v -> {
            v.setStatus(OrderStatusEnum.GROUP_FAILED);
            update(v);
        });
    }

    /**
     * 根据用户id和订单状态计数
     */
    public long countByUserIdAndStatus(int userId, OrderStatusEnum status) {
        return orderDao.countByUserIdAndStatus(userId, status);
    }
}
