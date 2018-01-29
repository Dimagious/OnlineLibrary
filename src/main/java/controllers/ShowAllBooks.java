package controllers;

import db.pojo.Books;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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
@Controller
@RequestMapping("/showBooks")
public class ShowAllBooks extends HttpServlet {
    private static final Logger logger = Logger.getLogger(ShowAllBooks.class);
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            List<Books> list = GetAllBooks.getAllBooks();
            req.setAttribute("list", list);
            req.getRequestDispatcher("WEB-INF/pages/showBooks.jsp").forward(req, resp);
        } catch (ServletException e) {
            logger.error(e.getMessage());
        }
    }
}