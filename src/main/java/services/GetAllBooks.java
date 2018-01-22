package services;

import dao.BooksDAO;
import dao.BooksDAOImpl;
import pojo.Books;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dmitriy Yurkin on 17.01.2018.
 */
public class GetAllBooks {
    public static List<Books> getAllBooks() throws SQLException {
        BooksDAO booksDAO = new BooksDAOImpl();
        List<Books> bookList = new ArrayList<>();
        for (Books book : booksDAO.getAllBooks()) {
//            bookList.add("Название книги: " + book.getTitle() +
//                    ", Автор: " + book.getAuthors().getFirst_name() + " " + book.getAuthors().getLast_name() +
//                    ", Жанр: " + book.getGenres().getName());
            bookList.add(book);
        }
        return bookList;
    }
}