package site.binghai.SuperBigDumpling.web.controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.binghai.SuperBigDumpling.common.definations.ApiRequestMapping;
import site.binghai.SuperBigDumpling.dao.service.BannerService;
import site.binghai.SuperBigDumpling.web.controllers.MultiController;

import java.util.Map;

/**
 * Created by binghai on 2017/12/24.
 *
 * @ super_big_dumpling
 */
@RestController
@RequestMapping("")
public class AdvertController extends MultiController {
    @Autowired
    private BannerService bannerService;

    @ApiRequestMapping("advert")
    public Object advert(Map params){
        return bannerService.findTop3();
    }
}
