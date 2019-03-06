package com.qmx.member.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.qmx.base.api.base.BaseModel;
import com.qmx.member.enumerate.DeliverType;
import com.qmx.member.enumerate.ExchangeProductType;

import java.util.Date;

/**
 * 兑换商品添加
 */
@TableName("gds_member_exchange")
public class GdsMemberExchange extends BaseModel {
    /**
     * 供应商id
     */
    @TableField("supplier_id")
    private Long supplierId;
    /**
     * 集团供应商id
     */
    @TableField("group_supplier_id")
    private Long groupSupplierId;
    /**
     * 商品类别
     */
    @TableField("exchange_product_type")
    private ExchangeProductType exchangeProductType;
    /**
     * 图片
     */
    @TableField("image")
    private String image;
    /**
     * 商品说明
     */
    @TableField("text")
    private String text;
    /**
     * 商品名字
     */
    @TableField("product_name")
    private String productName;
    /**
     * 所需积分
     */
    @TableField("integeral")
    private Integer integeral;
    /**
     * 发货方式
     */
    @TableField("deliver_type")
    private DeliverType deliverType;
    /**
     * 过期时间
     */
    @TableField("expiry_time")
    private Date expiryTime;
    /**
     * 商品状态(0过期,1正常)
     */
    @TableField("state")
    private Boolean state;

    @TableField("money")
    private Double money;

    @TableField("integral_proportion")
    private Double integralProportion;

    public Double getIntegralProportion() {
        return integralProportion;
    }

    public void setIntegralProportion(Double integralProportion) {
        this.integralProportion = integralProportion;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public Long getGroupSupplierId() {
        return groupSupplierId;
    }

    public void setGroupSupplierId(Long groupSupplierId) {
        this.groupSupplierId = groupSupplierId;
    }

    public Date getExpiryTime() {
        return expiryTime;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    public void setExpiryTime(Date expiryTime) {
        this.expiryTime = expiryTime;
    }

    public ExchangeProductType getExchangeProductType() {
        return exchangeProductType;
    }

    public void setExchangeProductType(ExchangeProductType exchangeProductType) {
        this.exchangeProductType = exchangeProductType;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getIntegeral() {
        return integeral;
    }

    public void setIntegeral(Integer integeral) {
        this.integeral = integeral;
    }

    public DeliverType getDeliverType() {
        return deliverType;
    }

    public void setDeliverType(DeliverType deliverType) {
        this.deliverType = deliverType;
    }
}
