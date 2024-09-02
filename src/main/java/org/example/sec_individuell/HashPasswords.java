package org.example.sec_individuell;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

import java.io.*;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;

@ComponentScan
public class HashPasswords implements CommandLineRunner {

    private final HashServices service;

    private static final String COMMON_PASSWORDS_FILE = "src/main/resources/10k-most-common.txt";
    private static final String HASHED_PASSWORDS_FILE = "src/main/resources/hashedPasswords";

    public HashPasswords(HashServices service) {
        this.service = service;
    }

    @Override
    public void run(String... args) {
        hashMostCommonPasswordFile();
    }

    public static void main(String[] args) {
        HashPasswords hashPasswords = new HashPasswords(new HashServices());
        hashPasswords.run(args);
    }



    private void hashMostCommonPasswordFile() {

        try (
            BufferedReader br = new BufferedReader(new FileReader(COMMON_PASSWORDS_FILE));
            BufferedWriter bw = new BufferedWriter(new FileWriter(HASHED_PASSWORDS_FILE));
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
