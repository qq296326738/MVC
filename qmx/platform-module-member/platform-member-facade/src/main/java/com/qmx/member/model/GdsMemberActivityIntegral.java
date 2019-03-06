package com.qmx.member.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.qmx.base.api.base.BaseModel;
import com.qmx.member.enumerate.IntegralType;
import com.qmx.member.enumerate.ProductType;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * 多倍积分
 */
@TableName("gds_activity_integral")
public class GdsMemberActivityIntegral extends BaseModel {
    /**
     * 等级id
     */
    @TableField("level_id")
    private Long levelId;
    /**
     * 等级名称
     */
    @TableField("level_name")
    private String levelName;

    /**
     * 供应商id
     */
    @TableField("supplier_id")
    private Long supplierId;
    /**
     * 开始时间
     */
    @TableField("start_time")
    private Date startTime;
    /**
     * 结束时间
     */
    @TableField("end_time")
    private Date endTime;
    /**
     * 多倍类型
     */
    @TableField("type")
    private IntegralType type;

    /**
     * 多倍倍数
     */
    @TableField("multiple")
    private Double multiple;

    @TableField(exist = false)
    private HashMap<ProductType, List<GdsMemberAssociated>> map;

    public HashMap<ProductType, List<GdsMemberAssociated>> getMap() {
        return map;
    }

    public void setMap(HashMap<ProductType, List<GdsMemberAssociated>> map) {
        this.map = map;
    }

    public GdsMemberActivityIntegral() {
    }

    public Long getLevelId() {
        return levelId;
    }

    public void setLevelId(Long levelId) {
        this.levelId = levelId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public Double getMultiple() {
        return multiple;
    }

    public void setMultiple(Double multiple) {
        this.multiple = multiple;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public IntegralType getType() {
        return type;
    }

    public void setType(IntegralType type) {
        this.type = type;
    }

}
