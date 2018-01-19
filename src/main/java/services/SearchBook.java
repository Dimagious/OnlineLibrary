package services;

import dao.BooksDAO;
import dao.BooksDAOImpl;
import pojo.Books;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Dmitriy Yurkin on 18.01.2018.
 */
public class SearchBook {
    /**
     * Выполняет поиск указанной книги и возвращает её
     *
     * @param bookTitle название книги
     */
    public static Books getBookByTitle(String bookTitle) throws SQLException {
        BooksDAO booksDAO = new BooksDAOImpl();
        return booksDAO.findBookByTitle(bookTitle);
    }
    /**
     * Выполняет поиск книг указанного автора и возвращает список
     *
     * @param authorLastName фамилия автора
     */
    public static List<Books> getAllAuthorBooks(String authorLastName) throws SQLException {
        BooksDAO booksDAO = new BooksDAOImpl();
        return booksDAO.getBooksByAuthorsLastname(authorLastName);
    }
    /**
     * Выполняет поиск книг по указанному жанру и возвращает список
     *
     * @param genreName название жанра
     */
    public static List<Books> getAllGenresBooks(String genreName) throws SQLException {
        BooksDAO booksDAO = new BooksDAOImpl();
        return booksDAO.getBooksByGenre(genreName);
    }
}