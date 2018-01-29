package servlets;

import db.pojo.Books;
import org.apache.log4j.Logger;
import services.GetAllBooks;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Dmitriy Yurkin on 17.01.2018.
 */
public class ShowAllBooks extends HttpServlet {
    private static final Logger logger = Logger.getLogger(ShowAllBooks.class);
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            List<Books> list = GetAllBooks.getAllBooks();
            req.setAttribute("list", list);
            req.getRequestDispatcher("/showBooks.jsp").forward(req, resp);
        } catch (ServletException e) {
            logger.error(e.getMessage());
        }
    }
}