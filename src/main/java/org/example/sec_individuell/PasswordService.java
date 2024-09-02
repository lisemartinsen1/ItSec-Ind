package org.example.sec_individuell;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.*;


@Service
public class PasswordService {

    @Value("${hashedPasswordsFile}")
    private String hashedPasswordsFile;

    @Value("${commonPasswordsFile}")
    private String commonPasswordsFile;

    public String getCrackedPassword(String hashedPassword) {
        Integer lineNr = findMatchingHash(hashedPassword);
        if (lineNr == null) {
            return null;
        }
        return findPassword(lineNr);
    }

    public Integer findMatchingHash(String input)  {
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

    public String findPassword(int lineNr) {
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
/*
    public static void main(String[] args) {
        try {
            PasswordService service = new PasswordService();
            String crackedPassword = service.getCrackedPassword("30c5461fc27b84f1f1ad0a83162a26882b22d11cdfa45978dd21c810056e8d0e");
            if (crackedPassword != null) {
                System.out.println("Cracked Password: " + crackedPassword);
            } else {
                System.out.println("Password not found.");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

 */
}
