package services;

import dao.BooksDAO;
import dao.BooksDAOImpl;
import org.apache.log4j.Logger;
import pojo.Books;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * Created by Dmitriy Yurkin on 18.01.2018.
 */

public class ReadBook {
    private static final Logger logger = Logger.getLogger(GetAllBooks.class);
    private static BooksDAO booksDAO = new BooksDAOImpl();

    public static String readBook(String bookTitle) {
        Books book = booksDAO.findBookByTitle(bookTitle);
        String text = "";
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(book.getBook_ref()), StandardCharsets.UTF_8))){
            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                text = sb.append(line).toString();
            }
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return text;
    }
}