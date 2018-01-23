package servlets;

import org.apache.log4j.Logger;
import services.ReadBook;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Dmitriy Yurkin on 18.01.2018.
 */
public class ReadingBook  extends HttpServlet {
    private static final Logger logger = Logger.getLogger(ReadingBook.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("book", ReadBook.readBook(req.getParameter("title"), Integer.parseInt(req.getParameter("page"))));
        req.setAttribute("page", Integer.parseInt(req.getParameter("page")));
        req.setAttribute("title", req.getParameter("title"));
        req.getRequestDispatcher("/readbook.jsp").forward(req, resp);
        logger.debug("Пользователь открыл книгу");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
