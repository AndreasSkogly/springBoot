package springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"controller", "springboot"})
public class MainApp {
    public static void main(String[] args) {

        SpringApplication.run(MainApp.class, args);
    }
}
