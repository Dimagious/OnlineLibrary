package controllers;

import db.exceptions.DAOException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import services.ReadBook;

import java.io.IOException;

/**
 * Created by Dmitriy Yurkin on 18.01.2018.
 */
@Controller
public class ReadingBook {

    private static final Logger logger = Logger.getLogger(ReadingBook.class);

    @Autowired
    private ReadBook readBook;

    public ReadBook getReadBook() {
        return readBook;
    }

    public void setReadBook(ReadBook readBook) {
        this.readBook = readBook;
    }

    @RequestMapping(value = "inner/readbook", method = RequestMethod.GET)
    public ModelAndView readBook(@RequestParam(value = "title", required = false) String title,
                                 @RequestParam(value = "page", required = false) int page) throws IOException, DAOException {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("inner/readbook");
        logger.debug("Пользователь открыл книгу \"" + title + "\"");
        modelAndView.addObject("book", readBook.readBook(title, page));
        modelAndView.addObject("title", title);
        modelAndView.addObject("page", page);
        return modelAndView;
    }
}