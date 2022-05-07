package me.majkelmichel.server.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BookDoesNotExistException extends RuntimeException {

    public BookDoesNotExistException(Long id) {
        super(String.format("Book with id %d does not exist", id));
    }
}
