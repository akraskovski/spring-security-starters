package com.github.akraskovski.tts.demo;

import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

    private final OAuth2AuthorizedClientService oAuth2AuthorizedClientService;

    public MessageController(OAuth2AuthorizedClientService oAuth2AuthorizedClientService) {
        this.oAuth2AuthorizedClientService = oAuth2AuthorizedClientService;
    }

    @GetMapping("/message")
    public String getMessage() {
        return "You've accessed the secured resource";
    }
}
