package services;

import db.dao.UserDAO;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import db.pojo.UserData;
import db.pojo.UserPersonal;
import java.lang.reflect.Field;
import java.sql.SQLException;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Dmitriy Yurkin on 22.01.2018.
 */
class RegisterUserTest {
    private static final Logger logger = Logger.getLogger(AuthorizeUser.class);

    @BeforeAll
    static void setUp() {
        try {
            UserDAO mock = mock(UserDAO.class);
            UserPersonal userPersonal = new UserPersonal("Дмитрий", "Юркин", "м");
            UserData userData = new UserData("dimasta", "12345");
            when(mock.getUserDataByLogin("dimasta")).thenReturn(userData);

            Field field = RegisterUser.class.getDeclaredField("checker");
            field.setAccessible(true);
            field.set(RegisterUser.class, mock);
        } catch (NoSuchFieldException | IllegalAccessException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    void registerUser() {
        RegisterUser registerUser = new RegisterUser();
        assertTrue(registerUser.registerUser("Дмитрий", "Юркин", "м", "dimagic", "12345"));
        assertFalse(registerUser.registerUser("Дмитрий", "Юркин", "м", "dimasta", "12345"));
    }
}