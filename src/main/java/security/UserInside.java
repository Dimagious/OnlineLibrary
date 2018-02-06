package security;

import db.pojo.UserData;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * Created by Dmitriy Yurkin on 05.02.2018.
 */
public class UserInside extends User{
    private UserData user;

    public UserData getUser() {
        return user;
    }

    public UserInside(String username, String password, Collection<? extends GrantedAuthority> authorities, UserData user) {
        super(username, password, authorities);
        this.user = user;
    }
}
