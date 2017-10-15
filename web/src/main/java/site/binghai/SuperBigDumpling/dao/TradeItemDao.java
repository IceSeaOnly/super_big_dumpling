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
    List<TradeItem> findByFatherCategory(Category fa, Pageable page);
    List<TradeItem> findByFatherCategoryAndChildCategory(Category fa, Category ch, Pageable page);
    /**
     * 根据父类获取被推荐的商品
     * */
    List<TradeItem> findByFatherCategoryAndRecommend(Category category,boolean recommand,Pageable page);
    List<TradeItem> findByIndexRecommend(boolean recommand,Pageable page);
}
