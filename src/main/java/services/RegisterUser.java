package services;

import dao.UserDAO;
import dao.UserDAOImpl;
import org.apache.log4j.Logger;
import pojo.UserData;
import pojo.UserPersonal;

import java.sql.SQLException;

/**
 * Created by Dmitriy Yurkin on 18.01.2018.
 */
public class RegisterUser {
    private static final Logger logger = Logger.getLogger(RegisterUser.class);
    private static UserDAO checker = new UserDAOImpl();
    public static boolean registerUser(String first_name, String last_name, String sex,
                             String login, String password) {
        UserData registeredUser = new UserData(login, password);
        UserData userDataFromDB = null;
        try {
            userDataFromDB = checker.getUserDataByLogin(registeredUser.getLogin());
        } catch (SQLException e) {
            logger.debug(e.getMessage());
        }
        if (userDataFromDB != null && registeredUser.getLogin() != null) {
            logger.debug("Пользователь с указанным логином уже зарегистрирован в системе");
            return false;
        } else {
            UserPersonal person = new UserPersonal(first_name, last_name, sex);
            UserData data = new UserData(login, password);
            try {
                checker.saveUser(person, data);
                logger.debug("Пользователь успешно зарегистрирован");
            } catch (SQLException ex) {
                logger.error(ex.getMessage());
            }
            return true;
        }
    }
}
