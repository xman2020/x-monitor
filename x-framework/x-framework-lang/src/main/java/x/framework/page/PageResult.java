/**
 * xxx
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package x.framework.page;

import java.util.List;

import x.framework.lang.Result;

/**
 * 分页列表
 * 
 * @author xman 2010-7-31
 */
public class PageResult<T> extends Result<List<T>> {

    private static final long serialVersionUID = -3729460386670004909L;

    /** 分页 */
    protected Page            page;

    /**
     * 构造函数
     */
    public PageResult() {
        super();
    }

    /**
     * 构造函数
     * @param page 分页
     * @param list 数据列表
     */
    public PageResult(Page page, List<T> list) {
        super();
        this.page = page;
        this.data = list;
    }

    /**
     * 获取分页
     * @return 分页
     */
    public Page getPage() {
        return page;
    }

    /**
     * 设置分页
     * @param page 分页
     */
    public void setPage(Page page) {
        this.page = page;
    }

    /**
     * 获取数据列表
     * @return 数据列表
     */
    public List<T> getList() {
        return data;
    }

    /**
     * 设置数据列表
     * @param list 数据列表
     */
    public void setList(List<T> list) {
        this.data = list;
    }

}
