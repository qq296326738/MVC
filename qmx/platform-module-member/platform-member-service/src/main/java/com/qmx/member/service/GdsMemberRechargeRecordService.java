package com.qmx.member.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.qmx.base.api.annotation.GdsCacheConfig;
import com.qmx.base.api.dto.PageDto;
import com.qmx.base.core.utils.InstanceUtil;
import com.qmx.member.model.GdsMemberIntegeral;
import com.qmx.member.model.GdsMemberMoney;
import com.qmx.member.query.GdsMemberIntegeralVO;
import com.qmx.member.query.GdsMemberMoneyVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Map;

/**
 * 会员金额,积分,兑换记录
 */
@Service
@GdsCacheConfig(cacheNS = "GDS-MEMBER",cacheName = "GdsMemberRechargeRecord")
public class GdsMemberRechargeRecordService {
    @Autowired
    private GdsMemberMoneyService memberMoneyService ;
    @Autowired
    private GdsMemberIntegeralService memberIntegeralService ;

    public PageDto<GdsMemberMoney> memberMoneyList(GdsMemberMoneyVO dto) {
        try {
            Assert.notNull(dto.getMemberId(), "获得用户信息失败");
            Map<String, Object> params = InstanceUtil.transBean2StringMap(dto);
            Page<GdsMemberMoney> page = memberMoneyService.query(params);
            PageDto<GdsMemberMoney> pageDto = new PageDto<>(page.getTotal(), page.getSize(), page.getCurrent(), page.getRecords());
            return pageDto;
        } catch (Exception e) {
            e.printStackTrace();
            return new PageDto<>();
        }
    }

    public PageDto<GdsMemberIntegeral> memberIntegeralList(GdsMemberIntegeralVO dto) {
        try {
            Assert.notNull(dto.getMemberId(), "获得用户信息失败");
            Map<String, Object> params = InstanceUtil.transBean2StringMap(dto);
            Page<GdsMemberIntegeral> page = memberIntegeralService.query(params);
            PageDto<GdsMemberIntegeral> pageDto = new PageDto<>(page.getTotal(), page.getSize(), page.getCurrent(), page.getRecords());
            return pageDto;
        } catch (Exception e) {
            e.printStackTrace();
            return new PageDto<>();
        }
    }
}
