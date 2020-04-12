package x.framework.core.dao.mybatis;

import tk.mybatis.mapper.entity.Condition;
import x.framework.page.Page;

/**
 * 查询DTO基类
 *
 * @author shuyuan
 * @date 2020-04-12
 */
public abstract class BaseQueryDto {

    protected Page page;

    /**
     * 获取条件
     *
     * @return 条件
     */
    public abstract Condition getCondition();

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

}
