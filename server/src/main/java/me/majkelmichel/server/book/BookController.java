package me.majkelmichel.server.book;

import me.majkelmichel.server.exceptions.AuthorDoesNotExistException;
import me.majkelmichel.server.exceptions.BookDoesNotExistException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<BookDto> getBooks() {
        return bookService.getBooks();
    }

    @GetMapping("/{id}")
    public BookDto getBook(@PathVariable Long id) {
        return bookService.getBook(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookDto createBook(@RequestBody BookCreationDto bookDto) {
        return bookService.createBook(bookDto);
    }

    @PutMapping("/{id}")
    public BookDto updateBook(@PathVariable Long id, @RequestBody BookCreationDto bookUpdateDto) {
        return bookService.updateBook(id, bookUpdateDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
    }

    @ExceptionHandler({BookDoesNotExistException.class, AuthorDoesNotExistException.class})
    public ResponseEntity<Map<String, String>> handleException(BookDoesNotExistException e) {
        return ResponseEntity
                .badRequest()
                .body(Map.of("error", e.getMessage()));
    }
}
