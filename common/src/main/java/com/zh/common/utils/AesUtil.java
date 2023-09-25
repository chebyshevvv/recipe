package com.zh.common.utils;

import lombok.SneakyThrows;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Base64;

public class AesUtil {
    @SneakyThrows
    public static String decode(String value,String key){
        Key secretKey = getKey(key);
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE,secretKey);
        byte[] bytes = cipher.doFinal(Base64.getDecoder().decode(value));
        return new String(bytes);
    }
    @SneakyThrows
    public static String encode(String value, String key){
        Key secretKey = getKey(key);
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE,secretKey);
        byte[] result = cipher.doFinal(value.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(result);
    }
    private static Key getKey(String secret){
        return new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8),"AES");
    }
}
