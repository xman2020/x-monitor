/**
 * xxx
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package x.framework.core.dao;

import java.util.List;

import x.framework.core.dao.GenericDao;

/**
 * 会员卡数据访问接口
 * 
 * @author xman 2012-5-20
 */
public interface CardDao extends GenericDao<Card> {

    List<Card> queryByMobile(String mobile);

}
