package x.platform.auto.service.impl;

import org.springframework.stereotype.Service;
import x.framework.core.service.BaseServiceImpl;
import x.platform.auto.dao.intf.OrderDao;
import x.platform.auto.dmo.Order;
import x.platform.auto.dto.OrderQueryDto;
import x.platform.auto.service.intf.OrderService;

/**
 * 订单服务实现
 *
 * @author shuyuan
 * @date 2020-04-12
 */
@Service
public class OrderServiceImpl extends BaseServiceImpl<Order, OrderQueryDto, OrderDao> implements OrderService {

}
