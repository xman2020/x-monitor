/**
 * xxx
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package x.framework.core.dao.mybatis.typehandler;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

import org.apache.ibatis.type.DateTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

/**
 * 增强型日期处理器
 * <p>
 * 解决Oracle不能插入NULL值问题
 * 
 * @author xman 2013-12-27
 */
@MappedTypes(Date.class)
public class EnDateTypeHandler extends DateTypeHandler {

    /**
     * {@inheritDoc}
     */
    @Override
    public void setParameter(PreparedStatement ps, int i, Date parameter, JdbcType jdbcType) throws SQLException {
        if (parameter == null) {
            if (jdbcType == null) {
                ps.setNull(i, JdbcType.DATE.TYPE_CODE);
            } else {
                ps.setNull(i, jdbcType.TYPE_CODE);
            }
        } else {
            setNonNullParameter(ps, i, parameter, jdbcType);
        }
    }

}
