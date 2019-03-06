package com.qmx.member.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.qmx.base.api.base.BaseModel;
import com.qmx.member.enumerate.RechargeType;

import java.util.Date;

/**
 * 会员金额记录
 */
@TableName("gds_member_money")
public class GdsMemberMoney extends BaseModel {
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
     * 订单号
     */
    @TableField("sn")
    private String sn;
    /**
     * 订单内容
     */
    @TableField("sn_text")
    private String snText;
    /**
     * 充值/消费时间
     */
    @TableField("time")
    private Date time;
    /**
     * 充值/消费金额
     */
    @TableField("money")
    private Double money;
    /**
     * 实际(充值/消费)金额
     */
    @TableField("actual_money")
    private Double actualMoney;
    /**
     * 充值消费方式
     */
    @TableField("recharge_type")
    private RechargeType rechargeType;
    /**
     * 余额
     */
    @TableField("balance_money")
    private Double balanceMoney;
    /**
     * 产品id
     */
    @TableField("product_id")
    private Long productId;
    /**
     * 产品名称
     */
    @TableField("product_name")
    private String productName;
    /**
     * 赠送积分
     */
    @TableField("integral")
    private Integer integral;
    /**
     * 赠送(消费优惠)金额
     */
    @TableField("donation_money")
    private Double donationMoney;
    /**
     * 记录种类(true充值,false消费)
     */
    @TableField("record_type")
    private Boolean recordType;
    /**
     * 订单状态
     */
    @TableField("status")
    private Boolean status;

    /**
     * 同步状态(线上线下同步)
     */
    @TableField("syn_state")
    private Boolean synState;

    /**
     * 线下会员id
     */
    @TableField("fz_id")
    private String fzId;

    public String getFzId() {
        return fzId;
    }

    public void setFzId(String fzId) {
        this.fzId = fzId;
    }

    public Boolean getSynState() {
        return synState;
    }

    public void setSynState(Boolean synState) {
        this.synState = synState;
    }

    public String getSnText() {
        return snText;
    }

    public void setSnText(String snText) {
        this.snText = snText;
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

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public Double getActualMoney() {
        return actualMoney;
    }

    public void setActualMoney(Double actualMoney) {
        this.actualMoney = actualMoney;
    }

    public RechargeType getRechargeType() {
        return rechargeType;
    }

    public void setRechargeType(RechargeType rechargeType) {
        this.rechargeType = rechargeType;
    }

    public Double getBalanceMoney() {
        return balanceMoney;
    }

    public void setBalanceMoney(Double balanceMoney) {
        this.balanceMoney = balanceMoney;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getIntegral() {
        return integral;
    }

    public void setIntegral(Integer integral) {
        this.integral = integral;
    }

    public Double getDonationMoney() {
        return donationMoney;
    }

    public void setDonationMoney(Double donationMoney) {
        this.donationMoney = donationMoney;
    }

    public Boolean getRecordType() {
        return recordType;
    }

    public void setRecordType(Boolean recordType) {
        this.recordType = recordType;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
