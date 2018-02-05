package services;

import db.dao.UserDAO;
import db.exceptions.DAOException;
import db.pojo.UserData;
import db.pojo.UserPersonal;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

/**
 * Created by Dmitriy Yurkin on 18.01.2018.
 */
@Service
public class RegisterUser {
    private static final Logger logger = Logger.getLogger(RegisterUser.class);
    private UserDAO checker;

    public UserDAO getChecker() {
        return checker;
    }

    @Autowired
    public void setChecker(UserDAO checker) {
        this.checker = checker;
    }

    /**
     * Сервис, который ррегистрирует пользователя в системе
     *
     * @param first_name имя пользователя
     * @param last_name  фамилия пользователя
     * @param sex        пол пользователя
     * @param login      логин пользователя
     * @param password   пароль пользователя
     * @return true или false
     */
    public boolean registerUser(String first_name, String last_name, String sex,
                                String login, String password) throws DAOException {
        UserData registeredUser = new UserData(login, password);
        UserData userDataFromDB = null;
        userDataFromDB = checker.getUserDataByLogin(registeredUser.getLogin());
        if (userDataFromDB != null && registeredUser.getLogin() != null) {
            logger.debug("Пользователь с указанным логином уже зарегистрирован в системе");
            return false;
        } else {
            UserPersonal person = new UserPersonal(first_name, last_name, sex);
            UserData data = new UserData(login, password);
            checker.saveUser(person, data);
            logger.debug("Пользователь успешно зарегистрирован");

            return true;
        }
    }
}