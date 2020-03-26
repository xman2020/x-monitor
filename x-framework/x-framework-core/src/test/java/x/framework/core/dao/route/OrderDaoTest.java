/**
 * xxx
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package x.framework.core.dao.route;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import x.framework.core.dao.Card;
import x.framework.core.dao.RouteRule;
import x.framework.core.dao.RouteTransactionManager;

/**
 * 订单数据访问测试
 * 
 * @author xman 2012-5-26
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext-test-route.xml" })
public class OrderDaoTest {

    @Autowired
    private OrderDao                orderDao;

    @Autowired
    @Qualifier("orderRouteRule")
    private RouteRule<Card>         orderRouteRule;

    @Autowired
    private RouteTransactionManager routeTransactionManager;

    @Test
    public void testInsert_1() {
        Card card = this.getChen();

        Order order = new Order();
        order.setOrderName("suning apple tv");
        order.setMoney(1000L);
        order.setBuyerId(card.getId());
        order.setBuyerName(card.getUserName());
        order.setCreatedBy("111111");
        order.setCreatedTime(new Date());

        this.orderRouteRule.execute(card);
        this.orderDao.insert(order);

        System.out.println("~~~~~~~~~~~~~~~~~~~order id = " + order.getId());
    }

    @Test
    public void testInsert_2() {
        Card card = this.getMike();

        Order order = new Order();
        order.setOrderName("suning android tv");
        order.setMoney(2000L);
        order.setBuyerId(card.getId());
        order.setBuyerName(card.getUserName());
        order.setCreatedBy("222222");
        order.setCreatedTime(new Date());

        this.orderRouteRule.execute(card);
        this.orderDao.insert(order);

        System.out.println("~~~~~~~~~~~~~~~~~~~order id = " + order.getId());
    }

    @Test
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public void testTransaction_1() {
        final Card card = this.getChen();
        TransactionTemplate transactionTemplate = this.routeTransactionManager.getTemplate(
                "orderRouteRule", card);

        transactionTemplate.execute(new TransactionCallback() {
            public Object doInTransaction(TransactionStatus status) {
                Order order = new Order();
                order.setOrderName("suning apple tv");
                order.setMoney(1000L);
                order.setBuyerId(card.getId());
                order.setBuyerName(card.getUserName());
                order.setCreatedBy("111111");
                order.setCreatedTime(new Date());

                orderDao.insert(order);

                System.out.println("~~~~~~~~~~~~~~~~~~~order id = " + order.getId());
                return null;
            }
        });
    }

    @Test
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public void testTransaction_2() {
        final Card card = this.getMike();
        TransactionTemplate transactionTemplate = this.routeTransactionManager.getTemplate(
                "orderRouteRule", card);

        transactionTemplate.execute(new TransactionCallback() {
            public Object doInTransaction(TransactionStatus status) {
                Order order = new Order();
                order.setOrderName("suning android tv");
                order.setMoney(2000L);
                order.setBuyerId(card.getId());
                order.setBuyerName(card.getUserName());
                order.setCreatedBy("222222");
                order.setCreatedTime(new Date());

                orderDao.insert(order);

                System.out.println("~~~~~~~~~~~~~~~~~~~order id = " + order.getId());
                return null;
            }
        });
    }

    @Test
    public void testUpdate_1() {
        Card card = this.getChen();
        this.orderRouteRule.execute(card);

        Order order = this.orderDao.getById(1L);
        order.setMoney(10000L);
        order.setLastUpdatedBy("1X");
        order.setLastUpdatedTime(new Date());
        this.orderDao.update(order);
    }

    @Test
    public void testUpdate_2() {
        Card card = this.getMike();
        this.orderRouteRule.execute(card);

        Order order = this.orderDao.getById(1L);
        order.setMoney(20000L);
        order.setLastUpdatedBy("2X");
        order.setLastUpdatedTime(new Date());
        this.orderDao.update(order);
    }

    private Card getChen() {
        Card card = new Card();
        card.setId(60L);
        card.setUserName("xman");

        return card;
    }

    private Card getMike() {
        Card card = new Card();
        card.setId(220L);
        card.setUserName("mike");

        return card;
    }

}
