package servlets;

import org.apache.log4j.Logger;
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
    private static final Logger logger = Logger.getLogger(SearchBookByTitle.class);
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String bookTitle = req.getParameter("searchByTitle");
        try {
            Books foundedBook = SearchBook.getBookByTitle(bookTitle);
            req.setAttribute("bookTitle", foundedBook);
            req.getRequestDispatcher("/searchbooksbytitle").forward(req, resp);
            logger.debug("Пользователь выполнил поиск по названию");
        } catch (SQLException ex) {
            logger.error(ex.getMessage());
        }
    }
}