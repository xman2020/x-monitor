/**
 * xxx
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package x.framework.core.dao.route;

import org.apache.ibatis.type.Alias;

import x.framework.core.entity.BaseEntity;

/**
 * 订单
 *
 * @author xman 2012-5-23
 */
@Alias("Order")
public class Order extends BaseEntity {

    private static final long serialVersionUID = -4638218332095743529L;

    /** 订单名称 */
    private String orderName;
    
    /** 金额 */
    private Long money;
    
    /** 买家ID */
    private Long buyerId;
    
    /** 买家名 */
    private String buyerName;

    /**
     * @return the orderName
     */
    public String getOrderName() {
        return orderName;
    }

    /**
     * @param orderName the orderName to set
     */
    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    /**
     * @return the money
     */
    public Long getMoney() {
        return money;
    }

    /**
     * @param money the money to set
     */
    public void setMoney(Long money) {
        this.money = money;
    }

    /**
     * @return the buyerId
     */
    public Long getBuyerId() {
        return buyerId;
    }

    /**
     * @param buyerId the buyerId to set
     */
    public void setBuyerId(Long buyerId) {
        this.buyerId = buyerId;
    }

    /**
     * @return the buyerName
     */
    public String getBuyerName() {
        return buyerName;
    }

    /**
     * @param buyerName the buyerName to set
     */
    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }
    
    
}
