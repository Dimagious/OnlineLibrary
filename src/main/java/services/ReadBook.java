package services;

import db.dao.BooksDAO;
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
    private static BooksDAO booksDAO;

    /**
     * Находит указанную книгу в БД и возвращает её текст
     *
     * @param bookTitle название книги
     * @return text текст книги
     */
    public static String readBook(String bookTitle, int page) {
        Books book = booksDAO.findBookByTitle(bookTitle);
        StringBuilder text = new StringBuilder();
        List<String> stringList = null;
        try {
            stringList = Files.readAllLines(Paths.get(book.getBook_ref()));
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        for (int i = 100 * page; i < 100 * page + 100; i++) {
//            if (i<100) {
//                text.append(stringList.get(100-(i-100)));
//            } else if (page<0) {
//
//            } else {
                text.append(stringList.get(i));
//            }
        }
        return text.toString();
    }
}