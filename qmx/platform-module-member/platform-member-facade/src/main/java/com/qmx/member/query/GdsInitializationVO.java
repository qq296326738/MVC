package com.qmx.member.query;

import com.qmx.base.api.dto.QueryDto;

public class GdsInitializationVO extends QueryDto {
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
     * 客服电话
     */
    private String mobile;
    /**
     * 注册第一次积分
     */
    private Integer integral;
    /**
     * 初始化密码
     */
    private String password;
    /**
     * 每日签到积分
     */
    private Integer dailyIntegral;
    /**
     * 连续签到天数
     */
    private Integer daily;
    /**
     * 达到连续签到天数获得积分
     */
    private Integer rewardIntegral;
    /**
     * 分享获得积分
     */
    private Integer codeIntegral;

    /**
     * 分享获得金额
     */
    private Double codeMoney;

    public Double getCodeMoney() {
        return codeMoney;
    }

    public void setCodeMoney(Double codeMoney) {
        this.codeMoney = codeMoney;
    }

    public Integer getCodeIntegral() {
        return codeIntegral;
    }

    public void setCodeIntegral(Integer codeIntegral) {
        this.codeIntegral = codeIntegral;
    }

    public Integer getDailyIntegral() {
        return dailyIntegral;
    }

    public void setDailyIntegral(Integer dailyIntegral) {
        this.dailyIntegral = dailyIntegral;
    }

    public Integer getDaily() {
        return daily;
    }

    public void setDaily(Integer daily) {
        this.daily = daily;
    }

    public Integer getRewardIntegral() {
        return rewardIntegral;
    }

    public void setRewardIntegral(Integer rewardIntegral) {
        this.rewardIntegral = rewardIntegral;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
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

    public Integer getIntegral() {
        return integral;
    }

    public void setIntegral(Integer integral) {
        this.integral = integral;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
