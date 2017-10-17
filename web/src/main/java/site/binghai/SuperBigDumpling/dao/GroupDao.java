package site.binghai.SuperBigDumpling.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import site.binghai.SuperBigDumpling.entity.things.Group;
import site.binghai.SuperBigDumpling.entity.things.TradeItem;

import java.awt.print.Pageable;
import java.util.List;

/**
 * Created by binghai on 2017/10/16.
 *
 * @ MoGuJie
 */
public interface GroupDao extends JpaRepository<Group,Integer> {
    List<Group> findByTradeItem(TradeItem tradeItem, Pageable page);
}
