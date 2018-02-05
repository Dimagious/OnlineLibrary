package db.dao;

import db.exceptions.DAOException;
import db.pojo.UserData;
import db.pojo.UserPersonal;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Dmitriy Yurkin on 10.01.2018.
 */
public interface UserDAO {
    List<UserData> getAllUsers() throws DAOException;
    UserPersonal getUserPersonalByLogin(String login) throws DAOException;
    UserData getUserDataByLogin(String login) throws DAOException;
    void saveUser(UserPersonal person, UserData data) throws DAOException;
    int getUserPersonalId(String first_name, String last_name, String sex) throws DAOException;
}
