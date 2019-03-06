package com.qmx.member.mapper;

import com.qmx.base.core.base.IBaseMapper;
import com.qmx.member.model.GdsMemberSign;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface GdsMemberSignMapper extends IBaseMapper<GdsMemberSign> {
    /**
     * 查询今天有没有签到
     *
     * @param id
     * @return
     */
    GdsMemberSign findByMemberId(@Param("id") Long id);

    /**
     * 查询昨天有没有签到
     *
     * @param id
     * @return
     */
    GdsMemberSign selectIsContinuousSign(@Param("id") Long id);

    /**
     * 查询本月签到数
     *
     * @param id
     * @return
     */
    Integer findMonthById(@Param("id") Long id);

    /**
     * 查询累计签到与累计奖励
     *
     * @param id
     * @return
     */
    Map<String, Object> findSignCount(@Param("id") Long id);

    /**
     * 查询签到列表
     *
     * @param id
     * @return
     */
    List<GdsMemberSign> listTime(@Param("id") Long id, @Param("size") int s);

}
