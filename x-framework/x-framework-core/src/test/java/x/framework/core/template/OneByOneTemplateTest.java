/**
 * xxx
 * Copyright (c) 2011-2011 All Rights Reserved.
 */
package x.framework.core.template;

import java.util.Random;

import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import x.framework.core.template.OneByOne;
import x.framework.core.template.OneByOneTemplate;
import x.framework.lang.CallBack;

/**
 * 一个接一个业务处理模版_测试
 * 
 * @author xman 2011-11-10
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext-test.xml" })
public class OneByOneTemplateTest extends TestCase {

    /** logger */
    private static final Logger logger = LoggerFactory.getLogger(OneByOneTemplateTest.class);

    @Autowired
    private OneByOneTemplate    oneByOneTemplate;

    @Test
    public void test1() {
        Boolean result = this.oneByOneTemplate.execute(new OneByOne("PAY_ORDER", "111",
                "BANK_RECEIPT"), new CallBack<Boolean>() {

            public Boolean invoke() {
                return true;
            }
        });

        assertTrue(result);
    }

    // @Test
    public void testMulThread() {
        logger.warn("多线程测试开始");

        for (int i = 0; i < 10; i++) {
            OneThread thread = new OneThread();
            thread.setName("TT__" + i);
            thread.start();
        }

        try {
            Thread.sleep(10000000000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // ====================测试结果====================
        // 使用本机Mysql5测试
        // ------------------------------------------------
        // 30个并发线程，每个线程处理1000次，合计30000次
        // 开始2011-11-17 22:48:47
        // 结束2011-11-17 22:50:36
        // 一共耗时约 110秒
        // 平均每次处理时间100ms左右（为了控制带来的性能损耗）
        // 吞吐量272次/秒
        // 并发冲突处理429次，冲突概率14/1000
        // ------------------------------------------------
        // 2个并发线程，每个线程处理1000次，合计2000次
        // 开始2011-11-17 23:18:37
        // 结束2011-11-17 23:20:20
        // 一共耗时约 103秒
        // 平均每次处理时间100ms左右
        // 吞吐量19次/秒
        // 并发冲突处理4次，冲突概率2/1000
        // ================================================
        // 使用易付宝PRE DB2测试
        // ------------------------------------------------
        // 30个并发线程，每个线程处理1000次，合计30000次
        // 开始2011-11-18 10:57:02
        // 结束2011-11-18 11:00:04
        // 一共耗时约180秒
        // 平均每次处理时间15ms左右，有些大于1s，性能不平均
        // 吞吐量166次/秒
        // 并发冲突处理407次，冲突概率14/1000
        // ------------------------------------------------
        // 2个并发线程，每个线程处理1000次，合计2000次
        // 开始2011-11-18 10:52:58
        // 结束2011-11-18 10:55:11
        // 一共耗时约 133秒
        // 平均每次处理时间15ms左右，有些大于1s，性能不平均
        // 吞吐量15次/秒
        // 并发冲突处理1次，冲突概率0.5/1000
    }

    class OneThread extends Thread {

        public void run() {
            Random r = new Random();

            for (int i = 0; i < 1000; i++) {
                int id = r.nextInt(1000);
                oneByOneTemplate.execute(new OneByOne("PAY_ORDER", String.valueOf(id),
                        "BANK_RECEIPT"), new CallBack<Boolean>() {

                    public Boolean invoke() {
                        return true;
                    }
                });
            }

            logger.warn(this.getName() + "线程执行结束");

            super.run();
        }
    }

}
