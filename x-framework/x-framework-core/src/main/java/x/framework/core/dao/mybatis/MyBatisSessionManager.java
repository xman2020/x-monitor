/**
 * xxx
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package x.framework.core.dao.mybatis;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;

/**
 * MyBatis会话管理器
 * 
 * @author xman 2012-5-18
 */
public class MyBatisSessionManager {

    /** 会话Map，Key为数据源名 */
    private Map<String, SqlSession> sessionMap;

    /**
     * 设置会话Map
     * @param sessionMap 会话Map
     */
    public void setSessionMap(Map<String, SqlSession> sessionMap) {
        this.sessionMap = sessionMap;
    }

    /**
     * 获取会话
     * @param datasource 数据源
     * @return 会话
     */
    public SqlSession getSession(String datasource) {
        return this.sessionMap == null ? null : this.sessionMap.get(datasource);
    }

}
