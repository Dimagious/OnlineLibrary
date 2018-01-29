package controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import services.AuthorizeUser;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Dmitriy Yurkin on 18.01.2018.
 */
@Controller
public class Authorization extends HttpServlet {
    private static final Logger logger = Logger.getLogger(Authorization.class);

    @Autowired
    private AuthorizeUser authorizeUser;

    public AuthorizeUser getAuthorizeUser() {
        return authorizeUser;
    }

    public void setAuthorizeUser(AuthorizeUser authorizeUser) {
        this.authorizeUser = authorizeUser;
    }


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/pages/login.jsp").forward(req, resp);
        logger.debug("Пользователь открыл страницу авторизации");
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        if (AuthorizeUser.authorizeUser(login, password)) {
            HttpSession session = req.getSession();
            session.setAttribute("login", login);
            if (login.equals("Admin")) {
                resp.sendRedirect("WEB-INF/pages/adminmenu.jsp");
                logger.debug("Администратор авторизовался");
            } else {
                resp.sendRedirect("WEB-INF/pages/usermenu.jsp");
                logger.debug("Пользователь авторизовался");
            }
        }
        else {
            logger.debug("Неправильный логин или пароль");
            req.setAttribute("loginError", "Неправильный логин или пароль");
            req.getRequestDispatcher("WEB-INF/pages/login.jsp").forward(req, resp);
        }
    }
}