package site.binghai.SuperBigDumpling.utils;

import site.binghai.SuperBigDumpling.entity.people.Administrator;
import site.binghai.SuperBigDumpling.entity.things.AbstractEntity;
import site.binghai.SuperDumpling.common.definations.DeleteAble;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    public static List deleteAableFileter(List input,boolean delete,boolean avaliable){
        Object[] rs = input
                        .stream()
                        .filter(v -> ((DeleteAble)v).hasDelete() == delete
                                && ((DeleteAble)v).accessAble() == avaliable)
                        .toArray();
        return Arrays.asList(rs);

    }
}
