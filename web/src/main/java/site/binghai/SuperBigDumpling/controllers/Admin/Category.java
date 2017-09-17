package site.binghai.SuperBigDumpling.controllers.Admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by IceSea on 2017/9/17.
 * 类目管理层
 * GitHub: https://github.com/IceSeaOnly
 */
@Controller
@RequestMapping("admin")
public class Category {
    @RequestMapping("category")
    public String category(){
        return "category";
    }
}
