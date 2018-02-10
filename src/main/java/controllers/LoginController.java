package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by Dmitriy Yurkin on 10.02.2018.
 */

@Controller
public class LoginController {

    @RequestMapping(value = "/auth", method = GET)
    public String showAuth() {
        return "public/login";
    }
}