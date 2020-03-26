/**
 * xxx
 * Copyright (c) 2012-2016 All Rights Reserved.
 */
package x.framework.security;

import java.io.IOException;

import x.framework.lang.StringTools;

/**
 * Base64工具
 * 
 * @author xman 2016年4月4日
 */
public class Base64Tools {

    /**
     * 编码
     * @param plainText 原文
     * @return 编后码
     */
    public static String encode(String plainText) {
        String encodedString = encode(StringTools.byteEncode(plainText));

        return encodedString;
    }

    /**
     * 编码
     * @param plainTextBytes 原文字节数组
     * @return 编后码
     */
    @SuppressWarnings("restriction")
    public static String encode(byte[] plainTextBytes) {
        String encodedString = new sun.misc.BASE64Encoder().encode(plainTextBytes);

        return encodedString;
    }

    /**
     * 解码
     * @param encodedString 编后码
     * @return 原文
     */
    public static String decode(String encodedString) {
        String plainText = StringTools.byteDecode(decode2Bytes(encodedString));

        return plainText;
    }

    /**
     * 解码
     * @param encodedString 编后码
     * @return 原文字节数组
     */
    @SuppressWarnings("restriction")
    public static byte[] decode2Bytes(String encodedString) {
        byte[] plainBytes = null;
        try {
            plainBytes = new sun.misc.BASE64Decoder().decodeBuffer(encodedString);
        } catch (IOException e) {
            // no problem
            e.printStackTrace();
        }

        return plainBytes;
    }

}
