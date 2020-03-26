/**
 * xxx
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package x.framework.core.dao.mybatis;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

import x.framework.core.dao.Dao;
import x.framework.core.dao.GenericDao;
import x.framework.core.entity.Entity;
import x.framework.core.entity.IdEntity;

/**
 * 范型数据访问MyBatis实现
 * 
 * @author xman 2012-5-17
 */
public abstract class GenericDaoMyBatis<T extends Entity> implements GenericDao<T>,
        InitializingBean {

    /** 数据实体类型 */
    private Class<T>              entityClass;

    /** MyBatis会话 */
    private SqlSession            sqlSession;

    /** MyBatis会话管理器 */
    private MyBatisSessionManager myBatisSessionManager;

    /**
     * 默认构造函数
     */
    @SuppressWarnings("unchecked")
    public GenericDaoMyBatis() {
        Type superClassType = getClass().getGenericSuperclass();

        if (superClassType instanceof ParameterizedType) {
            Type[] paramTypes = ((ParameterizedType) superClassType).getActualTypeArguments();
            this.entityClass = (Class<T>) paramTypes[0];
        }
    }

    /**
     * 获取数据实体类型
     * @return 数据实体类型
     */
    public Class<T> getEntityClass() {
        return entityClass;
    }

    /**
     * 获取MyBatis会话 <br>
     * 如果有分库的需要，覆写该方法
     * @return MyBatis会话
     */
    public SqlSession getSqlSession() {
        return sqlSession;
    }

    /**
     * 获取MyBatis会话管理器
     * @return MyBatis会话管理器
     */
    public MyBatisSessionManager getMyBatisSessionManager() {
        return myBatisSessionManager;
    }

    /**
     * 设置MyBatis会话管理器
     * @param myBatisSessionManager MyBatis会话管理器
     */
    public void setMyBatisSessionManager(MyBatisSessionManager myBatisSessionManager) {
        this.myBatisSessionManager = myBatisSessionManager;
    }

    /**
     * {@inheritDoc}
     */
    public void afterPropertiesSet() throws Exception {
        if (this.getClass().isAnnotationPresent(Dao.class)) {
            Dao dao = this.getClass().getAnnotation(Dao.class);
            this.sqlSession = this.myBatisSessionManager.getSession(dao.datasource());
        }

        Assert.notNull(this.sqlSession, "MyBatis会话找不到，可能是你的配置有问题。");
    }

    /**
     * {@inheritDoc}
     */
    public int insert(T entity) {
        return this.getSqlSession().insert(this.entityClass.getName() + ".insert", entity);
    }

    /**
     * {@inheritDoc}
     */
    public int deleteById(Long id) {
        return this.getSqlSession().delete(this.entityClass.getName() + ".deleteById", id);
    }

    /**
     * {@inheritDoc}
     */
    public int delete(T entity) {
        if (entity instanceof IdEntity) {
            return this.getSqlSession().delete(this.entityClass.getName() + ".deleteById",
                    ((IdEntity) entity).getId());
        }

        return 0;
    }

    /**
     * {@inheritDoc}
     */
    public int update(T entity) {
        return this.getSqlSession().update(this.entityClass.getName() + ".update", entity);
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public T getById(Long id) {
        return (T) this.getSqlSession().selectOne(this.entityClass.getName() + ".getById", id);
    }

}
