package me.majkelmichel.server.book;

import me.majkelmichel.server.author.Author;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {

    public BookDto toDto(Book book) {
        return new BookDto(book.getId(), book.getTitle(), book.getPublicationDate(), book.getAuthor().getId());
    }

    public Book toBook(BookCreationDto bookDto, Author author) {
        Book book = new Book();
        book.setTitle(bookDto.getTitle());
        book.setPublicationDate(bookDto.getPublicationDate());
        book.setAuthor(author);
        return book;
    }
}
