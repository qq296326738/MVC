package com.qmx.member.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.qmx.base.api.annotation.GdsCacheConfig;
import com.qmx.base.api.dto.PageDto;
import com.qmx.base.api.dto.RestResponse;
import com.qmx.base.api.enumerate.SysUserType;
import com.qmx.base.core.base.BaseService;
import com.qmx.base.core.utils.InstanceUtil;
import com.qmx.coreservice.model.SysUser;
import com.qmx.member.enumerate.ProductType;
import com.qmx.member.mapper.GdsMemberDiscountMapper;
import com.qmx.member.model.GdsMember;
import com.qmx.member.model.GdsMemberAssociated;
import com.qmx.member.model.GdsMemberDiscount;
import com.qmx.member.query.GdsMemberDiscountVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 会员优惠管理
 */
@Service
@GdsCacheConfig(cacheNS = "GDS-MEMBER", cacheName = "GdsMemberDiscount")
public class GdsMemberDiscountService extends BaseService<GdsMemberDiscount> {

    @Autowired
    private GdsMemberDiscountMapper gdsMemberDiscountMapper;
    @Autowired
    private GdsMemberAssociatedService associatedService;

    /**
     * 根据等级id查询会员优惠规则是否存在
     *
     * @param currentUser
     * @param levelId
     * @return
     */
    public GdsMemberDiscount findByLevelId(SysUser currentUser, Long levelId) {
        return gdsMemberDiscountMapper.findByLevelId(currentUser, levelId);
    }

    /**
     * 保存关联产品
     *
     * @param model
     * @param dto
     * @return
     */
    public GdsMemberDiscount saveAll(GdsMemberDiscount model, GdsMemberDiscountVO dto) {
        GdsMemberDiscount discount = this.save(model);
        savaAssociated(discount.getId(), dto);
        return discount;
    }

    /**
     * 更新关联产品
     *
     * @param model
     * @param dto
     * @return
     */
    @Transactional
    public GdsMemberDiscount updateAll(GdsMemberDiscount model, GdsMemberDiscountVO dto) {
        GdsMemberDiscount discount = this.update(model);
        associatedService.delByConsumptionId(discount.getId());
        savaAssociated(discount.getId(), dto);
        return discount;
    }

    @Transactional
    public void savaAssociated(Long id, GdsMemberDiscountVO dto) {
        List<String> ids = dto.getIds();
        associatedService.savaAssociated(id, ids);
    }

    /**
     * 根据等级id删除会员优惠规则
     *
     * @param id
     */
    @Transactional
    public void delByLevenId(Long id) {
        gdsMemberDiscountMapper.delByLevenId(id);
    }

    public PageDto<GdsMemberDiscount> findList(SysUser userDto, GdsMemberDiscountVO dto) {
        try {
            Map<String, Object> params = InstanceUtil.transBean2StringMap(dto);
            if (userDto.getUserType() == SysUserType.admin) {
            } else if (userDto.getUserType() == SysUserType.supplier) {
                params.put("supplierId", userDto.getId());
            } else {
                return new PageDto<>();
            }
            Page<GdsMemberDiscount> page = this.query(params);
            PageDto<GdsMemberDiscount> pageDto = new PageDto<>(page.getTotal(), page.getSize(), page.getCurrent(), page.getRecords());
            return pageDto;
        } catch (Exception e) {
            e.printStackTrace();
            return new PageDto<>();
        }
    }

    @Transactional
    public RestResponse<GdsMemberDiscount> createDto(SysUser currentUser, GdsMemberDiscountVO dto) {
        try {
            Assert.notNull(dto, "数据不能为空");
            if (dto.getIds() == null) {
                GdsMemberDiscount gdsMemberDiscount = gdsMemberDiscountMapper.selectWhetherOrNot(currentUser.getId(), dto.getLevelId());
                if (gdsMemberDiscount != null) {
                    return RestResponse.fail("相同等级只能设置一个未关联产品规则");
                }
            } else {
                Integer count = associatedService.selectCountByDiscountIds(dto);
                if (count != null && count > 0) {
                    return RestResponse.fail("相同等级有重复的关联产品");
                }
            }
            GdsMemberDiscount model = new GdsMemberDiscount();
            BeanUtils.copyProperties(dto, model);
//            model.setCreateBy(currentUser.getId());
//            model.setUpdateBy(currentUser.getId());
            model.setSupplierId(currentUser.getId());
            model = saveAll(model, dto);
            return RestResponse.ok(model);
        } catch (Exception e) {
            e.printStackTrace();
            return RestResponse.fail(e.getMessage());
        }
    }

    public RestResponse<GdsMemberDiscount> findById(Long id) {
        GdsMemberDiscount model = this.find(id);
        List<GdsMemberAssociated> menpiao = associatedService.findByConsumptionId(id, ProductType.menpiao);
        HashMap<ProductType, List<GdsMemberAssociated>> productTypeListHashMap = new HashMap<>();
        productTypeListHashMap.put(ProductType.menpiao, menpiao);
        List<GdsMemberAssociated> shangpin = associatedService.findByConsumptionId(id, ProductType.shangpin);
        productTypeListHashMap.put(ProductType.shangpin, shangpin);
        model.setMap(productTypeListHashMap);
        return RestResponse.ok(model);
    }

    @Transactional
    public RestResponse<GdsMemberDiscount> updateDto(SysUser currentUser, GdsMemberDiscountVO dto) {
        try {
            Assert.notNull(dto, "数据不能为空");
            GdsMemberDiscount model = this.find(dto.getId());
            if (dto.getIds() == null) {
                GdsMemberDiscount gdsMemberDiscount = gdsMemberDiscountMapper.selectWhetherOrNot(currentUser.getId(), dto.getLevelId());
                if (gdsMemberDiscount != null) {
                    if (model.getId().equals(gdsMemberDiscount.getId())) {
                        if (!dto.getRate().equals(gdsMemberDiscount.getRate())) {
                            model.setRate(dto.getRate());
                            this.update(model);
                        }
                        return RestResponse.ok();
                    }
                    return RestResponse.fail("相同等级只能设置一个未关联产品规则");
                }
            } else {
                Integer count = associatedService.selectCountByDiscountIds(dto);
                if (count != null && count > 0) {
                    return RestResponse.fail("相同等级有重复的关联产品");
                }
            }
            BeanUtils.copyProperties(dto, model, new String[]{"id", "enable", "createTime", "createBy", "supplierId"});
            model.setUpdateBy(currentUser.getId());
            model = updateAll(model, dto);
            return RestResponse.ok(model);
        } catch (Exception e) {
            return RestResponse.fail(e.getMessage());
        }
    }

    @Transactional
    public RestResponse deleteDto(SysUser currentUser, Long id) {
        try {
            this.del(id, currentUser.getId());
            return RestResponse.ok(Boolean.TRUE);
        } catch (Exception e) {
            return RestResponse.fail(e.getMessage());
        }
    }

    /**
     * 商品id查询会员优惠规则
     *
     * @param model
     * @param pid
     * @return
     */
    public GdsMemberDiscount findByIdAndPid(GdsMember model, Long pid) {
        GdsMemberDiscount gdsMemberDiscount = gdsMemberDiscountMapper.findByIdAndPid(model, pid);
        if (gdsMemberDiscount == null) {
            gdsMemberDiscount = gdsMemberDiscountMapper.selectWhetherOrNot(model.getSupplierId(), model.getLevelId());
        }
        return gdsMemberDiscount;
    }
}
