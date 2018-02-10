package controllers;

import db.exceptions.DAOException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import services.GetAllBooks;
import services.GetAllUsers;

import javax.servlet.http.HttpServlet;

/**
 * Created by Dmitriy Yurkin on 10.02.2018.
 */
@Controller
public class ShowAllUsers extends HttpServlet {
    private static final Logger logger = Logger.getLogger(ShowAllBooks.class);
    private GetAllUsers getAllUsers;

    public GetAllUsers getGetAllUsers() {
        return getAllUsers;
    }
    @Autowired
    public void setGetAllUsers(GetAllUsers getAllUsers) {
        this.getAllUsers = getAllUsers;
    }

    @RequestMapping(value = "admin/showUsers", method = RequestMethod.GET)
    public ModelAndView getUsers() throws DAOException {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("list", getAllUsers.getAllUsers());
        modelAndView.setViewName("admin/showUsers");
        return modelAndView;
    }

    @ExceptionHandler(DAOException.class)
    public ModelAndView handleDBException(){
        ModelAndView modelAndView = new ModelAndView("inner/errorpage");
        return modelAndView;
    }
}
