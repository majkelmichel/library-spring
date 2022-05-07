package me.majkelmichel.client.author;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public Flux<AuthorDto> getAuthors() {
        return authorService.getAuthors();
    }

    @GetMapping("/{id}")
    public Mono<AuthorDto> getAuthors(@PathVariable Long id) {
        return authorService.getAuthor(id);
    }
}
