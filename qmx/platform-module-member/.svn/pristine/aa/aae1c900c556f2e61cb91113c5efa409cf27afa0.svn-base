package com.qmx.member.mapper;

import com.qmx.base.core.base.IBaseMapper;
import com.qmx.coreservice.model.SysUser;
import com.qmx.member.model.GdsMember;
import com.qmx.member.model.GdsMemberDiscount;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GdsMemberDiscountMapper extends IBaseMapper<GdsMemberDiscount> {
    /**
     * 根据等级id查询会员优惠规则是否存在
     *
     * @param currentUser
     * @param levelId
     * @return
     */
    GdsMemberDiscount findByLevelId(@Param("cm") SysUser currentUser, @Param("levelId") Long levelId);

    /**
     * 根据等级id删除会员优惠规则
     *
     * @param id
     */
    void delByLevenId(@Param("id") Long id);

    /**
     * 商品id查询会员优惠规则
     *
     * @param model
     * @param pid
     * @return
     */
    GdsMemberDiscount findByIdAndPid(@Param("dto") GdsMember model, @Param("pid") Long pid);

    /**
     * 查询没有关联产品的优惠规则
     *
     * @param id
     * @param levelId
     * @return
     */
    GdsMemberDiscount selectWhetherOrNot(@Param("id") Long id, @Param("levelId") Long levelId);
}
