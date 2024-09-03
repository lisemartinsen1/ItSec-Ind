package org.example.sec_individuell.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class UserService {

    public void setUsernameInModel(Model model, Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            if (authentication instanceof OAuth2AuthenticationToken oauthToken) {
                OAuth2User oauthUser = oauthToken.getPrincipal();
                String username = oauthUser.getName();
                model.addAttribute("username", username);
            }
        }
    }
}
