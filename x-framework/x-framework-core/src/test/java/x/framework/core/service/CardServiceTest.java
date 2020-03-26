/**
 * xxx
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package x.framework.core.service;

import java.util.Date;

import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import x.framework.core.dao.Card;

/**
 * 卡服务测试
 * 
 * @author xman 2012-5-27
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext-test.xml" })
public class CardServiceTest extends TestCase {

    @Autowired
    private CardService cardService;

    @Test
    public void testSave_1() {
        Card card = new Card();
        card.setUserName("xman@163.com");
        card.setRealName("xman1");
        card.setEmail("xman@163.com");
        card.setMobile("13851751606");
        card.setPassword("123456");
        card.setCreatedBy("111111");
        card.setCreatedTime(new Date());

        this.cardService.save(card);

        System.out.println("~~~~~~~~~~~~~~~~~~~card id = " + card.getId());
    }

}
