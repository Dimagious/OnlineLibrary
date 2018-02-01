package services;

import db.dao.BooksDAO;
import db.pojo.Books;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dmitriy Yurkin on 17.01.2018.
 */
@Service
public class GetAllBooks {
    private static final Logger logger = Logger.getLogger(GetAllBooks.class);
    private BooksDAO booksDAO;

    public BooksDAO getBooksDAO() {
        return booksDAO;
    }

    @Autowired
    public void setBooksDAO(BooksDAO booksDAO) {
        this.booksDAO = booksDAO;
    }

    /**
     * Сервис, который берёт из БД все книги и возвращает лист из всех книг
     *
     * @return booklist список всех книг
     */
    public List<Books> getAllBooks() {
        try {
            List<Books> bookList = new ArrayList<>(booksDAO.getAllBooks());
            return bookList;
        } catch (SQLException e) {
            logger.debug(e.getMessage());
            return null;
        }
    }
}