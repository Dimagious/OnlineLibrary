package dao;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class UserDAOImplTest {
    UserDAOImpl userDAO;
    @BeforeEach
    void setUp() {
        userDAO = new UserDAOImpl();
    }

    @AfterEach
    void tearDown() {
        userDAO = null;
    }

    @Test
    void getAllUsers() {
    }

    @Test
    @Disabled
    void saveUser() {
//        UserPersonal user_personal = new UserPersonal("Кара", "Делевинь", "ж");
//        try {
//            userDAO.saveUser(user_personal, new UserData(0, 0, "witch", "magic"));
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }
}