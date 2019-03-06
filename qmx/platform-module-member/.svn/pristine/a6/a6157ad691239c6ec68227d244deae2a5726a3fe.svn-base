package com.qmx.member.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.qmx.base.api.annotation.GdsCacheConfig;
import com.qmx.base.api.dto.PageDto;
import com.qmx.base.api.dto.RestResponse;
import com.qmx.base.api.enumerate.SysUserType;
import com.qmx.base.core.base.BaseService;
import com.qmx.base.core.utils.InstanceUtil;
import com.qmx.coreservice.model.SysUser;
import com.qmx.member.enumerate.RuleType;
import com.qmx.member.mapper.GdsMemberRechargeRuleMapper;
import com.qmx.member.model.GdsMemberRechargeRule;
import com.qmx.member.query.GdsMemberRechargeRuleVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 会员充值管理
 */
@Service
@GdsCacheConfig(cacheNS = "GDS-MEMBER", cacheName = "GdsMemberRechargeRule")
public class GdsMemberRechargeRuleService extends BaseService<GdsMemberRechargeRule> {

    @Autowired
    private GdsMemberRechargeRuleMapper gdsMemberRechargeRuleMapper;

    public List<GdsMemberRechargeRule> findByLevelId(Long levelId) {
        return gdsMemberRechargeRuleMapper.findByLevelId(levelId);
    }

    public List<GdsMemberRechargeRule> findRules(SysUser currentUser, GdsMemberRechargeRuleVO dto) {
        return gdsMemberRechargeRuleMapper.findRules(currentUser, dto);
    }

    @Transactional
    public void delByLevenId(Long id) {
        gdsMemberRechargeRuleMapper.delByLevenId(id);
    }

    public PageDto<GdsMemberRechargeRule> findList(SysUser userDto, GdsMemberRechargeRuleVO dto) {
        Map<String, Object> params = InstanceUtil.transBean2StringMap(dto);
        if (userDto.getUserType() == SysUserType.supplier) {
            params.put("supplierId", userDto.getId());
        } else if (userDto.getUserType() == SysUserType.admin) {
        } else {
            return new PageDto<>();
        }
        Page<GdsMemberRechargeRule> page = this.query(params);
        PageDto<GdsMemberRechargeRule> pageDto = new PageDto<>(page.getTotal(), page.getSize(), page.getCurrent(), page.getRecords());
        return pageDto;

    }

    @Transactional
    public RestResponse<GdsMemberRechargeRule> createDto(SysUser currentUser, GdsMemberRechargeRuleVO dto) {
        try {
            if (dto.getType() == RuleType.fixed) {
                dto.setMinAmount(null);
                dto.setMaxAmount(null);
            } else {
                dto.setAmount(null);
                if (dto.getMinAmount() > dto.getMaxAmount()) {
                    return RestResponse.fail("输入金额错误");
                }
            }
            List<GdsMemberRechargeRule> rules = this.findRules(currentUser, dto);
            if (rules == null || rules.size() != 0) {
                return RestResponse.fail("重复的充值规则");
            }
            GdsMemberRechargeRule model = new GdsMemberRechargeRule();
            BeanUtils.copyProperties(dto, model);
            model.setCreateBy(currentUser.getId());
            model.setUpdateBy(currentUser.getId());
            model.setSupplierId(currentUser.getId());
            model = this.save(model);
            return RestResponse.ok(model);
        } catch (Exception e) {
            e.printStackTrace();
            return RestResponse.fail(e.getMessage());
        }
    }

    public RestResponse<GdsMemberRechargeRule> findById(Long id) {
        try {
            GdsMemberRechargeRule model = this.find(id);
            return RestResponse.ok(model);
        } catch (Exception e) {
            e.printStackTrace();
            return RestResponse.fail(e.getMessage());
        }
    }

    @Transactional
    public RestResponse<GdsMemberRechargeRule> updateDto(SysUser currentUser, GdsMemberRechargeRuleVO dto) {
        try {
            GdsMemberRechargeRule model = this.find(dto.getId());
            List<GdsMemberRechargeRule> rules = this.findRules(currentUser, dto);
            if (rules == null || rules.size() != 0) {
                return RestResponse.fail("重复的充值规则");
            }
            if (dto.getType() == RuleType.fixed) {
                dto.setMinAmount(null);
                dto.setMaxAmount(null);
            } else {
                dto.setAmount(null);
                if (dto.getMinAmount() > dto.getMaxAmount()) {
                    RestResponse.fail("输入金额错误");
                }
            }

            BeanUtils.copyProperties(dto, model, new String[]{"id", "enable", "createTime", "createBy", "supplierId"});
            model.setUpdateBy(currentUser.getId());
            model = this.update(model);
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
}
