package controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import services.GetAllBooks;

import javax.servlet.http.HttpServlet;

/**
 * Created by Dmitriy Yurkin on 17.01.2018.
 */
@Controller
public class ShowAllBooks extends HttpServlet {
    private static final Logger logger = Logger.getLogger(ShowAllBooks.class);

    @Autowired
    private GetAllBooks getAllBooks;

    public GetAllBooks getGetAllBooks() {
        return getAllBooks;
    }

    public void setGetAllBooks(GetAllBooks getAllBooks) {
        this.getAllBooks = getAllBooks;
    }

    @RequestMapping (value = "inner/showBooks", method = RequestMethod.GET)
    public ModelAndView getBooks() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("list", getAllBooks.getAllBooks());
        modelAndView.setViewName("inner/showBooks");
        return modelAndView;
    }
}