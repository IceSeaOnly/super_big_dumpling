package site.binghai.SuperBigDumpling.utils;

import com.alibaba.fastjson.JSONObject;
import site.binghai.SuperBigDumpling.entity.people.Order;
import site.binghai.SuperBigDumpling.enums.OrderStatusEnum;

/**
 * Created by IceSea on 2017/10/26.
 * GitHub: https://github.com/IceSeaOnly
 */
public class OrderUtils {
    public static void makeOrderNo(Order order) {
        long cn = System.nanoTime();
        order.setOrderNum(MD5.encryption(JSONObject.toJSONString(order)).substring(20) + cn);
    }

    public static void orderStatusUpdate(Order order, OrderStatusEnum status){
        order.setStatus(status.getIndex());
        order.setOrderStatus(status.getStatus());
    }
}
