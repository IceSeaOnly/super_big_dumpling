package site.binghai.SuperBigDumpling.web.controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by binghai on 2017/9/15.
 *
 * @ MoGuJie
 */
@Controller
@RequestMapping("/admin")
public class AdminLogin {
    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/")
    public String index(){
        return "index";
    }
}
