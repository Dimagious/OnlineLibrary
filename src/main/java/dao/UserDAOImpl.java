package dao;

import for_db.Connector;
import org.apache.log4j.Logger;
import pojo.UserData;
import pojo.UserPersonal;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dmitriy Yurkin on 10.01.2018.
 */
public class UserDAOImpl implements UserDAO {
    private static final Logger logger = Logger.getLogger(UserDAOImpl.class);

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
     * Авторизует пользователя в системе.
     *
     * @param login    логин
     * @param password пароль
     */
    @Override
    public boolean authorizeUser(String login, String password) {
        try {
            Connector.executeQuery(con -> {
                PreparedStatement statement = con.prepareStatement(
                        "SELECT * FROM user_data WHERE login = ? AND password = ?");
                statement.setString(1, login);
                statement.setString(2, password);
                return true;
            });
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Сохраняет в базе данных информацию о логине и пароле пользователя.
     * Обновляет id в полученных объектах
     *
     * @param data - сохраняемый объект UserData
     */
    @Override
    public void saveUser(UserPersonal person, UserData data) {
        try {
            Connector.executeQuery(con -> {
                PreparedStatement statement = con.prepareStatement(
                        "INSERT INTO user_personal (first_name, last_name, sex) " +
                                "VALUES (?, ?, ?) RETURNING id");
                statement.setString(1, person.getFirst_name());
                statement.setString(2, person.getLast_name());
                statement.setString(3, person.getSex());
                ResultSet resultSet = statement.executeQuery();
                resultSet.next();
                person.setId(resultSet.getInt("id"));
                data.setId_personal(person.getId());
                logger.debug("Данные нового пользователя: " + person);
                return person;
            });
        } catch (SQLException ex) {
            logger.error(ex.getMessage());
        }

        try {
            Connector.executeQuery(con -> {
                PreparedStatement statement = con.prepareStatement(
                        "INSERT INTO user_data (id_personal, login, password) " +
                                "VALUES (?, ?, ?) RETURNING id");
                statement.setInt(1, person.getId());
                statement.setString(2, data.getLogin());
                statement.setString(3, data.getPassword());
                ResultSet set = statement.executeQuery();
                set.next();
                data.setId(set.getInt("id"));
                logger.debug("Логин и пароль нового пользователя: " + data);
                return data;
            });
        } catch (SQLException ex) {
            logger.error(ex.getMessage());
        }
    }

    /**
     * Регистрирует пользователя в системе.
     *
     * @param first_name имя
     * @param last_name  фамилия
     * @param sex        пол
     */
    public int getUserPersonalId(String first_name, String last_name, String sex) {
        try {
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
        } catch (SQLException ex) {
            logger.error(ex.getMessage());
        }
        return 0;
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