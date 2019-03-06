package com.qmx.member.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.qmx.base.api.base.BaseModel;
import com.qmx.member.enumerate.ProductType;

/**
 * 对应等级关联产品
 */
@TableName("gds_member_associated")
public class GdsMemberAssociated extends BaseModel {
    /**
     * 优惠(消费)(多倍积分)规则ID
     */
    @TableField("ids")
    private Long ids;
    /**
     * 产品ID
     */
    @TableField("product_id")
    private Long productId;
    /**
     * 产品名字
     */
    @TableField("product_name")
    private String productName;
    /**
     * 产品类别
     */
    @TableField("product_type")
    private ProductType productType;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
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
}
