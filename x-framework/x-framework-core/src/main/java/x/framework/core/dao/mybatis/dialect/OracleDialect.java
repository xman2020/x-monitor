/**
 * xxx
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package x.framework.core.dao.mybatis.dialect;

import org.apache.ibatis.session.RowBounds;

/**
 * Oracle方言
 * 
 * @author xman 2013-12-26
 */
public class OracleDialect implements Dialect {

    /**
     * {@inheritDoc}
     */
    public String generatePageSql(String sql, RowBounds rowBounds) {
        StringBuffer pageSql = new StringBuffer();

        if (rowBounds.getOffset() == RowBounds.NO_ROW_OFFSET) {
            pageSql.append("SELECT A.*, ROWNUM RN FROM (").append(sql).append(") A WHERE ROWNUM <= ")
                    .append(rowBounds.getLimit());
        } else {
            pageSql.append("SELECT * FROM (SELECT A.*, ROWNUM RN FROM (").append(sql).append(") A WHERE ROWNUM <= ")
                    .append(rowBounds.getOffset() + rowBounds.getLimit()).append(") WHERE RN > ")
                    .append(rowBounds.getOffset());
        }

        return pageSql.toString();
    }

}
