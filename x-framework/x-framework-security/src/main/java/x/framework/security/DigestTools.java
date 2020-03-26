/**
 * xxx
 * Copyright (c) 2012-2016 All Rights Reserved.
 */
package x.framework.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import x.framework.lang.StringTools;

/**
 * 摘要工具
 * 
 * @author xman 2016年4月4日
 */
public class DigestTools {

    /**
     * SHA1摘要
     * @param plainText 原文
     * @return 摘要
     */
    public static String Sha1(String plainText) {
        MessageDigest sha1 = null;
        String degist = null;
        try {
            sha1 = MessageDigest.getInstance("SHA-1");
            byte[] digestBytes = sha1.digest(StringTools.byteEncode(plainText));
            degist = bytes2Hex(digestBytes);
        } catch (NoSuchAlgorithmException e) {
            // no problem
            e.printStackTrace();
        }

        return degist;
    }

    /**
     * 字节数组转十六进制字符串
     * @param bytes 字节数组
     * @return 十六进制字符串
     */
    private static String bytes2Hex(byte[] bytes) {
        return Bytes2HexTools.bytes2Hex1(bytes);
    }

}
