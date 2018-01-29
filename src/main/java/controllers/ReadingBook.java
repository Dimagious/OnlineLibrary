package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import services.ReadBook;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Dmitriy Yurkin on 18.01.2018.
 */
@Controller
@RequestMapping("/readbook")
public class ReadingBook  extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("book", ReadBook.readBook(req.getParameter("title"), Integer.parseInt(req.getParameter("page"))));
        req.setAttribute("page", Integer.parseInt(req.getParameter("page")));
        req.setAttribute("title", req.getParameter("title"));
        req.getRequestDispatcher("WEB-INF/pages/readbook.jsp").forward(req, resp);
    }
}
