package site.binghai.SuperBigDumpling.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import site.binghai.SuperBigDumpling.common.entity.things.Album;

/**
 * Created by IceSea on 2017/10/21.
 * GitHub: https://github.com/IceSeaOnly
 */
public interface AlbumDao extends JpaRepository<Album,Integer>{

}
