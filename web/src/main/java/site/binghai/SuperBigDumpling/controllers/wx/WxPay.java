package site.binghai.SuperBigDumpling.controllers.wx;

import org.springframework.stereotype.Controller;
import site.binghai.SuperBigDumpling.controllers.MultiController;
import site.binghai.SuperDumpling.common.definations.ApiRequestMapping;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by IceSea on 2017/10/26.
 * GitHub: https://github.com/IceSeaOnly
 */
@Controller
public class WxPay extends MultiController {

    @Override
    public Object handleRequest(Map params) {
        switch (getAct(params)){
            case "wx-pay":
                return wxPay(params);
        }
        return unkownRequest();
    }

    private Object wxPay(Map params) {
        return null;
    }

    @Override
    public List<String> getActHeader() {
        return Arrays.asList("wx-pay");
    }

    @ApiRequestMapping("hello")
    public void test(){}
}
