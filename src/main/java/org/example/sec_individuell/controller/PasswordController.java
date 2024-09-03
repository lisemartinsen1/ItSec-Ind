package org.example.sec_individuell.controller;

import org.example.sec_individuell.service.PasswordService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PasswordController {

    private final PasswordService ps;

    public PasswordController(PasswordService ps) {
        this.ps = ps;
    }


    @PostMapping("/crackPassw")
    public String passwordCracker(@RequestParam String input, Model model) {
        String crackedPassword = ps.getCrackedPassword(input);
        System.out.println(crackedPassword);
        model.addAttribute("crackedPassword", crackedPassword);
        model.addAttribute("input", input);
        model.addAttribute("submitted", true);
        return "cracking";
    }




}
