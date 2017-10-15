package site.binghai.SuperBigDumpling.controllers.admin;

import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import site.binghai.SuperBigDumpling.controllers.wx.ApiDomain;
import site.binghai.SuperBigDumpling.entity.things.Category;
import site.binghai.SuperBigDumpling.facades.CategoryFacade;
import site.binghai.SuperBigDumpling.service.CategoryService;
import site.binghai.SuperBigDumpling.service.SimpleDataService;
import site.binghai.SuperBigDumpling.utils.BeansUtils;
import site.binghai.SuperBigDumpling.utils.UserUtils;
import site.binghai.SuperDumpling.common.definations.WxHandler;
import site.binghai.SuperDumpling.common.system.ErrorList;
import site.binghai.SuperDumpling.common.system.JSONResponse;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * Created by IceSea on 2017/9/17.
 * 类目管理层
 * GitHub: https://github.com/IceSeaOnly
 */
@Controller
@RequestMapping("admin/category")
public class CategoryController implements WxHandler, InitializingBean {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private SimpleDataService service;
    private CategoryFacade categoryFacade = new CategoryFacade();

    @RequestMapping("/")
    public String category(Integer childCategory, Model model) {
        if (childCategory == null) { //查看父类目
            List<Category> rs = service.findAll(Category.class);
            model.addAttribute("categorys", rs.stream().filter(v -> v.isSuperCategory()).toArray());
            return "category";
        }
        // 查看子类目
        Category category = service.findById(childCategory, Category.class);
        if (category == null) {
            return "error";
        }
        model.addAttribute("currentCategory", category);
        model.addAttribute("categorys", categoryService.findByFatherCategory(category));
        return "category";
    }

    @RequestMapping("addCategory")
    public String addCategory(Category category, HttpSession session) {
        BeansUtils.initThings(category, UserUtils.getAdministrator(session));
        service.save(category);
        if (category.isSuperCategory()) {
            return "redirect:";
        }
        return "redirect:/admin/category/?childCategory=" + category.getFatherCategory().getId();
    }

    @RequestMapping("deleteCategory")
    @ResponseBody
    public Object deleteCategory(@RequestParam int categoryId) {
        Category category = service.findById(categoryId, Category.class);
        if (null == category) {
            return JSONResponse.errorResp(ErrorList.INVALID_PARAMETER, "", null);
        }

        if (category.isSuperCategory() && categoryService.findByFatherCategory(category).size() > 0) {
            return JSONResponse.errorResp(ErrorList.NON_EMPTY_SUPER_CATEGORY, "", null);
        }

        service.deleteById(categoryId, Category.class);

        return JSONResponse.successResp("删除成功", null);
    }

    @ResponseBody
    @RequestMapping("list")
    public Object list(Integer sc) { // sc为父级类目id
        if (sc == null) {
            return categoryService.findByFatherCategory(null);
        } else {
            return categoryService.findByFatherCategory(service.findById(sc, Category.class));
        }
    }

    @Override
    public Object handleRequest(Map params) {
        int cid = MapUtils.getInteger(params, "cid");
        Category root = null;
        if (cid > 0) {
            root = service.findById(cid, Category.class);
        }
        return categoryFacade.asList(categoryService.findByFatherCategory(root));
    }

    @Override
    public String getActHeader() {
        return "category";
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        ApiDomain.registerHandler(this);
    }
}
