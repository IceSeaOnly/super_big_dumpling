package site.binghai.SuperBigDumpling.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import site.binghai.SuperBigDumpling.web.dao.CategoryDao;
import site.binghai.SuperBigDumpling.common.entity.things.Category;
import site.binghai.SuperBigDumpling.common.utils.BeansUtils;

import java.util.Collections;
import java.util.List;

/**
 * Created by IceSea on 2017/9/23.
 * GitHub: https://github.com/IceSeaOnly
 */
@Service
public class CategoryService extends BaseService<Category> {
    @Autowired
    private CategoryDao dao;

    public List<Category> findByFatherCategory(Category category) {
        List<Category> rs = dao.findByFatherCategory(category);
        return rs == null ?
                Collections.EMPTY_LIST :
                BeansUtils.deleteAableFileter(rs, false, true);
    }

    @Override
    JpaRepository<Category, Integer> getDao() {
        return dao;
    }
}
