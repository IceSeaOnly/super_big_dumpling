package site.binghai.SuperBigDumpling.controllers.openApis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.binghai.SuperBigDumpling.entity.things.BreadCrumb;
import site.binghai.SuperBigDumpling.service.SimpleDataService;

import java.util.List;

/**
 * Created by binghai on 2017/9/19.
 *
 * @ MoGuJie
 */
@RequestMapping("openApi")
@RestController
public class GoldBreadcrumb {
    @Autowired
    SimpleDataService service;

    @RequestMapping("goldBreadcrumb")
    public String goldBreadcrumb(){
        List<BreadCrumb> ls = service.findAll(BreadCrumb.class);
        if(ls.size() > 0){
            int mod = Long.valueOf(System.currentTimeMillis()/1000).intValue() % ls.size();
            return ls.get(mod).getContent();
        }else{
            return "系统没有设置任何面包屑金句";
        }
    }
}
