package site.binghai.SuperBigDumpling.dao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import site.binghai.SuperBigDumpling.common.entity.things.Album;
import site.binghai.SuperBigDumpling.dao.AlbumDao;

/**
 * Created by binghai on 2017/12/23.
 *
 * @ super_big_dumpling
 */
@Service
public class AlbumService extends BaseService<Album> {
    @Autowired
    private AlbumDao dao;

    @Override
    JpaRepository<Album, Integer> getDao() {
        return dao;
    }
}
