package com.qmx.member.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.qmx.base.api.annotation.GdsCacheConfig;
import com.qmx.base.api.dto.PageDto;
import com.qmx.base.api.enumerate.SysUserType;
import com.qmx.base.api.exception.BusinessException;
import com.qmx.base.core.base.BaseService;
import com.qmx.base.core.utils.InstanceUtil;
import com.qmx.coreservice.model.SysUser;
import com.qmx.member.enumerate.IntegralType;
import com.qmx.member.enumerate.ProductType;
import com.qmx.member.mapper.GdsMemberActivityIntegralMapper;
import com.qmx.member.model.GdsMemberActivityIntegral;
import com.qmx.member.model.GdsMemberAssociated;
import com.qmx.member.query.GdsMemberActivityIntegralVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.*;

/**
 * 多倍积分
 */
@Service
@GdsCacheConfig(cacheNS = "GDS-MEMBER", cacheName = "GdsMemberActivityIntegralService")
public class GdsMemberActivityIntegralService extends BaseService<GdsMemberActivityIntegral> {

    @Autowired
    private GdsMemberActivityIntegralMapper mapper;
    @Autowired
    private GdsMemberAssociatedService associatedService;

    public PageDto<GdsMemberActivityIntegral> findList(SysUser currentUser, GdsMemberActivityIntegralVO vo) {
        Map<String, Object> map = InstanceUtil.transBean2StringMap(vo);
        if (currentUser.getUserType() == SysUserType.admin) {
        } else if (currentUser.getUserType() == SysUserType.supplier) {
            map.put("supplierId", currentUser.getId());
        } else {
            return new PageDto<>();
        }
        Page<GdsMemberActivityIntegral> page = this.query(map);
        PageDto<GdsMemberActivityIntegral> pageDto = new PageDto<>(page.getTotal(), page.getSize(), page.getCurrent(), page.getRecords());
        return pageDto;

    }

    @Transactional
    public GdsMemberActivityIntegral createDto(SysUser user, GdsMemberActivityIntegralVO vo) {
        GdsMemberActivityIntegral gdsMemberActivityIntegral = new GdsMemberActivityIntegral();
        vo.setSupplierId(user.getId());
        List<String> ids = null;
        switch (vo.getType()) {
            case consumption: // 消费
                ids = vo.getIds();
                Assert.notNull(ids, "关联产品不能为空!!!");
                Integer integer = associatedService.selectCountByActivityIntegralIds(vo);
                if (integer != null && integer > 0) {
                    throw new BusinessException("相同等级与时间有重复的关联产品");
                }
                break;
            case recharge: //充值
                int count1 = mapper.selectByTime(vo);
                if (count1 > 0) {
                    throw new BusinessException("等级相同,请设置正确的时间");
                }
                break;
            case register: //注册
                GdsMemberActivityIntegral data = mapper.findRegisterByUserId(user.getId(), vo);
                if (data != null) {
                    throw new BusinessException("注册多倍积分,请设置正确的时间");
                }
                break;
        }

        BeanUtils.copyProperties(vo, gdsMemberActivityIntegral);
        gdsMemberActivityIntegral.setSupplierId(user.getId());
        GdsMemberActivityIntegral save = this.save(gdsMemberActivityIntegral);
        if (ids != null) {
            associatedService.savaAssociated(save.getId(), ids);
        }
        return save;
    }

    @Transactional
    public void deleteDto(Long id, SysUser currentUser) {
        GdsMemberActivityIntegral integral = this.find(id);
        this.del(id, currentUser.getId());
        if (integral.getType() == IntegralType.consumption) {
            associatedService.delByConsumptionId(id);
        }
    }

    @Transactional
    public GdsMemberActivityIntegral edit(Long id) {
        GdsMemberActivityIntegral data = find(id);
        if (data.getType() == IntegralType.consumption) {
            List<GdsMemberAssociated> menpiao = associatedService.findByConsumptionId(id, ProductType.menpiao);
            HashMap<ProductType, List<GdsMemberAssociated>> productTypeListHashMap = new HashMap<>();
            productTypeListHashMap.put(ProductType.menpiao, menpiao);
            List<GdsMemberAssociated> shangpin = associatedService.findByConsumptionId(id, ProductType.shangpin);
            productTypeListHashMap.put(ProductType.shangpin, shangpin);
            data.setMap(productTypeListHashMap);
        }
        return data;
    }

    @Transactional
    public GdsMemberActivityIntegral updateDto(SysUser currentUser, GdsMemberActivityIntegralVO vo) {
        GdsMemberActivityIntegral activityIntegral = this.find(vo.getId());
        vo.setType(activityIntegral.getType());
        Assert.notNull(activityIntegral, "没有对应积分设置");
        activityIntegral.setStartTime(vo.getStartTime());
        activityIntegral.setEndTime(vo.getEndTime());
        activityIntegral.setMultiple(vo.getMultiple());
        activityIntegral.setLevelId(vo.getLevelId());
        activityIntegral.setLevelName(vo.getLevelName());
        switch (activityIntegral.getType()) {
            case recharge: //充值
                int count1 = mapper.selectByTime(vo);
                if (count1 > 0) {
                    throw new BusinessException("等级相同,请设置正确的时间");
                }
                break;
            case register: //注册
                GdsMemberActivityIntegral data = mapper.findRegisterByUserId(currentUser.getId(), vo);
                if (data != null) {
                    throw new BusinessException("注册多倍积分,请设置正确的时间");
                }
                break;
            case consumption: //消费
                Integer integer = associatedService.selectCountByActivityIntegralIds(vo);
                if (integer != null && integer > 0) {
                    throw new BusinessException("相同等级有重复的关联产品");
                }
                associatedService.delByConsumptionId(activityIntegral.getId());
                associatedService.savaAssociated(activityIntegral.getId(), vo.getIds());
                break;
        }
        return this.update(activityIntegral);
    }

    //注册多倍
    public GdsMemberActivityIntegral findRegisterByTime(Long supplierId) {
        return mapper.findRegisterByTime(supplierId);
    }

    //充值多倍
    public GdsMemberActivityIntegral findRechargeByTime(Long supplierId, Long levelId) {
        return mapper.findRechargeByTime(supplierId, levelId);
    }

    //消费多倍
    public GdsMemberActivityIntegral findConsumptionByTime(Long supplierId, Long levelId, Long pid) {
        return mapper.findConsumptionByTime(supplierId, levelId, pid);
    }

}
