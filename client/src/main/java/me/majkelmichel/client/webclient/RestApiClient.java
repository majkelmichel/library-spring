package me.majkelmichel.client.webclient;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class RestApiClient {

    @Bean
    public WebClient apiClient() {
        return WebClient.create("http://localhost:8080/");
    }
}
