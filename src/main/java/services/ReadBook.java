package services;

import db.dao.BooksDAO;
import db.exceptions.DAOException;
import db.pojo.Books;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * Created by Dmitriy Yurkin on 18.01.2018.
 */
@Service
public class ReadBook {
    private static final Logger logger = Logger.getLogger(GetAllBooks.class);

    @Autowired
    private BooksDAO booksDAO;

    /**
     * Находит указанную книгу в БД и возвращает её текст
     * @param bookTitle название книги
     * @return text текст книги
     */
    public String readBook(String bookTitle, int page) throws DAOException, IOException {
        Books book = booksDAO.findBookByTitle(bookTitle);
        StringBuilder text = new StringBuilder();
        List<String> stringList = Files.readAllLines(Paths.get(book.getBook_ref()));
        for (int i = 100 * page; i < 100 * page + 100; i++) {
            text.append(stringList.get(i));
        }
        return text.toString();
    }
}