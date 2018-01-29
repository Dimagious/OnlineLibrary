package controllers;

import db.pojo.Books;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import services.SearchBook;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Dmitriy Yurkin on 18.01.2018.
 */
@Controller
@RequestMapping("/searchBookByTitle")
public class SearchBookByTitle extends HttpServlet {
    private static final Logger logger = Logger.getLogger(SearchBookByTitle.class);

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String bookTitle = req.getParameter("searchByTitle");
        Books foundedBook = SearchBook.getBookByTitle(bookTitle);
        req.setAttribute("foundedBook", foundedBook);
        req.getRequestDispatcher("WEB-INF/pages/searchBookByTitle.jsp").forward(req, resp);
        logger.debug("Пользователь выполнил поиск по названию");
    }
}