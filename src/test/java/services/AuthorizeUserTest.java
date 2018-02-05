package services;

import db.dao.UserDAO;
import db.exceptions.DAOException;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import db.pojo.UserData;
import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Dmitriy Yurkin on 22.01.2018.
 */
class AuthorizeUserTest {
    private static final Logger logger = Logger.getLogger(AuthorizeUserTest.class);

    @BeforeAll
    static void setUp() throws DAOException {
        try {
            UserDAO mock = mock(UserDAO.class);
            UserData userData = new UserData(3, 4, "admin", "adminpass");
            when(mock.getUserDataByLogin("admin")).thenReturn(userData);

            Field field = AuthorizeUser.class.getDeclaredField("checker");
            field.setAccessible(true);
            field.set(AuthorizeUser.class, mock);
        } catch (NoSuchFieldException e) {
            logger.warn(e.getMessage(), e);
        } catch (IllegalAccessException ex) {
            logger.error(ex.getMessage());
        }
    }

    @Test
    void authorizeUser() throws DAOException {
        AuthorizeUser auth = new AuthorizeUser();
        assertTrue(auth.authorizeUser("admin", "adminpass"));
        assertFalse(auth.authorizeUser("admin", "asdfasdf"));
        assertFalse(auth.authorizeUser(null, "asdfasdf"));
        assertFalse(auth.authorizeUser("admin", null));
        assertFalse(auth.authorizeUser(null, null));
        assertFalse(auth.authorizeUser("qwerty", "adminpass"));
        assertFalse(auth.authorizeUser("qwerty", null));
    }
}