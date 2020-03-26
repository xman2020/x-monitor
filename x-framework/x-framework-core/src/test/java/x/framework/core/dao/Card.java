/**
 * xxx
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package x.framework.core.dao;

import org.apache.ibatis.type.Alias;

import x.framework.core.entity.BaseEntity;

/**
 * 会员卡
 * 
 * @author xman 2010-7-13
 */
@Alias("Card")
public class Card extends BaseEntity {

    private static final long serialVersionUID = -8269921637561574322L;

    /** 用户名 */
    private String          userName;

    /** 用户真名 */
    private String          realName;

    /** Email */
    private String          email;

    /** 手机 */
    private String          mobile;

    /** 密码 */
    private String          password;

    /**
     * 获取用户名
     * @return 用户名
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置用户名
     * @param userName 用户名
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 获取用户真名
     * @return 用户真名
     */
    public String getRealName() {
        return realName;
    }

    /**
     * 设置用户真名
     * @param realName 用户真名
     */
    public void setRealName(String realName) {
        this.realName = realName;
    }

    /**
     * 获取Email
     * @return Email
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置Email
     * @param email Email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 获取手机
     * @return 手机
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * 设置手机
     * @param mobile 手机
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * 获取密码
     * @return 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

}
