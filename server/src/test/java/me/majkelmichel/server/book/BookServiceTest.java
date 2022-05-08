package me.majkelmichel.server.book;

import me.majkelmichel.server.author.Author;
import me.majkelmichel.server.author.AuthorRepository;
import me.majkelmichel.server.exceptions.AuthorDoesNotExistException;
import me.majkelmichel.server.exceptions.BookDoesNotExistException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class BookServiceTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookService bookService;
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private BookMapper bookMapper;

    @BeforeEach
    void setUp() {
        Author author = new Author(1L);
        author.setFirstName("First");
        author.setLastName("Last");
        authorRepository.save(author);

        Book BOOK_1 = new Book(1L, "Metro 2033", new Date(), author);
        Book BOOK_2 = new Book(2L, "Metro 2034", new Date(), author);
        Book BOOK_3 = new Book(3L, "Metro 2035", new Date(), author);
        List<Book> books = new ArrayList<>(Arrays.asList(BOOK_1, BOOK_2, BOOK_3));
        bookRepository.saveAll(books);
    }

    @AfterEach
    void tearDown() {
        bookRepository.deleteAll();
    }

    @Test
    @DirtiesContext
    void getBooks() {
        List<BookDto> books = bookService.getBooks();

        assertEquals(3, books.size());
        assertEquals("Metro 2034", books.get(1).getTitle());
    }

    @Test
    @DirtiesContext
    void getBook() {
        BookDto bookDto1 = bookService.getBook(1L);
        assertEquals("Metro 2033", bookDto1.getTitle());

    }

    @Test
    @DirtiesContext
    void getBookFails_whenIdDoesNotExist() {
        assertThrows(BookDoesNotExistException.class, () -> bookService.getBook(4L));
    }

    @Test
    @DirtiesContext
    void createBook() {
        assertEquals(3, bookRepository.findAll().size());

        BookCreationDto bookCreationDto = new BookCreationDto("FUTU.RE", new Date(), 1L);

        BookDto created = bookService.createBook(bookCreationDto);

        assertEquals(4L, created.getId());
        assertEquals("FUTU.RE", created.getTitle());
        assertEquals(1L, created.getAuthorId());
    }

    @Test
    @DirtiesContext
    void createBookFails_whenAuthorWithGivenIdDoesNotExist() {
        BookCreationDto bookCreationDto = new BookCreationDto("Divergent", new Date(), 2L);

        assertThrows(AuthorDoesNotExistException.class, () -> bookService.createBook(bookCreationDto));
        assertEquals(3, bookRepository.findAll().size());
    }

    @Test
    @DirtiesContext
    void updateBook() {
        BookCreationDto bookCreationDto = new BookCreationDto("FUTU.RE", new Date(), 1L);

        BookDto bookDto = bookService.updateBook(1L, bookCreationDto);

        BookDto book = bookService.getBook(1L);

        assertEquals(book.getTitle(), bookDto.getTitle());
        assertEquals(book.getAuthorId(), bookDto.getAuthorId());
    }

    @Test
    @DirtiesContext
    void updateBookFails_whenBookWithGivenIdDoesNotExist() {
        BookCreationDto bookCreationDto = new BookCreationDto("FUTU.RE", new Date(), 1L);

        assertThrows(BookDoesNotExistException.class, () -> bookService.updateBook(4L, bookCreationDto));
    }

    @Test
    @DirtiesContext
    void updateBookFails_whenAuthorWithGivenIdDoesNotExist() {
        BookCreationDto bookCreationDto = new BookCreationDto("FUTU.RE", new Date(), 2L);

        assertThrows(AuthorDoesNotExistException.class, () -> bookService.updateBook(2L, bookCreationDto));
    }

    @Test
    @DirtiesContext
    void deleteBook() {
        assertEquals(bookRepository.findAll().size(), 3);

        bookService.deleteBook(1L);

        List<BookDto> books = bookRepository.findAll().stream().map(bookMapper::toDto).collect(Collectors.toList());

        assertEquals(books.size(), 2);
        assertFalse(books.stream().anyMatch(book -> book.getId() == 1L));
    }

    @Test
    @DirtiesContext
    void deleteBookFails_whenBookWithGivenIdDoesNotExist() {
        assertThrows(BookDoesNotExistException.class, () -> bookService.deleteBook(5L));
    }
}