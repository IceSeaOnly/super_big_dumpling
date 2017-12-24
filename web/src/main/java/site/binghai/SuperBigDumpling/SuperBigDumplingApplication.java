package site.binghai.SuperBigDumpling;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import site.binghai.SuperBigDumpling.common.entity.things.Configs;
import site.binghai.SuperBigDumpling.dao.service.DefaultConfigService;

@SpringBootApplication
@ComponentScan("site.binghai.*")
@EnableScheduling
public class SuperBigDumplingApplication implements CommandLineRunner {

    @Autowired
    private DefaultConfigService defaultConfigService;

    public static void main(String[] args) {
        SpringApplication.run(SuperBigDumplingApplication.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        /**
         * 初始化默认配置
         * */
        if (!defaultConfigService.defaultConfigExist()) {
            defaultConfigService.save(new Configs());
        }
    }
}
