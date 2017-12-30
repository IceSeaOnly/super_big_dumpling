package site.binghai.SuperBigDumpling.web.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by binghai on 2017/9/17.
 *
 * @ MoGuJie
 */
@Configuration
public class ViewControllers extends WebMvcConfigurerAdapter {

    /**
     * 无逻辑处理，直接url -> page 的映射关系
     * */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/admin/tradeItem/").setViewName("tradeitem");
        registry.addViewController("/admin/banners/").setViewName("banner");
        super.addViewControllers(registry);
    }



}
