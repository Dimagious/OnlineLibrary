package controllers;

import db.exceptions.DAOException;
import db.pojo.Books;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import services.SearchBook;

/**
 * Created by Dmitriy Yurkin on 18.01.2018.
 */
@Controller
public class SearchBookByTitle {
    private static final Logger logger = Logger.getLogger(SearchBookByTitle.class);

    @Autowired
    private SearchBook searchBook;

    public SearchBook getSearchBook() {
        return searchBook;
    }

    public void setSearchBook(SearchBook searchBook) {
        this.searchBook = searchBook;
    }

    @RequestMapping(value = "inner/searchBookByTitle", method = RequestMethod.GET)
    public ModelAndView getGenresBooks(@RequestParam(value = "searchByTitle", required = false) String bookTitle) throws DAOException {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("inner/searchBookByTitle");
        logger.debug("Пользователь выполнил поиск по жанру");
        Books foundedBook = searchBook.getBookByTitle(bookTitle);
        if (foundedBook != null) {
            modelAndView.addObject("foundedBook", foundedBook);
            return modelAndView;
        }
        modelAndView.setViewName("inner/errorpage");
        return modelAndView;
    }
}