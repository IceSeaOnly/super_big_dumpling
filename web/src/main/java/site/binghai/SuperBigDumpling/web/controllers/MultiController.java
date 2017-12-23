package site.binghai.SuperBigDumpling.web.controllers;

import org.apache.commons.collections4.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import site.binghai.SuperBigDumpling.web.controllers.wx.ApiDomain;
import site.binghai.SuperBigDumpling.common.entity.people.User;
import site.binghai.SuperBigDumpling.dao.service.SimpleDataService;
import site.binghai.SuperBigDumpling.dao.service.UserService;
import site.binghai.SuperBigDumpling.common.definations.WxHandler;
import site.binghai.SuperBigDumpling.common.system.ErrorList;
import site.binghai.SuperBigDumpling.common.system.JSONResponse;

import java.util.Map;

/**
 * Created by binghai on 2017/10/16.
 *
 * @ MoGuJie
 */
public abstract class MultiController implements WxHandler, InitializingBean {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private Map params = null;
    @Autowired
    protected SimpleDataService simpleDataService;
    @Autowired
    protected UserService userService;

    /**
     * 此方法为父类使用，子类不可重写
     * 如需获得监听初始化能力
     * 只需重写afterBeanInitialized方法
     */
    @Override
    public final void afterPropertiesSet() throws Exception {
        ApiDomain.registerHandler(this);
        afterBeanInitialized();
    }

    protected void afterBeanInitialized() {/** 空方法，按需重写以获得监听初始化能力 */}

    /**
     * 通过用户传递过来的uuid（code）获取用户
     */
    public User getUserByWxCode(Map params) throws Exception {
        return getUserByWxUuid(getUserCode(params));
    }

    public User getUserByWxUuid(String uuid) {
        return userService.findByUuid(uuid);
    }

    public String getUserCode(Map params) throws Exception {
        String code = getString(params, "code");
        if (StringUtils.isEmpty(code)) {
            throw new Exception("code can't be empty!");
        }
        return code;
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

    public JSONResponse illegalRequest(Map params) {
        logger.error("非法访问:{}", params);
        return error(ErrorList.ILLEGAL_REQUEST, null, null);
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
     */
    public int getMoneyY2F(Map map, Object key) {
        return (int) (getDouble(map, key) * 100);
    }
}
