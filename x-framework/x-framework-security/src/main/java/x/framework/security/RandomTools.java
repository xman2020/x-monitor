/**
 * xxx
 * Copyright (c) 2012-2016 All Rights Reserved.
 */
package x.framework.security;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * 随机数工具
 * 
 * @author xman 2016年4月7日
 */
public class RandomTools {

    /**
     * 生成随机数
     * @param password 密码
     * @return 随机数
     */
    public static SecureRandom genRandom(String password) {
        SecureRandom secureRandom = null;
        if (password == null || password.length() == 0) {
            secureRandom = new SecureRandom();
        } else {
            try {
                secureRandom = SecureRandom.getInstance("SHA1PRNG");
            } catch (NoSuchAlgorithmException e) {
                // no problem
                e.printStackTrace();
            }
            secureRandom.setSeed(password.getBytes());
        }

        return secureRandom;
    }

}
