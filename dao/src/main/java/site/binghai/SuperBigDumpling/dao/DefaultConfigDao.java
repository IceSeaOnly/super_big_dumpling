package site.binghai.SuperBigDumpling.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import site.binghai.SuperBigDumpling.common.entity.things.Configs;

/**
 * Created by binghai on 2017/12/23.
 *
 * @ super_big_dumpling
 */
public interface DefaultConfigDao extends JpaRepository<Configs,Integer> {
}
