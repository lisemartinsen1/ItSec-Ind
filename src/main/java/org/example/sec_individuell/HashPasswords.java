package org.example.sec_individuell;
import org.example.sec_individuell.service.HashService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;;
import org.springframework.stereotype.Component;

import java.io.*;
import java.security.NoSuchAlgorithmException;

@Component
public class HashPasswords implements CommandLineRunner {

    private final HashService service;

    @Value("${hashedPasswordsFile}")
    private String hashedPasswordsFile;

    @Value("${commonPasswordsFile}")
    private String commonPasswordsFile;


    public HashPasswords(HashService service) {
        this.service = service;
    }

    @Override
    public void run(String... args) {
        hashMostCommonPasswordFile();
    }

    private void hashMostCommonPasswordFile() {

        try (
            BufferedReader br = new BufferedReader(new FileReader(commonPasswordsFile));
            BufferedWriter bw = new BufferedWriter(new FileWriter(hashedPasswordsFile));
        ){
            String line;
            while ((line = br.readLine()) != null) {
                try {
                    String hashedWord = service.hashInput(line, "SHA-256");
                    System.out.println(hashedWord);
                    bw.write(hashedWord);
                    bw.newLine();
                } catch (NoSuchAlgorithmException e) {
                    System.err.println("Hashing algorithm not found: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("IO Exception occurred: " + e.getMessage());
        }
    }
}
