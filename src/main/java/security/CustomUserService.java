package security;

import db.dao.UserDAO;
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
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Component
public class CustomUserService implements UserDetailsService {

    @Autowired
    private UserDAO userDAO;

    private UserInside userInside;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        try {
            UserData userData = userDAO.getUserDataByLogin(login);

            Set<GrantedAuthority> grantedAuthoritySet = new HashSet<>();
            grantedAuthoritySet.add(new SimpleGrantedAuthority(userData.getRole()));

            userInside = new UserInside(userData.getLogin(), userData.getPassword(), grantedAuthoritySet, userData.getId());

        } catch (DAOException e) {
            throw new UsernameNotFoundException("Пользователь не найден", e);
        }
        return userInside;
    }

    public UserInside getUserInside() {
        return userInside;
    }
}