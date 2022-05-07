package me.majkelmichel.client.books;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class BookService {

    private final WebClient apiClient;

    public BookService(WebClient apiClient) {
        this.apiClient = apiClient;
    }

    public Flux<BookDto> getBooks() {
        return apiClient
                .get()
                .uri("/books")
                .retrieve()
                .bodyToFlux(BookDto.class);
    }

    public Mono<BookDto> getBook(Long id) {
        return apiClient
                .get()
                .uri("/books/" + id)
                .retrieve()
                .bodyToMono(BookDto.class);
    }
}
