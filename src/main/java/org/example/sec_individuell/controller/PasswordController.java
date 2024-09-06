package org.example.sec_individuell.controller;

import org.example.sec_individuell.service.PasswordService;
import org.example.sec_individuell.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PasswordController {

    private final PasswordService ps;
    private final UserService us;

    public PasswordController(PasswordService ps, UserService us) {
        this.ps = ps;
        this.us = us;
    }

    @GetMapping("/passwordCracker")
    public String crack(Model model, Authentication authentication) {
        us.setUsernameInModel(model, authentication);
        model.addAttribute("submitted", false);
        return "cracking";
    }

    @PostMapping("/crackPassw")
    public String passwordCracker(@RequestParam String input, Model model, Authentication authentication) {
        String crackedPassword = ps.getCrackedPassword(input);
        System.out.println(crackedPassword);
        model.addAttribute("crackedPassword", crackedPassword);
        model.addAttribute("input", input);
        model.addAttribute("submitted", true);
        us.setUsernameInModel(model, authentication);
        return "cracking";
    }

}
