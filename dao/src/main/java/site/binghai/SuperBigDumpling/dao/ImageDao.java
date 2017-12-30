package site.binghai.SuperBigDumpling.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import site.binghai.SuperBigDumpling.common.entity.things.Image;

/**
 * Created by binghai on 2017/12/30.
 *
 * @ super_big_dumpling
 */
public interface ImageDao extends JpaRepository<Image,Integer> {
}
