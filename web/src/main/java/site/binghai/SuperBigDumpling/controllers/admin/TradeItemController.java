package site.binghai.SuperBigDumpling.controllers.admin;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import site.binghai.SuperBigDumpling.controllers.MultiController;
import site.binghai.SuperBigDumpling.entity.people.Administrator;
import site.binghai.SuperBigDumpling.entity.things.Album;
import site.binghai.SuperBigDumpling.entity.things.Category;
import site.binghai.SuperBigDumpling.entity.things.TradeItem;
import site.binghai.SuperBigDumpling.facades.TradeItemFacade;
import site.binghai.SuperBigDumpling.service.SimpleDataService;
import site.binghai.SuperBigDumpling.service.TradeItemService;
import site.binghai.SuperBigDumpling.utils.BeansUtils;
import site.binghai.SuperBigDumpling.utils.UserUtils;
import site.binghai.SuperDumpling.common.system.ErrorList;
import site.binghai.SuperDumpling.common.system.JSONResponse;
import site.binghai.SuperDumpling.common.utils.MapUtils;

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

    @RequestMapping("edit")
    public String editTradeItem(@RequestParam int id, ModelMap map){
        TradeItem item = getTradeItem(id);
        if(item == null){
            new NotFoundException("商品不存在");
        }
        map.put("item",item);
        return "editTradeItem";
    }

    private TradeItem getTradeItem(int id) {
        return dataService.findById(id,TradeItem.class);
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
            return error(ErrorList.INVALID_PARAMETER, null, null);
        }

        return success("success", service.findByCategory(c, page == null ? 0 : page));
    }

    @ResponseBody
    @RequestMapping("changeRecommend")
    public Object changeRecommend(@RequestParam int id){
        TradeItem item = dataService.findById(id,TradeItem.class);
        item.setRecommend(!item.isRecommend());
        dataService.update(item);
        return success("",null);
    }

    @ResponseBody
    @RequestMapping("changeIndexRecommend")
    public Object changeIndexRecommend(@RequestParam int id){
        TradeItem item = dataService.findById(id,TradeItem.class);
        item.setIndexRecommend(!item.isIndexRecommend());
        dataService.update(item);
        return success("",null);
    }


    @RequestMapping(value = "addAlbum",method = RequestMethod.POST)
    @ResponseBody
    public Object addAlbum(@RequestParam String url,HttpSession session){
        Album album = new Album();
        album.setUrl(url);
        album.setWeight(0);
        BeansUtils.initThings(album,UserUtils.getAdministrator(session));
        album = dataService.save(album);
        return success("",album);
    }

    @RequestMapping("albumAddWeigth")
    @ResponseBody
    public Object albumAddWeigth(@RequestParam int id){
        Album album = dataService.findById(id,Album.class);
        if(album == null){
            return error(ErrorList.NOT_FOUND,"404",null);
        }
        album.setWeight(album.getWeight()+1);
        dataService.update(album);
        return success("",album);
    }

    @ResponseBody
    @RequestMapping("findById")
    public Object findById(@RequestParam int id) {
        return success(null, dataService.findById(id, TradeItem.class));
    }

    @Override
    public Object handleRequest(Map params) {
        switch (getAct(params)) {
            case "index":
                return GoodIndex(params);
            case "goods-list":
                return GoodsList(params);
            case "goods-detail":
                return GoodsDetail(params);
        }

        return unkownRequest();
    }

    private Object GoodIndex(Map params) {
        if (getCid(params) < 0) {
            return null;
        }
        return tradeItemFacade.asList(service.findRecommand(getCid(params), getPage(params), 0));
    }

    private Object GoodsDetail(Map params) {
        int gid = MapUtils.getInt(params,"gid");
        TradeItem item = dataService.findById(gid,TradeItem.class);
        TradeItemFacade facade = tradeItemFacade.asObj(item);
        return facade;
    }

    /**
     * 获取类目下商品
     * */
    private Object GoodsList(Map params) {
        if (getCid(params) < 0) {
            return null;
        }

        Category category = dataService.findById(getCid(params), Category.class);
        if (category == null) {
            return null;
        }
        return tradeItemFacade.asList(service.findByCategory(category, getPage(params)));
    }


    @Override
    public List<String> getActHeader() {
        return Arrays.asList("index", "goods-list","goods-detail");
    }
}
