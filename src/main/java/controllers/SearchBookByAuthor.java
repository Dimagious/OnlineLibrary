package controllers;

import db.exceptions.DAOException;
import db.pojo.Books;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import services.SearchBook;

import java.util.List;

/**
 * Created by Dmitriy Yurkin on 18.01.2018.
 */
@Controller
public class SearchBookByAuthor {
    private static final Logger logger = Logger.getLogger(SearchBookByAuthor.class);

    @Autowired
    private SearchBook searchBook;

    public SearchBook getSearchBook() {
        return searchBook;
    }

    public void setSearchBook(SearchBook searchBook) {
        this.searchBook = searchBook;
    }

    @RequestMapping(value = "inner/searchBooksByAuthor", method = RequestMethod.GET)
    public ModelAndView getAuthorsBooks(@RequestParam(value = "searchByAuthor", required = false) String authorlastname) throws DAOException {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("inner/searchBookByAuthor");
        logger.debug("Пользователь выполнил поиск по автору");
        List<Books> list = searchBook.getAllAuthorBooks(authorlastname);
        if (list != null) {
            modelAndView.addObject("list", list);
            return modelAndView;
        }
        modelAndView.setViewName("inner/errorpage");
        return modelAndView;
    }

    @ExceptionHandler(DAOException.class)
    public ModelAndView handleDBException(){
        ModelAndView modelAndView = new ModelAndView("inner/errorpage");
        return modelAndView;
    }
}