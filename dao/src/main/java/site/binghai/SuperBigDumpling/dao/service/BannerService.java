package site.binghai.SuperBigDumpling.dao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import site.binghai.SuperBigDumpling.common.entity.things.Banners;
import site.binghai.SuperBigDumpling.common.facades.BannerFacade;
import site.binghai.SuperBigDumpling.dao.BannerDao;

import java.util.List;

/**
 * Created by binghai on 2017/12/24.
 *
 * @ super_big_dumpling
 */
@Service
public class BannerService extends BaseService<Banners> {
    @Autowired
    private BannerDao bannerDao;

    @Override
    JpaRepository<Banners, Integer> getDao() {
        return bannerDao;
    }

    public List<BannerFacade> findTop3() {
        List<Banners> result = bannerDao.findByAvailableOrderByPointsDesc(true, new PageRequest(0, 3));
        if(!CollectionUtils.isEmpty(result)){
            return new BannerFacade().asList(result);
        }
        return null;
    }
}
