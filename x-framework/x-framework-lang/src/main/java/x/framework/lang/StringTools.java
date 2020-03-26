/**
 * xxx
 * Copyright (c) 2012-2016 All Rights Reserved.
 */
package x.framework.lang;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 字符串工具
 * 
 * @author xman 2016年4月9日
 */
public class StringTools {

    public static final String UTF8 = "UTF-8";

    public static final String AND  = "&";

    /**
     * 字节编码
     * @param text 字符串
     * @return 字节数组
     */
    public static byte[] byteEncode(String text) {
        if (text == null || text.length() == 0) {
            return null;
        }

        byte[] bytes = null;
        try {
            bytes = text.getBytes(UTF8);
        } catch (UnsupportedEncodingException e) {
            // no problem
            e.printStackTrace();
        }

        return bytes;
    }

    /**
     * 字节解码
     * @param bytes 字节数组
     * @return 字符串
     */
    public static String byteDecode(byte[] bytes) {
        if (bytes == null || bytes.length == 0) {
            return null;
        }

        String text = null;
        try {
            text = new String(bytes, UTF8);
        } catch (UnsupportedEncodingException e) {
            // no problem
            e.printStackTrace();
        }

        return text;
    }

    /**
     * URL编码
     * @param plainText 原文
     * @return 编码后的文字
     */
    public static String urlEncode(String plainText) {
        if (plainText == null || plainText.length() == 0) {
            return plainText;
        }

        String encodedText = null;
        try {
            encodedText = URLEncoder.encode(plainText, UTF8);
        } catch (UnsupportedEncodingException e) {
            // no problem
            e.printStackTrace();
        }

        return encodedText;
    }

    /**
     * URL解码
     * @param encodedText 编码后的文字
     * @return 原文
     */
    public static String urlDecode(String encodedText) {
        if (encodedText == null || encodedText.length() == 0) {
            return encodedText;
        }

        String plainText = null;
        try {
            plainText = URLDecoder.decode(encodedText, UTF8);
        } catch (UnsupportedEncodingException e) {
            // no problem
            e.printStackTrace();
        }

        return plainText;
    }

    /**
     * 生成QueryString
     * @param params 参数
     * @return QueryString
     */
    public static String genQueryString(Map<String, String> params) {
        if (params == null || params.size() == 0) {
            return null;
        }

        StringBuffer string = new StringBuffer();
        String separator = "";

        for (Map.Entry<String, String> entry : params.entrySet()) {
            String name = entry.getKey();
            String value = entry.getValue() == null ? "" : entry.getValue();
            string.append(separator).append(name).append("=").append(urlEncode(value));
            separator = AND;
        }

        return string.toString();
    }

    /**
     * QueryString转Map
     * @param string QueryString
     * @return Map
     */
    public static Map<String, String> queryString2Map(String string) {
        if (string == null || string.length() == 0) {
            return null;
        }

        String[] nameValues = string.split(AND);
        Map<String, String> map = new HashMap<String, String>();

        for (String s : nameValues) {
            String[] nameValue = s.split("=");
            String name = nameValue[0];
            String value = urlDecode(nameValue[1]);
            map.put(name, value);
        }

        return map;
    }

    /**
     * 生成签名原文
     * @param params 参数
     * @return 签名原文
     */
    public static String genSignPlainText(Map<String, String> params) {
        if (params == null || params.size() == 0) {
            return null;
        }

        List<String> names = new ArrayList<String>(params.keySet());
        Collections.sort(names);

        StringBuffer plainText = new StringBuffer();
        for (int i = 0; i < names.size(); i++) {
            String name = names.get(i);
            String value = params.get(name) == null ? "" : params.get(name);
            plainText.append(i == 0 ? "" : AND).append(name).append("=").append(value);
        }

        return plainText.toString();
    }

    /**
     * 生成UUID
     * @return UUID
     */
    public static String genUUID_() {
        return UUID.randomUUID().toString().replace("-", "");
    }
    
}
