package security;

import db.connections.ConnectionManager;
import db.connections.CustomConnectionManager;
import db.exceptions.DAOException;
import org.apache.log4j.Logger;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Dmitriy Yurkin on 31.01.2018.
 */
public class MyPasswordEncoder implements PasswordEncoder {

    private static final Logger logger = Logger.getLogger(MyPasswordEncoder.class);
    private static ConnectionManager connectionManager;

    @Override
    public String encode(CharSequence rawPassword) {
        return BCrypt.hashpw(rawPassword.toString(), BCrypt.gensalt(7));
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return BCrypt.checkpw(rawPassword.toString(), encodedPassword);
    }
}