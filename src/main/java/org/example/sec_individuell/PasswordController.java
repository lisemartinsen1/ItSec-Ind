package org.example.sec_individuell;

import org.springframework.stereotype.Controller;

@Controller
public class PasswordController {

    private final PasswordService ps;
    public PasswordController(PasswordService ps) {
        this.ps = ps;
    }

    public void crackPassword(String input) {
        
    }
}
