package com.qmx.member.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.qmx.base.api.base.BaseModel;
import com.qmx.member.enumerate.RechargeType;
import com.qmx.member.enumerate.SourceType;

import java.util.Date;

/**
 * 会员积分详情
 */
@TableName("gds_member_integeral")
public class GdsMemberIntegeral extends BaseModel {
    /**
     * 所属人id
     */
    @TableField("member_id")
    private Long memberId;
    /**
     * 金额,兑换与签到记录关联id
     */
    @TableField("record_id")
    private Long recordId;
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
     * 获得/使用积分时间
     */
    @TableField("time")
    private Date time;
    /**
     * 充值/消费积分
     */
    @TableField("integeral")
    private Integer integeral;
    /**
     * 积分来源
     */
    @TableField("source_type")
    private SourceType sourceType;
    /**
     * 充值消费方式
     */
    @TableField("recharge_type")
    private RechargeType rechargeType;
    /**
     * 剩余积分
     */
    @TableField("balance_integeral")
    private Integer balanceIntegeral;
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
     * 记录种类 false(消费积分)true(获得积分)
     */
    @TableField("record_type")
    private Boolean recordType;
    /**
     * 订单状态
     */
    @TableField("status")
    private Boolean status;
    /**
     * 线下会员id
     */
    @TableField("fz_id")
    private String fzId;

    /**
     * 同步状态(线上线下同步)
     */
    @TableField("syn_state")
    private Boolean synState;

    @TableField(exist = false)
    private Double money;

    @TableField(exist = false)
    private Double actualMoney;

    @TableField(exist = false)
    private Double donationMoney;

    public Double getDonationMoney() {
        return donationMoney;
    }

    public void setDonationMoney(Double donationMoney) {
        this.donationMoney = donationMoney;
    }

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


    public Long getRecordId() {
        return recordId;
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

    public void setRecordId(Long recordId) {
        this.recordId = recordId;
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

    public RechargeType getRechargeType() {
        return rechargeType;
    }

    public void setRechargeType(RechargeType rechargeType) {
        this.rechargeType = rechargeType;
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

    public Integer getIntegeral() {
        return integeral;
    }

    public void setIntegeral(Integer integeral) {
        this.integeral = integeral;
    }

    public SourceType getSourceType() {
        return sourceType;
    }

    public void setSourceType(SourceType sourceType) {
        this.sourceType = sourceType;
    }

    public Integer getBalanceIntegeral() {
        return balanceIntegeral;
    }

    public void setBalanceIntegeral(Integer balanceIntegeral) {
        this.balanceIntegeral = balanceIntegeral;
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
