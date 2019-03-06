package com.qmx.member.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.qmx.base.api.annotation.GdsCacheConfig;
import com.qmx.base.api.dto.PageDto;
import com.qmx.base.api.dto.RestResponse;
import com.qmx.base.api.enumerate.SysUserType;
import com.qmx.base.core.base.BaseService;
import com.qmx.base.core.utils.InstanceUtil;
import com.qmx.coreservice.model.SysUser;
import com.qmx.member.enumerate.ExchangeProductType;
import com.qmx.member.mapper.GdsMemberExchangeMapper;
import com.qmx.member.model.GdsMember;
import com.qmx.member.model.GdsMemberExchange;
import com.qmx.member.query.GdsMemberExchangeVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.Assert;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 兑换商品
 */
@Service
@GdsCacheConfig(cacheNS = "GDS-MEMBER", cacheName = "GdsMemberExchange")
public class GdsMemberExchangeService extends BaseService<GdsMemberExchange> {
    @Autowired
    private GdsMemberExchangeMapper gdsMemberExchangeMapper;
    @Autowired
    private GdsMemberService gdsMemberService;

    /**
     * 定时任务,时间过期设置状态为过期
     */
    @Transactional
    public void updateState() {
        gdsMemberExchangeMapper.updateState();
    }

    public PageDto<GdsMemberExchange> findList(SysUser currentUser, GdsMemberExchangeVO dto) {
        try {
            Map<String, Object> params = InstanceUtil.transBean2StringMap(dto);
            if (currentUser.getUserType() == SysUserType.admin) {
            } else if (currentUser.getUserType() == SysUserType.supplier) {
                params.put("supplierId", currentUser.getId());
            } else {
                return new PageDto<>();
            }
            Page<GdsMemberExchange> page = this.query(params);
            PageDto<GdsMemberExchange> pageDto = new PageDto<>(page.getTotal(), page.getSize(), page.getCurrent(), page.getRecords());
            return pageDto;
        } catch (BeansException e) {
            e.printStackTrace();
            return new PageDto<>();
        }
    }

    @Transactional(rollbackFor = Throwable.class)
    public RestResponse<GdsMemberExchange> createDto(SysUser user, GdsMemberExchangeVO dto) {
        try {
            Assert.notNull(dto, "数据不能为空");
            Integer pastTime = new Integer(new SimpleDateFormat("yyyyMMdd").format(dto.getExpiryTime()));
            Integer startTime = new Integer(new SimpleDateFormat("yyyyMMdd").format(new Date()));
            if (pastTime - startTime <= 0) {
                return RestResponse.fail("请选择正确的过期时间");
            }
            GdsMemberExchange model = new GdsMemberExchange();
            BeanUtils.copyProperties(dto, model);
            model.setCreateBy(user.getId());
            model.setUpdateBy(user.getId());
            model.setSupplierId(user.getId());
            model = this.save(model);
            return RestResponse.ok(model);
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return RestResponse.fail(e.getMessage());
        }
    }

    @Transactional(rollbackFor = Throwable.class)
    public RestResponse<GdsMemberExchange> updateDto(SysUser currentUser, GdsMemberExchangeVO dto) {
        try {
            Assert.notNull(dto, "数据不能为空");
            Integer pastTime = new Integer(new SimpleDateFormat("yyyyMMdd").format(dto.getExpiryTime()));
            Integer startTime = new Integer(new SimpleDateFormat("yyyyMMdd").format(new Date()));
            boolean fale = true;
            if (pastTime - startTime <= 0) {
                fale = false;
            }
            GdsMemberExchange exchange = this.find(dto.getId());
            BeanUtils.copyProperties(dto, exchange, new String[]{"id", "enable", "state", "createTime", "createBy", "supplierId"});
            exchange.setUpdateBy(currentUser.getId());
            exchange.setState(fale);
            exchange = this.update(exchange);
            return RestResponse.ok(exchange);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return RestResponse.fail(e.getMessage());
        }

    }

    @Transactional(rollbackFor = Throwable.class)
    public RestResponse deleteDto(SysUser currentUser, Long id) {
        try {
            this.del(id, currentUser.getId());
            return RestResponse.ok(Boolean.TRUE);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return RestResponse.fail(e.getMessage());
        }
    }

    /**
     * 查询商品
     *
     * @return
     */
    public List<GdsMemberExchange> findExchangeList(Long id, ExchangeProductType type) {
        GdsMember gdsMember = gdsMemberService.find(id);
        Long supplierId = gdsMember.getSupplierId();
        List<GdsMemberExchange> list = gdsMemberExchangeMapper.findExchangeList(supplierId, type);
        return list;
    }
}
