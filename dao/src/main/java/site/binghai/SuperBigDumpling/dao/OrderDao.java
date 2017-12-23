package site.binghai.SuperBigDumpling.dao;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import site.binghai.SuperBigDumpling.api.enums.OrderStatusEnum;
import site.binghai.SuperBigDumpling.common.entity.people.Order;

import java.util.List;

/**
 * Created by IceSea on 2017/10/29.
 * GitHub: https://github.com/IceSeaOnly
 */
public interface OrderDao extends JpaRepository<Order, Integer> {
    List<Order> findByUserIdAndStatusAndAvailableOrderByCreatedDesc(int userId, OrderStatusEnum status, boolean available, Pageable page);

    List<Order> findByUserIdAndAvailableOrderByCreatedDesc(int userId, boolean available, Pageable page);

    List<Order> findByOrderNum(String outTradeNo);
}
