package site.binghai.SuperBigDumpling.dao;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import site.binghai.SuperBigDumpling.common.entity.things.Banners;

import java.util.List;

/**
 * Created by binghai on 2017/12/24.
 *
 * @ super_big_dumpling
 */
public interface BannerDao extends JpaRepository<Banners, Integer> {
    List<Banners> findByAvailableOrderByPointsDesc(boolean available, Pageable page);
}
