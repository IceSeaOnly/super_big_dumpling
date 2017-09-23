package site.binghai.SuperBigDumpling.configuration;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by IceSea on 2017/9/23.
 * GitHub: https://github.com/IceSeaOnly
 */
@ControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(value = Exception.class)
    public ModelAndView exception(Exception exception, WebRequest webRequest){
        ModelAndView view = new ModelAndView("error");
        exception.printStackTrace();
        return view;
    }
}
