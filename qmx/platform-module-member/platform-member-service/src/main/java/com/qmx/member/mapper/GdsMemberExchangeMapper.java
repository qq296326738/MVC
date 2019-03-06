package com.qmx.member.mapper;

import com.qmx.base.core.base.IBaseMapper;
import com.qmx.member.enumerate.ExchangeProductType;
import com.qmx.member.model.GdsMemberExchange;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GdsMemberExchangeMapper extends IBaseMapper<GdsMemberExchange> {
    /**
     * 更新商品过期状态
     */
    void updateState();

    /**
     * 根据类型查询,为null查全部
     *
     * @param supplierId
     * @param type
     * @return
     */
    List<GdsMemberExchange> findExchangeList(@Param("supplierId") Long supplierId, @Param("type") ExchangeProductType type);
}
