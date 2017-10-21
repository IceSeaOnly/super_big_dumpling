package site.binghai.SuperBigDumpling.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import site.binghai.SuperBigDumpling.entity.things.Album;

import java.util.List;

/**
 * Created by IceSea on 2017/10/21.
 * GitHub: https://github.com/IceSeaOnly
 */
public interface AlbumDao extends JpaRepository<Album,Integer>{

}
