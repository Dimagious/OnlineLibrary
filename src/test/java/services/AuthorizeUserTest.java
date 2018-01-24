package services;

import db.dao.UserDAO;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import db.pojo.UserData;
import java.lang.reflect.Field;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Dmitriy Yurkin on 22.01.2018.
 */
class AuthorizeUserTest {
    private static final Logger logger = Logger.getLogger(AuthorizeUser.class);

    @BeforeAll
    static void setUp(){
        try {
            UserDAO mock = mock(UserDAO.class);
            UserData userData = new UserData(3, 4, "admin", "adminpass");
            when(mock.getUserDataByLogin("admin")).thenReturn(userData);

            Field field = AuthorizeUser.class.getDeclaredField("checker");
            field.setAccessible(true);
            field.set(AuthorizeUser.class, mock);

        } catch (NoSuchFieldException e) {
            logger.warn(e.getMessage(), e);
        } catch (IllegalAccessException | SQLException e) {
            logger.error(e.getMessage());
        }
    }

    @Test
    void authorizeUser() {
        assertTrue(AuthorizeUser.authorizeUser("admin", "adminpass"));
        assertFalse(AuthorizeUser.authorizeUser("admin", "asdfasdf"));
        assertFalse(AuthorizeUser.authorizeUser(null, "asdfasdf"));
        assertFalse(AuthorizeUser.authorizeUser("admin", null));
        assertFalse(AuthorizeUser.authorizeUser(null, null));
        assertFalse(AuthorizeUser.authorizeUser("qwerty", "adminpass"));
        assertFalse(AuthorizeUser.authorizeUser("qwerty", null));
    }
}