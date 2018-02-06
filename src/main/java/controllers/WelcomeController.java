package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Dmitriy Yurkin on 31.01.2018.
 */

@Controller
public class WelcomeController {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String mainPage(){
        return "public/login";
    }
}
