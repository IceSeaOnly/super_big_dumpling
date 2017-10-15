package site.binghai.SuperBigDumpling.utils;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by binghai on 2017/9/9.
 *
 * @ MoGuJie
 */
public class HttpRequestUtils {
    public static String getRequestParams(HttpServletRequest request) {
        StringBuilder sb = new StringBuilder();
        Map<String,String> map = getRequestParamMap(request);
        Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry next = it.next();
            sb.append("&" + next.getKey() + "=" + next.getValue());
        }
        return sb.substring(1);
    }

    public static Map<String, String> getRequestParamMap(HttpServletRequest request) {
        Map<String,String> map = new HashMap<>();
        Enumeration enu = request.getParameterNames();
        while (enu.hasMoreElements()) {
            String paraName = (String) enu.nextElement();
            map.put(paraName,request.getParameter(paraName));
        }
        return map;
    }

    public static String getRequestURL(HttpServletRequest request) {
        return request.getRequestURL().toString();
    }

    public static String getRequestFullPath(HttpServletRequest request) {
        return getRequestURL(request) + "?" + getRequestParams(request);
    }

    public static String getRequestPath(HttpServletRequest request) {
        String full = getRequestFullPath(request);
        return full.substring(full.lastIndexOf("/"), full.length());
    }
}
