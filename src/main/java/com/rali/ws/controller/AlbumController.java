package com.rali.ws.controller;

import com.rali.ws.dto.AlbumDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class AlbumController {

    private final OAuth2AuthorizedClientService oAuth2AuthorizedClientService;
    private final WebClient webClient;

    @GetMapping(value = "albums")
    public String getAlbums(Model model, @AuthenticationPrincipal OidcUser principal) {

        String url = "http://localhost:8082/albums";

        List<AlbumDto> albums = webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<AlbumDto>>(){})
                .block();

        model.addAttribute("albums", albums);

        return "albums";
    }
}
