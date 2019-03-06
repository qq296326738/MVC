package com.qmx.member.mapper;

import com.qmx.base.core.base.IBaseMapper;
import com.qmx.member.enumerate.IntegralType;
import com.qmx.member.model.GdsMemberActivityIntegral;
import com.qmx.member.query.GdsMemberActivityIntegralVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GdsMemberActivityIntegralMapper extends IBaseMapper<GdsMemberActivityIntegral> {
    /**
     * 查询时间是否有交集
     *
     * @param vo vo
     * @return 不等于零则代表时间有重复的地方
     */
    int selectByTime(@Param("cm") GdsMemberActivityIntegralVO vo);

    /**
     * 注册多倍积分查询,有则更新,没有则新增
     *
     * @param id
     * @param vo
     * @return
     */
    GdsMemberActivityIntegral findRegisterByUserId(@Param("id") Long id, @Param("cm") GdsMemberActivityIntegralVO vo);

    /**
     * 查询注册多倍积分
     *
     * @param supplierId 供应商id
     * @return 注册多倍积分信息
     */
    GdsMemberActivityIntegral findRegisterByTime(@Param("supplierId") Long supplierId);

    /**
     * 查询充值多倍积分
     *
     * @param supplierId 供应商id
     * @param levelId    等级id
     * @return 充值多倍积分信息
     */
    GdsMemberActivityIntegral findRechargeByTime(
            @Param("supplierId") Long supplierId,
            @Param("levelId") Long levelId);

    /**
     * 查询消费多倍积分
     *
     * @param supplierId 供应商id
     * @param levelId    等级id
     * @param pid        商品id
     * @return
     */
    GdsMemberActivityIntegral findConsumptionByTime(
            @Param("supplierId") Long supplierId,
            @Param("levelId") Long levelId,
            @Param("pid") Long pid);
}
