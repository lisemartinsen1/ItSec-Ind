package org.example.sec_individuell.controller;

import org.example.sec_individuell.service.HashService;
import org.example.sec_individuell.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.NoSuchAlgorithmException;

@Controller
public class HashController {

    private final HashService service;
    private final UserService userService;

    public HashController(HashService service, UserService userService) {
        this.service = service;
        this.userService = userService;
    }

    @GetMapping("/hash")
    public String hash(Model model, Authentication authentication) {
        userService.setUsernameInModel(model, authentication);
        return "hashing";
    }

    @PostMapping("/getHash")
    public String getHash(@RequestParam String input, Model model, Authentication auth) {
        try {
            String md5Hash = service.hashInput(input, "MD5");
            String sha256Hash = service.hashInput(input, "SHA-256");

            model.addAttribute("input", input);
            model.addAttribute("md5", md5Hash);
            model.addAttribute("sha256", sha256Hash);
            userService.setUsernameInModel(model, auth);

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        return "hashing";
    }

}
