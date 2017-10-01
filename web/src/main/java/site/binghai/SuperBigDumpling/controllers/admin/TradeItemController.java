package site.binghai.SuperBigDumpling.controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import site.binghai.SuperBigDumpling.entity.people.Administrator;
import site.binghai.SuperBigDumpling.entity.things.TradeItem;
import site.binghai.SuperBigDumpling.service.SimpleDataService;
import site.binghai.SuperBigDumpling.utils.BeansUtils;
import site.binghai.SuperBigDumpling.utils.UserUtils;

import javax.servlet.http.HttpSession;

/**
 * Created by IceSea on 2017/9/25.
 * GitHub: https://github.com/IceSeaOnly
 */
@Controller
@RequestMapping("admin/tradeItem")
public class TradeItemController {

    @Autowired
    SimpleDataService dataService;

    @RequestMapping("addTradeItem")
    public String addTradeItem(TradeItem tradeItem, HttpSession session){
        Administrator administrator = UserUtils.getAdministrator(session);

        if(tradeItem != null){
            BeansUtils.initThings(tradeItem,administrator);
            dataService.save(tradeItem);
        }
        return "redirect:admin/tradeItem/";
    }

    @RequestMapping("delTradeItem")
    @ResponseBody
    public String delTradeItem(@RequestParam Integer tradeItemId,HttpSession session){
        TradeItem tradeItem = dataService.findById(tradeItemId,TradeItem.class);

        if(tradeItem != null){
            dataService.deleteById(tradeItemId,TradeItem.class);
        }

        return "ok";
    }
}
