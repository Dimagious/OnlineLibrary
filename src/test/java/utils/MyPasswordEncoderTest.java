package utils;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Dmitriy Yurkin on 31.01.2018.
 */
class MyPasswordEncoderTest {
    @Test
    void notMatches() {
        MyPasswordEncoder passwordEncoder = new MyPasswordEncoder();
        String result = passwordEncoder.encode("password");
        assertTrue(passwordEncoder.matches("password", result));
        assertFalse(passwordEncoder.matches("qwerty", result));
    }

    @Test
    void matches() {
        MyPasswordEncoder passwordEncoder = new MyPasswordEncoder();
        String result = passwordEncoder.encode("password");
        assertFalse(result.equals("password"));
        assertTrue(passwordEncoder.matches("password", result));
        assertFalse(passwordEncoder.matches("passwor", result));
        assertFalse(passwordEncoder.matches("pasword", result));
        assertFalse(passwordEncoder.matches("pssword", result));
    }
}