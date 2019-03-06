package com.qmx.member.remoteapi;

import com.qmx.base.api.base.RestResult;
import com.qmx.wxbasics.model.WxAuthorization;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 微信接口
 */
@FeignClient("${com.qmx.wxbasicsservice.name}")
@RequestMapping("/internal-api/wxAuthorization")
@Component
public interface WxAuthorizationRemoteApi {
    /**
     * 获得供应商授权信息
     *
     * @param mid 供应商id
     */
    @RequestMapping(value = "/findByMemberId", method = RequestMethod.POST)
    RestResult<WxAuthorization> findByMemberId(@RequestParam("mid") Long mid);

    /**
     * 获得openid方法
     *
     * @param appId 供应商微信appid
     * @param url   用户访问的url
     */
    @RequestMapping(value = "/getRedirectUrl", method = RequestMethod.POST)
    RestResult<String> getRedirectUrl(@RequestParam("appId") String appId,
                                      @RequestParam("url") String url);

    /**
     * 通过appId获取accessToken(过期自动刷新)
     *
     * @param appId 供应商微信appid
     */
    @RequestMapping(value = "/getAccessToken", method = RequestMethod.POST)
    RestResult<String> getAccessToken(@RequestParam("appId") String appId);
}
