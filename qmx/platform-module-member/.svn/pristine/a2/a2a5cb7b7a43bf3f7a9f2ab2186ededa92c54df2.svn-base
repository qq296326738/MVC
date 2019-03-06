package com.qmx.member.query;

import com.qmx.base.api.dto.QueryDto;
import com.qmx.member.enumerate.ProductType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.HashMap;
import java.util.List;

@ApiModel
public class GdsMemberDiscountVO extends QueryDto {
    @ApiModelProperty(value = "id", example = "id")
    private Long id;
    private Long levelId;
    private String levelName;
    //折扣率
    private String rate;
    //是否与优惠券叠加
    private Boolean superposition;
    private Long memberId;
    private Long supplierId;
    private Long groupSupplierId;

    private List<String> ids;

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

    private HashMap<ProductType, List<GdsMemberAssociatedVO>> map;

    public List<String> getIds() {
        return ids;
    }

    public void setIds(List<String> ids) {
        this.ids = ids;
    }

    public HashMap<ProductType, List<GdsMemberAssociatedVO>> getMap() {
        return map;
    }

    public void setMap(HashMap<ProductType, List<GdsMemberAssociatedVO>> map) {
        this.map = map;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLevelId() {
        return levelId;
    }

    public void setLevelId(Long levelId) {
        this.levelId = levelId;
    }

    public String getRate() {
        return rate;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public Boolean getSuperposition() {
        return superposition;
    }

    public void setSuperposition(Boolean superposition) {
        this.superposition = superposition;
    }
}
