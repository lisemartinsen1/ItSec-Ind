package org.example.sec_individuell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.io.*;
import java.security.NoSuchAlgorithmException;

@Component
public class HashPasswords implements CommandLineRunner {

@Autowired
private final HashServices service;

String COMMON_PASSWORDS_FILE = "src/main/resources/10k-most-common.txt";
String HASHED_PASSWORDS_FILE = "src/main/resources/hashedPasswords";

    public HashPasswords(HashServices service) {
        this.service = service;
    }

    public void run(String...args) {
        hashMostCommonPasswordFile();
    }

    private void hashMostCommonPasswordFile() {

        try {
            BufferedReader br = new BufferedReader(new FileReader(COMMON_PASSWORDS_FILE));
            BufferedWriter bw = new BufferedWriter(new FileWriter(HASHED_PASSWORDS_FILE));

            String line;
            while((line = br.readLine()) != null) {
                String hashedWord = service.hashInput(line, "SHA-256");
                bw.write(hashedWord);
                bw.newLine();
            }
        } catch (IOException | NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
