/**
 * xxx
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package x.framework.core.dao;

import java.util.HashMap;
import java.util.Map;

/**
 * 路由上下文
 * 
 * @author xman 2012-5-22
 */
public class RouteContext {

    /** 路由上下文线程变量 */
    private static final ThreadLocal<RouteContext> contextLocal = new ThreadLocal<RouteContext>();

    /** 上下文中的属性 */
    private Map<String, String>                    properties   = new HashMap<String, String>();

    /** 数据源 */
    private String                                 datasource   = "default";

    /**
     * 获取应用上下文
     * @return RouteContext 应用上下文
     */
    public static RouteContext get() {
        RouteContext routeContext = contextLocal.get();

        if (routeContext == null) {
            routeContext = new RouteContext();
            contextLocal.set(routeContext);
        }

        return routeContext;
    }

    /**
     * 增加属性
     * @param key 属性键
     * @param value 属性值
     * @return 属性值
     */
    public Object put(String key, String value) {
        return this.properties.put(key, value);
    }

    /**
     * 删除属性
     * @param key 属性键
     * @return 属性值
     */
    public Object remove(String key) {
        return this.properties.remove(key);
    }

    /**
     * 获取属性
     * @param key 属性键
     * @return 属性值
     */
    public String get(String key) {
        return this.properties.get(key);
    }

    /**
     * 获取上下文中的属性
     * @return 上下文中的属性
     */
    public Map<String, String> getProperties() {
        return properties;
    }

    /**
     * 获取数据源
     * @return 数据源
     */
    public String getDatasource() {
        return datasource;
    }

    /**
     * 设置数据源
     * @param datasource 数据源
     */
    public void setDatasource(String datasource) {
        this.datasource = datasource;
    }

    /**
     * 清除上下文
     */
    public static void clear() {
        contextLocal.set(null);
    }

}
