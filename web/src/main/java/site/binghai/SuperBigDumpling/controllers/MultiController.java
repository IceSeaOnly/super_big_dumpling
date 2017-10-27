package site.binghai.SuperBigDumpling.controllers;

import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import site.binghai.SuperBigDumpling.controllers.wx.ApiDomain;
import site.binghai.SuperBigDumpling.entity.people.User;
import site.binghai.SuperBigDumpling.service.SimpleDataService;
import site.binghai.SuperDumpling.common.definations.WxHandler;
import site.binghai.SuperDumpling.common.system.ErrorList;
import site.binghai.SuperDumpling.common.system.JSONResponse;

import java.util.Map;

/**
 * Created by binghai on 2017/10/16.
 *
 * @ MoGuJie
 */
public abstract class MultiController implements WxHandler, InitializingBean {

    private Map params = null;
    @Autowired
    protected SimpleDataService simpleDataService;

    /**
     * 此方法为父类使用，子类不可重写
     * 如需获得监听初始化能力
     * 只需重写afterBeanInitialized方法
     * */
    @Override
    public final void afterPropertiesSet() throws Exception {
        ApiDomain.registerHandler(this);
        afterBeanInitialized();
    }

    protected void afterBeanInitialized(){/** 空方法，按需重写以获得监听初始化能力 */}

    public User getUserByWxCode(Map params) {
        // todo
        return simpleDataService.findById(1, User.class);
    }

    public String getUserCode(Map params){
        return getString(params,"code");
    }

    public String getAct(Map params) {
        return MapUtils.getString(params, "act");
    }

    public int getCid(Map params) {
        return MapUtils.getInteger(params, "cid", -1);
    }

    public int getPage(Map params) {
        return MapUtils.getInteger(params, "page", 0);
    }

    public JSONResponse error(ErrorList e, String msg, Object data) {
        return JSONResponse.errorResp(e, msg, data);
    }

    public JSONResponse success(String msg, Object data) {
        return JSONResponse.successResp(msg, data);
    }

    public JSONResponse unkownRequest() {
        return error(ErrorList.UNKONW_ACT, null, null);
    }

    public boolean getBoolean(Map map, Object key) {
        return MapUtils.getBoolean(map, key);
    }

    public String getString(Map map, Object key) {
        return MapUtils.getString(map, key);
    }

    public int getInt(Map map, Object key) {
        return MapUtils.getIntValue(map, key);
    }

    public double getDouble(Map map, Object key) {
        return MapUtils.getDouble(map, key);
    }

    /**
     * 人民币转换元到分
     * */
    public int getMoneyY2F(Map map, Object key) {
        return (int) (getDouble(map, key) * 100);
    }
}
