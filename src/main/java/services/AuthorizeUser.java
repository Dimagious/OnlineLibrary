package services;

import dao.UserDAO;
import dao.UserDAOImpl;
import org.apache.log4j.Logger;
import pojo.UserData;

import java.sql.SQLException;

/**
 * Created by Dmitriy Yurkin on 18.01.2018.
 */
public class AuthorizeUser {
    private static final Logger logger = Logger.getLogger(AuthorizeUser.class);
    public static boolean authorizeUser(String login, String password) throws SQLException {
        UserData registeredUser = new UserData(login, password);
        UserDAO checker = new UserDAOImpl();
        UserData userDataFromDB = checker.getUserDataByLogin(registeredUser.getLogin());
        if (userDataFromDB.getLogin().equals(registeredUser.getLogin() == null) ||
                !userDataFromDB.getPassword().equals(registeredUser.getPassword())) {
            logger.debug("Неправильный логин или пароль");
            return false;
        } else {
            logger.debug("Авторизация прошла успешно");
            return true;
        }
    }

    public static void main(String[] args) throws SQLException {
        authorizeUser("dimasta", "12345");
        authorizeUser("asfvasfasfv", "12asd345");
    }
}
