package com.qmx.member.mapper;

import com.qmx.base.core.base.IBaseMapper;
import com.qmx.member.model.GdsMemberMoney;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public interface GdsMemberMoneyMapper extends IBaseMapper<GdsMemberMoney> {
    /**
     * 获得会员每月充值(消费)记录
     *
     * @param id
     * @param recordType true(充值)  false(消费)
     * @return
     */
    List<GdsMemberMoney> listrecordType(@Param("id") Long id, @Param("recordType") Boolean recordType);

    /**
     * 获得历史(充值/消费)总记录 recharge总充值consumption总消费
     *
     * @param id
     * @return
     */
    Map<String, Double> getGeneralRecord(@Param("id") Long id);

    /**
     * 查询会员金额记录同步状态
     *
     * @param id
     * @return
     */
    List<GdsMemberMoney> queryMoneyIsUpdated(@Param("id") Long id);

    /**
     * 同步会员金额记录
     *
     * @param id 金额记录id
     * @return
     */
    void updaMemberMoneyOnLineSyn(@Param("id") Long id);

    /**
     * 删除会员删除对应的金额记录
     *
     * @param id
     * @param userId
     * @param date
     */
    void delBymemberId(@Param("id") Long id, @Param("userId") Long userId, @Param("date") Date date);

    /**
     * 根据订单号查询是否已经存在订单,防止重复更新
     *
     * @param sn 订单号
     * @param id 会员id
     * @return
     */
    GdsMemberMoney findBySn(@Param("sn") String sn, @Param("id") Long id);
}
