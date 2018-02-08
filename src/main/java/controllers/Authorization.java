package controllers;

import db.exceptions.DAOException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import services.AuthorizeUser;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Dmitriy Yurkin on 18.01.2018.
 */
@Controller
public class Authorization {
    private static final Logger logger = Logger.getLogger(Authorization.class);
    private AuthorizeUser authorizeUser;

    public AuthorizeUser getAuthorizeUser() {
        return authorizeUser;
    }
    @Autowired
    public void setAuthorizeUser(AuthorizeUser authorizeUser) {
        this.authorizeUser = authorizeUser;
    }

    @RequestMapping(value = "public/login", method = RequestMethod.GET)
    public void login() {
        logger.debug("Пользователь открыл страницу авторизации");
    }

    @RequestMapping(value = "public/login", method = RequestMethod.POST)
    public ModelAndView auth(@RequestParam(value = "username") String login,
                             @RequestParam(value = "password") String password,
                             HttpServletRequest request) throws DAOException {
        ModelAndView modelAndView = new ModelAndView();
        boolean result = authorizeUser.authorizeUser(login, password);
        if (result) {
            request.getSession().setAttribute("username", login);
            if (login.equals("Admin")) {
                modelAndView.setViewName("/inner/adminmenu");
                logger.debug("Администратор авторизовался");
                return modelAndView;
            } else {
                modelAndView.setViewName("/inner/usermenu");
                logger.debug("Пользователь авторизовался");
                return modelAndView;
            }
        } else {
            logger.debug("Неправильный логин или пароль");
            modelAndView.addObject("loginError", "Некорректный логин или пароль");
            modelAndView.setViewName("public/login");
            return modelAndView;
        }
    }

    @ExceptionHandler(DAOException.class)
    public ModelAndView handleDBException(){
        ModelAndView modelAndView = new ModelAndView("inner/errorpage");
        return modelAndView;
    }
}