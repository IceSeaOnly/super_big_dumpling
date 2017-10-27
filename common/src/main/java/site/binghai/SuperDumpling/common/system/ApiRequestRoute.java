package site.binghai.SuperDumpling.common.system;

import lombok.AllArgsConstructor;
import lombok.Data;
import site.binghai.SuperDumpling.common.definations.WxHandler;

import java.lang.reflect.Method;

/**
 * Created by binghai on 2017/10/27.
 *
 * @ MoGuJie
 */
@Data
@AllArgsConstructor
public class ApiRequestRoute {
    private Method method;
    private Object instance;
    private Class clazz;
}
