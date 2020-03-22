package com.ns.security.crypto;

import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

public class EncryptUtil {
    //从配置文件中获得  
    private static final String SITE_WIDE_SECRET = "my-secret-key";  
    private static final PasswordEncoder encoder =   new StandardPasswordEncoder(
            SITE_WIDE_SECRET);
    public static String encrypt(String rawPassword) {  
         return encoder.encode(rawPassword);  
    }  
   
    public static boolean match(String rawPassword, String password) {  
         return encoder.matches(rawPassword, password);  
    }

    public static void main(String[] args) {
        TextEncryptor textEncryptor=new StringEncryptor("weoweo","232444");

        System.out.println(textEncryptor.encrypt("每次结果都不一样")+"##");
        System.out.println(textEncryptor.encrypt("每次结果都不一样"));
        System.out.println(textEncryptor.encrypt("每次结果都不一样"));
        System.out.println(textEncryptor.encrypt("每次结果都不一样"));
        System.out.println(textEncryptor.encrypt("每次结果都不一样"));
        //但是把每次结果拿出来进行match，你会发现可以得到true。
        System.out.println("##"+textEncryptor.decrypt("16a3c6894d2c707bd8a6342f233ef0bbd4e54efe15d46e9ecd901ea33cf9315ca5dcbb471228643b4763f7367cb1963e"));
        System.out.println( System.getProperty("user.home"));

    }
 }