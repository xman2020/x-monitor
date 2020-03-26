/**
 * xxx
 * Copyright (c) 2012-2016 All Rights Reserved.
 */
package x.framework.security;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * AES加密解密工具
 * 
 * @author xman 2016年4月4日
 */
public class AesTools {

    private static final String AES = "AES";

    /**
     * 生成密钥
     * @param password 密码
     * @return 密钥
     */
    public static Key genKey(String password) {
        Key key = new Key();
        KeyGenerator kgen = null;

        try {
            kgen = KeyGenerator.getInstance(AES);
        } catch (NoSuchAlgorithmException e) {
            // no problem
            e.printStackTrace();
        }

        SecureRandom secureRandom = RandomTools.genRandom(password);
        kgen.init(128, secureRandom);
        SecretKey secretkey = kgen.generateKey();
        String keySting = Base64Tools.encode(secretkey.getEncoded());
        key.setKey(keySting);

        return key;
    }

    /**
     * 加密
     * @param plainText 原文
     * @param key 密钥
     * @return 密文
     */
    public static String encrypt(String plainText, String key) {
        SecretKeySpec keySpec = loadKey(key);
        Cipher cipher = CipherTools.getCipher(AES, Cipher.ENCRYPT_MODE, keySpec);
        String cipherText = CipherTools.encrypt(cipher, plainText);

        return cipherText;
    }

    /**
     * 解密
     * @param cipherText 密文
     * @param key 密钥
     * @return 原文
     */
    public static String decrypt(String cipherText, String key) {
        SecretKeySpec keySpec = loadKey(key);
        Cipher cipher = CipherTools.getCipher(AES, Cipher.DECRYPT_MODE, keySpec);
        String plainText = CipherTools.decrypt(cipher, cipherText);

        return plainText;
    }

    /**
     * 加载密钥
     * @param key 密钥
     * @return 密钥对象
     */
    private static SecretKeySpec loadKey(String key) {
        SecretKeySpec keySpec = new SecretKeySpec(Base64Tools.decode2Bytes(key), AES);

        return keySpec;
    }

}
