package x.platform.auto.dto;

import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;
import x.framework.core.dao.mybatis.BaseQueryDto;
import x.platform.auto.dmo.Order;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单查询DTO
 *
 * @author shuyuan
 * @date 2020-04-12
 */
public class OrderQueryDto extends BaseQueryDto {

    private Long id;

    private String name;

    private String memo;

    private BigDecimal price;

    private BigDecimal priceFrom;

    private BigDecimal priceTo;

    private Long amount;

    private BigDecimal total;

    private String createUser;

    private String updateUser;

    private Date createTime;

    private Date updateTime;

    private Date updateTimeFrom;

    private Date updateTimeTo;

    public Condition getCondition() {
        Condition condition = new Condition(Order.class);
        Example.Criteria criteria = condition.createCriteria();

        criteria.andLike("name", this.getName());
        criteria.andLike("memo", this.getMemo());
        criteria.andBetween("price", this.getPriceFrom(), this.getPriceTo());
        criteria.andGreaterThan("amount", this.getAmount());
        criteria.andBetween("updateTime", this.getUpdateTimeFrom(), this.getUpdateTimeTo());

        condition.orderBy("updateTime").desc();

        return condition;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String desc) {
        this.memo = desc;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public BigDecimal getPriceFrom() {
        return priceFrom;
    }

    public BigDecimal getPriceTo() {
        return priceTo;
    }

    public Date getUpdateTimeFrom() {
        return updateTimeFrom;
    }

    public Date getUpdateTimeTo() {
        return updateTimeTo;
    }

}
