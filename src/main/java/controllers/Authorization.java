package controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import services.AuthorizeUser;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Dmitriy Yurkin on 18.01.2018.
 */
@Controller
public class Authorization {
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
    public void login() {
        logger.debug("Пользователь открыл страницу авторизации");
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String auth(@RequestParam(value = "login") String login,
                       @RequestParam(value = "password") String password,
                       HttpServletRequest request,
                       Model model) {
        if (authorizeUser.authorizeUser(login, password)) {
            request.getSession().setAttribute("login", login);
            if (login.equals("Admin")) {
                logger.debug("Администратор авторизовался");
                return "redirect:/adminmenu";
            } else {
                logger.debug("Пользователь авторизовался");
                return "redirect:/usermenu";
            }
        } else {
            logger.debug("Неправильный логин или пароль");
            model.addAttribute("loginError", "Некорректный логин или пароль");
            return "redirect:/login";
        }
    }
}