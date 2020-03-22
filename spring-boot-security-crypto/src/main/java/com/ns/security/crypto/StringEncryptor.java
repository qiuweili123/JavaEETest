package com.ns.security.crypto;

import org.springframework.security.crypto.encrypt.BytesEncryptor;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;

public class StringEncryptor    implements  TextEncryptor{

    private final  TextEncryptor  textEncryptor;
    //盐值
    private String salt;

    private String pwd;

    public  StringEncryptor(String pwd,String salt){
        TextEncryptor textEncryptor = Encryptors.text(pwd, salt);
        this.textEncryptor = textEncryptor;
    }

    @Override
    public String encrypt(String text) {
        return textEncryptor.encrypt(text);
    }

    @Override
    public String decrypt(String encryptedText) {
        return textEncryptor.decrypt(encryptedText);
    }
}
