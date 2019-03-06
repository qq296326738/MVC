package com.qmx.member.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.qmx.base.api.base.BaseModel;
import com.qmx.coreservice.enumerate.APIPlatEnum;
import com.qmx.coreservice.enumerate.APPTypeEnum;
import com.qmx.coreservice.enumerate.PayMethodEnum;

/**
 * @Author liubin
 * @Description 系统用户api
 * @Date Created on 2018/2/8 17:00.
 * @Modified By
 */
@TableName("sys_user_api")
public class SysUserApi extends BaseModel {

    /**
     * api唯一标识前缀
     */
    @TableField("api_prefix")
    private String apiPrefix;
    /**
     * app名称
     */
    private String name;
    /**
     * api平台
     */
    @TableField("api_plat")
    private APIPlatEnum apiPlat;
    /**
     * 对应类型
     */
    @TableField("app_type")
    private APPTypeEnum appType;
    /**
     * 对应id
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 所属人id
     */
    @TableField("member_id")
    private Long memberId;
    /**
     * 对应订单来源id
     */
    @TableField("order_source_id")
    private Long orderSourceId;
    /**
     * 支付方式
     */
    @TableField("pay_method")
    private PayMethodEnum payMethod;
    /**
     * 下单自动支付
     */
    @TableField("auto_pay")
    private Boolean autoPay;
    /**
     * 支付后自动发码
     */
    @TableField("auto_send_code")
    private Boolean autoSendCode;
    /**
     * 默认发货状态
     */
    @TableField("shipping_status")
    private Boolean shippingStatus;
    /**
     * 客户端id
     */
    @TableField("client_id")
    private String clientId;
    /**
     * 接口账号
     */
    @TableField("app_key")
    private String appKey;
    /**
     * 账号密码
     */
    @TableField("secret_key")
    private String secretKey;
    /**
     * 消费通知地址
     */
    @TableField("consume_notify_url")
    private String consumeNotifyUrl;
    /**
     * 退款通知地址
     */
    @TableField("refund_notify_url")
    private String refundNotifyUrl;
    /**
     * 发码通知地址
     */
    @TableField("send_code_notify_url")
    private String sendCodeNotifyUrl;
    @TableField("order_source_name")
    private String orderSourceName;
    @TableField("member_account")
    private String memberAccount;
    @TableField("member_name")
    private String memberName;

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

    public String getOrderSourceName() {
        return orderSourceName;
    }

    public void setOrderSourceName(String orderSourceName) {
        this.orderSourceName = orderSourceName;
    }

    public String getMemberAccount() {
        return memberAccount;
    }

    public void setMemberAccount(String memberAccount) {
        this.memberAccount = memberAccount;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }
}