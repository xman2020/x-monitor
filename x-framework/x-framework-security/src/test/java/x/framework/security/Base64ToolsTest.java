/**
 * xxx
 * Copyright (c) 2012-2016 All Rights Reserved.
 */
package x.framework.security;

import org.junit.Test;

import junit.framework.TestCase;

/**
 * Base64测试
 * 
 * @author xman 2016年4月4日
 */
public class Base64ToolsTest extends TestCase {

    @Test
    public void testEncode() {
        String plainText = "abc123<>/{:} \"你好啊！";
        String encodedString = Base64Tools.encode(plainText);
        String encodedStringDecode = Base64Tools.decode(encodedString);
        System.out.println(encodedString);
        System.out.println();
        System.out.println(encodedStringDecode);
        System.out.println("--------------------");

        assertEquals(plainText, encodedStringDecode);
    }

}
