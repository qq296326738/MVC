package com.qmx.member.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.qmx.base.api.base.BaseModel;
import com.qmx.member.enumerate.GiveType;
import com.qmx.member.enumerate.RuleType;

/**
 * 充值规则
 */
@TableName("gds_member_recharge_rule")
public class GdsMemberRechargeRule extends BaseModel {
    /**
     * 所属人id
     */
    @TableField("member_id")
    private Long memberId;
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
     * 会员等级
     */
    @TableField("level_id")
    private Long levelId;
    /**
     * 会员等级名称
     */
    @TableField("level_name")
    private String levelName;
    /**
     * 规则类型（区间,固定）
     */
    @TableField("type")
    private RuleType type;
    /**
     * 充值金额
     */
    @TableField("amount")
    private Double amount;

    /**
     * 最小充值金额
     */
    @TableField("min_amount")
    private Double minAmount;
    /**
     * 最大充值金额
     */
    @TableField("max_amount")
    private Double maxAmount;
    /**
     * 赠送类型
     */
    @TableField("give")
    private GiveType give;
    /**
     * 积分比例(充值10元送1积分)
     */
    @TableField("integral_point")
    private Double integralPoint;
    /**
     * 金额赠送比例(充值10元送1元)
     */
    @TableField("money_point")
    private Double moneyPoint;
    /**
     * 金额折扣比例(充9元得10元)
     * */
    @TableField("discount_point")
    private Double discountPoint;

    public Double getDiscountPoint() {
        return discountPoint;
    }

    public void setDiscountPoint(Double discountPoint) {
        this.discountPoint = discountPoint;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
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

    public Long getLevelId() {
        return levelId;
    }

    public void setLevelId(Long levelId) {
        this.levelId = levelId;
    }

    public RuleType getType() {
        return type;
    }

    public void setType(RuleType type) {
        this.type = type;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getMinAmount() {
        return minAmount;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public void setMinAmount(Double minAmount) {
        this.minAmount = minAmount;
    }

    public Double getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(Double maxAmount) {
        this.maxAmount = maxAmount;
    }

    public GiveType getGive() {
        return give;
    }

    public void setGive(GiveType give) {
        this.give = give;
    }

    public Double getIntegralPoint() {
        return integralPoint;
    }

    public void setIntegralPoint(Double integralPoint) {
        this.integralPoint = integralPoint;
    }

    public Double getMoneyPoint() {
        return moneyPoint;
    }

    public void setMoneyPoint(Double moneyPoint) {
        this.moneyPoint = moneyPoint;
    }
}
