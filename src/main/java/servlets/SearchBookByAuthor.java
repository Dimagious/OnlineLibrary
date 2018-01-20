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
import java.util.List;

/**
 * Created by Dmitriy Yurkin on 18.01.2018.
 */
public class SearchBookByAuthor extends HttpServlet {
    private static final Logger logger = Logger.getLogger(SearchBookByAuthor.class);
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String authorLastName = req.getParameter("searchByAuthor");
        try {
            List<Books> list = SearchBook.getAllAuthorBooks(authorLastName);
            req.setAttribute("list", list);
            req.getRequestDispatcher("/searchBookByAuthor.jsp").forward(req, resp);
            logger.debug("Пользователь выполнил поиск по автору");
        } catch (SQLException ex) {
            logger.error(ex.getMessage());
        }
    }
}