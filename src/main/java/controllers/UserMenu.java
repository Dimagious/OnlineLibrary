package controllers;

import db.exceptions.DAOException;
import db.pojo.UserData;
import org.apache.log4j.Logger;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServlet;

/**
 * Created by Dmitriy Yurkin on 19.01.2018.
 */

@Controller
public class UserMenu extends HttpServlet {
    private static final Logger logger = Logger.getLogger(UserMenu.class);

    @RequestMapping(value = "inner/usermenu", method = RequestMethod.GET)
    public ModelAndView getUserMenu() {
        ModelAndView modelAndView = new ModelAndView();
        SimpleGrantedAuthority roleAdmin =
                new SimpleGrantedAuthority("ROLE_ADMIN");
        //((UserData)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getLogin();
        if (SecurityContextHolder.getContext().getAuthentication().getAuthorities().contains(roleAdmin)) {
            modelAndView.setViewName("admin/adminmenu");
        } else {
            modelAndView.setViewName("inner/usermenu");
        }
        logger.debug("Пользователь открыл читальный зал");
        return modelAndView;
    }

    @ExceptionHandler(DAOException.class)
    public ModelAndView handleDBException(){
        ModelAndView modelAndView = new ModelAndView("inner/errorpage");
        return modelAndView;
    }
}