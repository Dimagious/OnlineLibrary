package controllers;

import db.pojo.Books;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
public class SearchBookByGenre {
    private static final Logger logger = Logger.getLogger(SearchBookByGenre.class);

    @Autowired
    private SearchBook searchBook;

    public SearchBook getSearchBook() {
        return searchBook;
    }

    public void setSearchBook(SearchBook searchBook) {
        this.searchBook = searchBook;
    }

    @RequestMapping(value = "inner/searchBookByGenre", method = RequestMethod.GET)
    public ModelAndView getGenresBooks(@RequestParam(value = "searchByGenre", required = false) String genreName){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("inner/searchBookByGenre");
        logger.debug("Пользователь выполнил поиск по жанру");
        List<Books> list = searchBook.getAllGenresBooks(genreName);
        if (list != null) {
            modelAndView.addObject("list", list);
            return modelAndView;
        }
        modelAndView.setViewName("inner/errorpage");
        return modelAndView;
    }
}