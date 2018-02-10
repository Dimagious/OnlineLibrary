package controllers;

import db.exceptions.DAOException;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Dmitriy Yurkin on 20.01.2018.
 */
@Controller
public class AdminMenu {
    private static final Logger logger = Logger.getLogger(AdminMenu.class);

    @RequestMapping(value = "/admin/adminmenu", method = RequestMethod.GET)
    public ModelAndView getAdminMenu() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/adminmenu");
        logger.debug("Адмнинистратор открыл читальный зал");
        return modelAndView;
    }

    @ExceptionHandler(DAOException.class)
    public ModelAndView handleDBException(){
        ModelAndView modelAndView = new ModelAndView("inner/errorpage");
        return modelAndView;
    }
}