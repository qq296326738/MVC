package com.qmx.member.query;

import com.qmx.base.api.dto.QueryDto;
import com.qmx.member.enumerate.GiveType;
import com.qmx.member.enumerate.RuleType;
import io.swagger.annotations.ApiModel;

@ApiModel
public class GdsMemberRechargeRuleVO extends QueryDto {

    private Long id;

    /**
     * 所属人id
     */
    private Long memberId;
    /**
     * 供应商id
     */
    private Long supplierId;
    /**
     * 集团供应商id
     */
    private Long groupSupplierId;

    /**
     * 会员等级
     */
    private Long levelId;
    /**
     * 会员等级名称
     */
    private String levelName;
    /**
     * 规则类型（区间,固定）
     */
    private RuleType type;
    /**
     * 充值金额
     */
    private Double amount;

    /**
     * 最小充值金额
     */
    private Double minAmount;
    /**
     * 最大充值金额
     */
    private Double maxAmount;
    /**
     * 赠送类型
     */
    private GiveType give;
    /**
     * 积分比例(充值10元送1积分)
     */
    private Double integralPoint;
    /**
     * 金额赠送比例(充值10元送1元)
     */
    private Double moneyPoint;
    /**
     * 金额折扣比例(充9元得10元)
     */
    private Double discountPoint;

    public Long getSupplierId() {
        return supplierId;
    }

    public Long getGroupSupplierId() {
        return groupSupplierId;
    }

    public Double getDiscountPoint() {
        return discountPoint;
    }

    public void setDiscountPoint(Double discountPoint) {
        this.discountPoint = discountPoint;
    }

    public Long getId() {
        return id;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public void setGroupSupplierId(Long groupSupplierId) {
        this.groupSupplierId = groupSupplierId;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
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
