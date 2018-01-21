package servlets;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Dmitriy Yurkin on 20.01.2018.
 */
public class AdminMenu extends HttpServlet {
    private static final Logger logger = Logger.getLogger(AdminMenu.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher("/adminmenu.jsp").forward(req,resp);
        logger.debug("Адмнинистратор открыл читальный зал");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
