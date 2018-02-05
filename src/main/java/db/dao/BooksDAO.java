package db.dao;

import db.exceptions.DAOException;
import db.pojo.Authors;
import db.pojo.Books;
import db.pojo.Genres;
import java.sql.SQLException;
import java.util.List;
/**
 * Created by Dmitriy Yurkin on 11.01.2018.
 */
public interface BooksDAO {
    List<Books> getAllBooks() throws DAOException;
    List<Books> getBooksByAuthorsLastname(String authorslastname) throws DAOException;
    List<Books> getBooksByGenre(String genre) throws DAOException;
    void deleteBookByTitle(String title) throws DAOException;
    Books findBookByTitle(String title) throws DAOException;
    Authors saveAuthor(String first_name, String last_name) throws DAOException;
    int getAuthorId(String first_name, String last_name) throws DAOException;
    Genres saveGenre(String name) throws DAOException;
    int getGenreId(String name) throws DAOException;
    Books saveBook(String title, String first_name, String last_name, String name, String book_ref) throws DAOException;
}
