/**
 * xxx
 * Copyright (c) 2012-2014 All Rights Reserved.
 */
package x.framework.lang;

import java.io.Serializable;

/**
 * 操作员
 * 
 * @author xman 2014年1月2日
 */
public class Operator implements Serializable {

    private static final long serialVersionUID = -2775508072643541554L;

    /** 操作员角色 */
    private String            role;

    /** 组织机构 */
    private String            org;

    /** 操作员ID */
    private String            id;

    /** 操作员名 */
    private String            name;

    /**
     * 构造函数
     */
    public Operator() {
    }

    /**
     * 构造函数
     * @param role
     * @param id
     */
    public Operator(String role, String id) {
        this.role = role;
        this.id = id;
    }

    /**
     * 构造函数
     * @param role
     * @param org
     * @param id
     */
    public Operator(String role, String org, String id) {
        this.role = role;
        this.org = org;
        this.id = id;
    }

    /**
     * 构造函数
     * @param role
     * @param org
     * @param id
     * @param name
     */
    public Operator(String role, String org, String id, String name) {
        this.role = role;
        this.org = org;
        this.id = id;
        this.name = name;
    }

    /**
     * @return the role
     */
    public String getRole() {
        return role;
    }

    /**
     * @param role the role to set
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the org
     */
    public String getOrg() {
        return org;
    }

    /**
     * @param org the org to set
     */
    public void setOrg(String org) {
        this.org = org;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

}
