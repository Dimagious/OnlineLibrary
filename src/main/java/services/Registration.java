package services;

import dao.UserDAO;
import dao.UserDAOImpl;

import java.sql.SQLException;

/**
 * Created by Dmitriy Yurkin on 17.01.2018.
 */
public class Registration {
    static String login;
    public static void main(String[] args) {
        UserDAO loginChecker = new UserDAOImpl();
        try {
           loginChecker.getUserPersonalByLogin(login);
        } catch (SQLException e) {
            System.out.println("Пользователя с указанным логином не зарегистрировано в системе");
        }
    }
}
