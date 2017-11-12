package site.binghai.SuperBigDumpling.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import services.StockServiceApi;
import site.binghai.SuperBigDumpling.dao.TradeItemDao;
import site.binghai.SuperBigDumpling.entity.things.Category;
import site.binghai.SuperBigDumpling.entity.things.TradeItem;

import java.util.List;

/**
 * Created by IceSea on 2017/10/14.
 * GitHub: https://github.com/IceSeaOnly
 */
@Service
public class TradeItemService {
    @Autowired
    private TradeItemDao dao;
    @Autowired
    private SimpleDataService simpleDataService;
    @Autowired
    private StockServiceApi stockServiceApi;

    /**
     * 分页查询
     * 如果传入的是超级类目，则发挥本超级类目下的推荐商品
     * 否则按页返回具体子类下的商品
     * */
    public List<TradeItem> findByCategory(Category category,int startPage){
        if(!category.isSuperCategory()){
            return dao.findByFatherCategoryAndChildCategory(category.getFatherCategory(),category,new PageRequest(startPage,20));
        }
        return dao.findByFatherCategory(category,new PageRequest(startPage,10));
    }

    /**
     * 获取推荐商品
     * type = 0 : 父类推荐
     * type = 1 : 首页推荐
     * */
    public List<TradeItem> findRecommand(int cid, int page, int type) {
        if(type == 0){
            Category category = simpleDataService.findById(cid,Category.class);
            return dao.findByFatherCategoryAndRecommend(category,true,new PageRequest(page,10));
        }
        return dao.findByIndexRecommend(true,new PageRequest(page,10));
    }

    /**
     * 减库存 todo 分布式应用从这里修改减库存逻辑
     * */
    public TradeItem getOneStock(TradeItem tradeItem) {
        int effect = stockServiceApi.getOneStock(tradeItem.getId());
        return effect > 0 ? tradeItem : null;
    }
}
