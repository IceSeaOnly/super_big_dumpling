package site.binghai.SuperBigDumpling.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

/**
 * Created by binghai on 2017/9/17.
 *
 * @ MoGuJie
 */
@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        super.addInterceptors(registry);
        registry.addInterceptor(tplInterceptor())
                .excludePathPatterns("/css/**","/images/**","/img/**","/font-awesome/**","/js/**");
    }

    @Bean
    public PageReturnInterceptor tplInterceptor() {
        return new PageReturnInterceptor();
    }

//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        super.addResourceHandlers(registry);
//        registry.addResourceHandler("/css/**")
//                .addResourceLocations("classpath:/css/*");
//        registry.addResourceHandler("/images/**")
//                .addResourceLocations("classpath:/images/*");
//        registry.addResourceHandler("/img/**")
//                .addResourceLocations("classpath:/img/*");
//        registry.addResourceHandler("/js/**")
//                .addResourceLocations("classpath:/js/*");
//        registry.addResourceHandler("/font-awesome/**")
//                .addResourceLocations("classpath:/font-awesome/*");
//    }
}
