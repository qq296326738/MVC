package com.qmx.member.mapper;

import com.qmx.base.core.base.IBaseMapper;
import com.qmx.member.enumerate.ProductType;
import com.qmx.member.model.GdsMemberAssociated;
import com.qmx.member.query.GdsMemberActivityIntegralVO;
import com.qmx.member.query.GdsMemberConsumptionVO;
import com.qmx.member.query.GdsMemberDiscountVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GdsMemberAssociatedMapper extends IBaseMapper<GdsMemberAssociated> {
    /**
     * 获得会员优惠消费关联产品
     *
     * @param id
     * @param type
     * @return
     */
    List<GdsMemberAssociated> findByConsumptionId(@Param("id") Long id, @Param("type") ProductType type);

    /**
     * 删除会员优惠消费关联产品
     *
     * @param id
     */
    void delByConsumptionId(@Param("id") Long id);

    Integer selectCountByConsumptionIds(@Param("data") GdsMemberConsumptionVO dto, @Param("list") List<String> list);

    Integer selectCountDiscountByIds(@Param("data") GdsMemberDiscountVO dto, @Param("list") List<String> list);

    Integer selectCountActivityIntegralIds(@Param("data") GdsMemberActivityIntegralVO vo, @Param("list") List<String> list);
}
