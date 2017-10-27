package site.binghai.SuperBigDumpling.controllers.wx;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import site.binghai.SuperBigDumpling.controllers.MultiController;
import site.binghai.SuperBigDumpling.enums.WxParams;
import site.binghai.SuperBigDumpling.utils.HttpRequestUtils;
import site.binghai.SuperDumpling.common.definations.ApiRequestMapping;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by binghai on 2017/10/17.
 *
 * @ MoGuJie
 */
@Component
public class UserController extends MultiController {
    private static String WXLOGIN = "https://api.weixin.qq.com/sns/jscode2session?";

    @Autowired
    WxParams wxParams;

    @Override
    protected void afterBeanInitialized() {
        WXLOGIN += String.format("appid=%s&secret=%s&grant_type=authorization_code",
                wxParams.getAppId(),wxParams.getSecret())+"&js_code=%s";
    }

    @ApiRequestMapping("login")
    public Object login(Map params) {
        String userCode = getUserCode(params);
        String url = String.format(WXLOGIN,userCode);
        JSONObject resp = HttpRequestUtils.getJson(url);
        return resp.getString("openid");
    }
}
