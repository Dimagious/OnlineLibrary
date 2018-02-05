package services;

import db.dao.BooksDAO;
import db.exceptions.DAOException;
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

    private BooksDAO booksDAO;

    public BooksDAO getBooksDAO() {
        return booksDAO;
    }

    @Autowired
    public void setBooksDAO(BooksDAO booksDAO) {
        this.booksDAO = booksDAO;
    }

    /**
     * Выполняет поиск указанной книги и возвращает её
     *
     * @param bookTitle название книги
     */
    public Books getBookByTitle(String bookTitle) throws DAOException {
        return booksDAO.findBookByTitle(bookTitle);
    }

    /**
     * Выполняет поиск книг указанного автора и возвращает список
     *
     * @param authorLastName фамилия автора
     */
    public List<Books> getAllAuthorBooks(String authorLastName) throws DAOException {
        return booksDAO.getBooksByAuthorsLastname(authorLastName);
    }

    /**
     * Выполняет поиск книг по указанному жанру и возвращает список
     *
     * @param genreName название жанра
     */
    public List<Books> getAllGenresBooks(String genreName) throws DAOException {
        return booksDAO.getBooksByGenre(genreName);
    }
}