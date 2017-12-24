package site.binghai.SuperBigDumpling.dao;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

    List<Order> findByUserIdAndGroupOrderAndAvailableOrderByCreatedDesc(int userId, boolean groupOrder, boolean available, Pageable page);

    List<Order> findByOrderNum(String outTradeNo);

    List<Order> findByUserIdAndGroupId(int userId, int groupId);

    List<Order> findAllByStatusAndAvailable(OrderStatusEnum status, boolean available, Pageable page);

    List<Order> findByGroupId(int groupId);

    @Query(countQuery = "select count(1) from orders where user_id = :userId and status = :index",nativeQuery = true)
    long countByUserIdAndStatus(@Param("userId")int userId,@Param("index") OrderStatusEnum index);
}
