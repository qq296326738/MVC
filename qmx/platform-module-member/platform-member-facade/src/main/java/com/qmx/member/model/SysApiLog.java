package com.qmx.member.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.qmx.base.api.base.BaseModel;

/**
 * @Author liubin
 * @Description 接口日志
 * @Date Created on 2018/7/4 16:44.
 * @Modified By
 */
@TableName("sys_api_log")
public class SysApiLog extends BaseModel {

    /**
     * 订单id
     */
	@TableField("order_id")
	private Long orderId;
    /**
     * appkey
     */
	@TableField("app_key")
	private String appKey;
    /**
     * 账号
     */
	private String account;
	@TableField("api_plat")
	private String apiPlat;
    /**
     * 接口名称
     */
	@TableField("request_uri")
	private String requestUri;
    /**
     * 请求方法
     */
	@TableField("request_method")
	private String requestMethod;
    /**
     * 请求内容
     */
	@TableField("request_content")
	private String requestContent;
    /**
     * 请求返回内容
     */
	@TableField("result_content")
	private String resultContent;
    /**
     * 请求IP
     */
	@TableField("request_ip")
	private String requestIp;
    /**
     * 是否成功
     */
	private Boolean success;
    /**
     * 请求耗时
     */
	@TableField("cost_time")
	private Integer costTime;


	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getAppKey() {
		return appKey;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getApiPlat() {
		return apiPlat;
	}

	public void setApiPlat(String apiPlat) {
		this.apiPlat = apiPlat;
	}

	public String getRequestUri() {
		return requestUri;
	}

	public void setRequestUri(String requestUri) {
		this.requestUri = requestUri;
	}

	public String getRequestMethod() {
		return requestMethod;
	}

	public void setRequestMethod(String requestMethod) {
		this.requestMethod = requestMethod;
	}

	public String getRequestContent() {
		return requestContent;
	}

	public void setRequestContent(String requestContent) {
		this.requestContent = requestContent;
	}

	public String getResultContent() {
		return resultContent;
	}

	public void setResultContent(String resultContent) {
		this.resultContent = resultContent;
	}

	public String getRequestIp() {
		return requestIp;
	}

	public void setRequestIp(String requestIp) {
		this.requestIp = requestIp;
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public Integer getCostTime() {
		return costTime;
	}

	public void setCostTime(Integer costTime) {
		this.costTime = costTime;
	}

}