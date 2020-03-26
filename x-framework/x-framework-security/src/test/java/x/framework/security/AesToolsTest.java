/**
 * xxx
 * Copyright (c) 2012-2016 All Rights Reserved.
 */
package x.framework.security;

import org.junit.Test;

import junit.framework.TestCase;

/**
 * AES加密测试
 * 
 * @author xman 2016年4月4日
 */
public class AesToolsTest extends TestCase {

    @Test
    public void testEncrypt() {
        Key key = AesTools.genKey("abc123");
        String plainText = "abc123<>/{:} \"你好啊！";
        String cipherText = AesTools.encrypt(plainText, key.getKey());
        System.out.println(cipherText);
        System.out.println();
        
        String cipherTextDecrypt = AesTools.decrypt(cipherText, key.getKey());
        System.out.println(cipherTextDecrypt);
        System.out.println("--------------------");

        assertEquals(plainText, cipherTextDecrypt);
    }

}
