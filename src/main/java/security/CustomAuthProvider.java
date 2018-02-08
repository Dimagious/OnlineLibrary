package security;

import db.dao.UserDAO;
import db.exceptions.DAOException;
import db.pojo.UserData;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * Created by Dmitriy Yurkin on 08.02.2018.
 */
@Component
public class CustomAuthProvider implements AuthenticationProvider {

    private static final Logger logger = Logger.getLogger(CustomUserService.class);

    @Autowired
    private UserDAO userDao;

    @Autowired
    private MyPasswordEncoder myPasswordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String login = authentication.getName();
        String password = authentication.getCredentials().toString();
        try {
            UserData userData = userDao.getUserDataByLogin(login);
            if (userData != null) {
                ArrayList list = new ArrayList();
                String pass = userData == null ? null : userData.getPassword();
                list.add(new SimpleGrantedAuthority(userData.getRole()));
                if (myPasswordEncoder.matches(password, pass)) {
                    return new UsernamePasswordAuthenticationToken(login, pass, list);
                }
            }
        } catch (DAOException e) {
            logger.error("Ошибка при аутентификации пользователя ", e);
        }
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}