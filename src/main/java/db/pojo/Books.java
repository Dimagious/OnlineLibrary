package db.pojo;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Dmitriy Yurkin on 10.01.2018.
 */

@XmlType(propOrder = { "id", "title", "author_id", "authors", "genre_id", "genres", "book_ref"}, name = "All Books")
@XmlRootElement
public class Books {
    private int id;
    private String title;
    private Authors authors;
    private Genres genres;
    private int author_id;
    private int genre_id;
    private String book_ref;

    public Books(int id, String title, int author_id, int genre_id, String book_ref) {
        this.id = id;
        this.title = title;
        this.author_id = author_id;
        this.genre_id = genre_id;
        this.book_ref = book_ref;
    }

    public Books(String title, int author_id) {
        this.title = title;
        this.author_id = author_id;
    }

    public Books(String title) {
        this.title = title;
    }

    public Books(int id, String title, Authors authors, Genres genres, int author_id, int genre_id, String book_ref) {
        this.id = id;
        this.title = title;
        this.authors = authors;
        this.genres = genres;
        this.author_id = author_id;
        this.genre_id = genre_id;
        this.book_ref = book_ref;
    }

    public Books(String title, int author_id, int genre_id, String book_ref) {
        this.title = title;
        this.author_id = author_id;
        this.genre_id = genre_id;
        this.book_ref = book_ref;

    }

    public Books(String title, String book_ref) {
        this.title = title;
        this.book_ref = book_ref;
    }

    public String getBook_ref() {
        return book_ref;
    }

    @XmlElement
    public void setBook_ref(String book_ref) {
        this.book_ref = book_ref;
    }

    public Books(int id, String title, int author_id) {
        this.id = id;
        this.title = title;
        this.author_id = author_id;
    }

    public int getId() {
        return id;
    }

    @XmlAttribute
    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    @XmlElement
    public void setTitle(String title) {
        this.title = title;
    }

    public int getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(int author_id) {
        this.author_id = author_id;
    }

    public int getGenre_id() {
        return genre_id;
    }

    public Books() {
    }

    @Override

    public String toString() {
        return "Books{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", authors=" + authors +
                ", genres=" + genres +
                ", author_id=" + author_id +
                ", genre_id=" + genre_id +
                ", book_ref='" + book_ref + '\'' +
                '}';
    }

    public Books(int id, String title, Authors authors, Genres genres, String book_ref) {
        this.id = id;
        this.title = title;
        this.authors = authors;
        this.genres = genres;
        this.book_ref = book_ref;
    }

    public void setGenre_id(int genre_id) {
        this.genre_id = genre_id;
    }

    public Authors getAuthors() {
        return authors;
    }

    @XmlElement
    public void setAuthors(Authors authors) {
        this.authors = authors;
    }

    public Genres getGenres() {
        return genres;
    }

    @XmlElement
    public void setGenres(Genres genres) {
        this.genres = genres;
    }

    @XmlRootElement
    public static class BooksWrapper {
        @XmlElement
        private Set<Books> booksList = new HashSet<>();

        public Set<Books> getBooks() {
            return booksList;
        }

        @XmlElement
        public void setBooks(Set<Books> books) {
            this.booksList = books;
        }

        public void add(Books books) {
            booksList.add(books);
        }
    }
}