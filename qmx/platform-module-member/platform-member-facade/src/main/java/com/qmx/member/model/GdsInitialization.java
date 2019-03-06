package com.qmx.member.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.qmx.base.api.base.BaseModel;

/**
 * 会员初始化设置
 */
@TableName("gds_initialization")
public class GdsInitialization extends BaseModel {
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
     * 客服电话
     */
    @TableField("mobile")
    private String mobile;
    /**
     * 集团供应商id
     */
    @TableField("group_supplier_id")
    private Long groupSupplierId;
    /**
     * 注册第一次赠送积分
     */
    @TableField("integral")
    private Integer integral;
    /**
     * 初始化密码
     */
    @TableField("password")
    private String password;

    /**
     * 每日签到积分
     */
    @TableField("daily_integral")
    private Integer dailyIntegral;
    /**
     * 连续签到天数
     */
    @TableField("daily")
    private Integer daily;
    /**
     * 达到连续签到天数获得积分
     */
    @TableField("reward_integral")
    private Integer rewardIntegral;

    /**
     * 分享获得积分
     */
    @TableField("code_integral")
    private Integer codeIntegral;

    /**
     * 分享获得金额
     */
    @TableField("code_money")
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
