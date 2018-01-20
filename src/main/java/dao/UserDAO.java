package dao;

import pojo.UserData;
import pojo.UserPersonal;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Dmitriy Yurkin on 10.01.2018.
 */
public interface UserDAO {
    List<UserData> getAllUsers() throws SQLException;
    UserPersonal getUserPersonalByLogin(String login) throws SQLException;
    UserData getUserDataByLogin(String login) throws SQLException;
    void saveUser(UserPersonal person, UserData data) throws SQLException;
    int getUserPersonalId(String first_name, String last_name, String sex) throws SQLException;
    boolean authorizeUser(String login, String password)throws SQLException;
}
