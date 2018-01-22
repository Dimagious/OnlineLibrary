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

    private static UserDAO checker = new UserDAOImpl();

    /**
     * Проверяет логин и пароль в БД. Если такое сочетание есть, то возвращает true, в обратном случае false
     *
     * @param login логин пользователя
     * @param password пароль пользователя
     */
    public static boolean authorizeUser(String login, String password) {
        UserData registeredUser = new UserData(login, password);
        UserData userDataFromDB = null;
        try {
            userDataFromDB = checker.getUserDataByLogin(registeredUser.getLogin());
        } catch (SQLException e) {
            logger.debug(e.getMessage());
        }
        if (userDataFromDB != null && registeredUser.getLogin() != null && registeredUser.getPassword() != null) {
            if (userDataFromDB.getLogin().equals(registeredUser.getLogin()) &&
                    userDataFromDB.getPassword().equals(registeredUser.getPassword())) {
                logger.debug("Авторизация прошла успешно");
                return true;
            }
        }
        logger.debug("Неправильный логин или пароль");
        return false;
    }
}