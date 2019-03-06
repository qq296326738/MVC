package com.qmx.member.mapper;

import com.qmx.base.core.base.IBaseMapper;
import com.qmx.coreservice.model.SysUser;
import com.qmx.member.model.GdsMemberRechargeRule;
import com.qmx.member.query.GdsMemberRechargeRuleVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GdsMemberRechargeRuleMapper extends IBaseMapper<GdsMemberRechargeRule> {

    List<GdsMemberRechargeRule> findByLevelId(@Param("levelId") Long levelId);

    /**
     * 查询充值规则
     *
     * @param currentUser
     * @param dto
     * @return
     */
    List<GdsMemberRechargeRule> findRules(@Param("cm") SysUser currentUser, @Param("dto") GdsMemberRechargeRuleVO dto);

    void delByLevenId(@Param("id") Long id);
}
