package site.binghai.SuperBigDumpling.controllers.admin;

import org.springframework.beans.factory.InitializingBean;
import site.binghai.SuperBigDumpling.controllers.wx.ApiDomain;
import site.binghai.SuperDumpling.common.definations.WxHandler;

/**
 * Created by binghai on 2017/10/16.
 *
 * @ MoGuJie
 */
public abstract class MultiController implements WxHandler, InitializingBean {
    @Override
    public void afterPropertiesSet() throws Exception {
        ApiDomain.registerHandler(this);
    }
}
