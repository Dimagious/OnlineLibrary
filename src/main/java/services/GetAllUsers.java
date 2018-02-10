package services;

import db.dao.BooksDAO;
import db.dao.UserDAO;
import db.exceptions.DAOException;
import db.pojo.Books;
import db.pojo.UserData;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dmitriy Yurkin on 10.02.2018.
 */
@Service
public class GetAllUsers {
    private static final Logger logger = Logger.getLogger(GetAllBooks.class);
    private UserDAO userDAO;

    public UserDAO getUserDAO() {
        return userDAO;
    }
    @Autowired
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    /**
     * Сервис, который берёт из БД всех пользователей и возвращает лист из них
     * @return userlist список всех книг
     */
    public List<UserData> getAllUsers() throws DAOException {
        List<UserData> userlist = new ArrayList<>(userDAO.getAllUsers());
        return userlist;
    }
}
