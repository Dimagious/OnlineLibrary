package services;

import db.dao.BooksDAO;
import db.pojo.Books;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Dmitriy Yurkin on 18.01.2018.
 */
@Service
public class SearchBook {

    @Autowired
    private static BooksDAO booksDAO;
    /**
     * Выполняет поиск указанной книги и возвращает её
     *
     * @param bookTitle название книги
     */
    public static Books getBookByTitle(String bookTitle) {
        return booksDAO.findBookByTitle(bookTitle);
    }

    /**
     * Выполняет поиск книг указанного автора и возвращает список
     *
     * @param authorLastName фамилия автора
     */
    public static List<Books> getAllAuthorBooks(String authorLastName) throws SQLException {
        return booksDAO.getBooksByAuthorsLastname(authorLastName);
    }

    /**
     * Выполняет поиск книг по указанному жанру и возвращает список
     *
     * @param genreName название жанра
     */
    public static List<Books> getAllGenresBooks(String genreName) throws SQLException {
        return booksDAO.getBooksByGenre(genreName);
    }
}