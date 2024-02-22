package net.codejava.user;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Encode {
    public static String encode(String rawPassword){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
       return encoder.encode(rawPassword);
    }
}
