package site.binghai.SuperBigDumpling.dao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import site.binghai.SuperBigDumpling.common.entity.things.Property;
import site.binghai.SuperBigDumpling.dao.PropertyDao;

/**
 * Created by binghai on 2017/12/23.
 *
 * @ super_big_dumpling
 */
@Service
public class PropertyService extends BaseService<Property> {

    @Autowired
    private PropertyDao dao;

    @Override
    JpaRepository<Property, Integer> getDao() {
        return dao;
    }
}
