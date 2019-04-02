package springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Locale;
/**
 * Created by Danylo on 31.03.2019
 */
@SpringBootApplication
public class CustomersSpringBootApplication {
    public static void main(String[] args) {
        Locale.setDefault(Locale.ENGLISH);
        SpringApplication.run(CustomersSpringBootApplication.class, args);
    }
}
