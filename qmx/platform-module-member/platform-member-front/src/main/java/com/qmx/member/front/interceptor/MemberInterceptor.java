package com.qmx.member.front.interceptor;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MemberInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        Object user = session.getAttribute("user");
        if (user != null) {
            return super.preHandle(request, response, handler);
        } else {
            request.setAttribute("msg","请从供应商获得正确登录地址,在进行登录操作");
            request.setAttribute("code","9999");
            request.setAttribute("time",new SimpleDateFormat("YYYY-MM-dd HH:mm:ss").format(new Date()));
            request.getRequestDispatcher("/member/error").forward(request,response);
            return false;
        }
    }
}
