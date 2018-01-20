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
    public static void registerUser(String first_name, String last_name, String sex,
                             String login, String password) throws SQLException {
        UserDAO loginChecker = new UserDAOImpl();
        if (loginChecker.getUserPersonalByLogin(login) != null){
            logger.debug("Пользователь с указанным логином уже зарегистрирован в системе");
        } else {
            UserPersonal person = new UserPersonal(first_name, last_name, sex);
            UserData data = new UserData(login, password);
            try {
                loginChecker.saveUser(person,data);
            } catch (SQLException ex) {
                logger.error(ex.getMessage());
            }
        }
    }
}
