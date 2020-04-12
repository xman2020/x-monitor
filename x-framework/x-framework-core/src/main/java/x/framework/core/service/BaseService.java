package x.framework.core.service;

import x.framework.lang.Result;
import x.framework.page.PageResult;

import java.util.List;

/**
 * 基本服务接口
 *
 * @author shuyuan
 * @date 2020-04-12
 */
public interface BaseService<Dmo, QueryDto> {

    Result insert(Dmo dmo);

    Result update(Dmo dmo);

    Result deleteById(Object id);

    Result<Dmo> getById(Object id);

    Result<List<Dmo>> Query(QueryDto queryDto);

    PageResult<Dmo> QueryByPage(QueryDto queryDto);

}
