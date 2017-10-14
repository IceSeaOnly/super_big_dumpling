package site.binghai.SuperBigDumpling.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
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
    TradeItemDao dao;

    /**
     * 分页查询，每页20个结果
     * */
    public List<TradeItem> findByCategory(Category fa,Category ch,int startPage){
        return dao.findByFatherCategoryAndChildCategory(fa,ch,new PageRequest(startPage,20));
    }
}
