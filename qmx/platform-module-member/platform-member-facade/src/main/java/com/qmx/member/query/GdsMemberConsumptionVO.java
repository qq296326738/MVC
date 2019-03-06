package com.qmx.member.query;

import com.qmx.base.api.dto.QueryDto;
import com.qmx.member.enumerate.ProductType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.HashMap;
import java.util.List;

@ApiModel
public class GdsMemberConsumptionVO extends QueryDto {
    @ApiModelProperty(value = "id", example = "id")
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
     * 会员等级
     */
    private Long levelId;
    /**
     * 会员等级名称
     */
    private String levelName;
    /**
     * 积分比例
     */
    private Double integralProportion;

    private List<String> ids;
    private HashMap<ProductType, List<GdsMemberAssociatedVO>> map;


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

    public HashMap<ProductType, List<GdsMemberAssociatedVO>> getMap() {
        return map;
    }

    public void setMap(HashMap<ProductType, List<GdsMemberAssociatedVO>> map) {
        this.map = map;
    }

    public List<String> getIds() {
        return ids;
    }

    public void setIds(List<String> ids) {
        this.ids = ids;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public Long getLevelId() {
        return levelId;
    }

    public void setLevelId(Long levelId) {
        this.levelId = levelId;
    }

    public Double getIntegralProportion() {
        return integralProportion;
    }

    public void setIntegralProportion(Double integralProportion) {
        this.integralProportion = integralProportion;
    }
}
