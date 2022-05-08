package me.majkelmichel.server.book;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class BookDto implements Serializable {
    private final Long id;
    private final String title;
    private final Date publicationDate;
    private final Long authorId;

    /**
     * @param id Long
     * @param title String
     * @param publicationDate Date
     * @param authorId Long | id of book author
     */
    public BookDto(Long id, String title, Date publicationDate, Long authorId) {
        this.id = id;
        this.title = title;
        this.publicationDate = publicationDate;
        this.authorId = authorId;
    }

    public Long getId() {
        return id;
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
        BookDto entity = (BookDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.title, entity.title) &&
                Objects.equals(this.publicationDate, entity.publicationDate) &&
                Objects.equals(this.authorId, entity.authorId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, publicationDate, authorId);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "title = " + title + ", " +
                "publicationDate = " + publicationDate + ", " +
                "authorId = " + authorId + ")";
    }
}
