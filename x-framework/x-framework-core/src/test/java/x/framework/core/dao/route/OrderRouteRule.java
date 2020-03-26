/**
 * xxx
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package x.framework.core.dao.route;

import org.springframework.stereotype.Component;

import x.framework.core.dao.Card;
import x.framework.core.dao.RouteContext;
import x.framework.core.dao.RouteRule;

/**
 * 订单路由规则
 * 
 * @author xman 2012-5-22
 */
@Component("orderRouteRule")
public class OrderRouteRule implements RouteRule<Card> {

    /**
     * {@inheritDoc}
     */
    public void execute(Card card) {
        long cardId = card.getId();
        String datasource;
        String order;

        if (cardId <= 100) {
            datasource = "datasource1";

            if (cardId <= 50) {
                order = "T_ORDER_1";
            } else {
                order = "T_ORDER_2";
            }
        } else {
            datasource = "datasource2";

            if (cardId <= 150) {
                order = "T_ORDER_1";
            } else {
                order = "T_ORDER_2";
            }
        }

        RouteContext.get().setDatasource(datasource);
        RouteContext.get().put("order", order);
    }

}
