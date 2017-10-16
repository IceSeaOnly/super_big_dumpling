package site.binghai.SuperBigDumpling.controllers.admin;

import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import site.binghai.SuperBigDumpling.entity.people.Administrator;
import site.binghai.SuperBigDumpling.entity.things.Category;
import site.binghai.SuperBigDumpling.entity.things.TradeItem;
import site.binghai.SuperBigDumpling.facades.TradeItemFacade;
import site.binghai.SuperBigDumpling.service.SimpleDataService;
import site.binghai.SuperBigDumpling.service.TradeItemService;
import site.binghai.SuperBigDumpling.utils.BeansUtils;
import site.binghai.SuperBigDumpling.utils.UserUtils;
import site.binghai.SuperDumpling.common.system.ErrorList;
import site.binghai.SuperDumpling.common.system.JSONResponse;

import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by IceSea on 2017/9/25.
 * GitHub: https://github.com/IceSeaOnly
 */
@Controller
@RequestMapping("admin/tradeItem")
public class TradeItemController extends MultiController {

    @Autowired
    private TradeItemService service;
    @Autowired
    private SimpleDataService dataService;
    private TradeItemFacade tradeItemFacade = new TradeItemFacade();

    @RequestMapping(value = "addTradeItem", method = RequestMethod.POST)
    @ResponseBody
    public Object addTradeItem(@NotNull TradeItem tradeItem, HttpSession session) {
        Administrator administrator = UserUtils.getAdministrator(session);

        BeansUtils.initThings(tradeItem, administrator);
        dataService.save(tradeItem);

        return JSONResponse.successResp("success", null);
    }

    @RequestMapping("delTradeItem")
    @ResponseBody
    public String delTradeItem(@RequestParam Integer tradeItemId, HttpSession session) {
        TradeItem tradeItem = dataService.findById(tradeItemId, TradeItem.class);

        if (tradeItem != null) {
            dataService.deleteById(tradeItemId, TradeItem.class);
        }

        return "ok";
    }

    @RequestMapping("listByCategory")
    @ResponseBody
    public Object listByCategory(@RequestParam int ch, Integer page) {
        Category c = dataService.findById(ch, Category.class);
        if (c == null) {
            return JSONResponse.errorResp(ErrorList.INVALID_PARAMETER, null, null);
        }

        return JSONResponse.successResp("success", service.findByCategory(c, page == null ? 0 : page));
    }

    @ResponseBody
    @RequestMapping("findById")
    public Object findById(@RequestParam int id) {
        return JSONResponse.successResp(null, dataService.findById(id, TradeItem.class));
    }

    @Override
    public Object handleRequest(Map params) {
        String act = MapUtils.getString(params,"act");
        int page = MapUtils.getInteger(params,"page",0);
        int cid = MapUtils.getInteger(params,"cid",-1);

        if(cid < 0){
            return null;
        }

        if(act.equals("index")){ // 获取父类推荐
            return tradeItemFacade.asList(service.findRecommand(cid,page,0));
        }else{ //获取类目下的所有商品
            Category category = dataService.findById(cid,Category.class);
            if(category == null){
                return null;
            }
            return tradeItemFacade.asList(service.findByCategory(category,page));
        }
    }

    @Override
    public List<String> getActHeader() {
        return Arrays.asList("index","goods-list");
    }
}
