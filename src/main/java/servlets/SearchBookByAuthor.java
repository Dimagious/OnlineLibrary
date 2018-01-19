package servlets;

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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String authorLastName = req.getParameter("searchByAuthor");
        try {
            List<Books> list = SearchBook.getAllAuthorBooks(authorLastName);
            req.setAttribute("list", list);
            req.getRequestDispatcher("/searchBookByAuthor.jsp").forward(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}