package site.binghai.SuperBigDumpling.common.utils;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;

/**
 * Created by binghai on 2017/9/9.
 *
 * @ MoGuJie
 */
public class HttpRequestUtils {
    private static final Logger logger = LoggerFactory.getLogger(HttpRequestUtils.class);

    public static String getRequestParams(HttpServletRequest request) {
        StringBuilder sb = new StringBuilder();
        Map<String, String> map = getRequestParamMap(request);
        Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry next = it.next();
            sb.append("&" + next.getKey() + "=" + next.getValue());
        }
        return sb.substring(1);
    }

    public static Map<String, String> getRequestParamMap(HttpServletRequest request) {
        Map<String, String> map = new HashMap<>();
        Enumeration enu = request.getParameterNames();
        while (enu.hasMoreElements()) {
            String paraName = (String) enu.nextElement();
            map.put(paraName, request.getParameter(paraName));
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

    public static JSONObject getJson(String url) {
        return JSONObject.parseObject(sendGetRequest(url));
    }

    public static String sendGetRequest(String url) {
        StringBuilder result = new StringBuilder();
        BufferedReader in = null;
        try {
            URL realUrl = new URL(url);
            URLConnection connection = realUrl.openConnection();
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            connection.connect();
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
        } catch (Exception e) {
            logger.error("sendGetRequest error-1!", e);
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                logger.error("sendGetRequest error-2!", e2);
            }
        }
        return result.toString();
    }

    public static String sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            out = new PrintWriter(conn.getOutputStream());
            out.print(param);
            out.flush();
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！" + e);
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }
}
