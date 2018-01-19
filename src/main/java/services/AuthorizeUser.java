package services;

import dao.UserDAO;
import dao.UserDAOImpl;
import pojo.UserData;

import java.sql.SQLException;

/**
 * Created by Dmitriy Yurkin on 18.01.2018.
 */
public class AuthorizeUser {
    public static boolean authorizeUser(String login, String password) throws SQLException {
        UserData registeredUser = new UserData(login, password);
        UserDAO loginPasswordChecker = new UserDAOImpl();
        UserData userDataFromDB = loginPasswordChecker.getUserDataByLogin(registeredUser.getLogin());
        if (!userDataFromDB.getLogin().equals(registeredUser.getLogin()) ||
                !userDataFromDB.getPassword().equals(registeredUser.getPassword())) {
            System.out.println("Неправильный логин или пароль");
            return false;
        } else {
            System.out.println("Авторизация прошла успешно");
            return true;
        }
    }
}
