package servlets;

import org.apache.log4j.Logger;
import services.RegisterUser;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by Dmitriy Yurkin on 18.01.2018.
 */

public class Registration extends HttpServlet {
    private static final Logger logger = Logger.getLogger(Registration.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("CP1251");
        req.setCharacterEncoding("UTF-8");
        req.getRequestDispatcher("/registration.jsp").forward(req,resp);
        logger.debug("Пользователь открыл страницу регистрации");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("CP1251");
        req.setCharacterEncoding("UTF-8");
        String firstname = req.getParameter("firstname");
        String lastname = req.getParameter("lastname");
        String sex = req.getParameter("sex");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        try {
            RegisterUser.registerUser(firstname, lastname, sex, login, password);
            logger.debug("Пользователь зарегистрирован");
        } catch (SQLException ex) {
            logger.error(ex.getMessage());
        }
        resp.sendRedirect("/login.jsp");
    }
}