package org.example.sec_individuell;
import org.example.sec_individuell.service.HashService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.*;
import java.security.NoSuchAlgorithmException;

@Component
public class HashPasswords implements CommandLineRunner {

    private final HashService service;

    @Value("${md5PasswordsFile}")
    private String md5PasswordsFile;

    @Value("${sha256PasswordsFile}")
    private String sha256PasswordsFile;

    @Value("${commonPasswordsFile}")
    private String commonPasswordsFile;


    public HashPasswords(HashService service) {
        this.service = service;
    }

    @Override
    public void run(String... args) {
        hashMostCommonPasswordFile("MD5", md5PasswordsFile);
        hashMostCommonPasswordFile("SHA-256", sha256PasswordsFile);
    }

    private void hashMostCommonPasswordFile(String hashAlg, String outputFile) {

        try (
            BufferedReader br = new BufferedReader(new FileReader(commonPasswordsFile));
            BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile))
        ){
            String line;
            while ((line = br.readLine()) != null) {
                try {
                    String hashedWord = service.hashInput(line, hashAlg);
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
