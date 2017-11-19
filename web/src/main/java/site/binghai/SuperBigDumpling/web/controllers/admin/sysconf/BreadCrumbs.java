package site.binghai.SuperBigDumpling.web.controllers.admin.sysconf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import site.binghai.SuperBigDumpling.common.entity.people.Administrator;
import site.binghai.SuperBigDumpling.common.entity.things.BreadCrumb;
import site.binghai.SuperBigDumpling.web.service.SimpleDataService;
import site.binghai.SuperBigDumpling.common.utils.BeansUtils;
import site.binghai.SuperBigDumpling.common.utils.UserUtils;

import javax.servlet.http.HttpSession;

/**
 * Created by IceSea on 2017/9/18.
 * GitHub: https://github.com/IceSeaOnly
 */
@RequestMapping("/admin/sysconfig/")
@Controller
public class BreadCrumbs{
    @Autowired
    SimpleDataService service;

    @RequestMapping("breadcrumbs")
    public String breadcrumbs(Model model){
        model.addAttribute("records",service.findAll(BreadCrumb.class));
        return "sysconf_breadcrumbs";
    }

    @RequestMapping("addBreadcrumbs")
    public String addBreadcrumbs(BreadCrumb breadCrumb, HttpSession session){
        Administrator admin = UserUtils.getAdministrator(session);
        BeansUtils.initThings(breadCrumb,admin);
        service.save(breadCrumb);
        return "redirect:breadcrumbs";
    }

    @RequestMapping("delBreadcrumbs")
    public String delBreadcrumbs(@RequestParam int id){
        service.deleteById(id,BreadCrumb.class);
        return "redirect:breadcrumbs";
    }
}
