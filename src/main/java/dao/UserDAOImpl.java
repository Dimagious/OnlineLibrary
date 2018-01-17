package dao;

import for_db.Connector;
import pojo.UserData;
import pojo.UserPersonal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dmitriy Yurkin on 10.01.2018.
 */
public class UserDAOImpl implements UserDAO {
    /**
     * Возвращает список всех пользователей с их данными
     * Метод доступен для администратора
     */
    @Override
    public List<UserData> getAllUsers() throws SQLException {
        List<UserData> list = Connector.executeQuery(con -> {
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "SELECT up.first_name,up.last_name, up.sex, ud.id_personal, ud.id, " +
                            "ud.login, ud.password FROM user_personal AS up " +
                            "LEFT JOIN user_data AS ud ON up.id = ud.id ORDER BY ud.id"
            );
            List<UserData> users = new ArrayList<>();
            while (resultSet.next()) {
                UserPersonal userPersonal = getFieldsFromUserPersonal(resultSet);
                UserData userData = getFieldsFromUserData(resultSet);
                userData.setUserPersonal(userPersonal);
                users.add(userData);
            }
            return users;
        });
        return list;
    }

    /**
     * Регистрирует пользователя в системе.
     *
     * @param first_name имя
     * @param last_name фамилия
     * @param sex пол
     * @param login логин
     * @param password пароль
     */
    @Override
    public void registerUser(String first_name, String last_name, String sex,
                             String login, String password) throws SQLException {
        UserPersonal person = new UserPersonal(first_name, last_name, sex);
        UserData data = new UserData(login, password);
        UserDAO loginChecker = new UserDAOImpl();
        saveUser(person, data);
    }

    /**
     * Авторизует пользователя в системе.
     *
     * @param login логин
     * @param password пароль
     */
    @Override
    public void authorizeUser(String login, String password) throws SQLException {
        UserData registeredUser = new UserData(login, password);
        UserDAO loginPasswordChecker = new UserDAOImpl();
        Connector.executeQuery(con -> {
            PreparedStatement statement = con.prepareStatement(
                    "SELECT * FROM user_data WHERE login = ? AND password = ?");
            statement.setString(1, registeredUser.getLogin());
            statement.setString(2, registeredUser.getPassword());
            UserData userDataFromDB = loginPasswordChecker.getUserDataByLogin(registeredUser.getLogin());
            if (!userDataFromDB.getLogin().equals(registeredUser.getLogin()) ||
                    !userDataFromDB.getPassword().equals(registeredUser.getPassword())) {
                System.out.println("Неправильныый логин или пароль");
            } else {
                System.out.println("Авторизация прошла успешно");
                return registeredUser;
            }
            return true;
        });
    }
    /**
     * Сохраняет в базе данных информацию о логине и пароле пользователя.
     * Обновляет id в полученных объектах
     *
     * @param data   - сохраняемый объект UserData
     */
    @Override
    public void saveUser(UserPersonal person, UserData data) throws SQLException {
        Connector.executeQuery(con -> {
            PreparedStatement statement = con.prepareStatement(
                    "INSERT INTO user_personal (first_name, last_name, sex) " +
                            "VALUES (?, ?, ?) RETURNING id, first_name, last_name, sex");
            statement.setString(1, person.getFirst_name());
            statement.setString(2, person.getLast_name());
            statement.setString(3, person.getSex());
            ResultSet resultSet = statement.executeQuery();
//            if (resultSet.next()) {
//                UserPersonal userPersonal = getFieldsFromUserPersonal(resultSet);
//                userPersonal.setId(resultSet.getInt("id"));
//                userPersonal.setFirst_name(resultSet.getString("first_name"));
//                userPersonal.setLast_name(resultSet.getString("last_name"));
//                userPersonal.setSex(resultSet.getString("sex"));
//            }
            return person;
        });

        Connector.executeQuery(con -> {
            PreparedStatement statement = con.prepareStatement(
                    "INSERT INTO user_data (id_personal, login, password) " +
                            "VALUES (?, ?, ?) RETURNING id, id_personal, login, password");
            statement.setInt(1, getUserPersonalId(person.getFirst_name(), person.getLast_name(), person.getSex()));
            statement.setString(2, data.getLogin());
            statement.setString(3, data.getPassword());
            ResultSet set = statement.executeQuery();
//            if (set.next()) {
//                UserData userData = getFieldsFromUserData(set);
//                userData.setId(set.getInt("id"));
//                userData.setId_personal(set.getInt("id_personal"));
//                userData.setLogin(set.getString("login"));
//                userData.setPassword(set.getString("password"));
//            }
            return data;
        });
    }
    /**
     * Регистрирует пользователя в системе.
     *
     * @param first_name имя
     * @param last_name фамилия
     * @param sex пол
     */
    public int getUserPersonalId(String first_name, String last_name, String sex) throws SQLException{
        return Connector.executeQuery(con -> {
                    PreparedStatement statement = con.prepareStatement(
                            "SELECT * FROM user_personal WHERE first_name = ? AND last_name = ? AND sex = ?");
                    statement.setString(1, first_name);
                    statement.setString(2, last_name);
                    statement.setString(3, sex);
                    ResultSet resultSet = statement.executeQuery();
                    resultSet.next();
                    int idFromDB = resultSet.getInt("id");
                    UserData newUserData = new UserData();
                    newUserData.setId_personal(idFromDB);
                    return newUserData.getId();
        });
    }

    /**
     * Извлекает из базы данных персональную информацию по логину
     *
     * @param login логин пользователя
     * @return найденного пользователя.
     * NULL если пользователь не был найден
     */
    @Override
    public UserPersonal getUserPersonalByLogin(String login) throws SQLException {
        UserPersonal user = Connector.executeQuery(connection -> {
            UserPersonal result = null;
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM user_personal " +
                            "WHERE id = " +
                            "(SELECT user_data.id_personal FROM user_data " +
                            "WHERE user_data.login = ?)");
            statement.setString(1, login);
            ResultSet set = statement.executeQuery();
            if (set.next()) {
                result = getFieldsFromUserPersonal(set);
            }
            return result;
        });
        return user;
    }

    /**
     * Извлекает из базы данных пароль по логину
     *
     * @param login логин пользователя
     * @return найденного пользователя из таблицы UserData.
     * NULL если пользователь не был найден
     */
    @Override
    public UserData getUserDataByLogin(String login) throws SQLException {
        UserData userData = Connector.executeQuery(connection -> {
            UserData result = null;
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM user_data WHERE user_data.login = ?");
            statement.setString(1, login);

            ResultSet set = statement.executeQuery();
            if (set.next()) {
                result = getFieldsFromUserData(set);
            }
            return result;
        });
        return userData;
    }

    /**
     * Достаёт из БД информацию из таблицы UserData
     */
    private UserData getFieldsFromUserData(ResultSet resultSet) throws SQLException {
        UserData userData = new UserData(
                resultSet.getInt("id"),
                resultSet.getInt("id_personal"),
                resultSet.getString("login"),
                resultSet.getString("password")
        );
        return userData;
    }

    /**
     * Достаёт из БД информацию из таблицы UserPersonal
     */
    private UserPersonal getFieldsFromUserPersonal(ResultSet resultSet) throws SQLException {
        UserPersonal userPersonal = new UserPersonal(
                resultSet.getInt("id"),
                resultSet.getString("first_name"),
                resultSet.getString("last_name"),
                resultSet.getString("sex")
        );
        return userPersonal;
    }
}