/**
 * xxx
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package x.framework.core.dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.util.Assert;

import x.framework.core.dao.RouteContext;

/**
 * 带路由事务管理器
 * 
 * @author xman 2012-5-22
 */
public class RouteTransactionManager implements ApplicationContextAware {

    /** Spring上下文 */
    private ApplicationContext                                   applicationContext;

    /** 事务管理器Map，Key为数据源名 */
    private Map<String, PlatformTransactionManager>              transactionManagerMap;

    /** 事务模板Map */
    private Map<PlatformTransactionManager, TransactionTemplate> transactionTemplateMap = new HashMap<PlatformTransactionManager, TransactionTemplate>();

    /**
     * 获取事务模板
     * @param <T> 条件类型
     * @param routeRuleName 路由规则名，就是路由规则Bean的名称
     * @param condition 条件
     * @return 事务模板
     */
    @SuppressWarnings("unchecked")
    public <T> TransactionTemplate getTemplate(String routeRuleName, T condition) {
        // 执行路由规则
        RouteRule<T> routeRule = (RouteRule<T>) this.applicationContext.getBean(routeRuleName);
        routeRule.execute(condition);

        // 获取数据源对应的事务管理器
        PlatformTransactionManager transactionManager = this.transactionManagerMap.get(RouteContext
                .get().getDatasource());

        Assert.notNull(transactionManager, RouteContext.get().getDatasource() + "数据源对应的事务管理器找不到。");

        // 获取事务模板
        TransactionTemplate transactionTemplate = this.transactionTemplateMap.get(transactionManager);

        if (transactionTemplate == null) {
            transactionTemplate = new TransactionTemplate(transactionManager);
            this.transactionTemplateMap.put(transactionManager, transactionTemplate);
        }

        return transactionTemplate;
    }

    /**
     * 设置Spring上下文
     * @param applicationContext Spring上下文
     */
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    /**
     * 设置事务管理器Map
     * @param transactionManagerMap 事务管理器Map
     */
    public void setTransactionManagerMap(Map<String, PlatformTransactionManager> transactionManagerMap) {
        this.transactionManagerMap = transactionManagerMap;
    }

}
