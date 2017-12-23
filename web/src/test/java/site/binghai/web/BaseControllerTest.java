package site.binghai.web;

import org.junit.Before;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by binghai on 2017/12/23.
 *
 * @ super_big_dumpling
 */
public abstract class BaseControllerTest extends BaseTest {
    private Map<String,Object> params;

    @Before
    public final void setUp() throws Exception {
        params = new HashMap<>();
        initTest();
    }

    protected abstract void initTest();

    public void clearParams(){
        params.clear();
    }


    public Map<String, Object> getParams() {
        return params;
    }
}
