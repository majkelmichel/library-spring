package me.majkelmichel.client.books;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/books")
public class BooksController {


    private final BookService bookService;

    public BooksController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public Flux<BookDto> getBooks() {
        return bookService.getBooks();
    }

    @GetMapping("/{id}")
    public Mono<BookDto> getOneBook(@PathVariable Long id) {
        return bookService.getBook(id);
    }
}
