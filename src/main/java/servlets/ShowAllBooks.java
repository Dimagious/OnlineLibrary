package servlets;

import pojo.Books;
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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        GetAllBooks allBooks = new GetAllBooks();
        try {
            List<Books> list = GetAllBooks.getAllBooks();
            req.setAttribute("list", list);
            req.getRequestDispatcher("/showBooks.jsp").forward(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }
}