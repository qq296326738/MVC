package com.qmx.member.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.qmx.base.api.base.BaseModel;

import java.util.Date;

/**
 * 会员签到
 */
@TableName("gds_member_sign")
public class GdsMemberSign extends BaseModel {
    /**
     * 所属人ID
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
     * 签到时间
     */
    @TableField("time")
    private Date time;
    /**
     * 获得积分
     */
    @TableField("integral")
    private Integer integral;
    /**
     * 连续签到次数
     */
    @TableField("continuous_sign")
    private Integer continuousSign;

    //  本月签到
    @TableField(exist = false)
    private Integer monthSign;

    //  累计签到
    @TableField(exist = false)
    private Integer cumulativeSign;

    //  累计奖励
    @TableField(exist = false)
    private Integer cumulativeRewards;

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

    public Integer getMonthSign() {
        return monthSign;
    }

    public void setMonthSign(Integer monthSign) {
        this.monthSign = monthSign;
    }

    public Integer getCumulativeSign() {
        return cumulativeSign;
    }

    public void setCumulativeSign(Integer cumulativeSign) {
        this.cumulativeSign = cumulativeSign;
    }

    public Integer getCumulativeRewards() {
        return cumulativeRewards;
    }

    public void setCumulativeRewards(Integer cumulativeRewards) {
        this.cumulativeRewards = cumulativeRewards;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Integer getIntegral() {
        return integral;
    }

    public void setIntegral(Integer integral) {
        this.integral = integral;
    }

    public Integer getContinuousSign() {
        return continuousSign;
    }

    public void setContinuousSign(Integer continuousSign) {
        this.continuousSign = continuousSign;
    }
}
