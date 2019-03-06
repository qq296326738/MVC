package com.qmx.member.query;

import com.qmx.base.api.dto.QueryDto;
import com.qmx.member.enumerate.DeliverType;
import com.qmx.member.enumerate.ExchangeProductType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel
public class GdsMemberExchangeVO extends QueryDto {
    @ApiModelProperty(value = "id", example = "id")
    private Long id;
    /**
     * 供应商id
     */
    private Long supplierId;
    /**
     * 集团供应商id
     */
    private Long groupSupplierId;
    /**
     * 商品类别
     */
    private ExchangeProductType exchangeProductType;
    /**
     * 图片
     */
    private String image;
    /**
     * 商品说明
     */
    private String text;
    /**
     * 商品名字
     */
    private String productName;
    /**
     * 所需积分
     */
    private Integer integeral;
    /**
     * 发货方式
     */
    private DeliverType deliverType;
    private Date expiryTime;
    private Boolean state;
    private Double money;
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

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    public Date getExpiryTime() {
        return expiryTime;
    }

    public void setExpiryTime(Date expiryTime) {
        this.expiryTime = expiryTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
