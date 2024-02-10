package com.rali.ws.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class AppConfig {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public WebClient webClient(ClientRegistrationRepository clientRegistrationRepository,
                               OAuth2AuthorizedClientRepository oAuth2AuthorizedClientRepository) {
        ServletOAuth2AuthorizedClientExchangeFilterFunction oAuthClientExchangeFilterFunction =
                new ServletOAuth2AuthorizedClientExchangeFilterFunction(clientRegistrationRepository, oAuth2AuthorizedClientRepository);
        oAuthClientExchangeFilterFunction.setDefaultOAuth2AuthorizedClient(true);

        return WebClient.builder().apply(oAuthClientExchangeFilterFunction.oauth2Configuration()).build();
    }
}
