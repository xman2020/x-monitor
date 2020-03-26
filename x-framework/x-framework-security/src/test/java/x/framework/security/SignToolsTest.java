package x.framework.security;

/**
 * xxx
 * Copyright (c) 2012-2013 All Rights Reserved.
 */

import junit.framework.TestCase;

import org.junit.Test;

/**
 * 签名测试
 * 
 * @author xman 2012-5-20
 */
public class SignToolsTest extends TestCase {

    @Test
    public void testSign() {
        SignConfig config = new SignConfig();
        config.setName("TestKey");
        config.setPrivateKeyPath("/Users/xman/Desktop/project/x-plan/x-framework/x-framework-security/src/test/resources/TestPrivateKey.pfx");
        config.setKeyStorePassword("abc123");
        config.setKeyPassword("abc123");
        config.setPublicKeyPath("/Users/xman/Desktop/project/x-plan/x-framework/x-framework-security/src/test/resources/TestPublicKey.cer");

        String plainText = "abc123<>/{:} \"你好啊！";
        String sign = SignTools.sign(config, plainText);
        System.out.println("sign=" + sign);
        System.out.println();

        boolean verify1 = SignTools.verify(config, plainText, sign);
        boolean verify2 = SignTools.verify(config, plainText + "xx", sign);
        System.out.println("verify1=" + verify1);
        System.out.println();
        System.out.println("verify1=" + verify2);
        System.out.println("--------------------");

        assertTrue(verify1);
    }

}
