package org.example.sec_individuell;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Objects;

@SpringBootApplication
public class SecIndividuellApplication {

    public static void main(String[] args) {
        if (args.length == 0) {
            Dotenv dotenv = Dotenv.load();
            dotenv.entries().forEach(entry -> System.setProperty(entry.getKey(), entry.getValue()));
            SpringApplication.run(SecIndividuellApplication.class, args);
        } else if(Objects.equals(args[0], "hashPasswords")) {
            SpringApplication application = new SpringApplication(HashPasswords.class);
            application.setWebApplicationType(WebApplicationType.NONE);
            application.run(args);
        }

    }

    //Ladda ned fil med vanliga lösenord
    //Skapa fil där de hashade lösenorden ska sparas
    //Metod för att läsa in vanliga lösenord, hasha och spara ned.
    //Metod för att hasha lösenord
    //Metod för att jämföra hashat lösenord med hashar i hashfil.

}
