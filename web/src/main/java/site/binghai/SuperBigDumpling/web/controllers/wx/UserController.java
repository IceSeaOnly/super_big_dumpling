package site.binghai.SuperBigDumpling.web.controllers.wx;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import site.binghai.SuperBigDumpling.common.entity.people.User;
import site.binghai.SuperBigDumpling.common.utils.HttpRequestUtils;
import site.binghai.SuperBigDumpling.dao.service.DefaultConfigService;
import site.binghai.SuperBigDumpling.web.controllers.MultiController;
import site.binghai.SuperBigDumpling.common.entity.WxParams;
import site.binghai.SuperBigDumpling.common.utils.MD5;
import site.binghai.SuperBigDumpling.common.utils.UserUtils;
import site.binghai.SuperBigDumpling.common.definations.ApiRequestMapping;

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
    private WxParams wxParams;
    @Autowired
    private DefaultConfigService configService;

    @Override
    protected void afterBeanInitialized() {
        WXLOGIN += String.format("appid=%s&secret=%s&grant_type=authorization_code",
                wxParams.getAppId(), wxParams.getSecret()) + "&js_code=%s";
    }

    /**
     * code -> wx server  -> openid + session_key + uuid
     */
    @ApiRequestMapping("login")
    public Object login(Map params) {
        String userCode = null;
        try {
            userCode = getUserCode(params);
        } catch (Exception e) {
            logger.error("no code when user login:{}", params);
            return unkownRequest();
        }

        String url = String.format(WXLOGIN, userCode);
        JSONObject resp = HttpRequestUtils.getJson(url);
        User user = getUserByWxOpenId(resp.getString("openid"));
        if (user == null) {
//            user = regAuto(resp.getString("openid"),resp.getString("unionid"),resp.getString("session_key")); 暂无UUID，替换为自己生产UUID机制
            user = regAuto(params, resp.getString("openid"), UserUtils.diyUUID(resp.getString("openid")), resp.getString("session_key"));
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
     */
    private User regAuto(Map params, String openid, String unionid, String session_key) {
        User user = new User();
        if (StringUtils.isEmpty(getString(params, "nickName"))) {
            user.setUsername(configService.getDefaultConfigs().getDefaultUserNickName() + getShortfix());
        } else {
            user.setUsername(getString(params, "nickName"));
        }

        user.setWxNickName(user.getUsername());
        user.setWxCity(getString(params, "city"));
        user.setWxProvince(getString(params, "province"));
        user.setWxCountry(getString(params, "country"));
        user.setWxGender(getString(params, "gender"));
        user.setWxLanguage(getString(params, "language"));
        user.setAvatarUrl(configService.getDefaultConfigs().getDefaultAvatar());
        user.setPassword(MD5.encryption("123456"));
        user.setPhone("10000000000");
        user.setUuid(unionid);
        user.setOpenId(openid);
        user.setMoney(0);
        user.setCps(0);
        user.setSessionKey(session_key);
        return simpleDataService.save(user);
    }

    private String getShortfix() {
        return String.valueOf(System.currentTimeMillis()).substring(7, 10);
    }


    @ApiRequestMapping("member")
    public Object memberCenter(Map params) throws Exception {
        return userService.getMemberData(getUserByWxCode(params));
    }
}
