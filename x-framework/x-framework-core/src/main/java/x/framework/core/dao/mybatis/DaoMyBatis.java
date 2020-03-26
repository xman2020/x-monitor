/**
 * xxx
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package x.framework.core.dao.mybatis;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.executor.BatchResult;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

import x.framework.core.dao.Dao;
import x.framework.page.Page;

/**
 * 数据访问MyBatis基类
 * <p>
 * 有较好的通用性，Dao实现类继承该类
 * 
 * @author xman 2013-12-23
 */
public class DaoMyBatis implements InitializingBean {

    /** MyBatis会话 */
    private SqlSession            sqlSession;

    /** MyBatis会话管理器 */
    private MyBatisSessionManager myBatisSessionManager;

    /**
     * 获取MyBatis会话 <br>
     * 如果有分库的需要，覆写该方法
     * @return MyBatis会话
     */
    public SqlSession getSqlSession() {
        return sqlSession;
    }

    /**
     * 设置MyBatis会话
     * @param sqlSession MyBatis会话
     */
    public void setSqlSession(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
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
        // 如果没有设置MyBatis会话，从会话管理器中取
        if (this.sqlSession == null && this.getClass().isAnnotationPresent(Dao.class)) {
            Dao dao = this.getClass().getAnnotation(Dao.class);
            this.sqlSession = this.myBatisSessionManager.getSession(dao.datasource());
        }

        Assert.notNull(this.sqlSession, "MyBatis会话找不到，可能是你的配置有问题。");
    }

    // [Begin] From MyBatis 3.1.0

    /**
     * Retrieve a single row mapped from the statement key
     * @param <T> the returned object type
     * @param statement Unique identifier matching the statement to use.
     * @return Mapped object
     */
    public <T> T selectOne(String statement) {
        return this.<T> selectOne(statement, null);
    }

    /**
     * Retrieve a single row mapped from the statement key and parameter.
     * @param <T> the returned object type
     * @param statement Unique identifier matching the statement to use.
     * @param parameter A parameter object to pass to the statement.
     * @return Mapped object
     */
    @SuppressWarnings("unchecked")
    public <T> T selectOne(String statement, Object parameter) {
        return (T) this.getSqlSession().selectOne(statement, parameter);
    }

    /**
     * Retrieve a list of mapped objects from the statement key and parameter.
     * @param <E> the returned list element type
     * @param statement Unique identifier matching the statement to use.
     * @return List of mapped object
     */
    public <E> List<E> selectList(String statement) {
        return this.<E> selectList(statement, null);
    }

    /**
     * Retrieve a list of mapped objects from the statement key and parameter.
     * @param <E> the returned list element type
     * @param statement Unique identifier matching the statement to use.
     * @param parameter A parameter object to pass to the statement.
     * @return List of mapped object
     */
    public <E> List<E> selectList(String statement, Object parameter) {
        return this.<E> selectList(statement, parameter, RowBounds.DEFAULT);
    }

    /**
     * Retrieve a list of mapped objects from the statement key and parameter,
     * within the specified row bounds.
     * @param <E> the returned list element type
     * @param statement Unique identifier matching the statement to use.
     * @param parameter A parameter object to pass to the statement.
     * @param rowBounds Bounds to limit object retrieval
     * @return List of mapped object
     */
    @SuppressWarnings("unchecked")
    public <E> List<E> selectList(String statement, Object parameter, RowBounds rowBounds) {
        return this.getSqlSession().selectList(statement, parameter, rowBounds);
    }

    /**
     * The selectMap is a special case in that it is designed to convert a list
     * of results into a Map based on one of the properties in the resulting
     * objects. Eg. Return a of Map[Integer,Author] for
     * selectMap("selectAuthors","id")
     * @param <K> the returned Map keys type
     * @param <V> the returned Map values type
     * @param statement Unique identifier matching the statement to use.
     * @param mapKey The property to use as key for each value in the list.
     * @return Map containing key pair data.
     */
    public <K, V> Map<K, V> selectMap(String statement, String mapKey) {
        return this.selectMap(statement, null, mapKey);
    }

    /**
     * The selectMap is a special case in that it is designed to convert a list
     * of results into a Map based on one of the properties in the resulting
     * objects.
     * @param <K> the returned Map keys type
     * @param <V> the returned Map values type
     * @param statement Unique identifier matching the statement to use.
     * @param parameter A parameter object to pass to the statement.
     * @param mapKey The property to use as key for each value in the list.
     * @return Map containing key pair data.
     */
    public <K, V> Map<K, V> selectMap(String statement, Object parameter, String mapKey) {
        return this.selectMap(statement, parameter, mapKey, RowBounds.DEFAULT);
    }

    /**
     * The selectMap is a special case in that it is designed to convert a list
     * of results into a Map based on one of the properties in the resulting
     * objects.
     * @param <K> the returned Map keys type
     * @param <V> the returned Map values type
     * @param statement Unique identifier matching the statement to use.
     * @param parameter A parameter object to pass to the statement.
     * @param mapKey The property to use as key for each value in the list.
     * @param rowBounds Bounds to limit object retrieval
     * @return Map containing key pair data.
     */
    @SuppressWarnings("unchecked")
    public <K, V> Map<K, V> selectMap(String statement, Object parameter, String mapKey, RowBounds rowBounds) {
        return this.getSqlSession().selectMap(statement, parameter, mapKey, rowBounds);
    }

    /**
     * Retrieve a single row mapped from the statement using a
     * {@code ResultHandler}.
     * @param statement Unique identifier matching the statement to use.
     * @param handler ResultHandler that will handle each retrieved row
     * @return Mapped object
     */
    public void select(String statement, ResultHandler handler) {
        this.select(statement, null, handler);
    }

    /**
     * Retrieve a single row mapped from the statement key and parameter using a
     * {@code ResultHandler}.
     * @param statement Unique identifier matching the statement to use.
     * @param parameter A parameter object to pass to the statement.
     * @param handler ResultHandler that will handle each retrieved row
     * @return Mapped object
     */
    public void select(String statement, Object parameter, ResultHandler handler) {
        this.select(statement, parameter, RowBounds.DEFAULT, handler);
    }

    /**
     * Retrieve a single row mapped from the statement key and parameter using a
     * {@code ResultHandler} and {@code RowBounds}
     * @param statement Unique identifier matching the statement to use.
     * @param rowBounds RowBound instance to limit the query results
     * @param handler ResultHandler that will handle each retrieved row
     * @return Mapped object
     */
    public void select(String statement, Object parameter, RowBounds rowBounds, ResultHandler handler) {
        this.getSqlSession().select(statement, parameter, rowBounds, handler);
    }

    /**
     * Execute an insert statement.
     * @param statement Unique identifier matching the statement to execute.
     * @return int The number of rows affected by the insert.
     */
    public int insert(String statement) {
        return this.insert(statement, null);
    }

    /**
     * Execute an insert statement with the given parameter object. Any
     * generated autoincrement values or selectKey entries will modify the given
     * parameter object properties. Only the number of rows affected will be
     * returned.
     * @param statement Unique identifier matching the statement to execute.
     * @param parameter A parameter object to pass to the statement.
     * @return int The number of rows affected by the insert.
     */
    public int insert(String statement, Object parameter) {
        return this.getSqlSession().insert(statement, parameter);
    }

    /**
     * Execute insert statement with the given parameter list.
     * <p>
     * <b>Warning：如果数据量大，建议使用SqlSession的批量模式</b>
     * @param statement Unique identifier matching the statement to execute.
     * @param parameterList parameter list to pass to the statement.
     * @return int The number of rows affected by the insert.
     */
    public int batchInsert(String statement, List<?> parameterList) {
        int count = 0;

        for (Object parameter : parameterList) {
            count = count + this.insert(statement, parameter);
        }

        return count;
    }

    /**
     * Execute an update statement. The number of rows affected will be
     * returned.
     * @param statement Unique identifier matching the statement to execute.
     * @return int The number of rows affected by the update.
     */
    public int update(String statement) {
        return this.update(statement, null);
    }

    /**
     * Execute an update statement. The number of rows affected will be
     * returned.
     * @param statement Unique identifier matching the statement to execute.
     * @param parameter A parameter object to pass to the statement.
     * @return int The number of rows affected by the update.
     */
    public int update(String statement, Object parameter) {
        return this.getSqlSession().update(statement, parameter);
    }

    /**
     * Execute update statement. The number of rows affected will be returned.
     * <p>
     * <b>Warning：如果数据量大，建议使用SqlSession的批量模式</b>
     * @param statement Unique identifier matching the statement to execute.
     * @param parameterList parameter list to pass to the statement.
     * @return int The number of rows affected by the update.
     */
    public int batchUpdate(String statement, List<?> parameterList) {
        int count = 0;

        for (Object parameter : parameterList) {
            count = count + this.update(statement, parameter);
        }

        return count;
    }

    /**
     * Execute a delete statement. The number of rows affected will be returned.
     * @param statement Unique identifier matching the statement to execute.
     * @return int The number of rows affected by the delete.
     */
    public int delete(String statement) {
        return this.delete(statement, null);
    }

    /**
     * Execute a delete statement. The number of rows affected will be returned.
     * @param statement Unique identifier matching the statement to execute.
     * @param parameter A parameter object to pass to the statement.
     * @return int The number of rows affected by the delete.
     */
    public int delete(String statement, Object parameter) {
        return this.getSqlSession().delete(statement, parameter);
    }

    /**
     * Flushes batch statements and commits database connection. Note that
     * database connection will not be committed if no updates/deletes/inserts
     * were called. To force the commit call {@link SqlSession#commit(boolean)}
     */
    public void commit() {
        this.getSqlSession().commit();
    }

    /**
     * Flushes batch statements and commits database connection.
     * @param force forces connection commit
     */
    public void commit(boolean force) {
        this.getSqlSession().commit(force);
    }

    /**
     * Discards pending batch statements and rolls database connection back.
     * Note that database connection will not be rolled back if no
     * updates/deletes/inserts were called. To force the rollback call
     * {@link SqlSession#rollback(boolean)}
     */
    public void rollback() {
        this.getSqlSession().rollback();
    }

    /**
     * Discards pending batch statements and rolls database connection back.
     * Note that database connection will not be rolled back if no
     * updates/deletes/inserts were called.
     * @param force forces connection rollback
     */
    public void rollback(boolean force) {
        this.getSqlSession().rollback(force);
    }

    /**
     * Flushes batch statements.
     * @return BatchResult list of updated records
     */
    public List<BatchResult> flushStatements() {
        return this.getSqlSession().flushStatements();
    }

    /**
     * Closes the session
     */
    public void close() {
        this.getSqlSession().close();
    }

    /**
     * Clears local session cache
     */
    public void clearCache() {
        this.getSqlSession().clearCache();
    }

    /**
     * Retrieves current configuration
     * @return Configuration
     */
    public Configuration getConfiguration() {
        return this.getSqlSession().getConfiguration();
    }

    /**
     * Retrieves a mapper.
     * @param <T> the mapper type
     * @param type Mapper interface class
     * @return a mapper bound to this SqlSession
     */
    public <T> T getMapper(Class<T> type) {
        return this.getSqlSession().getMapper(type);
    }

    /**
     * Retrieves inner database connection
     * @return Connection
     */
    public Connection getConnection() {
        return this.getSqlSession().getConnection();
    }

    // [End] From MyBatis 3.1.0

    /**
     * 分页查询
     * @param <E> 查询对象类型
     * @param countStatement 查行数语句
     * @param queryStatement 查询语句
     * @param parameter 参数
     * @param page 分页对象
     * @return 查询结果列表
     */
    public <E> List<E> selectByPage(String countStatement, String queryStatement, Object parameter, Page page) {
        long count = this.<Long> selectOne(countStatement, parameter);
        page.setCount(count);
        List<E> list = null;

        if (count > 0) {
            list = this.selectList(queryStatement, parameter, new RowBounds(page.getIndex(), page.getPageSize()));
        } else {
            list = new ArrayList<E>();
        }

        return list;
    }

}
