package site.binghai.SuperBigDumpling.controllers.wx;

import org.springframework.stereotype.Component;
import site.binghai.SuperBigDumpling.controllers.MultiController;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by binghai on 2017/10/17.
 *
 * @ MoGuJie
 */
@Component
public class UserController extends MultiController {
    @Override
    public Object handleRequest(Map params) {
        switch (getAct(params)){
            case "login":
                return login(params);
        }

        return unkownRequest();
    }

    private Object login(Map params) {
        String access_token = "ABCDEFG123456789";
        return access_token;
    }

    @Override
    public List<String> getActHeader() {
        return Arrays.asList("login");
    }
}
