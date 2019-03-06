package com.qmx.member.query;

import com.qmx.base.api.dto.QueryDto;
import com.qmx.member.enumerate.IntegralType;

import java.util.Date;
import java.util.List;

/**
 * 多倍积分
 */

public class GdsMemberActivityIntegralVO extends QueryDto {

    private Long id;

    /**
     * 等级id
     */

    private Long levelId;
    /**
     * 等级名称
     */

    private String levelName;

    /**
     * 开始时间
     */
    private Date startTime;
    /**
     * 结束时间
     */

    private Date endTime;
    /**
     * 多倍类型
     */

    private IntegralType type;

    /**
     * 多倍倍数
     */

    private Double multiple;

    /**
     * 商品id集合
     */
    private List<String> ids;

    public List<String> getIds() {
        return ids;
    }

    public void setIds(List<String> ids) {
        this.ids = ids;
    }

    public GdsMemberActivityIntegralVO() {
    }

    public Long getLevelId() {
        return levelId;
    }

    public void setLevelId(Long levelId) {
        this.levelId = levelId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
