package site.binghai.SuperBigDumpling.dao;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import site.binghai.SuperBigDumpling.common.entity.things.Category;
import site.binghai.SuperBigDumpling.common.entity.things.TradeItem;

import java.util.List;

/**
 * Created by IceSea on 2017/10/1.
 * GitHub: https://github.com/IceSeaOnly
 */
public interface TradeItemDao extends JpaRepository<TradeItem, Integer> {
    List<TradeItem> findByFatherCategory(Category fa, Pageable page);

    List<TradeItem> findByFatherCategoryAndChildCategory(Category fa, Category ch, Pageable page);

    /**
     * 根据父类获取被推荐的商品
     */
    List<TradeItem> findByFatherCategoryAndRecommend(Category category, boolean recommand, Pageable page);

    List<TradeItem> findByIndexRecommend(boolean recommand, Pageable page);

    @Query(value = "update trade_item set stock = stock-1,sale_num = sale_num+1 where id= :id and stock>0", nativeQuery = true)
    @Transactional
    @Modifying(clearAutomatically = true)
    int consumeOneStock(@Param("id") int id);
}
