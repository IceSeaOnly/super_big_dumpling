package site.binghai.SuperBigDumpling.web.configuration;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import site.binghai.SuperBigDumpling.common.system.ErrorList;
import site.binghai.SuperBigDumpling.common.system.JSONResponse;

import javax.validation.ConstraintViolationException;

/**
 * Created by IceSea on 2017/9/23.
 * GitHub: https://github.com/IceSeaOnly
 */
@ControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(value = Exception.class)
    public ModelAndView exception(Exception exception, WebRequest webRequest){
        ModelAndView view = new ModelAndView("error");
        view.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        exception.printStackTrace();
        return view;
    }

    /**
     * validate check exception
     * */
    @ExceptionHandler(value = ConstraintViolationException.class)
    @ResponseBody
    public Object validateCheckException(Exception exception, WebRequest webRequest){
        return JSONResponse
                .errorResp(ErrorList.INVALID_PARAMETER,exception.getMessage(),null);
    }
}
