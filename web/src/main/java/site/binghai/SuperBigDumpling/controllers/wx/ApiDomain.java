package site.binghai.SuperBigDumpling.controllers.wx;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.binghai.SuperBigDumpling.utils.HttpRequestUtils;
import site.binghai.SuperDumpling.common.definations.WxHandler;
import site.binghai.SuperDumpling.common.system.ErrorList;
import site.binghai.SuperDumpling.common.system.JSONResponse;

import javax.servlet.http.HttpServletRequest;
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
    private static Map<String,WxHandler> wxHandlerMap = new HashMap<>();

    @RequestMapping("api")
    public Object api(HttpServletRequest request){
        Map req = HttpRequestUtils.getRequestParamMap(request);
        Object act = req.get("act");

        if(act == null){
            return JSONResponse.errorResp(ErrorList.NULL_ACT,null,null);
        }

        return route(String.valueOf(act),req);
    }

    /**
     * 请求路由
     * */
    private Object route(String act, Map req) {
        return JSONResponse.successResp("",wxHandlerMap.get(act).handleRequest(req));
    }

    public static void registerHandler(WxHandler handler){
        wxHandlerMap.put(handler.getActHeader(),handler);
    }
}
