package site.binghai.SuperBigDumpling.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import site.binghai.SuperBigDumpling.common.entity.things.Category;

import java.util.List;

/**
 * Created by IceSea on 2017/9/23.
 * for RestData
 * GitHub: https://github.com/IceSeaOnly
 */
public interface CategoryDao extends JpaRepository<Category,Integer> {
    List<Category> findByFatherCategory(Category category);
}
