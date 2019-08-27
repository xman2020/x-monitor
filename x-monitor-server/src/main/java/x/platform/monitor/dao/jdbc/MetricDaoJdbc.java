package x.platform.monitor.dao.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import x.platform.monitor.dao.intf.MetricDao;
import x.platform.monitor.dmo.Metric;

@Repository
public class MetricDaoJdbc implements MetricDao {

    @Autowired
    private NamedParameterJdbcTemplate npJdbcTemplate;

    private String insertSql = "INSERT INTO METRIC \n" +
            "(MAIN_KEY, SUB_KEY, VALUE, OBJ_TYPE, OBJ_NO, MAIN_TYPE, MAIN_TYPE_EXT, SUB_TYPE, VALUE_TYPE, COLLECT_TIME, CREATE_TIME, UPDATE_TIME)\n" +
            "VALUES \n" +
            "(:mainKey, :subKey, :value, :objType, :objNo, :mainType, :mainTypeExt, :subType, :valueType, :collectTime, :createTime, :updateTime)";

    public void insert(Metric metric) {
        SqlParameterSource parameters = new BeanPropertySqlParameterSource(metric);
        this.npJdbcTemplate.update(this.insertSql, parameters);
    }

}
