/**
 * xxx
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package x.framework.core.dao;

import java.util.Date;
import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import x.framework.core.dao.mybatis.DaoMyBatis;
import x.framework.page.Page;

/**
 * 会员卡数据访问测试
 * 
 * @author xman 2012-5-20
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext-test.xml" })
public class CardDaoTest extends TestCase {

    @Autowired
    private CardDao cardDao;
    
    @Autowired
    private DaoMyBatis daoMyBatis;

    @Test
    public void testInsert_1() {
        Card card = new Card();
        card.setUserName("xman002@163.com");
        card.setRealName("xman");
        card.setEmail("xman@163.com");
        card.setMobile("13851751606");
        card.setPassword("123456");
        card.setCreatedBy("111111");
        card.setCreatedTime(new Date());

        this.cardDao.insert(card);

        System.out.println("~~~~~~~~~~~~~~~~~~~card id = " + card.getId());
    }

    @Test
    public void testDelete_1() {
        Card card = new Card();
        card.setUserName("xman@163.com");
        card.setRealName("xman");
        card.setEmail("xman@163.com");
        card.setMobile("13851751606");
        card.setPassword("123456");
        card.setCreatedBy("111111");
        card.setCreatedTime(new Date());

        this.cardDao.insert(card);
        this.cardDao.delete(card);
    }

    @Test
    public void testDelete_2() {
        Card card = new Card();
        card.setUserName("xman@163.com");
        card.setRealName("xman");
        card.setEmail("xman@163.com");
        card.setMobile("13851751606");
        card.setPassword("123456");
        card.setCreatedBy("111111");
        card.setCreatedTime(new Date());

        this.cardDao.insert(card);
        this.cardDao.deleteById(card.getId());
    }
    
    @Test
    public void testUpate_1() {
        //Card card = new Card();
        Card card = this.cardDao.getById(264L);
        //card.setId(210L);
        card.setUserName("xman@hotmail.com");
        card.setRealName("CSY");
        card.setEmail("xman@hotmail.com");
        card.setMobile("18651665749");
        card.setPassword("654321");
        card.setLastUpdatedBy("222222");
        card.setLastUpdatedTime(new Date());
        this.cardDao.update(card);
    }
    
    @Test
    public void testQueryByMobile() {
        List<Card> list = this.cardDao.queryByMobile("13851751606");
        System.out.println("~~~~~~~~~~~~~~~~~~~card count = " + list.size());
    }
    

    @Test
    @SuppressWarnings("serial")
    public void testPageQuery(){
        List<Card> list = this.daoMyBatis.<Card>selectByPage("x.framework.core.dao.Card.queryByMobileCount", 
                "x.framework.core.dao.Card.queryByMobile", "13851751606", new Page() {{setCurrentPage(2); setPageSize(2);}});
        System.out.println("~~~~~~~~~~~~~~~~~~~card count = " + list.size());
    }

}
