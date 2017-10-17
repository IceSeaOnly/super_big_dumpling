package site.binghai.SuperBigDumpling.controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import site.binghai.SuperBigDumpling.controllers.MultiController;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by binghai on 2017/10/16.
 *
 * @ MoGuJie
 */
@Controller
@RequestMapping("admin/order")
public class OrderController extends MultiController {
    @Override
    public Object handleRequest(Map params) {
        return null;
    }

    @Override
    public List<String> getActHeader() {
        return Arrays.asList("orders-list");
    }
}
