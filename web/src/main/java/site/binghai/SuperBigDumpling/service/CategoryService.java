package site.binghai.SuperBigDumpling.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.binghai.SuperBigDumpling.dao.CategoryDao;
import site.binghai.SuperBigDumpling.entity.things.Category;

import java.util.Collections;
import java.util.List;

/**
 * Created by IceSea on 2017/9/23.
 * GitHub: https://github.com/IceSeaOnly
 */
@Service
public class CategoryService {
    @Autowired
    private CategoryDao dao;

    public List<Category> findByFatherCategory(Category category){
        List<Category> rs = dao.findByFatherCategory(category);
        return rs == null ? Collections.EMPTY_LIST : rs;
    }
}
