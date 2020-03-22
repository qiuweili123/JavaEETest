package com.ns.security.crypto;

import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.nio.charset.Charset;

public   class AbstractEncryptorHolder  {



    private String password;
    private String salt;



    public String getPassword() {
        return password;
    }

    public String getSalt() {
        return salt;
    }

    public static void main(String[] args) {
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}