package site.binghai.SuperBigDumpling.common.utils;

import com.alibaba.fastjson.JSONObject;
import site.binghai.SuperBigDumpling.api.enums.OrderStatusEnum;
import site.binghai.SuperBigDumpling.common.entity.people.Order;

/**
 * Created by IceSea on 2017/10/26.
 * GitHub: https://github.com/IceSeaOnly
 */
public class OrderUtils {
    public static void makeOrderNo(Order order) {
        long cn = System.nanoTime();
        order.setOrderNum(MD5.encryption(JSONObject.toJSONString(order)).substring(20) + cn);
    }
}
