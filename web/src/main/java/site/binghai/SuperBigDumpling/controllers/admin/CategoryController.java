package site.binghai.SuperBigDumpling.controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import site.binghai.SuperBigDumpling.entity.things.Category;
import site.binghai.SuperBigDumpling.service.SimpleDataService;

/**
 * Created by IceSea on 2017/9/17.
 * 类目管理层
 * GitHub: https://github.com/IceSeaOnly
 */
@Controller
@RequestMapping("admin")
public class CategoryController {
    @Autowired
    SimpleDataService service;

    @RequestMapping("category")
    public String category(Model model){
        model.addAttribute("cts",service.findAll(Category.class));
        return "category";
    }
}
