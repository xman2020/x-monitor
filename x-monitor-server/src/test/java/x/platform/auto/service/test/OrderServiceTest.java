package x.platform.auto.service.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import x.framework.lang.Result;
import x.framework.page.Page;
import x.framework.page.PageResult;
import x.platform.auto.dmo.Order;
import x.platform.auto.dto.OrderQueryDto;
import x.platform.auto.service.intf.OrderService;
import x.platform.monitor.Application;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 订单服务测试
 *
 * @author shuyuan
 * @date 2020-04-12
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @Test
    public void testInsert() {
        Order order = new Order();
        order.setName("西瓜11");
        order.setMemo("好甜的西瓜");
        order.setPrice(BigDecimal.valueOf(6.20d));
        order.setAmount(4L);
        order.setTotal(order.getPrice().multiply(BigDecimal.valueOf(order.getAmount())));
        order.setCreateUser("nanxi");
        order.setCreateTime(new Date());
        order.setUpdateUser(order.getCreateUser());
        order.setUpdateTime(order.getCreateTime());
        Result result = this.orderService.insert(order);
    }

    @Test
    public void testUpdate() {
        // Result<Order> result = this.orderService.getById(4);
        // Order order = result.getData();
        Order order = new Order();
        order.setId(4L);
        order.setName("西瓜");
        order.setMemo("好大的西瓜");
        order.setUpdateUser("nanxi");
        order.setUpdateTime(new Date());
        Result result1 = this.orderService.update(order);

        // 4	西瓜u		4.52	3	13.56	dongdong	huihui	2020-03-27 18:34:02	2020-03-27 18:38:38
        // 4	西瓜	好大的西瓜	4.52	3	13.56	dongdong	nanxi	2020-03-27 18:34:02	2020-04-12 23:32:49
    }

    @Test
    public void testDelete() {
        Result result = this.orderService.deleteById(5);
    }

    @Test
    public void testGetById() {
        Result<Order> result = this.orderService.getById(1);
    }

    @Test
    public void testSelectSimple() throws ParseException {
        OrderQueryDto queryDto = new OrderQueryDto();
        // queryDto.setName("西瓜");

        queryDto.setUpdateTimeFrom(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2020-03-27 14:00:00"));
        queryDto.setUpdateTimeTo(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2020-04-13 18:23:00"));

        Result<List<Order>> list = this.orderService.Query(queryDto);
    }

    @Test
    public void testSelectByPage() throws ParseException {
        OrderQueryDto queryDto = new OrderQueryDto();
        // queryDto.setName("西瓜");

        queryDto.setUpdateTimeFrom(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2020-03-27 14:00:00"));
        queryDto.setUpdateTimeTo(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2020-04-13 18:23:00"));

        Page page = new Page();
        page.setPageSize(2);
        page.setCurrentPage(2);
        queryDto.setPage(page);

        PageResult<Order> list = this.orderService.QueryByPage(queryDto);
    }

}
