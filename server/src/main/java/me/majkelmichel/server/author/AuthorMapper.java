package me.majkelmichel.server.author;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuthorMapper {

    public AuthorDto toDto(Author author) {
        return new AuthorDto(author.getId(), author.getFirstName(), author.getLastName(), author.getNationality(), author.getBirthDate());
    }

    public Author toAuthor(AuthorCreationDto authorCreationDto) {
        Author author = new Author();
        author.setFirstName(authorCreationDto.getFirstName());
        author.setLastName(author.getLastName());
        author.setBirthDate(authorCreationDto.getBirthDate());
        author.setNationality(author.getNationality());
        return author;
    }
}
