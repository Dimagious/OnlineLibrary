package services;

import db.dao.UserDAO;
import db.pojo.UserData;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

/**
 * Created by Dmitriy Yurkin on 18.01.2018.
 */
@Service
public class AuthorizeUser {
    private static final Logger logger = Logger.getLogger(AuthorizeUser.class);

    private UserDAO checker;

    public UserDAO getChecker() {
        return checker;
    }

    @Autowired
    public void setChecker(UserDAO checker) {
        this.checker = checker;
    }

    /**
     * Проверяет логин и пароль в БД.
     * Если такое сочетание есть, то возвращает true, в обратном случае false
     *
     * @param login логин пользователя
     * @param password пароль пользователя
     */
    public boolean authorizeUser(String login, String password) {
        UserData registeredUser = new UserData(login, password);
        UserData userDataFromDB = null;
        try {
            userDataFromDB = checker.getUserDataByLogin(registeredUser.getLogin());
        } catch (SQLException e) {
            logger.debug(e.getMessage());
        }
        if (userDataFromDB != null && registeredUser.getLogin() != null && registeredUser.getPassword() != null) {
            return userDataFromDB.getLogin().equals(registeredUser.getLogin()) &&
                    userDataFromDB.getPassword().equals(registeredUser.getPassword());
        }
//        logger.debug("Неправильный логин или пароль");
        return false;
    }
}