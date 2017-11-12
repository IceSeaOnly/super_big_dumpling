package site.binghai.SuperBigDumpling.service;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import site.binghai.SuperBigDumpling.dao.OrderDao;
import site.binghai.SuperBigDumpling.entity.people.Order;
import site.binghai.SuperBigDumpling.entity.people.User;
import site.binghai.SuperBigDumpling.enums.OrderStatusEnum;

import java.util.List;

/**
 * Created by IceSea on 2017/10/29.
 * GitHub: https://github.com/IceSeaOnly
 */
@Service
public class OrderService {
    private final Logger logger = LoggerFactory.getLogger(OrderService.class);

    @Autowired
    private OrderDao orderDao;

    public List<Order> findAllByUser(User user, int page) {
        return orderDao.findByUserIdAndAvailableOrderByCreatedDesc(user.getId(), true, new PageRequest(page, 10));
    }

    public List<Order> findAllByUserAndStatus(User user, OrderStatusEnum statusEnum, int page) {
        return orderDao.findByUserIdAndStatusAndAvailableOrderByCreatedDesc(user.getId(), statusEnum.getIndex(), true, new PageRequest(page, 10));
    }

    public Order getByOutTradeNo(String outTradeNo) {
        List<Order> rs = orderDao.findByOrderNum(outTradeNo);
        if (rs == null || rs.size() > 1) {
            logger.error("根据商户订单号查询异常!rs={}", JSONObject.toJSONString(rs));
            return null;
        }
        return rs.get(0);
    }
}
