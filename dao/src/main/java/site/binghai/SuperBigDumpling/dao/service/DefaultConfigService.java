package site.binghai.SuperBigDumpling.dao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.binghai.SuperBigDumpling.common.entity.things.Configs;
import site.binghai.SuperBigDumpling.dao.DefaultConfigDao;

/**
 * Created by binghai on 2017/12/23.
 *
 * @ super_big_dumpling
 */
@Service
public class DefaultConfigService {
    @Autowired
    private DefaultConfigDao defaultConfigDao;

    public Configs getDefaultConfigs(){
        return defaultConfigDao.findOne(0);
    }
}
