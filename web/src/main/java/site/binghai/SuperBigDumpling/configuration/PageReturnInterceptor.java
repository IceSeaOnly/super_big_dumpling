package site.binghai.SuperBigDumpling.configuration;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by binghai on 2017/9/16.
 * 全局建言，对全局controller生效
 *
 * @ MoGuJie
 */

public class PageReturnInterceptor extends HandlerInterceptorAdapter {

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        String viewName = modelAndView == null ? null : modelAndView.getViewName();
        if (null == viewName) {
            return;
        }
        if (viewName.equals("error")) {
            modelAndView.setViewName("error");
            return;
        }
        if (!viewName.startsWith("redirect:")) {
            modelAndView.setViewName("tpl");
            modelAndView.addObject("tpl_page", viewName);
        }
    }
}
