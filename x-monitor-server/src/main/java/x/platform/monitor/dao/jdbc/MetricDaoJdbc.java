package x.platform.monitor.dao.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import x.framework.lang.StringTools;
import x.framework.page.Page;
import x.framework.page.PageList;
import x.platform.monitor.dao.intf.MetricDao;
import x.platform.monitor.dmo.Metric;

import java.util.List;

@Repository
public class MetricDaoJdbc implements MetricDao {

    @Autowired
    private NamedParameterJdbcTemplate npJdbcTemplate;

    private String insertSql = "INSERT INTO METRIC \n" +
            "(ID, MAIN_KEY, SUB_KEY, OBJ_TYPE, OBJ_NO, MAIN_TYPE, MAIN_TYPE_EXT, SUB_TYPE, VALUE, VALUE_TYPE, COLLECT_TIME, CREATE_TIME, UPDATE_TIME) \n" +
            "VALUES \n" +
            "(:id, :mainKey, :subKey, :objType, :objNo, :mainType, :mainTypeExt, :subType, :value, :valueType, :collectTime, :createTime, :updateTime)";

    private String updateSql = "UPDATE METRIC SET \n" +
            "MAIN_KEY=:mainKey, SUB_KEY=:subKey, OBJ_TYPE=:objType, OBJ_NO=:objNo, MAIN_TYPE=:mainType, MAIN_TYPE_EXT=:mainTypeExt, SUB_TYPE=:subType, \n" +
            "VALUE=:value, VALUE_TYPE=:valueType, COLLECT_TIME=:collectTime, UPDATE_TIME=:updateTime \n" +
            "WHERE ID=:id";

    private String deleteSql = "DELETE FROM METRIC WHERE ID=:id";

    private String selectSql = "SELECT \n" +
            "ID AS id, MAIN_KEY AS mainKey, SUB_KEY AS subKey, OBJ_TYPE AS objType, OBJ_NO AS objNo, MAIN_TYPE AS mainType, MAIN_TYPE_EXT AS mainTypeExt, \n" +
            "SUB_TYPE AS subType, VALUE AS value, VALUE_TYPE AS valueType, COLLECT_TIME AS collectTime, CREATE_TIME AS createTime, UPDATE_TIME AS updateTime \n" +
            "FROM METRIC WHERE 1=1 \n";

    private String getByIdSql = selectSql +
            "AND ID=:id";

    private String countSql = "SELECT COUNT(*) FROM METRIC WHERE 1=1 \n";

    public int insert(Metric metric) {
        metric.setId(StringTools.genUUID_());
        SqlParameterSource parameters = new BeanPropertySqlParameterSource(metric);
        return this.npJdbcTemplate.update(this.insertSql, parameters);
    }

    public int update(Metric metric) {
        SqlParameterSource parameters = new BeanPropertySqlParameterSource(metric);
        return this.npJdbcTemplate.update(this.updateSql, parameters);
    }

    public int delete(Metric metric) {
        SqlParameterSource parameters = new BeanPropertySqlParameterSource(metric);
        return this.npJdbcTemplate.update(this.deleteSql, parameters);
    }

    public Metric getById(String id) {
        SqlParameterSource parameters = new MapSqlParameterSource("id", id);
        return this.npJdbcTemplate.queryForObject(this.getByIdSql, parameters, BeanPropertyRowMapper.newInstance(Metric.class));
    }

    public PageList<Metric> selectByPage(Metric metric, Page page) {
        StringBuilder where = new StringBuilder();
        if (!StringUtils.isEmpty(metric.getObjType())) {
            where.append(" AND OBJ_TYPE=:objType ");
        }
        if (!StringUtils.isEmpty(metric.getObjNo())) {
            where.append(" AND OBJ_NO=:objNo ");
        }
        if (!StringUtils.isEmpty(metric.getMainType())) {
            where.append(" AND MAIN_TYPE=:mainType ");
        }
        if (!StringUtils.isEmpty(metric.getMainTypeExt())) {
            where.append(" AND MAIN_TYPE_EXT=:mainTypeExt ");
        }
        if (!ObjectUtils.isEmpty(metric.getCollectTimeFrom())) {
            where.append(" AND COLLECT_TIME>=:collectTimeFrom ");
        }
        if (!ObjectUtils.isEmpty(metric.getCollectTimeTo())) {
            where.append(" AND COLLECT_TIME<=:collectTimeTo ");
        }

        SqlParameterSource parameters = new BeanPropertySqlParameterSource(metric);
        String countSql1 = this.countSql + where;
        long count = this.npJdbcTemplate.queryForObject(countSql1, parameters, Long.class);
        page.setCount(count);
        PageList<Metric> pageList = new PageList(page, null);

        if (count > 0 ){
            String selectSql1 = this.selectSql + where +
                    " ORDER BY COLLECT_TIME DESC LIMIT " + page.getIndex() + ", " + page.getPageSize();
            List<Metric> list = this.npJdbcTemplate.query(selectSql1, parameters, BeanPropertyRowMapper.newInstance(Metric.class));
            pageList.setList(list);
        }

        return pageList;
    }

}
