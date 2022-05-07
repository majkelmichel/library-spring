package me.majkelmichel.server.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AuthorDoesNotExistException extends RuntimeException {
    public AuthorDoesNotExistException(Long id) {
        super(String.format("Author with id: %s does not exist", id));
    }
}
