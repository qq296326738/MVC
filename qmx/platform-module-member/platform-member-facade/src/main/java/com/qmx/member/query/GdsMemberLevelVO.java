package com.qmx.member.query;

import com.qmx.base.api.dto.QueryDto;
import io.swagger.annotations.ApiModel;

@ApiModel
public class GdsMemberLevelVO extends QueryDto {
    private Long id;
    private String name;
    //是否可以升级
    private Boolean levelLock;
    //升级所需积分
    private Long integral;
    //升级等级id
    private Long upgradeId;
    private Long memberId;
    private Long supplierId;
    private Long groupSupplierId;

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public Long getGroupSupplierId() {
        return groupSupplierId;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public void setGroupSupplierId(Long groupSupplierId) {
        this.groupSupplierId = groupSupplierId;
    }

    public Long getUpgradeId() {
        return upgradeId;
    }

    public void setUpgradeId(Long upgradeId) {
        this.upgradeId = upgradeId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
