package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by Dmitriy Yurkin on 20.01.2018.
 */
@Controller
public class LogOut {
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(@RequestParam(value = "login") String login,
                         @RequestParam(value = "password") String password,
                         HttpServletRequest request,
                         Model model){
        final HttpSession session = request.getSession();
        session.removeAttribute("password");
        session.removeAttribute("login");
        return "redirect:/login";
    }
}
