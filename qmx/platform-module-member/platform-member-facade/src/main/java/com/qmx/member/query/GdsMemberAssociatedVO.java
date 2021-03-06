package com.qmx.member.query;

import com.qmx.base.api.dto.QueryDto;
import com.qmx.member.enumerate.ProductType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class GdsMemberAssociatedVO extends QueryDto {
    @ApiModelProperty(value = "id", example = "id")
    private Long id;
    /**
     * 优惠(消费)规则ID
     */
    private Long ids;
    /**
     * 产品ID
     */
    private Long productId;
    /**
     * 产品名字
     */
    private String productName;
    /**
     * 产品类别
     */
    private ProductType productType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Long getIds() {
        return ids;
    }

    public void setIds(Long ids) {
        this.ids = ids;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }
}
