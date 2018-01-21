package servlets;

import org.apache.log4j.Logger;
import services.AuthorizeUser;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by Dmitriy Yurkin on 18.01.2018.
 */
public class Authorization extends HttpServlet {
    private static final Logger logger = Logger.getLogger(Authorization.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/login.jsp").forward(req, resp);
        logger.debug("Пользователь открыл страницу авторизации");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        try {
            if (AuthorizeUser.authorizeUser(login, password)) {
                HttpSession session = req.getSession();
                session.setAttribute("login", login);
                if (login.equals("Admin")) {
                    resp.sendRedirect("/adminmenu");
                    logger.debug("Администратор авторизовался");
                } else {
                    resp.sendRedirect("/usermenu.jsp");
                    logger.debug("Пользователь авторизовался");
                }
            }
            else {
                req.getRequestDispatcher("/errorLogin.jsp").forward(req, resp);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }
}