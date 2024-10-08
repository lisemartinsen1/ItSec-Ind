package org.example.sec_individuell;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class SecIndividuellApplication {

    public static void main(String[] args) {
        if (args.length == 0) {
            Dotenv dotenv = Dotenv.load();
            dotenv.entries().forEach(entry -> System.setProperty(entry.getKey(), entry.getValue()));
        }
        SpringApplication.run(SecIndividuellApplication.class, args);
    }
}
