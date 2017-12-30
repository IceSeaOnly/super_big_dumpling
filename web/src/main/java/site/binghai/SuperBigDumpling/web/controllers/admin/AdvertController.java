package site.binghai.SuperBigDumpling.web.controllers.admin;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import site.binghai.SuperBigDumpling.common.definations.ApiRequestMapping;
import site.binghai.SuperBigDumpling.common.entity.things.Banners;
import site.binghai.SuperBigDumpling.common.entity.things.Category;
import site.binghai.SuperBigDumpling.common.entity.things.Image;
import site.binghai.SuperBigDumpling.common.system.ErrorList;
import site.binghai.SuperBigDumpling.dao.service.BannerService;
import site.binghai.SuperBigDumpling.dao.service.CategoryService;
import site.binghai.SuperBigDumpling.dao.service.ImageService;
import site.binghai.SuperBigDumpling.web.controllers.MultiController;

import java.util.List;
import java.util.Map;

/**
 * Created by binghai on 2017/12/24.
 *
 * @ super_big_dumpling
 */
@RestController
@RequestMapping("admin/banner/")
public class AdvertController extends MultiController {
    @Autowired
    private BannerService bannerService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ImageService imageService;

    @ApiRequestMapping("advert")
    public Object advert(Map params) {
        return bannerService.findTop3();
    }

    @RequestMapping("list")
    public Object list() {
        List<Banners> bannersList = bannerService.findAll(100);
        return CollectionUtils.isNotEmpty(bannersList) ? success("", bannersList) : error(ErrorList.EMPTY_RESULT, "查询结果为空", null);
    }

    @RequestMapping("add")
    public Object add(@RequestParam Integer category, @RequestParam String imgUrl) {
        Category ct = categoryService.findById(category);
        if (ct == null) {
            return error(ErrorList.ILLEGAL_REQUEST, "类目信息错误", null);
        }
        Banners banners = new Banners();
        banners.setCategory(ct);
        banners.setImage(imageService.save(new Image(imgUrl)));

        banners = bannerService.save(banners);
        return success("ok", banners);
    }
}
