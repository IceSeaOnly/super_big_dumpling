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

    public static int getInt(Map params, String gid) {
        try {
            return params.get(gid) == null ? -1 : Integer.parseInt(String.valueOf(params.get(gid)));
        }catch (Exception e){
            e.printStackTrace();
        }
        return -1;
    }
}
