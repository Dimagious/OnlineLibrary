package db.dao;

import db.pojo.Authors;
import db.pojo.Books;
import db.pojo.Genres;
import java.sql.SQLException;
import java.util.List;
/**
 * Created by Dmitriy Yurkin on 11.01.2018.
 */
public interface BooksDAO {
    List<Books> getAllBooks() throws SQLException;
    List<Books> getBooksByAuthorsLastname(String authorslastname) throws SQLException;
    List<Books> getBooksByGenre(String genre) throws SQLException;
    void deleteBookByTitle(String title) throws SQLException;
    Books findBookByTitle(String title);
    Authors saveAuthor(String first_name, String last_name) throws SQLException;
    int getAuthorId(String first_name, String last_name) throws SQLException;
    Genres saveGenre(String name) throws SQLException;
    int getGenreId(String name) throws SQLException;
    Books saveBook(String title, String first_name, String last_name, String name, String book_ref) throws SQLException;
}
