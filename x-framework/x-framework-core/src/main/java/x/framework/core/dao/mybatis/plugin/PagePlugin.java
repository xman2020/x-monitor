/**
 * xxx
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package x.framework.core.dao.mybatis.plugin;

import java.util.Properties;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.mapping.MappedStatement.Builder;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import x.framework.core.dao.mybatis.dialect.Dialect;

/**
 * 分页插件
 * 
 * @author xman 2013-12-24
 */
@Intercepts({ @Signature(type = Executor.class, method = "query", args = { MappedStatement.class, Object.class,
        RowBounds.class, ResultHandler.class }) })
public class PagePlugin implements Interceptor {

    /** 数据库方言 */
    private Dialect dialect;

    /**
     * @param dialect the dialect to set
     */
    public void setDialect(Dialect dialect) {
        this.dialect = dialect;
    }

    /**
     * {@inheritDoc}
     */
    public Object intercept(Invocation invocation) throws Throwable {
        Object[] args = invocation.getArgs();
        RowBounds rowBounds = (RowBounds) args[2];

        // 不是分页查询
        if (rowBounds.getOffset() == RowBounds.NO_ROW_OFFSET && rowBounds.getLimit() == RowBounds.NO_ROW_LIMIT) {
            return invocation.proceed();
        }

        MappedStatement mappedStatement = (MappedStatement) args[0];
        Object parameter = args[1];

        // 获取原始SQL，生成分页SQL
        BoundSql boundSql = mappedStatement.getBoundSql(parameter);
        String sql = boundSql.getSql().trim();
        String pageSql = this.dialect.generatePageSql(sql, rowBounds);

        args[0] = this.createMappedStatement(mappedStatement, boundSql, pageSql);
        args[2] = new RowBounds();

        return invocation.proceed();
    }

    /**
     * 创建MappedStatement
     * @param mappedStatement 原始MappedStatement
     * @param boundSql 原始BoundSql
     * @param newSql 新SQL
     * @return 新MappedStatement
     */
    private MappedStatement createMappedStatement(MappedStatement mappedStatement, BoundSql boundSql, String newSql) {
        // TODO 需要进一步解读
        final BoundSql newBoundSql = new BoundSql(mappedStatement.getConfiguration(), newSql,
                boundSql.getParameterMappings(), boundSql.getParameterObject());

        for (ParameterMapping mapping : boundSql.getParameterMappings()) {
            String prop = mapping.getProperty();
            if (boundSql.hasAdditionalParameter(prop)) {
                newBoundSql.setAdditionalParameter(prop, boundSql.getAdditionalParameter(prop));
            }
        }

        Builder builder = new Builder(mappedStatement.getConfiguration(), mappedStatement.getId(), new SqlSource() {
            public BoundSql getBoundSql(Object parameterObject) {
                return newBoundSql;
            }
        }, mappedStatement.getSqlCommandType());

        builder.resource(mappedStatement.getResource());
        builder.fetchSize(mappedStatement.getFetchSize());
        builder.statementType(mappedStatement.getStatementType());
        builder.keyGenerator(mappedStatement.getKeyGenerator());
        builder.keyProperty(mappedStatement.getKeyProperty());

        // setStatementTimeout()
        builder.timeout(mappedStatement.getTimeout());

        // setStatementResultMap()
        builder.parameterMap(mappedStatement.getParameterMap());

        // setStatementResultMap()
        builder.resultMaps(mappedStatement.getResultMaps());
        builder.resultSetType(mappedStatement.getResultSetType());

        // setStatementCache()
        builder.cache(mappedStatement.getCache());
        builder.flushCacheRequired(mappedStatement.isFlushCacheRequired());
        builder.useCache(mappedStatement.isUseCache());

        return builder.build();
    }

    /**
     * {@inheritDoc}
     */
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    /**
     * {@inheritDoc}
     */
    public void setProperties(Properties properties) {
        // null
    }

}
