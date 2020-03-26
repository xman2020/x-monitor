/**
 * xxx
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package x.framework.core.entity;


/**
 * 数据实体基类
 * 
 * @author xman 2010-7-27
 */
public abstract class BaseEntity extends AuditEntity {

    private static final long serialVersionUID = -846128474871208829L;

    /** 主键 */
    private Long              id;

    /**
     * {@inheritDoc}
     */
    public Long getId() {
        return this.id;
    }

    /**
     * {@inheritDoc}
     */
    public void setId(Long id) {
        this.id = id;
    }

}
