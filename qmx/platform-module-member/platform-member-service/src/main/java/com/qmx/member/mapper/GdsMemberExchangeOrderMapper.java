package com.qmx.member.mapper;

import com.qmx.base.core.base.IBaseMapper;
import com.qmx.member.model.GdsMemberExchangeOrder;
import com.qmx.member.query.GdsMemberExchangeOrderVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GdsMemberExchangeOrderMapper extends IBaseMapper<GdsMemberExchangeOrder> {
    /**
     * 更新订单发货状态
     *
     * @param dto
     */
    void updateStateType(@Param("dto") GdsMemberExchangeOrderVO dto);

    /**
     * 获得会员兑换记录
     *
     * @param id
     * @return
     */
    List<GdsMemberExchangeOrder> listByMemberId(@Param("id") Long id);

}
