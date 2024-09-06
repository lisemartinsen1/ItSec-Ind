package org.example.sec_individuell.controller;

import org.example.sec_individuell.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MainController {

    private final UserService userService;

    public MainController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/index")
    public String home(Model model, Authentication authentication) {
        userService.setUsernameInModel(model, authentication);
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

}


