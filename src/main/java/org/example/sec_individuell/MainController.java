package org.example.sec_individuell;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MainController {

    @GetMapping("/index")
    public String home(Model model, Authentication authentication) {
        setUsernameInModel(model, authentication);
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/hash")
    public String hash(Model model, Authentication authentication) {
        setUsernameInModel(model, authentication);
        return "hashing";
    }

    @GetMapping("/passwordCracker")
    public String crack(Model model, Authentication authentication) {
        setUsernameInModel(model, authentication);
        model.addAttribute("submitted", false);
        return "cracking";
    }

    private void setUsernameInModel(Model model, Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            if (authentication instanceof OAuth2AuthenticationToken oauthToken) {
                OAuth2User oauthUser = oauthToken.getPrincipal();
                String username = oauthUser.getName();
                model.addAttribute("username", username);
                System.out.println("A N V Ä N D A R N A M N: " + username);
            }
        }
    }

}
