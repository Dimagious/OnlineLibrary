package servlets;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Dmitriy Yurkin on 20.01.2018.
 */
public class LogOut extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        final HttpSession session = req.getSession();
        session.removeAttribute("password");
        session.removeAttribute("login");
        resp.sendRedirect("/login");
    }
}
