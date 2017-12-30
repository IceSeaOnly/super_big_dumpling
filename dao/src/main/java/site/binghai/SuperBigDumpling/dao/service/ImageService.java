package site.binghai.SuperBigDumpling.dao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import site.binghai.SuperBigDumpling.common.entity.things.Image;
import site.binghai.SuperBigDumpling.dao.ImageDao;

/**
 * Created by binghai on 2017/12/30.
 *
 * @ super_big_dumpling
 */
@Service
public class ImageService extends BaseService<Image> {
    @Autowired
    private ImageDao dao;

    @Override
    JpaRepository<Image, Integer> getDao() {
        return dao;
    }
}
