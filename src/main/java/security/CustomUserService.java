package security;

import db.dao.UserDAOImpl;
import db.exceptions.DAOException;
import db.pojo.UserData;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class CustomUserService implements UserDetailsService {
    private static final Logger logger = Logger.getLogger(CustomUserService.class);
    @Autowired
    UserDAOImpl userDAO;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserInside userInside = null;
        try {
            UserData user = userDAO.getUserDataByLogin(s);
            if (user==null){
                throw new UsernameNotFoundException(s);
            }
            GrantedAuthority[] grantedAuthorities = new GrantedAuthority[1];
            grantedAuthorities[0] = new SimpleGrantedAuthority(user.getRole());
            userInside = new UserInside(user.getLogin(), user.getPassword(),
                    Arrays.asList(grantedAuthorities), user);
        } catch (DAOException e) {
            logger.error(e.getMessage());
        }
        return userInside;
    }

}