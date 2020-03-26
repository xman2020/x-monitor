/**
 * xxx
 * Copyright (c) 2012-2016 All Rights Reserved.
 */
package x.framework.security;

import java.net.URLEncoder;

import org.junit.Test;

import junit.framework.TestCase;

/**
 * RSA加密测试
 * 
 * @author xman 2016年4月4日
 */
public class RsaToolsTest extends TestCase {

    @Test
    public void testGenKey() {
        Key key = RsaTools.genKey("abc123");
        System.out.println(key.getPrivateKey());
        System.out.println();
        System.out.println(key.getPublicKey());
        System.out.println("--------------------");
    }

    @Test
    public void testEncrypt() {
        Key key = RsaTools.genKey("abc123");
        String plainText = "abc123<>/{:} \"你好啊！";
        String cipherText = RsaTools.encrypt(plainText, key.getPublicKey());
        System.out.println(cipherText);
        System.out.println();

        String cipherTextDecrypt = RsaTools.decrypt(cipherText, key.getPrivateKey());
        System.out.println(cipherTextDecrypt);
        System.out.println("--------------------");

        assertEquals(plainText, cipherTextDecrypt);
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testSign() {
        Key key = RsaTools.genKey("");
        String plainText = "abc123<>/{:} \"你好啊！abc123<>/{:} \"你好啊！abc123<>/{:} \"你好啊！abc123<>/{:} \"你好啊！";
        String sign = RsaTools.sign(plainText, key.getPrivateKey());
        System.out.println(sign);
        System.out.println();
        String signEncode = URLEncoder.encode(sign);
        System.out.println(signEncode);
        System.out.println("--------------------");

        boolean verify = RsaTools.verify(sign, plainText, key.getPublicKey());
        assertTrue(verify);
    }

}
