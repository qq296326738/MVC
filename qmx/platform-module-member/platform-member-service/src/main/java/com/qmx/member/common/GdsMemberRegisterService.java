package com.qmx.member.common;

import com.alibaba.fastjson.JSONObject;
import com.qmx.appextend.remoreapisub.SysSmsClient;
import com.qmx.appextend.remoreapisub.SysUserClient;
import com.qmx.base.api.annotation.GdsCacheConfig;
import com.qmx.base.api.exception.BusinessException;
import com.qmx.coreservice.dto.SendSmsDTO;
import com.qmx.coreservice.model.SysUser;
import com.qmx.member.model.GdsMemberExchangeOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * 发送短信
 */
@Service
@GdsCacheConfig(cacheNS = "GDS-MEMBER", cacheName = "GdsmemberRegister")
public class GdsMemberRegisterService {
    @Value("${com.qmx.admin.memberVerifyTemplateId:1024900665638805506}") //验证码短信id
    private Long memberVerifyTemplateId;
    @Value("${com.qmx.admin.memberExchangeTemplateId:986429008339169281}") //兑换码短信id
    private Long memberExchangeTemplateId;
    @Value("${com.qmx.admin.memberDeliverTemplateId:986448548758450178}") //发货短信id
    private Long memberDeliverTemplateId;
    @Autowired
    private SysUserClient sysUserService;
    @Autowired
    private SysSmsClient sysSmsService;

    /**
     * 发送验证码短信
     *
     * @param code   验证码
     * @param mobile 手机
     * @param userId 供应商id
     */
    public void send(String code, String mobile, Long userId) {
        SysUser userDto = this.findByUserId(mobile, userId);
        //封装短信类容
        SendSmsDTO sendSmsDto = new SendSmsDTO();
        sendSmsDto.setName(userDto.getAccount());
        sendSmsDto.setSupplierId(userDto.getSupplierId());
        sendSmsDto.setMemberId(userId);
        sendSmsDto.setTemplateId(memberVerifyTemplateId);
        sendSmsDto.setPhone(mobile);
        JSONObject object = new JSONObject();
        object.put("code", code);
        String content = object.toString();
        sendSmsDto.setSendContent(content);
        //发送短信
        sysSmsService.sendSms(sendSmsDto/*, userDto*/);
    }

    /**
     * 发送兑换码短信
     *
     * @param data 订单信息
     */
    public void sendExchange(GdsMemberExchangeOrder data) {
        SysUser userDto = this.findByUserId(data.getMobile(), data.getSupplierId());
        //封装短信类容
        SendSmsDTO sendSmsDto = new SendSmsDTO();
        sendSmsDto.setName(userDto.getAccount());
        sendSmsDto.setSupplierId(userDto.getSupplierId());
        sendSmsDto.setMemberId(data.getSupplierId());
        sendSmsDto.setTemplateId(memberExchangeTemplateId);
        sendSmsDto.setPhone(data.getMobile());
        JSONObject object = new JSONObject();
        object.put("contactName", data.getName()); //姓名
        object.put("releaseName", data.getProductName()); //商品名
        object.put("verificationCode", data.getRedeemCode());//兑换码
        String content = object.toString();
        sendSmsDto.setSendContent(content);
        //发送短信
        sysSmsService.sendSms(sendSmsDto/*, userDto*/);
    }

    /**
     * 发送发货短信
     *
     * @param data 订单信息
     */
    public void sendDeliver(GdsMemberExchangeOrder data) {
        SysUser userDto = this.findByUserId(data.getMobile(), data.getSupplierId());
        //封装短信类容
        SendSmsDTO sendSmsDto = new SendSmsDTO();
        sendSmsDto.setName(userDto.getAccount());
        sendSmsDto.setSupplierId(userDto.getSupplierId());
        sendSmsDto.setMemberId(data.getSupplierId());
        sendSmsDto.setTemplateId(memberDeliverTemplateId);
        sendSmsDto.setPhone(data.getMobile());
        JSONObject object = new JSONObject();
        object.put("contactName", data.getName()); //姓名
        object.put("releaseName", data.getProductName()); //商品名
        String content = object.toString();
        sendSmsDto.setSendContent(content);
        //发送短信
        sysSmsService.sendSms(sendSmsDto/*, userDto*/);
    }

    private SysUser findByUserId(String mobile, Long userId) {
        Assert.notNull(mobile, "系统异常！");
        Assert.notNull(userId, "供应商id不能为空");
        SysUser userDto = sysUserService.findById(userId);
        if (userDto == null) {
            throw new BusinessException("不存在的用户ID");
        }
        return userDto;
    }

}
