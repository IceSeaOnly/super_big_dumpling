package site.binghai.SuperBigDumpling.controllers;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.InitializingBean;
import site.binghai.SuperBigDumpling.controllers.wx.ApiDomain;
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

    @Override
    public void afterPropertiesSet() throws Exception {
        ApiDomain.registerHandler(this);
    }


    public String getAct(Map params){
        return MapUtils.getString(params,"act");
    }

    public int getCid(Map params){
        return MapUtils.getInteger(params,"cid",-1);
    }

    public int getPage(Map params){
        return MapUtils.getInteger(params,"page",0);
    }

    public JSONResponse error(ErrorList e,String msg,Object data){
        return JSONResponse.errorResp(e,msg,data);
    }

    public JSONResponse success(String msg,Object data){
        return JSONResponse.successResp(msg,data);
    }

    public JSONResponse unkownRequest(){
        return error(ErrorList.UNKONW_ACT, null, null);
    }
}
