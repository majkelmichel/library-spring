package me.majkelmichel.server.author;

import me.majkelmichel.server.book.BookDto;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

public class AuthorWithBooksDto implements Serializable {
    private final Long id;
    private final String firstName;
    private final String lastName;
    private final String nationality;
    private final Date birthDate;
    private final Set<BookDto> books;

    public AuthorWithBooksDto(Long id, String firstName, String lastName, String nationality, Date birthDate, Set<BookDto> books) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nationality = nationality;
        this.birthDate = birthDate;
        this.books = books;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getNationality() {
        return nationality;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public Set<BookDto> getBooks() {
        return books;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthorWithBooksDto entity = (AuthorWithBooksDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.firstName, entity.firstName) &&
                Objects.equals(this.lastName, entity.lastName) &&
                Objects.equals(this.nationality, entity.nationality) &&
                Objects.equals(this.birthDate, entity.birthDate) &&
                Objects.equals(this.books, entity.books);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, nationality, birthDate, books);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "firstName = " + firstName + ", " +
                "lastName = " + lastName + ", " +
                "nationality = " + nationality + ", " +
                "birthDate = " + birthDate + ", " +
                "books = " + books + ")";
    }
}
