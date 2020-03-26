/**
 * xxx
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package x.framework.core.dao;

import x.framework.core.dao.mybatis.DaoMyBatis;

/**
 * 请输入功能描述
 *
 * @author 请输入您的姓名 2013-12-27
 */
public class TestDaoMyBatis extends DaoMyBatis implements TestDao {

    /**
     * {@inheritDoc}
     */
    public void insert(Card card) {
        this.insert("x.framework.core.dao.Card.insert", card);
    }

}
