package site.binghai.SuperDumpling.common.definations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by binghai on 2017/10/27.
 * api请求路由注解
 * @ MoGuJie
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ApiRequestMapping {
    String value();
}
