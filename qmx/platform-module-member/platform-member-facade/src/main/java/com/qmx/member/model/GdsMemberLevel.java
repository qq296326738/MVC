package com.qmx.member.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.qmx.base.api.base.BaseModel;

/**
 * 会员等级管理
 */
@TableName("gds_member_level")
public class GdsMemberLevel extends BaseModel {
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
     * 等级名称
     */
    @TableField("name")
    private String name;
    /**
     * 升级等级Id
     */
    @TableField("upgrade_id")
    private Long upgradeId;
    /**
     * 等级是否锁定（是否可以升级）
     */
    @TableField("level_lock")
    private Boolean levelLock;
    /**
     * 升级所需积分
     */
    @TableField("integral")
    private Long integral;

    public Long getUpgradeId() {
        return upgradeId;
    }

    public void setUpgradeId(Long upgradeId) {
        this.upgradeId = upgradeId;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getLevelLock() {
        return levelLock;
    }

    public void setLevelLock(Boolean levelLock) {
        this.levelLock = levelLock;
    }

    public Long getIntegral() {
        return integral;
    }

    public void setIntegral(Long integral) {
        this.integral = integral;
    }
}
