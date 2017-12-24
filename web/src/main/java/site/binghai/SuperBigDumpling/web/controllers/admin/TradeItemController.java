package site.binghai.SuperBigDumpling.web.controllers.admin;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import site.binghai.SuperBigDumpling.api.enums.GroupStatusEnum;
import site.binghai.SuperBigDumpling.common.entity.things.*;
import site.binghai.SuperBigDumpling.common.facades.TradeItemFacade;
import site.binghai.SuperBigDumpling.dao.service.*;
import site.binghai.SuperBigDumpling.common.utils.BeansUtils;
import site.binghai.SuperBigDumpling.common.utils.HttpRequestUtils;
import site.binghai.SuperBigDumpling.web.controllers.MultiController;
import site.binghai.SuperBigDumpling.common.entity.people.Administrator;
import site.binghai.SuperBigDumpling.common.utils.UserUtils;
import site.binghai.SuperBigDumpling.common.definations.ApiRequestMapping;
import site.binghai.SuperBigDumpling.common.system.ErrorList;
import site.binghai.SuperBigDumpling.common.system.JSONResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
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
    private TradeItemService tradeItemService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private GroupService groupService;
    @Autowired
    private AlbumService albumService;
    @Autowired
    private PropertyService propertyService;

    private TradeItemFacade tradeItemFacade = new TradeItemFacade();

    @RequestMapping(value = "addTradeItem", method = RequestMethod.POST)
    @ResponseBody
    public Object addTradeItem(TradeItem tradeItem, HttpServletRequest req, HttpSession session) {
        Map<String, String> maps = HttpRequestUtils.getRequestParamMap(req);
        tradeItem.setIndexRecommend(Boolean.parseBoolean(maps.get("indexRecommand")));
        tradeItem.setRecommend(Boolean.parseBoolean(maps.get("recommand")));

        Administrator administrator = UserUtils.getAdministrator(session);
        BeansUtils.initThings(tradeItem, administrator);
        tradeItemService.save(tradeItem);

        return JSONResponse.successResp("success", null);
    }

    @RequestMapping("edit")
    public String editTradeItem(@RequestParam int id, ModelMap map) {
        TradeItem item = getTradeItem(id);
        if (item == null) {
            new NotFoundException("商品不存在");
        }
        map.put("item", item);
        return "editTradeItem";
    }

    private TradeItem getTradeItem(int id) {
        return tradeItemService.findById(id);
    }

    @RequestMapping("delTradeItem")
    @ResponseBody
    public String delTradeItem(@RequestParam Integer tradeItemId, HttpSession session) {
        TradeItem tradeItem = tradeItemService.findById(tradeItemId);

        if (tradeItem != null) {
            tradeItemService.delete(tradeItemId);
        }

        return "ok";
    }

    @RequestMapping("listByCategory")
    @ResponseBody
    public Object listByCategory(@RequestParam int ch, Integer page) {
        Category c = categoryService.findById(ch);
        if (c == null) {
            return error(ErrorList.INVALID_PARAMETER, null, null);
        }

        return success("success", tradeItemService.findByCategory(c, page == null ? 0 : page));
    }

    @ResponseBody
    @RequestMapping("changeRecommend")
    public Object changeRecommend(@RequestParam int id) {
        TradeItem item = tradeItemService.findById(id);
        item.setRecommend(!item.isRecommend());
        tradeItemService.update(item);
        return success("", null);
    }

    @ResponseBody
    @RequestMapping("changeIndexRecommend")
    public Object changeIndexRecommend(@RequestParam int id) {
        TradeItem item = tradeItemService.findById(id);
        item.setIndexRecommend(!item.isIndexRecommend());
        tradeItemService.update(item);
        return success("", null);
    }


    @RequestMapping(value = "addAlbum", method = RequestMethod.POST)
    @ResponseBody
    public Object addAlbum(@RequestParam String url, HttpSession session) {
        Album album = new Album();
        album.setUrl(url);
        album.setWeight(0);
        BeansUtils.initThings(album, UserUtils.getAdministrator(session));
        album = albumService.save(album);
        return success("", album);
    }

    @RequestMapping("albumAddWeigth")
    @ResponseBody
    public Object albumAddWeigth(@RequestParam int id) {
        Album album = albumService.findById(id);
        if (album == null) {
            return error(ErrorList.NOT_FOUND, "404", null);
        }
        album.setWeight(album.getWeight() + 1);
        albumService.update(album);
        return success("", album);
    }

    @RequestMapping("addTradeItemProperties")
    @ResponseBody
    public Object addTradeItemProperties(@RequestParam String properties, HttpSession session) {
        String[] arr = properties.split(" ");
        if (arr.length < 2) {
            return error(ErrorList.INVALID_PARAMETER, "输入格式有误", null);
        }
        Property property = new Property();
        property.setName(arr[0]);
        List<String> pts = new ArrayList<>();
        for (int i = 1; i < arr.length; i++) {
            pts.add(arr[i]);
        }
        property.setValue(pts);
        BeansUtils.initThings(property, UserUtils.getAdministrator(session));
        property = propertyService.save(property);
        return success("ok", property);
    }

    @ResponseBody
    @RequestMapping("findById")
    public Object findById(@RequestParam int id) {
        return success(null, tradeItemService.findById(id));
    }

    @ApiRequestMapping("index")
    public Object GoodIndex(Map params) {
        return tradeItemFacade.asList(tradeItemService.findRecommand(getCid(params), getPage(params), getCid(params) <= 0 ? 1 : 0));
    }

    @ApiRequestMapping("goods-detail")
    public Object GoodsDetail(Map params) {
        int gid = getInt(params, "gid");
        TradeItem item = tradeItemService.findById(gid);
        TradeItemFacade facade = tradeItemFacade.asObj(item);
        facade.getGroupList().addAll(groupService.findByTradeItemIdAndStatus(gid, GroupStatusEnum.GROUPING, 1));
        return facade;
    }

    /**
     * 获取类目下商品
     */
    @ApiRequestMapping("goods-list")
    public Object GoodsList(Map params) {
        if (getCid(params) < 0) {
            return null;
        }

        Category category = categoryService.findById(getCid(params));
        if (category == null) {
            return null;
        }
        return tradeItemFacade.asList(tradeItemService.findByCategory(category, getPage(params)));
    }
}
