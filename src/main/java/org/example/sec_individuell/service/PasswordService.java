package org.example.sec_individuell.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;


@Service
public class PasswordService {

    @Value("${md5PasswordsFile}")
    private String md5PasswordsFile;

    @Value("${sha256PasswordsFile}")
    private String sha256PasswordsFile;

    @Value("${commonPasswordsFile}")
    private String commonPasswordsFile;

    public String getCrackedPassword(String hashedPassword) {
        Integer lineNrForMD5 = findMatchingHash(hashedPassword, md5PasswordsFile);
        Integer lineNrForSHA256 = findMatchingHash(hashedPassword, sha256PasswordsFile);
        if (lineNrForMD5 != null) {
            return findPassword(lineNrForMD5);
        }

        if (lineNrForSHA256 != null) {
            return findPassword(lineNrForSHA256);
        }

        return null;
    }

    private Integer findMatchingHash(String input, String hashedPasswordsFile) {
        try (BufferedReader br = new BufferedReader(new FileReader(hashedPasswordsFile))) {
            String line;
            int counter = 0;

            while ((line = br.readLine()) != null) {
                line = line.strip();
                input = input.strip();

                if (line.equals(input)) {
                    return counter;
                }
                counter++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    private String findPassword(int lineNr) {
        try (BufferedReader br = new BufferedReader(new FileReader(commonPasswordsFile))) {
            String password = null;
            for (int i = 0; i <= lineNr; i++) {
                password = br.readLine();
                if (password == null) {
                    return null;
                }
            }
            return password;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
