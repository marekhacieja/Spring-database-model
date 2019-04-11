package pl.marek.springdata1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import pl.marek.springdata1.app.AppController;

import java.util.Scanner;

@SpringBootApplication
public class Springdata1Application {
        public static void main(String[] args) {
            ConfigurableApplicationContext ctx = SpringApplication.run(Springdata1Application.class, args);
            AppController controller = ctx.getBean(AppController.class);
            controller.mainLoop();
        }
    @Bean
    public Scanner scanner() {
        return new Scanner(System.in);
    }
}
