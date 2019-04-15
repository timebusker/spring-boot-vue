package com.timebusker.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "shop_order")
public class ShopOrder implements Serializable {
    /**
     * 订单主键ID
     */
    @Id
    private Long id;

    /**
     * 用户ID
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 商品ID
     */
    @Column(name = "goods_id")
    private Long goodsId;

    /**
     * 商品数量
     */
    private Integer count;

    /**
     * 订单状态
     */
    private Integer status;

    /**
     * 订单金额
     */
    private Double amount;

    /**
     * 支付时间
     */
    @Column(name = "pay_time")
    private Date payTime;

    /**
     * 支付截止时间
     */
    @Column(name = "pay_end_time")
    private Date payEndTime;

    /**
     * 订单创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    private static final long serialVersionUID = 1L;

    /**
     * 获取订单主键ID
     *
     * @return id - 订单主键ID
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置订单主键ID
     *
     * @param id 订单主键ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取用户ID
     *
     * @return user_id - 用户ID
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 设置用户ID
     *
     * @param userId 用户ID
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 获取商品ID
     *
     * @return goods_id - 商品ID
     */
    public Long getGoodsId() {
        return goodsId;
    }

    /**
     * 设置商品ID
     *
     * @param goodsId 商品ID
     */
    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    /**
     * 获取商品数量
     *
     * @return count - 商品数量
     */
    public Integer getCount() {
        return count;
    }

    /**
     * 设置商品数量
     *
     * @param count 商品数量
     */
    public void setCount(Integer count) {
        this.count = count;
    }

    /**
     * 获取订单状态
     *
     * @return status - 订单状态
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置订单状态
     *
     * @param status 订单状态
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取订单金额
     *
     * @return amount - 订单金额
     */
    public Double getAmount() {
        return amount;
    }

    /**
     * 设置订单金额
     *
     * @param amount 订单金额
     */
    public void setAmount(Double amount) {
        this.amount = amount;
    }

    /**
     * 获取支付时间
     *
     * @return pay_time - 支付时间
     */
    public Date getPayTime() {
        return payTime;
    }

    /**
     * 设置支付时间
     *
     * @param payTime 支付时间
     */
    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    /**
     * 获取支付截止时间
     *
     * @return pay_end_time - 支付截止时间
     */
    public Date getPayEndTime() {
        return payEndTime;
    }

    /**
     * 设置支付截止时间
     *
     * @param payEndTime 支付截止时间
     */
    public void setPayEndTime(Date payEndTime) {
        this.payEndTime = payEndTime;
    }

    /**
     * 获取订单创建时间
     *
     * @return create_time - 订单创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置订单创建时间
     *
     * @param createTime 订单创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", goodsId=").append(goodsId);
        sb.append(", count=").append(count);
        sb.append(", status=").append(status);
        sb.append(", amount=").append(amount);
        sb.append(", payTime=").append(payTime);
        sb.append(", payEndTime=").append(payEndTime);
        sb.append(", createTime=").append(createTime);
        sb.append("]");
        return sb.toString();
    }
}