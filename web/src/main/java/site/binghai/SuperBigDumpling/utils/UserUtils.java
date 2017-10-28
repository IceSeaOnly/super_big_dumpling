package site.binghai.SuperBigDumpling.utils;

import site.binghai.SuperBigDumpling.controllers.wx.UserController;
import site.binghai.SuperBigDumpling.entity.AbstractEntity;
import site.binghai.SuperBigDumpling.entity.people.Administrator;
import site.binghai.SuperBigDumpling.entity.people.User;

import javax.servlet.http.HttpSession;

/**
 * Created by IceSea on 2017/9/18.
 * GitHub: https://github.com/IceSeaOnly
 */
public class UserUtils {

    public static void userInit(AbstractEntity entity, User user){
        entity.setOwner(user.getUsername());
        entity.setUserId(user.getId());
    }

    public static String diyUUID(String openId){
        return MD5.encryption(openId+System.currentTimeMillis()).substring(16);
    }

    public static Administrator getAdministrator(HttpSession session) {
        Administrator admin = new Administrator();
        admin.setId(1);
        admin.setUsername("冰海");
        return admin;
//        return (Administrator) session.getAttribute("administrator");
    }
}
