package x.platform.auto.dao.test;

import org.apache.ibatis.session.RowBounds;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;
import x.platform.auto.dao.intf.OrderDao;
import x.platform.auto.dmo.Order;
import x.platform.monitor.Application;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class OrderDaoTest {

    @Autowired
    private OrderDao orderDao;

    @Test
    public void testInsert() {
        Order order = new Order();
        order.setName("西瓜");
        order.setMemo("海南的西瓜");
        order.setPrice(BigDecimal.valueOf(4.52d));
        order.setAmount(3L);
        order.setTotal(order.getPrice().multiply(BigDecimal.valueOf(order.getAmount())));
        order.setCreateUser("dongdong");
        order.setCreateTime(new Date());
        this.orderDao.insert(order);
    }

    @Test
    public void testUpdate() {
        Order order = this.orderDao.selectByPrimaryKey(4);
        order.setMemo(null);
        order.setName(order.getName() + "u");
        order.setUpdateUser("huihui");
        order.setUpdateTime(new Date());
        this.orderDao.updateByPrimaryKey(order);
        // 更新前：4	西瓜	海南的西瓜	4.52	3	13.56	dongdong		2020-03-27 18:34:02
        // 更新后：4	西瓜u		4.52	3	13.56	dongdong	huihui	2020-03-27 18:34:02	2020-03-27 18:38:38

        //this.orderDao.updateByPrimaryKeySelective(order);

    }

    @Test
    public void testDelete() {
        this.orderDao.deleteByPrimaryKey(3);
    }

    @Test
    public void testGetById() {
        Order order = this.orderDao.selectByPrimaryKey(1);
    }

    @Test
    public void testSelectSimple() throws ParseException {
        Condition condition = new Condition(Order.class);
        Example.Criteria criteria = condition.createCriteria();

        // criteria.andEqualTo("name", "苹果");
        // criteria.andGreaterThan("total", 100);

        //criteria.andLike("name", "%西瓜%");

        Date date1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2020-03-27 14:00:00");
        Date date2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2020-03-27 18:23:00");
        criteria.andBetween("createTime", date1, date2);

        condition.orderBy("createTime").desc();

        List<Order> list = this.orderDao.selectByExample(condition);
    }

    @Test
    public void testSelectByPage() throws ParseException {
        Condition condition = new Condition(Order.class);
        Example.Criteria criteria = condition.createCriteria();

        Date date1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2020-03-27 14:00:00");
        Date date2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2020-03-30 18:23:00");
        criteria.andBetween("createTime", date1, date2);

        condition.orderBy("createTime").desc();

        RowBounds rowbounds = new RowBounds(2, 2);

        List<Order> list = this.orderDao.selectByExampleAndRowBounds(condition, rowbounds);
    }

    //@Test
    public void generate() {

    }

}
