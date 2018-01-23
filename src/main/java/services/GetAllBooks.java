package services;

import dao.BooksDAO;
import dao.BooksDAOImpl;
import org.apache.log4j.Logger;
import pojo.Books;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dmitriy Yurkin on 17.01.2018.
 */

public class GetAllBooks {
    private static final Logger logger = Logger.getLogger(GetAllBooks.class);

    /**
     * Сервис, который берёт из БД все книги и возвращает лист из всех книг
     *
     * @return booklist список всех книг
     */
    public static List<Books> getAllBooks() {
        try {
            BooksDAO booksDAO = new BooksDAOImpl();
            List<Books> bookList = new ArrayList<>(booksDAO.getAllBooks());
            return bookList;
        } catch (SQLException e) {
            logger.debug(e.getMessage());
            return null;
        }
    }
}