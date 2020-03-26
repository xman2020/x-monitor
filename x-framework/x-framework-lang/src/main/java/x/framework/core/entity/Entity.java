/**
 * xxx
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package x.framework.core.entity;

import java.io.Serializable;
import java.util.Map;

import x.framework.core.dao.RouteContext;

/**
 * 数据实体基类
 * 
 * @author xman 2010-7-15
 */
public abstract class Entity implements Serializable {

    private static final long serialVersionUID = 3321472422589656636L;

    /**
     * 获取路由上下文中的属性
     * @return 路由上下文中的属性
     */
    public Map<String, String> getRcp() {
        return RouteContext.get().getProperties();
    }

}
