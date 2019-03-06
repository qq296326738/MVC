package com.qmx.member.mapper;

import com.qmx.base.core.base.IBaseMapper;
import com.qmx.member.model.GdsMemberIntegeral;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface GdsMemberIntegeralMapper extends IBaseMapper<GdsMemberIntegeral> {
    //查询用户积分记录
    List<GdsMemberIntegeral> findAll(@Param("id") Long id);

    GdsMemberIntegeral findByRecordId(@Param("id") Long id);

    List<GdsMemberIntegeral> getExchangeList(@Param("id") Long id);

    //查询会员积分记录同步状态
    List<GdsMemberIntegeral> queryIntegeralIsUpdated(@Param("id") Long id);

    //同步会员积分状态
    void updaMemberIntegeralSyn(@Param("id") Long id);

    //删除会员删除对应的积分记录
    void delBymemberId(@Param("id") Long id, @Param("userId") Long userId, @Param("date") Date date);

    //订单号查询积分记录
    GdsMemberIntegeral findBySn(@Param("sn") String sn, @Param("id") Long id);
}
