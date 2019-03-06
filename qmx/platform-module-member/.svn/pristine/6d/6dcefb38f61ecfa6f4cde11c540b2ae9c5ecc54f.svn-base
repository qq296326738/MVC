package com.qmx.member.query;
import com.baomidou.mybatisplus.annotations.TableField;
import com.qmx.base.api.dto.QueryDto;

/**
 * @Author liubin
 * @Description 接口日志查询
 * @Date Created on 2018/7/4 17:21.
 * @Modified By
 */
public class SysApiLogVO extends QueryDto {

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
     * 请求IP
     */
    @TableField("request_ip")
    private String requestIp;
    /**
     * 是否成功
     */
    private Boolean success;


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

}