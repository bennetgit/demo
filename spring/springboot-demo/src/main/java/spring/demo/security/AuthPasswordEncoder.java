package spring.demo.security;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

/**
 * Created by wangfacheng on 2017-11-07.
 */
public class AuthPasswordEncoder implements PasswordEncoder {

    private static final String SECRET_KEY = "asdfzezxexgz@#$%^^!#$DVV";

    private static final PasswordEncoder ENCODER = new StandardPasswordEncoder(SECRET_KEY);

    @Override
    public String encode(CharSequence rawPassword) {
        return ENCODER.encode(rawPassword);
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return ENCODER.matches(rawPassword, encodedPassword);
    }
}
