package com.qmx.member.query;

import com.qmx.base.api.dto.QueryDto;

import java.util.Date;

public class GdsMemberSignVO extends QueryDto {
    //  id
    private Long id;

    private Long supplierId;

    private Long groupSupplierId;

    //  会员id
    private Long memberId;

    //  签到时间
    private Date time;

    //  获得积分
    private Integer integral;

    //  连续签到次数
    private Integer continuousSign;

    //  本月签到
    private Integer monthSign;

    //  累计签到
    private Integer cumulativeSign;

    //  累计奖励
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
}
