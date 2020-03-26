/**
 * xxx
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package x.framework.core.dao;

import x.framework.core.entity.Entity;

/**
 * 泛型数据访问接口
 * 
 * @author xman 2010-7-17
 */
public interface GenericDao<T extends Entity> {

    /**
     * 插入数据实体
     * @param entity 数据实体
     * @return 插入记录条数
     */
    int insert(T entity);

    /**
     * 根据ID删除数据实体
     * @param id 主键
     * @return 删除记录条数
     */
    int deleteById(Long id);

    /**
     * 删除数据实体
     * @param entity 数据实体
     * @return 删除记录条数
     */
    int delete(T entity);

    /**
     * 更新数据实体
     * @param entity 数据实体
     * @return 更新记录条数
     */
    int update(T entity);

    /**
     * 根据ID获取数据实体
     * @param id 主键
     * @return 数据实体
     */
    T getById(Long id);

}
