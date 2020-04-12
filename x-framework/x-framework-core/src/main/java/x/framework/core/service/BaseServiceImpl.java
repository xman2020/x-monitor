package x.framework.core.service;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Condition;
import x.framework.core.dao.mybatis.BaseQueryDto;
import x.framework.lang.Result;
import x.framework.page.Page;
import x.framework.page.PageResult;

import java.util.ArrayList;
import java.util.List;

/**
 * 基本服务实现
 *
 * @author shuyuan
 * @date 2020-04-12
 */
public class BaseServiceImpl<Dmo, QueryDto extends BaseQueryDto, Dao extends Mapper<Dmo>>
    implements BaseService<Dmo, QueryDto> {

    @Autowired
    protected Dao dao;

    public Result insert(Dmo dmo) {
        Result result = new Result();
        int rows = this.dao.insert(dmo);
        result.setSuccess(rows > 0);

        return result;
    }

    public Result update(Dmo dmo) {
        Result result = new Result();
        int rows = this.dao.updateByPrimaryKeySelective(dmo);
        result.setSuccess(rows > 0);

        return result;
    }

    public Result deleteById(Object id) {
        Result result = new Result();
        int rows = this.dao.deleteByPrimaryKey(id);
        result.setSuccess(rows > 0);

        return result;
    }

    public Result<Dmo> getById(Object id) {
        Result<Dmo> result = new Result();
        Dmo dmo = this.dao.selectByPrimaryKey(id);
        result.setData(dmo);

        return result;
    }

    public Result<List<Dmo>> Query(QueryDto queryDto) {
        Result<List<Dmo>> result = new Result<List<Dmo>>();
        List<Dmo> list = this.dao.selectByExample(queryDto.getCondition());
        result.setData(list);

        return result;
    }

    public PageResult<Dmo> QueryByPage(QueryDto queryDto) {
        PageResult<Dmo> result = new PageResult<Dmo>();
        Page page = queryDto.getPage();
        Condition condition = queryDto.getCondition();
        List<Dmo> list = null;

        int count = this.dao.selectCountByExample(condition);
        page.setCount(count);

        if (count > 0) {
            RowBounds rowbounds = new RowBounds(page.getIndex(), page.getPageSize());
            list = this.dao.selectByExampleAndRowBounds(condition, rowbounds);
        } else {
            list = new ArrayList<Dmo>();
        }

        result.setPage(page);
        result.setData(list);

        return result;
    }

}
