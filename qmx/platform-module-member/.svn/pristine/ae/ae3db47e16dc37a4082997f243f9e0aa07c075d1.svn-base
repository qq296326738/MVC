package com.qmx.member.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.qmx.base.api.base.BaseModel;
import com.qmx.member.enumerate.InviteType;

/**
 * 会员二维码分享
 */
@TableName("gds_invite")
public class GdsInvite extends BaseModel {
    /**
     * 邀请人id
     */
    @TableField("member_id")
    private Long memberId;
    /**
     * 被邀人openid
     */
    @TableField("openid")
    private String openid;
    /**
     * 被邀人id
     */
    @TableField("by_id")
    private Long byId;

    /**
     * 被邀人是否关注公众号
     */
    @TableField("state")
    private Boolean state;
    /**
     * 分享赠送积分
     */
    @TableField("code_integral")
    private Integer codeIntegral;
    /**
     * 分享赠送积分
     */
    @TableField("code_money")
    private Double codeMoney;
    /**
     * 分享类型
     */
    @TableField("invite_type")
    private InviteType inviteType;

    public GdsInvite() {
    }

    public GdsInvite(Long memberId, String openid, Boolean state, Integer codeIntegral, InviteType inviteType) {
        this.memberId = memberId;
        this.openid = openid;
        this.state = state;
        this.codeIntegral = codeIntegral;
        this.inviteType = inviteType;
    }

    public Double getCodeMoney() {
        return codeMoney;
    }

    public Long getById() {
        return byId;
    }

    public void setById(Long byId) {
        this.byId = byId;
    }

    public void setCodeMoney(Double codeMoney) {
        this.codeMoney = codeMoney;
    }

    public InviteType getInviteType() {
        return inviteType;
    }

    public void setInviteType(InviteType inviteType) {
        this.inviteType = inviteType;
    }

    public Integer getCodeIntegral() {
        return codeIntegral;
    }

    public void setCodeIntegral(Integer codeIntegral) {
        this.codeIntegral = codeIntegral;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }
}
