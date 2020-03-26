/**
 * xxx
 * Copyright (c) 2012-2016 All Rights Reserved.
 */
package x.framework.security;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import x.framework.lang.StringTools;

/**
 * 加密器工具
 * 
 * @author xman 2016年4月7日
 */
public class CipherTools {

    /**
     * 获取加密器
     * @param algorithm 加密算法
     * @param mode 模式
     * @param key 密钥
     * @return 加密器
     */
    public static Cipher getCipher(String algorithm, int mode, java.security.Key key) {
        Cipher cipher = null;

        try {
            cipher = Cipher.getInstance(algorithm);
            cipher.init(mode, key);
        } catch (NoSuchAlgorithmException e) {
            // no problem
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            // no problem
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            // no problem
            e.printStackTrace();
        }

        return cipher;
    }

    /**
     * 加密
     * @param cipher 加密器
     * @param plainText 原文
     * @return 密文
     */
    public static String encrypt(Cipher cipher, String plainText) {
        String cipherText = null;
        byte[] cipherBytes = null;
        try {
            cipherBytes = cipher.doFinal(StringTools.byteEncode(plainText));
        } catch (IllegalBlockSizeException e) {
            // no problem
            e.printStackTrace();
        } catch (BadPaddingException e) {
            // no problem
            e.printStackTrace();
        }

        cipherText = Base64Tools.encode(cipherBytes);
        return cipherText;
    }

    /**
     * 解密
     * @param cipher 加密器
     * @param cipherText 密文
     * @return 原文
     */
    public static String decrypt(Cipher cipher, String cipherText) {
        String plainText = null;
        byte[] plainBytes = null;
        try {
            plainBytes = cipher.doFinal(Base64Tools.decode2Bytes(cipherText));
        } catch (IllegalBlockSizeException e) {
            // no problem
            e.printStackTrace();
        } catch (BadPaddingException e) {
            // no problem
            e.printStackTrace();
        }

        plainText = StringTools.byteDecode(plainBytes);
        return plainText;
    }

}
