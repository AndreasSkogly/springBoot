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
        // Sto i oppgaven at man skal legge til 5-10 hardkodede deltagere i en liste
        deltagerListe.add("Test Testensen");
    }
}
