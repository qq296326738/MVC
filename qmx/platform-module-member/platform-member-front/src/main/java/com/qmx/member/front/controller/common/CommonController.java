//package com.qmx.member.front.controller.common;
//import com.qmx.base.api.dto.RestResponse;
//import com.qmx.base.core.constants.Constants;
//import com.qmx.base.core.jcaptcha.JCaptchaUtil;
//import com.qmx.coreservice.service.SysAreaService;
//import com.qmx.facade.model.core.SysArea;
//import com.qmx.coreservice.model.SysArea;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
///**
// * 公共controller
// * Created by liubin on 2017/8/29.
// */
//@Controller
//@RequestMapping("/common")
//public class CommonController {
//
//    @Autowired
//    private SysAreaService sysAreaService;
//
//    @ResponseBody
//    @RequestMapping("/area")
//    public RestResponse<Map<String, String>> getArea(String parentId) {
//        List<SysArea> areaDTOList = sysAreaService.findByAreaId(parentId);
//        Map<String, String> result = new HashMap<>();
//        for (SysArea sysAreaDTO : areaDTOList) {
//            result.put(sysAreaDTO.getAreaId(), sysAreaDTO.getName());
//        }
//        return RestResponse.ok(result);
//    }
//
//    /**
//     * <b>Summary: </b>
//     * query 是否需要验证码
//     *
//     * @param request
//     * @param response
//     * @return true需要，false不需要
//     */
//    @ResponseBody
//    @RequestMapping(value = "/needCaptcha")
//    public RestResponse<Boolean> needCaptcha(HttpServletRequest request, HttpServletResponse response) {
//        try {
//            Object obj = request.getSession().getAttribute(Constants.PASSWORD_ERROR_COUNT);
//            Integer count = (obj == null) ? 0 : (Integer) obj;
//            return RestResponse.ok(count > 2);
//        } catch (Exception e) {
//            return RestResponse.ok(true);
//        }
//    }
//
//    /**
//     * 验证码
//     */
//    @RequestMapping(value = "/captcha", method = RequestMethod.GET)
//    public void image(String captchaId, HttpServletRequest request, HttpServletResponse response) throws Exception {
//        try {
//            System.err.println("getSession():"+request.getSession().getId());
//            System.err.println("getRequestedSessionId():"+request.getRequestedSessionId());
//            JCaptchaUtil.buildImage(request.getRequestedSessionId(),response);
//        } catch (Exception e) {
//        }
//    }
//
//    /**
//     * 错误提示
//     */
//    @RequestMapping("/error")
//    public String error() {
//        return "/error";
//    }
//}
