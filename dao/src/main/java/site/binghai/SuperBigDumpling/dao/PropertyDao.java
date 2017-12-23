package site.binghai.SuperBigDumpling.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import site.binghai.SuperBigDumpling.common.entity.things.Property;

/**
 * Created by binghai on 2017/12/23.
 *
 * @ super_big_dumpling
 */
public interface PropertyDao extends JpaRepository<Property, Integer> {
}
