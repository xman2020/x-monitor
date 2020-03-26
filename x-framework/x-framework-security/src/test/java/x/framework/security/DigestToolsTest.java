/**
 * xxx
 * Copyright (c) 2012-2016 All Rights Reserved.
 */
package x.framework.security;

import junit.framework.TestCase;

import org.junit.Test;

/**
 * 摘要测试
 * 
 * @author xman 2016年4月4日
 */
public class DigestToolsTest extends TestCase {

    @Test
    public void testSha1() {
        String plainText = "abc123<>/{:} \"你好啊！";
        String digest = DigestTools.Sha1(plainText);
        // String digest = DigestUtils.shaHex(plainText);
        // String digest = DigestUtils.md5Hex(plainText);
        System.out.println(digest);
        System.out.println("--------------------");

        // 1\ 3dc1b3fb6922feb886be10abd5dc1b860ce3b031
        // 2\ 3DC1B3FB6922FEB886BE10ABD5DC1B860CE3B031
        // 3\ 3dc1b3fb6922feb886be10abd5dc1b860ce3b031
        // 4\ 3dc1b3fb6922feb886be10abd5dc1b860ce3b031

    }

}
