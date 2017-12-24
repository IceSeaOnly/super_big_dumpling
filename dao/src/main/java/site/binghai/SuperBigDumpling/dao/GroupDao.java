package site.binghai.SuperBigDumpling.dao;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import site.binghai.SuperBigDumpling.api.enums.GroupStatusEnum;
import site.binghai.SuperBigDumpling.common.entity.things.Group;
import site.binghai.SuperBigDumpling.common.entity.things.TradeItem;

import java.util.List;

/**
 * Created by binghai on 2017/10/16.
 *
 * @ MoGuJie
 */
public interface GroupDao extends JpaRepository<Group, Integer> {
    List<Group> findByTradeItemIdAndStatusAndAvailable(Integer tradeItemId, GroupStatusEnum groupStatusEnum, boolean available, Pageable page);

    List<Group> findByStatusAndAvailable(GroupStatusEnum groupStatusEnum, boolean available, Pageable page);
}
