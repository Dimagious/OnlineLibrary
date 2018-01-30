package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import services.ReadBook;

/**
 * Created by Dmitriy Yurkin on 18.01.2018.
 */
@Controller
public class ReadingBook {

    @Autowired
    private ReadBook readBook;

    public ReadBook getReadBook() {
        return readBook;
    }

    public void setReadBook(ReadBook readBook) {
        this.readBook = readBook;
    }

    @RequestMapping(value = "/readbook", method = RequestMethod.GET)
    public ModelAndView readBook(@RequestParam(value = "title", required = false) String title,
                                 @RequestParam(value = "page", required = false) int page) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/readbook");
        modelAndView.addObject("book", readBook.readBook(title, page));
        modelAndView.addObject("title", title);
        modelAndView.addObject("page", page);
        return modelAndView;
    }
}