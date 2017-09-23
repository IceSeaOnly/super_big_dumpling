package site.binghai.SuperBigDumpling.controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import site.binghai.SuperBigDumpling.entity.things.Category;
import site.binghai.SuperBigDumpling.service.CategoryService;
import site.binghai.SuperBigDumpling.service.SimpleDataService;
import site.binghai.SuperBigDumpling.utils.BeansUtils;
import site.binghai.SuperBigDumpling.utils.UserUtils;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by IceSea on 2017/9/17.
 * 类目管理层
 * GitHub: https://github.com/IceSeaOnly
 */
@Controller
@RequestMapping("admin")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private SimpleDataService service;

    @RequestMapping("category")
    public String category(Integer childCategory, Model model) {
        if (childCategory == null) { //查看父类目
            List<Category> rs = service.findAll(Category.class);
            model.addAttribute("categorys", rs.stream().filter(v -> v.isSuperCategory()).toArray());
            return "category";
        }
        // 查看子类目
        Category category = service.findById(childCategory, Category.class);
        if (category == null) {
            model.addAttribute("currentCategory", "参数错误");
            return "category";
        }
        model.addAttribute("currentCategory", category);
        model.addAttribute("categorys", categoryService.findByFatherCategory(category));
        return "category";
    }

    @RequestMapping("getCategory")
    public Object getCategory(@RequestParam int categoryId) {
        return service.findById(categoryId, Category.class);
    }

    @RequestMapping("addCategory")
    public String addCategory(Category category, HttpSession session) {
        BeansUtils.initThings(category, UserUtils.getAdministrator(session));
        service.save(category);
        if(category.isSuperCategory()){
            return "redirect:category";
        }
        return "redirect:category?childCategory=" + category.getFatherCategory().getId();
    }
}
