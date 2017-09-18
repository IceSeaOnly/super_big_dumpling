package site.binghai.SuperBigDumpling.utils;

import site.binghai.SuperBigDumpling.entity.people.Administrator;
import site.binghai.SuperBigDumpling.entity.things.AbstractEntity;

/**
 * Created by IceSea on 2017/9/18.
 * 人物类工具
 * GitHub: https://github.com/IceSeaOnly
 */
public class BeansUtils {
    /**
     * 初始化公用属性
     * */
    public static void initThings(AbstractEntity entity, Administrator admin) {
        entity.setUserId(admin.getId());
        entity.setOwner(admin.getUsername());
    }
}
