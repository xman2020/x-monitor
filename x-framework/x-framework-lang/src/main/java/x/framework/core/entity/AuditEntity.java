/**
 * xxx
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package x.framework.core.entity;

import java.util.Date;

/**
 * 含有审计信息的数据实体抽象类
 * 
 * @author xman 2010-7-13
 */
public abstract class AuditEntity extends IdEntity {

    private static final long serialVersionUID = -4837674581902502156L;

    /** 创建人ID */
    private String            createdBy;

    /** 创建时间 */
    private Date              createdTime;

    /** 最后修改人ID */
    private String            lastUpdatedBy;

    /** 最后修改时间 */
    private Date              lastUpdatedTime;

    /**
     * 获取创建人ID
     * @return 创建人ID
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * 设置创建人ID
     * @param createdBy 创建人ID
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * 获取创建时间
     * @return 创建时间
     */
    public Date getCreatedTime() {
        return createdTime;
    }

    /**
     * 设置创建时间
     * @param createdTime 创建时间
     */
    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    /**
     * 获取最后修改人ID
     * @return 最后修改人ID
     */
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    /**
     * 设置最后修改人ID
     * @param lastUpdatedBy 最后修改人ID
     */
    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    /**
     * 获取最后修改时间
     * @return 最后修改时间
     */
    public Date getLastUpdatedTime() {
        return lastUpdatedTime;
    }

    /**
     * 设置最后修改时间
     * @param lastUpdatedTime 最后修改时间
     */
    public void setLastUpdatedTime(Date lastUpdatedTime) {
        this.lastUpdatedTime = lastUpdatedTime;
    }

}
