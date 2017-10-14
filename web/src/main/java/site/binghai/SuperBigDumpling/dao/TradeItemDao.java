package site.binghai.SuperBigDumpling.dao;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import site.binghai.SuperBigDumpling.entity.things.Category;
import site.binghai.SuperBigDumpling.entity.things.TradeItem;

import java.util.List;

/**
 * Created by IceSea on 2017/10/1.
 * GitHub: https://github.com/IceSeaOnly
 */
public interface TradeItemDao extends JpaRepository<TradeItem,Integer>{
    List<TradeItem> findByFatherCategoryAndChildCategory(Category fa, Category ch, Pageable page);
}
