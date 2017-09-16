package site.binghai.SuperBigDumpling.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by binghai on 2017/9/16.
 *
 * @ MoGuJie
 */
@Controller
public class TplController {
    @RequestMapping("tpl")
    public String tpl(){
        return "tpl";
    }
}
