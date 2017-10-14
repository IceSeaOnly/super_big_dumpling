package site.binghai.SuperBigDumpling.controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import site.binghai.SuperBigDumpling.entity.people.Administrator;
import site.binghai.SuperBigDumpling.entity.things.Category;
import site.binghai.SuperBigDumpling.entity.things.TradeItem;
import site.binghai.SuperBigDumpling.service.SimpleDataService;
import site.binghai.SuperBigDumpling.service.TradeItemService;
import site.binghai.SuperBigDumpling.utils.BeansUtils;
import site.binghai.SuperBigDumpling.utils.UserUtils;
import site.binghai.SuperDumpling.common.system.ErrorList;
import site.binghai.SuperDumpling.common.system.JSONResponse;

import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;

/**
 * Created by IceSea on 2017/9/25.
 * GitHub: https://github.com/IceSeaOnly
 */
@Controller
@RequestMapping("admin/tradeItem")
public class TradeItemController {

    @Autowired
    TradeItemService service;
    @Autowired
    SimpleDataService dataService;

    @RequestMapping(value = "addTradeItem",method = RequestMethod.POST)
    @ResponseBody
    public Object addTradeItem(@NotNull TradeItem tradeItem, HttpSession session){
        Administrator administrator = UserUtils.getAdministrator(session);

        BeansUtils.initThings(tradeItem,administrator);
        dataService.save(tradeItem);

        return JSONResponse.successResp("success",null);
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

    @RequestMapping("listByCategory")
    @ResponseBody
    public Object listByCategory(@RequestParam int fa,@RequestParam int ch,Integer page){
        Category f = dataService.findById(fa, Category.class);
        Category c = dataService.findById(ch, Category.class);

        if(f == null || c == null){
            return JSONResponse.errorResp(ErrorList.INVALID_PARAMETER,null,null);
        }

        return JSONResponse.successResp("success",service.findByCategory(f,c,page == null ? 0 : page));
    }

    @ResponseBody
    @RequestMapping("findById")
    public Object findById(@RequestParam int id){
        return JSONResponse.successResp(null,dataService.findById(id,TradeItem.class));
    }
}
