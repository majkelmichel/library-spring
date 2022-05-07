package me.majkelmichel.client.author;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AuthorService {

    private final WebClient apiClient;

    public AuthorService(WebClient apiClient) {
        this.apiClient = apiClient;
    }

    public Flux<AuthorDto> getAuthors() {
        return apiClient
                .get()
                .uri("/authors")
                .retrieve()
                .bodyToFlux(AuthorDto.class);
    }

    public Mono<AuthorDto> getAuthor(Long id) {
        return apiClient
                .get()
                .uri("/authors/" + id)
                .retrieve()
                .bodyToMono(AuthorDto.class);
    }
}
