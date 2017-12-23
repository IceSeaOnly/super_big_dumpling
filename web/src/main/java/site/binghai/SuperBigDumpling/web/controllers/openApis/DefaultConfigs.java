package site.binghai.SuperBigDumpling.web.controllers.openApis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.binghai.SuperBigDumpling.dao.service.DefaultConfigService;

/**
 * Created by binghai on 2017/12/23.
 *
 * @ super_big_dumpling
 */
@RestController
@RequestMapping("openApi")
public class DefaultConfigs {

    @Autowired
    private DefaultConfigService defaultConfigService;

    @RequestMapping("defaultConfigs")
    public Object defaultConfigs(){
        return defaultConfigService.getDefaultConfigs();
    }
}
