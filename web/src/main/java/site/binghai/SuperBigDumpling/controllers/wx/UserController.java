package site.binghai.SuperBigDumpling.controllers.wx;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import site.binghai.SuperBigDumpling.controllers.MultiController;
import site.binghai.SuperBigDumpling.entity.people.User;
import site.binghai.SuperBigDumpling.enums.WxParams;
import site.binghai.SuperBigDumpling.utils.HttpRequestUtils;
import site.binghai.SuperBigDumpling.utils.MD5;
import site.binghai.SuperBigDumpling.utils.UserUtils;
import site.binghai.SuperDumpling.common.definations.ApiRequestMapping;
import java.util.Map;

/**
 * Created by binghai on 2017/10/17.
 *
 * @ MoGuJie
 */
@Component
public class UserController extends MultiController {
    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    private static String WXLOGIN = "https://api.weixin.qq.com/sns/jscode2session?";

    @Autowired
    WxParams wxParams;

    @Override
    protected void afterBeanInitialized() {
        WXLOGIN += String.format("appid=%s&secret=%s&grant_type=authorization_code",
                wxParams.getAppId(),wxParams.getSecret())+"&js_code=%s";
    }

    /**
     * code -> wx server  -> openid + session_key + uuid
     * */
    @ApiRequestMapping("login")
    public Object login(Map params) {
        String userCode = null;
        try {
            userCode = getUserCode(params);
        } catch (Exception e) {
            logger.error("no code when user login:{}",params);
            return unkownRequest();
        }

        String url = String.format(WXLOGIN,userCode);
        JSONObject resp = HttpRequestUtils.getJson(url);
        User user = getUserByWxOpenId(resp.getString("openid"));
        if(user == null){
//            user = regAuto(resp.getString("openid"),resp.getString("unionid"),resp.getString("session_key")); 暂无UUID，替换为自己生产UUID机制
            user = regAuto(resp.getString("openid"), UserUtils.diyUUID(resp.getString("openid")),resp.getString("session_key"));
        }
        user.setSessionKey(resp.getString("session_key"));
        user.setLastLogin(System.currentTimeMillis());
        simpleDataService.update(user);
        return user.getUuid();
    }

    private User getUserByWxOpenId(String openid) {
        return userService.findByOpenId(openid);
    }

    /**
     * 查询用户不存在自动注册
     * */
    private User regAuto(String openid, String unionid, String session_key) {
        User user = new User();
        user.setUsername("请设置用户名");
        user.setPassword(MD5.encryption("123456"));
        user.setPhone("10000000000");
        user.setUuid(unionid);
        user.setOpenId(openid);
        user.setMoney(0);
        user.setCps(0);
        user.setSessionKey(session_key);
        return simpleDataService.save(user);
    }
}
