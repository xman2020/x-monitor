/**
 * xxx
 * Copyright (c) 2012-2016 All Rights Reserved.
 */
package x.framework.security;

/**
 * 字节数组转十六进制字符串工具
 * 
 * @author xman 2016年4月5日
 */
public class Bytes2HexTools {

    /**
     * 字节数组转十六进制字符串
     * @param bytes 字节数组
     * @return 十六进制字符串
     */
    public static String bytes2Hex1(byte[] bytes) {
        StringBuffer strBuf = new StringBuffer();
        for (int i = 0; i < bytes.length; i++) {
            if (bytes[i] >= 0 && bytes[i] < 16) {
                strBuf.append("0");
            }
            strBuf.append(Integer.toHexString(bytes[i] & 0xFF));
        }

        return strBuf.toString();
    }

    /**
     * 字节数组转十六进制字符串
     * @param bytes 字节数组
     * @return 十六进制字符串
     */
    public static String bytes2Hex2(byte[] bytes) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(bytes[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex);
        }

        return sb.toString();
    }

    /**
     * 字节数组转十六进制字符串
     * @param bytes 字节数组
     * @return 十六进制字符串
     */
    public static String bytes2Hex3(byte[] bytes) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < bytes.length; ++i) {
            sb.append(Integer.toHexString((bytes[i] & 0xFF) | 0x100).substring(1, 3));
        }

        return sb.toString();
    }

    /**
     * 字节数组转十六进制字符串
     * @param bytes 字节数组
     * @return 十六进制字符串
     */
    public static String bytes2Hex4(byte[] bytes) {
        final String HEX = "0123456789abcdef";
        StringBuilder sb = new StringBuilder(bytes.length * 2);
        for (byte b : bytes) {
            sb.append(HEX.charAt((b >> 4) & 0x0f));
            sb.append(HEX.charAt(b & 0x0f));
        }

        return sb.toString();
    }

}
