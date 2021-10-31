package it.jpanik.jgaming.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author Deborah Medici
 */
public class PasswordMaker {

    public static void main(String[] args) {
        System.out.println(new BCryptPasswordEncoder(12).encode("gamer"));
    }
}
