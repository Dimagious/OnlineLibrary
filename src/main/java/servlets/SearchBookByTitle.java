package servlets;

import pojo.Books;
import services.SearchBook;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by Dmitriy Yurkin on 18.01.2018.
 */
public class SearchBookByTitle extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String bookTitle = req.getParameter("searchByTitle");
        try {
            Books foundedBook = SearchBook.getBookByTitle(bookTitle);
            req.setAttribute("bookTitle", foundedBook);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        req.getRequestDispatcher("/searchbooksbytitle").forward(req, resp);
    }
}