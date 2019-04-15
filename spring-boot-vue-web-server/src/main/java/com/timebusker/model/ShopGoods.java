package com.timebusker.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "shop_goods")
public class ShopGoods implements Serializable {
    /**
     * 商品主键ID
     */
    @Id
    private Long id;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 商品标题
     */
    private String title;

    /**
     * 商品图片地址
     */
    private String image;

    /**
     * 商品库存量
     */
    private Integer stock;

    /**
     * 商品价格
     */
    private Double price;

    /**
     * 秒杀开始时间
     */
    @Column(name = "start_time")
    private Date startTime;

    /**
     * 秒杀结束时间
     */
    @Column(name = "end_time")
    private Date endTime;

    private static final long serialVersionUID = 1L;

    /**
     * 获取商品主键ID
     *
     * @return id - 商品主键ID
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置商品主键ID
     *
     * @param id 商品主键ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取商品名称
     *
     * @return name - 商品名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置商品名称
     *
     * @param name 商品名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取商品标题
     *
     * @return title - 商品标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置商品标题
     *
     * @param title 商品标题
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * 获取商品图片地址
     *
     * @return image - 商品图片地址
     */
    public String getImage() {
        return image;
    }

    /**
     * 设置商品图片地址
     *
     * @param image 商品图片地址
     */
    public void setImage(String image) {
        this.image = image == null ? null : image.trim();
    }

    /**
     * 获取商品库存量
     *
     * @return stock - 商品库存量
     */
    public Integer getStock() {
        return stock;
    }

    /**
     * 设置商品库存量
     *
     * @param stock 商品库存量
     */
    public void setStock(Integer stock) {
        this.stock = stock;
    }

    /**
     * 获取商品价格
     *
     * @return price - 商品价格
     */
    public Double getPrice() {
        return price;
    }

    /**
     * 设置商品价格
     *
     * @param price 商品价格
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * 获取秒杀开始时间
     *
     * @return start_time - 秒杀开始时间
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * 设置秒杀开始时间
     *
     * @param startTime 秒杀开始时间
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * 获取秒杀结束时间
     *
     * @return end_time - 秒杀结束时间
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * 设置秒杀结束时间
     *
     * @param endTime 秒杀结束时间
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", title=").append(title);
        sb.append(", image=").append(image);
        sb.append(", stock=").append(stock);
        sb.append(", price=").append(price);
        sb.append(", startTime=").append(startTime);
        sb.append(", endTime=").append(endTime);
        sb.append("]");
        return sb.toString();
    }
}