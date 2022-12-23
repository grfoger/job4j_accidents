package ru.job4j.accidents;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PassGen {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String pass = encoder.encode("secret");
        System.out.println(pass);
        /**
         $2a$10$8eG.HP.WlwTdIfjtyQTtTudQs1vSi9bvAE9vjxOYyPqEyCH/QEV6a
         */
    }
}
