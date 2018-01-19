package servlets;

import services.AuthorizeUser;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by Dmitriy Yurkin on 18.01.2018.
 */
public class Authorization extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/login.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        try {
            if(AuthorizeUser.authorizeUser(login, password)){
                req.getSession().setAttribute("login",login);
                resp.sendRedirect("/dashboard.jsp");
            } else {
                req.getRequestDispatcher("/login.jsp?error=invalidauth").forward(req,resp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}