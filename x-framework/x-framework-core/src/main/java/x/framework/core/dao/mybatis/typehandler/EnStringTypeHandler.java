/**
 * xxx
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package x.framework.core.dao.mybatis.typehandler;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.StringTypeHandler;

/**
 * 增强型字符串处理器
 * <p>
 * 解决Oracle不能插入NULL值问题
 * 
 * @author xman 2013-12-27
 */
@MappedTypes(String.class)
public class EnStringTypeHandler extends StringTypeHandler {

    /**
     * {@inheritDoc}
     */
    @Override
    public void setParameter(PreparedStatement ps, int i, String parameter, JdbcType jdbcType) throws SQLException {
        if (parameter == null) {
            if (jdbcType == null) {
                ps.setNull(i, JdbcType.VARCHAR.TYPE_CODE);
            } else {
                ps.setNull(i, jdbcType.TYPE_CODE);
            }
        } else {
            setNonNullParameter(ps, i, parameter, jdbcType);
        }
    }

}
