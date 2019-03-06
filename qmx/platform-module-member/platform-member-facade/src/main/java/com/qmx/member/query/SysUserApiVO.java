package com.qmx.member.query;

import com.qmx.base.api.dto.QueryDto;
import com.qmx.coreservice.enumerate.APIPlatEnum;
import com.qmx.coreservice.enumerate.APPTypeEnum;
import com.qmx.coreservice.enumerate.PayMethodEnum;

/**
 * @Author liubin
 * @Description 系统用户api
 * @Date Created on 2018/2/8 17:00.
 * @Modified By
 */
public class SysUserApiVO extends QueryDto {

    /**
     * api唯一标识前缀
     */
    private String apiPrefix;
    /**
     * app名称
     */
    private String name;
    /**
     * api平台
     */
    private APIPlatEnum apiPlat;
    /**
     * 对应类型
     */
    private APPTypeEnum appType;
    /**
     * 对应用户id
     */
    private Long userId;
    /**
     * 所属人id
     */
    private Long memberId;
    /**
     * 对应订单来源id
     */
    private Long orderSourceId;
    /**
     * 支付方式
     */
    private PayMethodEnum payMethod;
    /**
     * 下单自动支付
     */
    private Boolean autoPay;
    /**
     * 支付后自动发码
     */
    private Boolean autoSendCode;
    /**
     * 默认发货状态
     */
    private Boolean shippingStatus;
    /**
     * 客户端id
     */
    private String clientId;
    /**
     * 接口账号
     */
    private String appKey;
    /**
     * 账号密码
     */
    private String secretKey;
    /**
     * 消费通知地址
     */
    private String consumeNotifyUrl;
    /**
     * 退款通知地址
     */
    private String refundNotifyUrl;
    /**
     * 发码通知地址
     */
    private String sendCodeNotifyUrl;

    public String getApiPrefix() {
        return apiPrefix;
    }

    public void setApiPrefix(String apiPrefix) {
        this.apiPrefix = apiPrefix;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public APIPlatEnum getApiPlat() {
        return apiPlat;
    }

    public void setApiPlat(APIPlatEnum apiPlat) {
        this.apiPlat = apiPlat;
    }

    public APPTypeEnum getAppType() {
        return appType;
    }

    public void setAppType(APPTypeEnum appType) {
        this.appType = appType;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Long getOrderSourceId() {
        return orderSourceId;
    }

    public void setOrderSourceId(Long orderSourceId) {
        this.orderSourceId = orderSourceId;
    }

    public PayMethodEnum getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(PayMethodEnum payMethod) {
        this.payMethod = payMethod;
    }

    public Boolean getAutoPay() {
        return autoPay;
    }

    public void setAutoPay(Boolean autoPay) {
        this.autoPay = autoPay;
    }

    public Boolean getAutoSendCode() {
        return autoSendCode;
    }

    public void setAutoSendCode(Boolean autoSendCode) {
        this.autoSendCode = autoSendCode;
    }

    public Boolean getShippingStatus() {
        return shippingStatus;
    }

    public void setShippingStatus(Boolean shippingStatus) {
        this.shippingStatus = shippingStatus;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getConsumeNotifyUrl() {
        return consumeNotifyUrl;
    }

    public void setConsumeNotifyUrl(String consumeNotifyUrl) {
        this.consumeNotifyUrl = consumeNotifyUrl;
    }

    public String getRefundNotifyUrl() {
        return refundNotifyUrl;
    }

    public void setRefundNotifyUrl(String refundNotifyUrl) {
        this.refundNotifyUrl = refundNotifyUrl;
    }

    public String getSendCodeNotifyUrl() {
        return sendCodeNotifyUrl;
    }

    public void setSendCodeNotifyUrl(String sendCodeNotifyUrl) {
        this.sendCodeNotifyUrl = sendCodeNotifyUrl;
    }

}