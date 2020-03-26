/**
 * xxx
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package x.framework.core.dao;

import java.util.List;

import x.framework.core.dao.Dao;
import x.framework.core.dao.mybatis.GenericDaoMyBatis;

/**
 * 会员卡数据访问MyBatis实现
 * 
 * @author xman 2012-5-20
 */
@Dao("cardDao")
public class CardDaoMyBatis extends GenericDaoMyBatis<Card> implements CardDao {

    @SuppressWarnings("unchecked")
    public List<Card> queryByMobile(String mobile) {
        return this.getSqlSession().selectList(this.getEntityClass().getName() + ".queryByMobile",
                mobile);
    }

}
