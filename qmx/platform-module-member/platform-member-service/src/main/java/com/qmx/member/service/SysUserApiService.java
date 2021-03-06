package com.qmx.member.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.qmx.appextend.remoreapisub.SysUserClient;
import com.qmx.base.api.annotation.GdsCacheConfig;
import com.qmx.base.api.dto.PageDto;
import com.qmx.base.api.enumerate.SysUserType;
import com.qmx.base.api.exception.ValidationException;
import com.qmx.base.core.base.BaseService;
import com.qmx.base.core.utils.InstanceUtil;
import com.qmx.coreservice.enumerate.APIPlatEnum;
import com.qmx.coreservice.enumerate.APPTypeEnum;
import com.qmx.coreservice.model.SysUser;
import com.qmx.member.model.SysUserApi;
import com.qmx.member.query.SysUserApiVO;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;
import java.util.UUID;

/**
 * @Author liubin
 * @Description 系统用户api 服务类
 * @Date Created on 2018/2/8 17:01.
 * @Modified By
 */
@Service
@GdsCacheConfig(cacheNS = "GDS-MEMBER", cacheName = "SysUserApi")
public class SysUserApiService extends BaseService<SysUserApi> {


//    @Value("${com.qmx.api.lx.consumeNotify:}")
//    private String lxConsumeNotify;
//    @Value("${com.qmx.api.lx.refundNotify:}")
//    private String lxRefundNotify;
//    @Value("${com.qmx.api.ctrip.consumeNotify:}")
//    private String ctripConsumeNotify;
//    @Value("${com.qmx.api.ctrip.refundNotify:}")
//    private String ctripRefundNotify;

//    @Value("com.qmx.api.qunar.consumeNotify:}")
//    private String qunarConsumeNotify;
//    @Value("${com.qmx.api.qunar.refundNotify:}")
//    private String qunarRefundNotify;
//    @Value("${com.qmx.api.qunar.sendCodeNotify:}")
//    private String qunarSendCodeNotify;
//
//    @Value("${com.qmx.api.meituan.consumeNotify:}")
//    private String meituanConsumeNotify;
//    @Value("${com.qmx.api.meituan.refundNotify:}")
//    private String meituanRefundNotify;
//
//    @Value("${com.qmx.api.lvmama.consumeNotify:}")
//    private String lvmamaConsumeNotify;
//    @Value("${com.qmx.api.lvmama.refundNotify:}")
//    private String lvmamaRefundNotify;

    @Autowired
    private SysUserClient sysUserService;
//    @Autowired
//    private SysOrderSourceService sysOrderSourceService;


    /**
     * 获取用户系统用户api分页
     *
     * @param sysUserApiVO
     * @return
     */
    public PageDto<SysUserApi> findPage(
            SysUserApiVO sysUserApiVO, SysUser currentMember) {
        Map<String, Object> params = InstanceUtil.transBean2StringMap(sysUserApiVO);
        if (currentMember.getUserType() == SysUserType.admin) {
            //管理员不过滤条件
        } else {
            //其他  memberId 代表创建接口人,可以看到创建的接口
            params.put("memberId", currentMember.getId());
        }
        Page<SysUserApi> pageInfo = super.query(params);
        PageDto<SysUserApi> pageDto = new PageDto<>(pageInfo.getTotal(), pageInfo.getSize(), pageInfo.getCurrent(), pageInfo.getRecords());
        return pageDto;
    }

    /**
     * 通过appkey获取userApi信息
     *
     * @param appKey
     * @return
     */
    public SysUserApi findByAppkey(String appKey) {
        Assert.notNull(appKey, "appKey不能为空");
        SysUserApi sysUserApi = super.findObjectByField("appKey", appKey);
        //Assert.notNull(sysUserApi,"未找到appKey信息");
        return sysUserApi;
    }

    /**
     * 通过userId获取userApi信息
     *
     * @param userId
     * @return
     */
    public SysUserApi findByUserId(Long userId) {
        Assert.notNull(userId, "userId不能为空");
        SysUserApi sysUserApi = super.findObjectByField("userId", userId);
        //Assert.notNull(sysUserApi,"未找到userApi信息");
        return sysUserApi;
    }

    /**
     * 添加系统用户接口
     *
     * @param sysUserApiDTO
     * @return
     */
    public SysUserApi createUserApi(
            SysUserApi sysUserApiDTO, SysUser currentUser) {

        Assert.notNull(sysUserApiDTO, "系统接口用户信息不能为空");
        Assert.notNull(sysUserApiDTO.getUserId(), "系统接口用户id不能为空");

        SysUserApi sysUserApi = new SysUserApi();
        sysUserApi.setUserId(sysUserApiDTO.getUserId());
        SysUserApi exitsUserApi = super.selectOne(sysUserApi);
        if (exitsUserApi != null) {
            throw new ValidationException("一个用户只能配置一个接口");
        }
        BeanUtils.copyProperties(sysUserApiDTO, sysUserApi, "id");
        SysUser sysUser = sysUserService.findById(sysUserApiDTO.getUserId());
        Assert.notNull(sysUser, "未找到用户信息");
        SysUser userMember = sysUserService.findById(sysUser.getMemberId());

        if (sysUser.getUserType() == SysUserType.distributor) {
            Assert.notNull(sysUserApiDTO.getPayMethod(), "系统接口用户支付方式不能为空");
            Assert.notNull(sysUserApiDTO.getOrderSourceId(), "系统接口用户订单来源id不能为空");

//            SysOrderSource sysOrderSource = sysOrderSourceService.find(sysUserApiDTO.getOrderSourceId());
//            Assert.notNull(sysOrderSource,"未找到订单来源信息");
//            sysUserApi.setOrderSourceName(sysOrderSource.getName());
        }

        sysUserApi.setAppType(APPTypeEnum.USER);//设置类型
        //if(sysUser.getUserType() == SysUserType.employee){
        sysUserApi.setMemberId(sysUser.getMemberId());
        //}else {
        //    sysUserApi.setMemberId(currentMember.getMemberId());
        //}
        /*if (currentMember.getUserType() != SysUserType.admin &&
                currentMember.getUserType() != SysUserType.supplier) {
            //管理员不过滤条件
            throw new ValidationException("只能是管理员或供应商有权限配置接口");
        }*/
        if (sysUserApi.getAutoPay() == null) {
            sysUserApi.setAutoPay(Boolean.FALSE);
        }
        if (sysUserApi.getAutoSendCode() == null) {
            sysUserApi.setAutoSendCode(Boolean.FALSE);
        }
        if (sysUserApi.getShippingStatus() == null) {
            sysUserApi.setShippingStatus(Boolean.FALSE);
        }

//        if(!StringUtils.hasText(sysUserApi.getConsumeNotifyUrl())){
//            if(sysUserApi.getApiPlat() == APIPlatEnum.LY){
//                sysUserApi.setConsumeNotifyUrl(lxConsumeNotify);
//            }else if(sysUserApi.getApiPlat() == APIPlatEnum.CTRIP){
//                sysUserApi.setConsumeNotifyUrl(ctripConsumeNotify);
//            }else if(sysUserApi.getApiPlat() == APIPlatEnum.MEITUAN){
//                sysUserApi.setConsumeNotifyUrl(meituanConsumeNotify);
//            }else if(sysUserApi.getApiPlat() == APIPlatEnum.LVMAMA){
//                sysUserApi.setConsumeNotifyUrl(lvmamaConsumeNotify);
//            }else if(sysUserApi.getApiPlat() == APIPlatEnum.QUNAR){
//                sysUserApi.setConsumeNotifyUrl(qunarConsumeNotify);
//            }
//        }
//        if(!StringUtils.hasText(sysUserApi.getRefundNotifyUrl())){
//            if(sysUserApi.getApiPlat() == APIPlatEnum.LY){
//                sysUserApi.setRefundNotifyUrl(lxRefundNotify);
//            }else if(sysUserApi.getApiPlat() == APIPlatEnum.CTRIP){
//                sysUserApi.setRefundNotifyUrl(ctripRefundNotify);
//            }else if(sysUserApi.getApiPlat() == APIPlatEnum.MEITUAN){
//                sysUserApi.setRefundNotifyUrl(meituanRefundNotify);
//            }else if(sysUserApi.getApiPlat() == APIPlatEnum.LVMAMA){
//                sysUserApi.setRefundNotifyUrl(lvmamaRefundNotify);
//            }else if(sysUserApi.getApiPlat() == APIPlatEnum.QUNAR){
//                sysUserApi.setRefundNotifyUrl(qunarRefundNotify);
//            }
//        }
//        if(!StringUtils.hasText(sysUserApi.getSendCodeNotifyUrl())){
//            if(sysUserApi.getApiPlat() == APIPlatEnum.QUNAR){
//                sysUserApi.setSendCodeNotifyUrl(qunarSendCodeNotify);
//            }
//        }

        sysUserApi.setClientId(UUID.randomUUID().toString());
        sysUserApi.setAppKey(UUID.randomUUID().toString());
        sysUserApi.setSecretKey(RandomStringUtils.randomAlphabetic(32));
        sysUserApi.setApiPrefix(RandomStringUtils.randomAlphabetic(4));
        sysUserApi.setName(sysUser.getAccount() + "/" + sysUser.getUsername());
        sysUserApi.setMemberAccount(userMember.getAccount());
        sysUserApi.setMemberName(userMember.getUsername());
        sysUserApi.setCreateBy(currentUser.getId());
        sysUserApi.setUpdateBy(currentUser.getId());
        SysUserApi respSysUserApi = super.save(sysUserApi);
        return respSysUserApi;
    }

    /**
     * 获取appkey信息
     *
     * @return
     */
    public SysUserApi findUserApiInfo(Long userId, SysUser currentUser) {
        Assert.notNull(userId, "用户id不能为空");
        Assert.notNull(currentUser, "未找到用户信息");
        SysUserApi respUserApi = super.findObjectByField("userId", userId);
        if (respUserApi == null) {
            SysUser sysUser = sysUserService.findById(userId);
            Assert.notNull(sysUser, "未找到用户信息");
            respUserApi = saveSupplierApiInfo(null, null, sysUser, APPTypeEnum.USER, currentUser.getId());
        }
        return respUserApi;
    }

    /**
     * 获取景区appkey信息
     *
     * @param sightId
     * @return
     */
    public SysUserApi findSightApiInfo(Long sightId, Long userId, String sightName, SysUser currentUser) {
        Assert.notNull(sightId, "sightId不能为空");
        SysUserApi userApi = new SysUserApi();
        userApi.setUserId(sightId);
        SysUserApi respUserApi = super.selectOne(userApi);
        if (respUserApi == null) {
            SysUser sysUser = sysUserService.findById(userId);
            Assert.notNull(sysUser, "未找到用户信息");
            respUserApi = saveSupplierApiInfo(sightId, sightName, sysUser, APPTypeEnum.SIGHT, currentUser.getId());
        }
        return respUserApi;
    }

    /**
     * 保存用户接口信息
     *
     * @param currentUser
     * @return
     */
    private SysUserApi saveSupplierApiInfo(Long sightId, String sightName, SysUser currentUser, APPTypeEnum appType, Long currentId) {
        SysUserApi sysUserApi = new SysUserApi();
        if (appType == APPTypeEnum.SIGHT) {
            sysUserApi.setUserId(sightId);
            if (currentUser != null) {
                sysUserApi.setMemberId(currentUser.getId());
                sysUserApi.setName(currentUser.getUsername() + sightName + "线下接口");
            } else {
                sysUserApi.setMemberId(1L);
                sysUserApi.setName(sightName + "线下接口");
            }
        } else {
            sysUserApi.setUserId(currentUser.getId());
            sysUserApi.setMemberId(currentUser.getMemberId());
            sysUserApi.setName(currentUser.getUsername());
        }
        sysUserApi.setAppType(appType);//设置用户类型
        sysUserApi.setAutoPay(Boolean.FALSE);
        sysUserApi.setApiPlat(APIPlatEnum.OFFLINE);
        sysUserApi.setAutoSendCode(Boolean.FALSE);
        sysUserApi.setShippingStatus(Boolean.FALSE);
        sysUserApi.setClientId(UUID.randomUUID().toString());
        sysUserApi.setAppKey(UUID.randomUUID().toString());
        sysUserApi.setSecretKey(RandomStringUtils.randomAlphabetic(32));
        sysUserApi.setApiPrefix(RandomStringUtils.randomAlphabetic(4));
        sysUserApi.setCreateBy(currentId);
        sysUserApi.setUpdateBy(currentId);
        return super.save(sysUserApi);
    }

    /**
     * 修改系统用户接口
     *
     * @param sysUserApiDTO
     * @return
     */
    public SysUserApi updateUserApi(SysUserApi sysUserApiDTO, SysUser currentMember) {
        Assert.notNull(sysUserApiDTO, "系统接口用户信息不能为空");
        Assert.notNull(sysUserApiDTO.getId(), "系统接口用户id不能为空");
        SysUserApi sysUserApi = super.find(sysUserApiDTO.getId());
        if (sysUserApi == null) {
            throw new ValidationException("未找到用户接口配置信息");
        }
        /*if (currentMember.getUserType() != SysUserType.admin) {
            if (!sysUserApi.getApiPrefix().equals(sysUserApiDTO.getApiPrefix())) {
                throw new ValidationException("只能是管理员能修改ApiPrefix");
            }
        }*/
        if (sysUserApiDTO.getAutoPay() == null) {
            sysUserApiDTO.setAutoPay(Boolean.FALSE);
        }
        if (sysUserApiDTO.getAutoSendCode() == null) {
            sysUserApiDTO.setAutoSendCode(Boolean.FALSE);
        }
        if (sysUserApiDTO.getShippingStatus() == null) {
            sysUserApiDTO.setShippingStatus(Boolean.FALSE);
        }
        if (sysUserApiDTO.getEnable() == null) {
            sysUserApiDTO.setEnable(Boolean.FALSE);
        }
        //SysUser sysUser = userService.find(sysUserApiDTO.getUserId());
        InstanceUtil.copyPropertiesIgnoreNull(sysUserApiDTO, sysUserApi, "id", "userId", "memberId", "apiPlat", "userType");
        /*if (sysUser.getUserType() != SysUserType.supplier &&
                sysUser.getUserType() != SysUserType.distributor &&
                sysUser.getUserType() != SysUserType.distributor2) {
            throw new ValidationException("只能是供应商或分销商可配置接口");
        }*/
        //sysUserApi.setMemberId(sysUser.getSupplierId());
        if (currentMember.getUserType() != SysUserType.admin &&
                currentMember.getUserType() != SysUserType.supplier) {
            //管理员不过滤条件
            throw new ValidationException("只能是管理员或供应商有权限配置接口");
        }
        SysUser sysUser = sysUserService.findById(sysUserApi.getUserId());
        SysUser userMember = sysUserService.findById(sysUserApi.getMemberId());
        if (sysUserApi.getOrderSourceId() != null) {
//            SysOrderSource sysOrderSource = sysOrderSourceService.find(sysUserApi.getOrderSourceId());
//            Assert.notNull(sysOrderSource,"未找到订单来源信息");
//            sysUserApi.setOrderSourceName(sysOrderSource.getName());
        }

        sysUserApi.setName(sysUser.getAccount() + "/" + sysUser.getUsername());
        sysUserApi.setMemberAccount(userMember.getAccount());
        sysUserApi.setMemberName(userMember.getUsername());
        //sysUserApi.setClientId(RandomStringUtils.randomAlphabetic(32));
        //sysUserApi.setAppKey(RandomStringUtils.randomAlphabetic(32));
        //sysUserApi.setSecretKey(RandomStringUtils.randomAlphabetic(32));
        //sysUserApi.setApiPrefix(RandomStringUtils.randomAlphabetic(32));
        sysUserApi.setUpdateBy(currentMember.getId());
        SysUserApi respSysUserApi = super.update(sysUserApi);
        return respSysUserApi;
    }

    /**
     * 通过ids删除用户api
     *
     * @param ids
     * @return
     */
    public void delete(@RequestParam("ids") Long[] ids, SysUser currentUser) {
        Assert.notNull(ids, "ids不能为空");
        for (Long id : ids) {
            super.delete(id);
        }
    }
}