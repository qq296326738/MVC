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
import com.qmx.member.mapper.GdsMemberConsumptionMapper;
import com.qmx.member.model.GdsMember;
import com.qmx.member.model.GdsMemberAssociated;
import com.qmx.member.model.GdsMemberConsumption;
import com.qmx.member.query.GdsMemberConsumptionVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 会员消费规则
 */
@Service
@GdsCacheConfig(cacheNS = "GDS-MEMBER", cacheName = "GdsMemberConsumption")
public class GdsMemberConsumptionService extends BaseService<GdsMemberConsumption> {

    @Autowired
    private GdsMemberConsumptionMapper gdsMemberConsumptionMapper;
    @Autowired
    private GdsMemberAssociatedService associatedService;

    public PageDto<GdsMemberConsumption> findList(SysUser userDto, GdsMemberConsumptionVO dto) {
        try {
            Map<String, Object> params = InstanceUtil.transBean2StringMap(dto);
            if (userDto.getUserType() == SysUserType.admin) {
            } else if (userDto.getUserType() == SysUserType.supplier) {
                params.put("supplierId", userDto.getId());
            } else {
                return new PageDto<>();
            }
            Page<GdsMemberConsumption> page = this.query(params);
            PageDto<GdsMemberConsumption> pageDto = new PageDto<>(page.getTotal(), page.getSize(), page.getCurrent(), page.getRecords());
            return pageDto;
        } catch (Exception e) {
            e.printStackTrace();
            return new PageDto<>();
        }
    }

    /**
     * 创建会员消费管理
     *
     * @param user
     * @param dto
     * @return
     */
    @Transactional
    public RestResponse<GdsMemberConsumption> createDto(SysUser user, GdsMemberConsumptionVO dto) {
        try {
            Assert.notNull(dto, "数据不能为空");
            if (dto.getIds() == null) {
                GdsMemberConsumption gdsMemberConsumption = gdsMemberConsumptionMapper.selectWhetherOrNot(user.getId(), dto.getLevelId());
                if (gdsMemberConsumption != null) {
                    return RestResponse.fail("相同等级只能设置一个未关联产品规则");
                }
            } else {
                Integer count = associatedService.selectCountByConsumptionIds(dto);
                if (count != null && count > 0) {
                    return RestResponse.fail("相同等级有重复的关联产品");
                }
            }
            GdsMemberConsumption model = new GdsMemberConsumption();
            BeanUtils.copyProperties(dto, model);
            model.setCreateBy(user.getId());
            model.setUpdateBy(user.getId());
            model.setSupplierId(user.getId());
            model = this.save(model);
            savaAssociated(model.getId(), dto);
            return RestResponse.ok(model);
        } catch (Exception e) {
            e.printStackTrace();
            return RestResponse.fail(e.getMessage());
        }
    }

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    public RestResponse<GdsMemberConsumption> findById(Long id) {
        try {
            GdsMemberConsumption model = this.find(id);
            HashMap<ProductType, List<GdsMemberAssociated>> productTypeListHashMap = new HashMap<>();
            List<GdsMemberAssociated> menpiao = associatedService.findByConsumptionId(id, ProductType.menpiao);
            productTypeListHashMap.put(ProductType.menpiao, menpiao);
            List<GdsMemberAssociated> shangpin = associatedService.findByConsumptionId(id, ProductType.shangpin);
            productTypeListHashMap.put(ProductType.shangpin, shangpin);
            model.setMap(productTypeListHashMap);
            return RestResponse.ok(model);
        } catch (BeansException e) {
            e.printStackTrace();
            return RestResponse.fail("获取失败");
        }
    }

    /**
     * 更新消费管理
     *
     * @param sysUser
     * @param dto
     * @return
     */
    @Transactional
    public RestResponse<GdsMemberConsumption> updateDto(SysUser sysUser, GdsMemberConsumptionVO dto) {
        try {
            Assert.notNull(dto, "数据不能为空");
            GdsMemberConsumption model = this.find(dto.getId());
            if (dto.getIds() == null) {
                GdsMemberConsumption gdsMemberConsumption = gdsMemberConsumptionMapper.selectWhetherOrNot(sysUser.getId(), dto.getLevelId());
                if (gdsMemberConsumption != null) {
                    if (model.getId().equals(gdsMemberConsumption.getId())) {
                        if (!dto.getIntegralProportion().equals(gdsMemberConsumption.getIntegralProportion())) {
                            model.setIntegralProportion(dto.getIntegralProportion());
                            this.update(model);
                        }
                        return RestResponse.ok();
                    }
                    return RestResponse.fail("相同等级只能设置一个未关联产品规则");
                }
            } else {
                Integer count = associatedService.selectCountByConsumptionIds(dto);
                if (count != null && count > 0) {
                    return RestResponse.fail("相同等级有重复的关联产品");
                }
            }
            BeanUtils.copyProperties(dto, model, new String[]{"id", "enable", "createTime", "createBy", "supplierId"});
            model.setUpdateBy(sysUser.getId());
            model = this.updateAll(model, dto);
            return RestResponse.ok(model);
        } catch (Exception e) {
            return RestResponse.fail(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param user
     * @param id
     * @return
     */
    public RestResponse<Boolean> deleteDto(SysUser user, Long id) {
        try {
            this.del(id, user.getId());
            return RestResponse.ok(Boolean.TRUE);
        } catch (Exception e) {
            return RestResponse.fail(e.getMessage());
        }
    }

    /**
     * 根据等级id查询会员消费规则
     *
     * @param user
     * @param levelId
     * @return
     */
    public GdsMemberConsumption findByLevelId(SysUser user, Long levelId) {
        return gdsMemberConsumptionMapper.findByLevelId(user, levelId);
    }

    /**
     * 保存关联产品
     *
     * @param model
     * @param dto
     * @return
     */
    @Transactional
    public GdsMemberConsumption saveAll(GdsMemberConsumption model, GdsMemberConsumptionVO dto) {
        GdsMemberConsumption consumption = this.save(model);
        savaAssociated(consumption.getId(), dto);
        return consumption;
    }

    /**
     * 更新关联产品
     *
     * @param model
     * @param dto
     * @return
     */
    @Transactional
    public GdsMemberConsumption updateAll(GdsMemberConsumption model, GdsMemberConsumptionVO dto) {
        GdsMemberConsumption consumption = this.update(model);
        associatedService.delByConsumptionId(consumption.getId());
        savaAssociated(consumption.getId(), dto);
        return consumption;
    }

    @Transactional
    public void savaAssociated(Long id, GdsMemberConsumptionVO dto) {
        //1016983326951444482&测试抢购&menpiao   产品ID&产品名称&产品类型
        List<String> ids = dto.getIds();
        associatedService.savaAssociated(id, ids);
    }

    /**
     * 根据等级id删除会员消费规则
     *
     * @param id
     */
    @Transactional
    public void delByLevenId(Long id) {
        gdsMemberConsumptionMapper.delByLevenId(id);
    }

    /**
     * 商品id查询会员消费规则
     *
     * @param model
     * @param pid
     * @return
     */
    public GdsMemberConsumption findByIdAndPid(GdsMember model, Long pid) {
        GdsMemberConsumption gdsMemberConsumption = gdsMemberConsumptionMapper.findByIdAndPid(model, pid);
        if (gdsMemberConsumption == null) {
            gdsMemberConsumption = gdsMemberConsumptionMapper.selectWhetherOrNot(model.getSupplierId(), model.getLevelId());
        }
        return gdsMemberConsumption;
    }

}
