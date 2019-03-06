package com.qmx.member.query;

import com.qmx.base.api.dto.QueryDto;
import com.qmx.member.enumerate.RechargeType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel
public class GdsMemberMoneyVO extends QueryDto {
    @ApiModelProperty(value = "id", example = "id")
    private Long id;
    private Long memberId;
    /**
     * 订单号
     */
    private String sn;
    /**
     * 供应商id
     */
    private Long supplierId;
    /**
     * 集团供应商id
     */
    private Long groupSupplierId;
    /**
     * 充值/消费时间
     */
    private Date time;
    /**
     * 充值/消费金额
     */
    private Double money;
    /**
     * 订单内容
     */
    private String snText;
    /**
     * 实际(充值/消费)金额
     */
    private Double actualMoney;
    /**
     * 充值来源
     */
    private RechargeType rechargeType;
    /**
     * 余额
     */
    private Double balanceMoney;
    /**
     * 产品id
     */
    private Long productId;
    /**
     * 产品名称
     */
    private String productName;
    /**
     * 赠送积分
     */
    private Integer integral;
    /**
     * 赠送金额
     */
    private Double donationMoney;
    /**
     * 记录种类
     */
    private Boolean recordType;
    /**
     * 订单状态
     */
    private Boolean status;
    /**
     * 同步状态(线上线下同步)
     */
    private Boolean synState;
    /**
     * 线下会员id
     */
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

    public void setSnText(String snText) {
        this.snText = snText;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
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
        return this.recordType;
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
