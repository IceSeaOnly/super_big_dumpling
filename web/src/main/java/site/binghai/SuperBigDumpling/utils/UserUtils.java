package site.binghai.SuperBigDumpling.utils;

import site.binghai.SuperBigDumpling.entity.people.Administrator;

import javax.servlet.http.HttpSession;

/**
 * Created by IceSea on 2017/9/18.
 * GitHub: https://github.com/IceSeaOnly
 */
public class UserUtils {

    public static Administrator getAdministrator(HttpSession session) {
        Administrator admin = new Administrator();
        admin.setId(1);
        admin.setUsername("冰海");
        return admin;
//        return (Administrator) session.getAttribute("administrator");
    }
}
