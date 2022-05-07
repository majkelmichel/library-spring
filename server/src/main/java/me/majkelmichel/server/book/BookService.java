package me.majkelmichel.server.book;

import me.majkelmichel.server.author.Author;
import me.majkelmichel.server.author.AuthorRepository;
import me.majkelmichel.server.exceptions.AuthorDoesNotExistException;
import me.majkelmichel.server.exceptions.BookDoesNotExistException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final Logger LOGGER = LoggerFactory.getLogger(BookService.class);

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final AuthorRepository authorRepository;


    public BookService(BookRepository bookRepository, BookMapper bookMapper, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
        this.authorRepository = authorRepository;
    }

    public List<BookDto> getBooks() {
        return bookRepository.findAll()
                .stream().map(bookMapper::toDto)
                .collect(Collectors.toList());
    }

    public BookDto getBook(Long id) {
        return bookMapper.toDto(bookRepository.findById(id).orElseThrow(() -> new BookDoesNotExistException(id)));
    }


    /**
     * Creates new book and saves it to the database.
     *
     * @param bookCreationDto Book DTO received in request
     * @return Saved book
     * @throws AuthorDoesNotExistException If author with given id does not exist, throws AuthorDoesNotExistException.
     */
    public BookDto createBook(BookCreationDto bookCreationDto) {
        Author author = authorRepository
                .findById(bookCreationDto.getAuthorId())
                .orElseThrow(() -> new AuthorDoesNotExistException(bookCreationDto.getAuthorId()));

        Book book = bookMapper.toBook(bookCreationDto, author);

        Book created = bookRepository.save(book);

        LOGGER.info("Created book with id=" + created.getId());

        return bookMapper.toDto(created);
    }

    public BookDto updateBook(Long id, BookCreationDto updatedBook) {
        Book book = bookRepository
                .findById(id)
                .orElseThrow(() -> new BookDoesNotExistException(updatedBook.getAuthorId()));

        Author author = authorRepository
                .findById(updatedBook.getAuthorId())
                .orElseThrow(() -> new AuthorDoesNotExistException(updatedBook.getAuthorId()));

        book.setTitle(updatedBook.getTitle());
        book.setAuthor(author);
        book.setPublicationDate(updatedBook.getPublicationDate());

        Book updated = bookRepository.save(book);

        LOGGER.info("Updated book with id=" + updated.getId());

        return bookMapper.toDto(updated);
    }

    public void deleteBook(Long id) {
        if (!bookRepository.existsById(id)) {
            LOGGER.warn("Failed deleting book with id=" + id);
            throw new BookDoesNotExistException(id);
        }
        bookRepository.deleteById(id);
    }
}
