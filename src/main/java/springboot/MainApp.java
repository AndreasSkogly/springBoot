package springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@ComponentScan(basePackages = {"controller", "springboot"})
public class MainApp {
    public static void main(String[] args) {
        SpringApplication.run(MainApp.class, args);

        List<String> deltagerListe = new ArrayList<>();

        deltagerListe.add("Test Testensen");  
    }
}
