/**
 * xxx
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package x.framework.core.dao.mybatis.dialect;

import org.apache.ibatis.session.RowBounds;

/**
 * MySQL方言
 * 
 * @author xman 2013-12-25
 */
public class MySqlDialect implements Dialect {

    /**
     * {@inheritDoc}
     */
    public String generatePageSql(String sql, RowBounds rowBounds) {
        StringBuffer pageSql = new StringBuffer();

        if (rowBounds.getOffset() == RowBounds.NO_ROW_OFFSET) {
            pageSql.append(sql).append(" LIMIT ").append(rowBounds.getLimit()).toString();
        } else {
            pageSql.append(sql).append(" LIMIT ").append(rowBounds.getOffset()).append(", ")
                    .append(rowBounds.getLimit()).toString();
        }

        return pageSql.toString();
    }

}
