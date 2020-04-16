package x.platform.auto.dmo;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

/**
 * 订单
 *
 * @author xman
 * @date 2020-03-27
 */
@Table(name = "auto_order")
public class Order {

    //DROP TABLE IF EXISTS `auto_order`;
    // CREATE TABLE `auto_order` (
    //   `id` int NOT NULL AUTO_INCREMENT,
    //   `name` varchar(100),
    //   `desc` text,
    //   `price` decimal(10,2),
    //   `amount` bigint,
    //   `total` decimal(10,2),
    //   `create_user` varchar(32),
    //   `update_user` varchar(32),
    //   `create_time` datetime,
    //   `update_time` datetime,
    //   PRIMARY KEY (`id`)
    // ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String memo;

    private BigDecimal price;

    private Long amount;

    private BigDecimal total;

    private String createUser;

    private String updateUser;

    private Date createTime;

    private Date updateTime;

    @Transient
    private String test;

    public String getTest() {
        return test;
    }
    @Transient

    public void setTest(String test) {
        this.test = test;
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

}
