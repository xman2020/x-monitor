/**
 * xxx
 * Copyright (c) 2011-2011 All Rights Reserved.
 */
package x.framework.core.template;

import java.util.Date;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import x.framework.core.oo.ErrorCode;
import x.framework.lang.AppException;
import x.framework.lang.CallBack;

/**
 * 一个接一个业务处理模版默认实现<p>
 * 
 * 用一个业务处理表记录，在处理前对锁状态进行判断 ，判断逻辑参见{@link #beforeInvoke}方法<br>
 * 
 * 业务处理表： 业务类型 PK|业务ID PK|方法|创建时间
 * 
 * @author xman 2011-10-27
 */
public class OneByOneTemplateImpl implements OneByOneTemplate, InitializingBean {

    /** logger */
    private static final Logger               logger        = LoggerFactory
                                                                    .getLogger(OneByOneTemplateImpl.class);

//    /** 描述 */
//    private static final ThreadLocal<String>  description   = new ThreadLocal<String>();
//
//    /** 插入成功 */
//    private static final ThreadLocal<Boolean> insertSuccess = new ThreadLocal<Boolean>();

    /** 插入处理记录 */
    private String                            insert;

    /** 删除处理记录 */
    private String                            delete;

    /** 表名 */
    private String                            table         = "T_ONE_BY_ONE";

    /** 数据源 */
    private DataSource                        dataSource;

    /** 事务模版 */
    private TransactionTemplate               transactionTemplate;

    /** Jdbc模版 */
    private JdbcTemplate                      jdbcTemplate;

    /**
     * {@inheritDoc}
     */
    public <T> T execute(OneByOne oneByOne, CallBack<T> callBack) {
//        description.set(oneByOne.getBizType() + "-" + oneByOne.getBizId() + "-"
//                + oneByOne.getMethod());

        oneByOne.setDescription(oneByOne.getBizType() + "-" + oneByOne.getBizId() + "-"
                + oneByOne.getMethod());
        
        try {
            this.beforeInvoke(oneByOne);

            return callBack.invoke();
        } finally {
            this.afterInvoke(oneByOne);
        }
    }

    /**
     * 设置数据源
     * @param dataSource 数据源
     */
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * 设置表名
     * @param table 表名
     */
    public void setTable(String table) {
        this.table = table;
    }

    /**
     * {@inheritDoc}
     */
    public void afterPropertiesSet() throws Exception {
        if (this.dataSource == null) {
            throw new AppException(ErrorCode.ERROR_PARAM_NULL, "数据源为空");
        }

        // 初始化Jdbc模版和事务模版
        this.jdbcTemplate = new JdbcTemplate(this.dataSource);
        this.transactionTemplate = new TransactionTemplate(new DataSourceTransactionManager(
                this.dataSource));

        // 初始化SQL
        this.insert = "INSERT INTO " + this.table
                + " (BIZ_TYPE, BIZ_ID, METHOD, CREATED_TIME) VALUES (?, ?, ?, ?)";

        this.delete = "DELETE FROM " + this.table + " WHERE BIZ_TYPE = ? AND BIZ_ID = ?";
    }

    /**
     * 回调前置
     * @param oneByOne 一个接一个处理记录
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    private void beforeInvoke(final OneByOne oneByOne) {
        try {
//            insertSuccess.set(true);
            oneByOne.setInsertSuccess(true);

            // 插入处理记录
            this.transactionTemplate.execute(new TransactionCallback() {
                public Object doInTransaction(TransactionStatus status) {
                    jdbcTemplate.update(insert, oneByOne.getBizType(), oneByOne.getBizId(),
                            oneByOne.getMethod(), new Date());

                    return null;
                }
            });
        } catch (Throwable t) {
//            insertSuccess.set(false);
            oneByOne.setInsertSuccess(false);

            if (logger.isWarnEnabled()) {
//                logger.warn(description.get() + "插入处理记录失败！", t);
                logger.warn(oneByOne.getDescription() + "插入处理记录失败！", t);
            }

            // 如果插入失败，抛出AppException
//            throw new AppException(ErrorCode.ERROR_BIZ_PROCESSING, description.get() + "业务正在处理中");
            throw new AppException(ErrorCode.ERROR_BIZ_PROCESSING, oneByOne.getDescription() + "业务正在处理中");
        }
    }

    /**
     * 回调后置
     * @param oneByOne 一个接一个处理记录
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    private void afterInvoke(final OneByOne oneByOne) {
        try {
            // 插入失败，不删除处理记录
//            if (!insertSuccess.get()) {
//                return;
//            }
            if (!oneByOne.isInsertSuccess()) {
                return;
            }

            // 删除处理记录
            this.transactionTemplate.execute(new TransactionCallback() {
                public Object doInTransaction(TransactionStatus status) {
                    jdbcTemplate.update(delete, oneByOne.getBizType(), oneByOne.getBizId());

                    return null;
                }
            });
        } catch (Throwable t) {
//            logger.error(description.get() + "删除处理记录失败！", t);
            logger.error(oneByOne.getDescription() + "删除处理记录失败！", t);
        } finally {
            // 清理
//            description.set(null);
//            insertSuccess.set(null);
        }
    }

    // 原方案
    {
        // 表设计
        // 业务处理表： 业务类型|业务ID|方法|状态|创建时间|最近一次处理开始时间|最近一次处理结束时间
        // 状态：L：锁定，U：解锁

        // beforeInvoke
        // boolean i = false;
        //
        // // 查询处理记录
        //
        // // 存在
        // if (i == true) {
        // // 锁定状态
        // if (i == true) {
        // // 抛出AppException
        // }
        // // 解锁状态
        // else {
        // // 更新为锁定状态
        // // 没有更新到记录
        // if (i == false) {
        // // 抛出AppException
        // }
        // }
        // }
        // // 不存在
        // else {
        // // 插入一条处理记录，状态为锁定
        // // 插入失败
        // if (i == false) {
        // // 抛出AppException
        // }
        // }

        // afterInvoke
        // boolean i = false;
        //
        // // 状态为锁定
        // if (i == true) {
        // // 更新为解锁状态
        // }
    }

}
