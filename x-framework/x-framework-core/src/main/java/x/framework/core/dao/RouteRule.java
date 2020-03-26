/**
 * xxx
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package x.framework.core.dao;

/**
 * 路由规则
 * 
 * @author xman 2012-5-22
 */
public interface RouteRule<T> {

    /**
     * 执行规则
     * @param t 条件
     */
    void execute(T condition);

}
