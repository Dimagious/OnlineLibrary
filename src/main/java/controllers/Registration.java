package controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import services.RegisterUser;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Dmitriy Yurkin on 18.01.2018.
 */
@Controller
public class Registration extends HttpServlet {
    private static final Logger logger = Logger.getLogger(Registration.class);

    @Autowired
    private RegisterUser registerUser;

    public RegisterUser getRegisterUser() {
        return registerUser;
    }

    public void setRegisterUser(RegisterUser registerUser) {
        this.registerUser = registerUser;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public ModelAndView getRegister() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/registration");
        logger.debug("Пользователь открыл страницу регистрации");
        return modelAndView;
    }
//
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        resp.setCharacterEncoding("CP1251");
//        req.setCharacterEncoding("UTF-8");
//        req.getRequestDispatcher("WEB-INF/pages/registration.jsp").forward(req, resp);
//    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("CP1251");
        req.setCharacterEncoding("UTF-8");
        String firstname = req.getParameter("firstname");
        String lastname = req.getParameter("lastname");
        String sex = req.getParameter("sex");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        if (RegisterUser.registerUser(firstname, lastname, sex, login, password)) {
            resp.sendRedirect("WEB-INF/pages/login.jsp");
            logger.debug("Пользователь зарегистрировался");
        } else {
            req.setAttribute("loginError", "Указанный логин уже используется");
            req.getRequestDispatcher("WEB-INF/pages/registration.jsp").forward(req, resp);
        }
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView register(@RequestParam(value = "firstname", required = false) String firstname,
                                 @RequestParam(value = "lastname", required = false) String lastname,
                                 @RequestParam(value = "sex", required = false) String sex,
                                 @RequestParam(value = "login", required = false) String login,
                                 @RequestParam(value = "password", required = false) String password) {

        boolean result = RegisterUser.registerUser(firstname, lastname, sex, login,password);
        ModelAndView modelAndView = new ModelAndView();
        if (result) {
            modelAndView.setViewName("/login");
            logger.debug("Пользователь зарегистрировался");
            return modelAndView;
        }

//        modelAndView. ("loginError", "Указанный логин уже используется");
        modelAndView.setViewName("/register");
        return modelAndView;
    }
}