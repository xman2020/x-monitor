/**
 * xxx
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package x.framework.core.dao.mybatis.dialect;

import org.apache.ibatis.session.RowBounds;

/**
 * 数据库方言
 *
 * @author xman 2013-12-25
 */
public interface Dialect {

    /**
     * 生成分页SQL
     * @param sql 原始SQL
     * @param rowBounds RowBounds
     * @return 分页SQL
     */
    String generatePageSql(String sql, RowBounds rowBounds);
    
}
