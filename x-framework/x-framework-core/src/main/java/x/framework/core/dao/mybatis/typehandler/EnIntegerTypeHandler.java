/**
 * xxx
 * Copyright (c) 2012-2014 All Rights Reserved.
 */
package x.framework.core.dao.mybatis.typehandler;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.ibatis.type.IntegerTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

/**
 * 增强型整型处理器
 * <p>
 * 解决Oracle不能插入NULL值问题
 * 
 * @author xman 2014年1月14日
 */
@MappedTypes(Integer.class)
public class EnIntegerTypeHandler extends IntegerTypeHandler {

    /**
     * {@inheritDoc}
     */
    @Override
    public void setParameter(PreparedStatement ps, int i, Integer parameter, JdbcType jdbcType) throws SQLException {
        if (parameter == null) {
            if (jdbcType == null) {
                ps.setNull(i, JdbcType.INTEGER.TYPE_CODE);
            } else {
                ps.setNull(i, jdbcType.TYPE_CODE);
            }
        } else {
            setNonNullParameter(ps, i, parameter, jdbcType);
        }
    }

}
