package site.binghai.SuperDumpling.common.utils;

import java.util.Map;

/**
 * Created by binghai on 2017/9/29.
 *
 * @ MoGuJie
 */
public class MapUtils {

    public static void putIfNotNull(Map<String, Object> model, Object value, String key) {
        if(value != null){
            model.put(key,value);
        }
    }
}
