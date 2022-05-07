package me.majkelmichel.server.author;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class AuthorDto implements Serializable {
    private final Long id;
    private final String firstName;
    private final String lastName;
    private final String nationality;
    private final Date birthDate;

    public AuthorDto(Long id, String firstName, String lastName, String nationality, Date birthDate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nationality = nationality;
        this.birthDate = birthDate;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthorDto entity = (AuthorDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.firstName, entity.firstName) &&
                Objects.equals(this.lastName, entity.lastName) &&
                Objects.equals(this.nationality, entity.nationality) &&
                Objects.equals(this.birthDate, entity.birthDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, nationality, birthDate);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "firstName = " + firstName + ", " +
                "lastName = " + lastName + ", " +
                "nationality = " + nationality + ", " +
                "birthDate = " + birthDate + ")";
    }
}
