package site.binghai.web;

import com.alibaba.fastjson.JSONObject;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import site.binghai.SuperBigDumpling.SuperBigDumplingApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SuperBigDumplingApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnableAutoConfiguration
public abstract class BaseTest {
    public void out(Object obj) {
        System.out.println(JSONObject.toJSONString(obj));
    }
}
