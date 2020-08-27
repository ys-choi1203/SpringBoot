package testSpringBoot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("testSpringBoot")
@MapperScan(basePackages= "testSpringBoot.mapper")
public class TestSpringBootApplication {
	public static void main(String[] args) {
		SpringApplication.run(TestSpringBootApplication.class, args);
	}
}