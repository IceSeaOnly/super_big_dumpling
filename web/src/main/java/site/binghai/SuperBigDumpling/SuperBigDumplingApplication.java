package site.binghai.SuperBigDumpling;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("site.binghai.*")
public class SuperBigDumplingApplication {

	public static void main(String[] args) {
		SpringApplication.run(SuperBigDumplingApplication.class, args);
	}
}
