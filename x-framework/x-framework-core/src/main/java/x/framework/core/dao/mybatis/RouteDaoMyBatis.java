/**
 * xxx
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package x.framework.core.dao.mybatis;

import org.apache.ibatis.session.SqlSession;
import org.springframework.util.Assert;

import x.framework.core.dao.Dao;
import x.framework.core.dao.RouteContext;
import x.framework.core.entity.Entity;

/**
 * 带路由的数据访问
 * 
 * @author xman 2012-5-22
 */
public class RouteDaoMyBatis<T extends Entity> extends GenericDaoMyBatis<T> {

    /**
     * {@inheritDoc}
     */
    public SqlSession getSqlSession() {
        // 根据路由获取会话
        SqlSession sqlSession = this.getMyBatisSessionManager().getSession(
                RouteContext.get().getDatasource());

        // 默认方式获取会话
        if (sqlSession == null && this.getClass().isAnnotationPresent(Dao.class)) {
            Dao dao = this.getClass().getAnnotation(Dao.class);
            sqlSession = this.getMyBatisSessionManager().getSession(dao.datasource());
        }

        Assert.notNull(sqlSession, "MyBatis会话找不到，可能是你的配置有问题。");

        return sqlSession;
    }

    /**
     * {@inheritDoc}
     */
    public void afterPropertiesSet() throws Exception {
        return;
    }

}
