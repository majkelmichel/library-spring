package me.majkelmichel.server.author;

import me.majkelmichel.server.exceptions.AuthorDoesNotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorService {



    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    public AuthorService(AuthorRepository authorRepository, AuthorMapper authorMapper) {
        this.authorRepository = authorRepository;
        this.authorMapper = authorMapper;
    }

    List<AuthorDto> getAuthors() {
        return authorRepository.findAll()
                .stream().map(authorMapper::toDto)
                .collect(Collectors.toList());
    }

    AuthorDto getAuthor(Long id) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new AuthorDoesNotExistException(id));

        return authorMapper.toDto(author);
    }
}
