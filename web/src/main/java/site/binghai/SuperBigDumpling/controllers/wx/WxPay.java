package site.binghai.SuperBigDumpling.controllers.wx;

import org.springframework.stereotype.Controller;
import site.binghai.SuperBigDumpling.controllers.MultiController;
import site.binghai.SuperDumpling.common.definations.ApiRequestMapping;
import java.util.Map;

/**
 * Created by IceSea on 2017/10/26.
 * GitHub: https://github.com/IceSeaOnly
 */
@Controller
public class WxPay extends MultiController {

    @ApiRequestMapping("wx-pay")
    public Object wxPay(Map params) {
        return null;
    }
}
