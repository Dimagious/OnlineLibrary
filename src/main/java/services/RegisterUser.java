package services;

import dao.UserDAO;
import dao.UserDAOImpl;

import java.sql.SQLException;

/**
 * Created by Dmitriy Yurkin on 18.01.2018.
 */
public class RegisterUser {
    public void registerUser(String first_name, String last_name, String sex,
                             String login, String password) throws SQLException{
        UserDAO loginChecker = new UserDAOImpl();
        while (loginChecker.getUserPersonalByLogin(login) != null){
            System.out.println("Пользователь с указанным логином уже зарегистрирован в системе");
            registerUser(first_name, last_name, sex, login, password);
        }
    }
}
