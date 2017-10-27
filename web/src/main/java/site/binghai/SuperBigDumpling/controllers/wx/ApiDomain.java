package site.binghai.SuperBigDumpling.controllers.wx;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.ContextLoader;
import site.binghai.SuperBigDumpling.utils.HttpRequestUtils;
import site.binghai.SuperDumpling.common.definations.ApiRequestMapping;
import site.binghai.SuperDumpling.common.definations.WxHandler;
import site.binghai.SuperDumpling.common.system.ApiRequestRoute;
import site.binghai.SuperDumpling.common.system.ErrorList;
import site.binghai.SuperDumpling.common.system.JSONResponse;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by binghai on 2017/10/15.
 *
 * @ MoGuJie
 */
@RestController
@RequestMapping("wx")
public class ApiDomain {
    private static Map<String, ApiRequestRoute> wxHandlerMap = new HashMap<>();

    @RequestMapping("api")
    public Object api(HttpServletRequest request) {
        Map req = HttpRequestUtils.getRequestParamMap(request);
        Object act = req.get("act");

        if (act == null) {
            return JSONResponse.errorResp(ErrorList.NULL_ACT, null, null);
        }

        return route(String.valueOf(act), req);
    }

    /**
     * 请求路由
     */
    private Object route(String act, Map req) {
        ApiRequestRoute route = wxHandlerMap.get(act);
        if(route == null){
            return JSONResponse.errorResp(ErrorList.UNKONW_ACT, null, null);
        }

        Object obj = ContextLoader.getCurrentWebApplicationContext().getBean(route.getClazz());
        return obj instanceof JSONResponse ? obj : JSONResponse.successResp("", obj);
    }

    public static void registerHandler(WxHandler handler) {
        Method[] methods = handler.getClass().getMethods();
        for (Method method : methods) {
            for (Annotation an : method.getDeclaredAnnotations()) {
                if (an.annotationType().equals(ApiRequestMapping.class)) {
                    String actName = ((ApiRequestMapping) an).value();
                    String methodName = method.getName();
                    ApiRequestRoute requestRoute = new ApiRequestRoute(actName,methodName,handler.getClass());
                    wxHandlerMap.put(actName,requestRoute);
                }
            }
        }
    }
}
