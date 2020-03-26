/**
 * xxx
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package x.framework.core.dao.route;

import x.framework.core.dao.Dao;
import x.framework.core.dao.mybatis.RouteDaoMyBatis;

/**
 * 订单数据访问MyBatis实现
 *
 * @author xman 2012-5-26
 */
@Dao("OrderDao")
public class OrderDaoMyBatis extends RouteDaoMyBatis<Order> implements OrderDao {

}
