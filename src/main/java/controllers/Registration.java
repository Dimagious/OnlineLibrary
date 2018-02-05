package controllers;

import db.exceptions.DAOException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import services.RegisterUser;

/**
 * Created by Dmitriy Yurkin on 18.01.2018.
 */
@Controller
public class Registration {
    private static final Logger logger = Logger.getLogger(Registration.class);

    @Autowired
    private RegisterUser registerUser;

    public RegisterUser getRegisterUser() {
        return registerUser;
    }

    public void setRegisterUser(RegisterUser registerUser) {
        this.registerUser = registerUser;
    }

    @RequestMapping(value = "public/registration", method = RequestMethod.GET)
    public ModelAndView getRegister() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("public/registration");
        logger.debug("Пользователь открыл страницу регистрации");
        return modelAndView;
    }

    @RequestMapping(value = "public/registration", method = RequestMethod.POST)
    public ModelAndView register(@RequestParam(value = "firstname", required = false) String firstname,
                                 @RequestParam(value = "lastname", required = false) String lastname,
                                 @RequestParam(value = "sex", required = false) String sex,
                                 @RequestParam(value = "login", required = false) String login,
                                 @RequestParam(value = "password", required = false) String password) throws DAOException {
        boolean result = registerUser.registerUser(firstname, lastname, sex, login, password);
        ModelAndView modelAndView = new ModelAndView();
        if (result) {
            modelAndView.setViewName("public/login");
            logger.debug("Пользователь зарегистрировался");
            return modelAndView;
        } else {
            modelAndView.addObject("loginError", "Указанный логин уже используется");
            modelAndView.setViewName("public/registration");
            return modelAndView;
        }
    }
}