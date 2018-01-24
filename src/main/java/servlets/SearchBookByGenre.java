package servlets;

import org.apache.log4j.Logger;
import db.pojo.Books;
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
public class SearchBookByGenre extends HttpServlet {
    private static final Logger logger = Logger.getLogger(SearchBookByGenre.class);
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String genreName = req.getParameter("searchByGenre");
        try {
            List<Books> list = SearchBook.getAllGenresBooks(genreName);
            req.setAttribute("list", list);
            req.getRequestDispatcher("/searchBookByGenre.jsp").forward(req, resp);
            logger.debug("Пользователь выполнил поиск по жанру");
        } catch (SQLException ex) {
            logger.error(ex.getMessage());
            req.getRequestDispatcher("/errorpage.jsp").forward(req, resp);

        }
    }
}