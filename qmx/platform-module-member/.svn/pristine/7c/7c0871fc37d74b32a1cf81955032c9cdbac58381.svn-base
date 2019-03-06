//package com.qmx.member.front.controller.common;
//
//import com.qmx.base.api.exception.BusinessException;
//import org.apache.commons.lang.StringUtils;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.ModelMap;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
///**
// * 关注公众号
// */
//@Controller
//@RequestMapping("/")
//public class SubscribeController {
//
//
//    /**
//     * 关注公众号
//     * @return
//     */
//    @RequestMapping(value = "/wxsubscribe")
//    public String weixinsubscribe(HttpServletRequest request, HttpServletResponse response,
//                                  ModelMap model){
//        String[] values = request.getParameterValues("imgurl");
//        if(values == null || values.length == 0) {
//            throw new BusinessException("出错了");
//        }
//        String imgurl = StringUtils.join(values, ",");
//        if(imgurl==null){
//            throw new BusinessException("出错了");
//        }
//        model.addAttribute("imgurl",imgurl);
//        return "/wx/subscribe/wxsubscribe";
//    }
//}
