package com.qmx.member.mapper;

import com.qmx.base.core.base.IBaseMapper;
import com.qmx.coreservice.model.SysUser;
import com.qmx.member.model.GdsMember;
import com.qmx.member.model.GdsMemberConsumption;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GdsMemberConsumptionMapper extends IBaseMapper<GdsMemberConsumption> {
    /**
     * 根据等级id查询会员消费规则是否存在
     *
     * @param user
     * @param levelId
     * @return
     */
    GdsMemberConsumption findByLevelId(@Param("cm") SysUser user, @Param("levelId") Long levelId);

    /**
     * 根据等级id删除会员消费规则
     *
     * @param id
     * @return
     */
    void delByLevenId(@Param("id") Long id);

    /**
     * 商品id查询会员优惠规则
     *
     * @param model
     * @param pid
     * @return
     */
    GdsMemberConsumption findByIdAndPid(@Param("dto") GdsMember model, @Param("pid") Long pid);

    /**
     * 查询没有关联产品的消费规则
     *
     * @param id
     * @param levelId
     * @return
     */
    GdsMemberConsumption selectWhetherOrNot(@Param("id") Long id, @Param("levelId") Long levelId);
}
