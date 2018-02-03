package db.entities;

import javax.persistence.*;

/**
 * Created by Dmitriy Yurkin on 02.02.2018.
 */

@Entity
@Table(name = "books")
public class Book {
    private Long id;
    private String title;
    private String book_ref;
    private Author author;
    private Genre genre;

    public Book() {
    }

    @Id
    @SequenceGenerator(name = "hibernateSeq", sequenceName = "HIBERNATE_SEQUENCE")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hibernateSeq")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBook_ref() {
        return book_ref;
    }

    public void setBook_ref(String book_ref) {
        this.book_ref = book_ref;
    }

    @OneToOne(optional = false, cascade = CascadeType.ALL)
    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    @OneToOne(optional = false, cascade = CascadeType.ALL)
    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", book_ref='" + book_ref + '\'' +
                ", author=" + author +
                ", genre=" + genre +
                '}';
    }
}