package me.majkelmichel.server.book;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * Represents incoming JSON objects for creating new book records and updating existing ones
 */
public class BookCreationDto implements Serializable {
    private final String title;
    private final Date publicationDate;
    private final Long authorId;

    public BookCreationDto(String title, Date publicationDate, Long authorId) {
        this.title = title;
        this.publicationDate = publicationDate;
        this.authorId = authorId;
    }

    public String getTitle() {
        return title;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public Long getAuthorId() {
        return authorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookCreationDto entity = (BookCreationDto) o;
        return Objects.equals(this.title, entity.title) &&
                Objects.equals(this.publicationDate, entity.publicationDate) &&
                Objects.equals(this.authorId, entity.authorId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, publicationDate, authorId);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "title = " + title + ", " +
                "publicationDate = " + publicationDate + ", " +
                "authorId = " + authorId + ")";
    }
}
