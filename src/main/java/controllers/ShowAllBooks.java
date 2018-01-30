package controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import services.GetAllBooks;

import javax.servlet.http.HttpServlet;
import java.awt.print.Book;

/**
 * Created by Dmitriy Yurkin on 17.01.2018.
 */
@Controller
public class ShowAllBooks extends HttpServlet {
    private static final Logger logger = Logger.getLogger(ShowAllBooks.class);

    @Autowired
    private GetAllBooks getAllBooks;

    @RequestMapping (value = "/showBooks", method = RequestMethod.GET)
    public ModelAndView getAllBooks() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("list", new Book());
        modelAndView.setViewName("showBooks");
        return modelAndView;
    }
}