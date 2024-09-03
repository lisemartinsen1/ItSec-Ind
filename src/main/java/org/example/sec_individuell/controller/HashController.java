package org.example.sec_individuell.controller;

import org.example.sec_individuell.service.HashService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.NoSuchAlgorithmException;

@Controller
public class HashController {

    private final HashService service;

    public HashController(HashService service) {
        this.service = service;
    }

    @PostMapping("/getHash")
    public String getHash(@RequestParam String input, Model model) {
        try {
            String md5Hash = service.hashInput(input, "MD5");
            String sha256Hash = service.hashInput(input, "SHA-256");

            model.addAttribute("input", input);
            model.addAttribute("md5", md5Hash);
            model.addAttribute("sha256", sha256Hash);

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        return "hashing";
    }

}
