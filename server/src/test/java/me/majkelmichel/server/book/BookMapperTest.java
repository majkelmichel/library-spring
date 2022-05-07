package me.majkelmichel.server.book;

import me.majkelmichel.server.author.Author;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;


class BookMapperTest {

    BookMapper bookMapper = new BookMapper();

    @Test
    void toDto() {
        Author author = new Author(1L);
        Book book = new Book(1L, "Divergent", new Date(2000000L), author);
        BookDto expected = new BookDto(1L, "Divergent", new Date(2000000L), 1L);

        BookDto result = bookMapper.toDto(book);

        assertEquals(result, expected);
    }

    @Test
    void toBook() {
        Author author = new Author(1L);
        BookCreationDto bookCreationDto = new BookCreationDto("Divergent", new Date(2000000L), 1L);
        Book expected = new Book(1L, "Divergent", new Date(2000000L), author);

        Book result = bookMapper.toBook(bookCreationDto, author);
        result.setId(1L); // ID is usually set in DB, so we need to set in manually

        assertEquals(result, expected);
    }
}