package site.binghai.SuperBigDumpling.web.controllers.admin;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.binghai.SuperBigDumpling.common.definations.ApiRequestMapping;
import site.binghai.SuperBigDumpling.web.controllers.MultiController;

import java.util.Map;

/**
 * Created by binghai on 2017/12/2.
 *
 * @ super_big_dumpling
 */
@RestController
@RequestMapping("admin/group")
public class GroupContoller extends MultiController {

    @ApiRequestMapping("group-detail")
    public Object groupDetail(Map params) {
        return null;
    }
}
