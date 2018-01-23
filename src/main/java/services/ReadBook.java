package services;

import dao.BooksDAO;
import dao.BooksDAOImpl;
import org.apache.log4j.Logger;
import pojo.Books;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * Created by Dmitriy Yurkin on 18.01.2018.
 */

public class ReadBook {
    private static final Logger logger = Logger.getLogger(GetAllBooks.class);
    private static BooksDAO booksDAO = new BooksDAOImpl();

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
            e.printStackTrace();
        }
        for (int i = 100 * page; i < 100 * page + 100; i++) {
            text.append(stringList.get(i));
        }
        return text.toString();
    }
}