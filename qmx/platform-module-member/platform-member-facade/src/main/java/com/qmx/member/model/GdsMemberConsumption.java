package com.qmx.member.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.qmx.base.api.base.BaseModel;
import com.qmx.member.enumerate.ProductType;

import java.util.HashMap;
import java.util.List;

/**
 * 会员消费规则管理
 */
@TableName("gds_member_consumption")
public class GdsMemberConsumption extends BaseModel {
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
     * 会员等级
     */
    @TableField("level_id")
    private Long levelId;
    /**
     * 会员等级名称
     */
    @TableField("level_name")
    private String levelName;
    /**
     * 积分比例
     */
    @TableField("integral_proportion")
    private Double integralProportion;

    @TableField(exist = false)
    private List<String> ids;

    @TableField(exist = false)
    private HashMap<ProductType, List<GdsMemberAssociated>> map;

    public HashMap<ProductType, List<GdsMemberAssociated>> getMap() {
        return map;
    }

    public void setMap(HashMap<ProductType, List<GdsMemberAssociated>> map) {
        this.map = map;
    }

    public List<String> getIds() {
        return ids;
    }

    public void setIds(List<String> ids) {
        this.ids = ids;
    }

    public Long getLevelId() {
        return levelId;
    }

    public void setLevelId(Long levelId) {
        this.levelId = levelId;
    }

    public Long getMemberId() {
        return memberId;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
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

    public Double getIntegralProportion() {
        return integralProportion;
    }

    public void setIntegralProportion(Double integralProportion) {
        this.integralProportion = integralProportion;
    }
}
