package site.binghai.web;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import site.binghai.SuperBigDumpling.web.controllers.admin.GroupContoller;

import java.util.Map;

/**
 * Created by IceSea on 2017/10/26.
 * GitHub: https://github.com/IceSeaOnly
 */
@EnableAutoConfiguration
public class TestGroupFacede extends BaseControllerTest {
    @Autowired
    private GroupContoller groupContoller;

    @Test
    public void testGroupFacde() throws Exception {
        clearParams();
        getParams().put("oid","f70b2a4ef7759959647097670");
        getParams().put("code","oqAvw0DO1BUmrFU1HPYzhPENbsQ8");
        out(groupContoller.groupDetail(getParams()));
    }

    @Override
    protected void initTest() {

    }
}
