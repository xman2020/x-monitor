/**
 * xxx
 * Copyright (c) 2011-2011 All Rights Reserved.
 */
package x.framework.core.template;

import java.util.Date;

/**
 * 一个接一个处理记录
 * 
 * @author xman 2011-11-9
 */
public class OneByOne {

    /** 业务类型 */
    private String bizType;

    /** 业务ID */
    private String bizId;

    /** 方法 */
    private String method;

    /** 创建时间 */
    private Date   createTime;

    private String description;
    
    private boolean insertSuccess;
    


    /**
     * 创建一个接一个处理记录
     * @param bizType 业务类型
     * @param bizId 业务ID 
     * @param method 方法
     */
    public OneByOne(String bizType, String bizId, String method) {
        this.bizType = bizType;
        this.bizId = bizId;
        this.method = method;
    }

    /**
     * 获取业务类型
     * @return 业务类型
     */
    public String getBizType() {
        return bizType;
    }

    /**
     * 设置业务类型
     * @param bizType 业务类型
     */
    public void setBizType(String bizType) {
        this.bizType = bizType;
    }

    /**
     * 获取业务ID
     * @return 业务ID
     */
    public String getBizId() {
        return bizId;
    }

    /**
     * 设置业务ID
     * @param bizId 业务ID
     */
    public void setBizId(String bizId) {
        this.bizId = bizId;
    }

    /**
     * 获取方法
     * @return 方法
     */
    public String getMethod() {
        return method;
    }

    /**
     * 设置方法
     * @param method 方法
     */
    public void setMethod(String method) {
        this.method = method;
    }

    /**
     * 获取创建时间
     * @return 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the insertSuccess
     */
    public boolean isInsertSuccess() {
        return insertSuccess;
    }

    /**
     * @param insertSuccess the insertSuccess to set
     */
    public void setInsertSuccess(boolean insertSuccess) {
        this.insertSuccess = insertSuccess;
    }
    
}
