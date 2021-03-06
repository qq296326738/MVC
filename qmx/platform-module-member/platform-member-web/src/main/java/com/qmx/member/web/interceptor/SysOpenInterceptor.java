package com.qmx.member.web.interceptor;

import com.qmx.base.core.base.BaseOpenController;
import com.qmx.base.core.constants.ApiConstants;
import com.qmx.base.core.constants.Constants;
import com.qmx.base.core.utils.DateUtil;
import com.qmx.base.core.utils.RequestUtils;
import com.qmx.base.core.utils.WebUtil;
import com.qmx.coreservice.model.SysUser;
import com.qmx.member.model.SysApiLog;
import com.qmx.member.service.SysApiLogService;
import com.qmx.member.web.api.SaveAPILog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.NamedThreadLocal;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @Author liubin
 * @Description open接口
 * @Date Created on 2017/12/20 14:51.
 * @Modified By
 */
public class SysOpenInterceptor extends HandlerInterceptorAdapter {

    private static final String SYS_API_LOG_NAME = "SysOpenInterceptor_SYS_API_LOG_NAME";

    private NamedThreadLocal<Long> startTimeThreadLocal = new NamedThreadLocal<Long>("StopWatch-StartTime");
    private NamedThreadLocal<String> apiThreadLocal = new NamedThreadLocal<>("apiThreadLocal");
    private static final Logger logger = LoggerFactory.getLogger(SysOpenInterceptor.class);

    @Autowired
    private SysApiLogService sysApiLogService;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //logger.info("进入open接口拦截器:" + request.getMethod() + " " + request.getRequestURI());
        //getHeadersInfo(request);
        long beginTime = System.currentTimeMillis();//1、开始时间
        startTimeThreadLocal.set(beginTime);//线程绑定变量（该数据只有当前请求的线程可见）
        if (!(handler instanceof HandlerMethod)) {
            return super.preHandle(request, response, handler);
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Object obj = handlerMethod.getBean();
        if (obj instanceof BaseOpenController) {
            BaseOpenController bean = (BaseOpenController) obj;
            String apiPlat = bean.getApiPlat(request);
            String appKey = bean.getAppKey(request);
            String apiName = bean.getApiName(request);
            apiThreadLocal.set(appKey);
            request.setAttribute(ApiConstants.API_NAME_ATTRIBUTE_NAME, apiName);
            String requestInfo = bean.getRequestStr(request);
            request.setAttribute(Constants.API_LOG_REQ_CONTENT_ATTRIBUTE_NAME, requestInfo);
            //获取注解
            // TODO: 2018/11/5 自己的注解
            SaveAPILog annotation = null;
            SysApiLog sysApiLog = null;
            if (handler instanceof HandlerMethod) {
                Method method = handlerMethod.getMethod();
                Class<?> methodDeclaringClass = method.getDeclaringClass();
                annotation = methodDeclaringClass.getAnnotation(SaveAPILog.class);
            }
            if (annotation != null) {
                sysApiLog = new SysApiLog();
                sysApiLog.setApiPlat(apiPlat);
                sysApiLog.setAppKey(appKey);
                sysApiLog.setRequestUri(apiName);
                sysApiLog.setRequestIp(RequestUtils.getIpAddr(request));
                sysApiLog.setRequestContent(requestInfo);
                sysApiLog.setRequestMethod(request.getMethod());
                request.setAttribute(SYS_API_LOG_NAME, sysApiLog);
            }
            logger.info(appKey + "--request--" + request.getMethod() + "--" + apiName + "--content-->" + requestInfo);
            Boolean flag = bean.preHandle(request, response);
            if (!flag) {
                String resp = request.getAttribute(Constants.API_LOG_RESP_CONTENT_ATTRIBUTE_NAME) + "";
                request.removeAttribute(Constants.API_LOG_RESP_CONTENT_ATTRIBUTE_NAME);
                request.removeAttribute(SYS_API_LOG_NAME);
                logger.info(appKey + "--" + apiName + "--response-->" + resp);
                response.setContentType("text/plain;charset=UTF-8");
                response.getWriter().write(resp);
                //保存失败日志
                if (sysApiLog != null) {
                    Object info = request.getAttribute("userInfo");
                    if (info != null) {
                        String userInfo = ((SysUser) info).getAccount();
                        if (StringUtils.hasText(userInfo)) {
                            sysApiLog.setSuccess(Boolean.FALSE);
                            sysApiLog.setCostTime((int) (System.currentTimeMillis() - beginTime));
                            sysApiLog.setResultContent(resp);
                            sysApiLog.setAccount(userInfo);
                            setAndsaveApiLog(sysApiLog);
                        }
                    }
                }
            }
            return flag;
        }
        return true;
    }

    /**
     * 获取header信息
     *
     * @param request
     */
    /*private static void getHeadersInfo(HttpServletRequest request) {
        //Map<String, String> map = new HashMap<String, String>();
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = headerNames.nextElement();
            String value = request.getHeader(key);
            logger.info("header-key:" + key + "-->" + value);
            //map.put(key, value);
        }
    }*/
    public void afterCompletion(
            HttpServletRequest request,
            HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        long endTime = System.currentTimeMillis();//2、结束时间
        long beginTime = startTimeThreadLocal.get();//得到线程绑定的局部变量（开始时间）
        long consumeTime = endTime - beginTime;//3、消耗的时间
        // 记录到日志文件
        String appKey = apiThreadLocal.get();
        String apiName = (String) request.getAttribute(ApiConstants.API_NAME_ATTRIBUTE_NAME);
        String resp = request.getAttribute(Constants.API_LOG_RESP_CONTENT_ATTRIBUTE_NAME) + "";
        request.removeAttribute(Constants.API_LOG_RESP_CONTENT_ATTRIBUTE_NAME);
        request.removeAttribute(ApiConstants.API_NAME_ATTRIBUTE_NAME);
        logger.info(appKey + "--" + apiName + "--response-->" + resp);
        String method = request.getParameter("method");
        String requestUri = request.getRequestURI();
        if (StringUtils.hasText(method)) {
            requestUri += "/" + method;
        }
        String message = "开始时间: {}; 结束时间: {}; 耗时: {}ms; Method:{}; URI: {};Status:{}; Host:{}; ";
        logger.info(message, DateUtil.format(beginTime, "HH:mm:ss.SSS"),
                DateUtil.format(endTime, "HH:mm:ss.SSS"), consumeTime,
                request.getMethod(), requestUri, response.getStatus(), WebUtil.getHost(request));

        //保存日志
        SysApiLog sysApiLog = (SysApiLog) request.getAttribute(SYS_API_LOG_NAME);
        if (sysApiLog != null) {
            if (method != null) {
                sysApiLog.setRequestUri(method);//系统接口和去哪儿接口
            }
            SysUser sysUser = (SysUser) request.getAttribute("userInfo");
            if (sysUser != null) {
                sysApiLog.setAccount(sysUser.getAccount());
            }
            try {
                Boolean succ = (Boolean) request.getAttribute(BaseOpenController.SYS_API_LOG_OP_SUCCESS);
                if (succ != null && succ) {
                    sysApiLog.setSuccess(Boolean.TRUE);
                } else {
                    sysApiLog.setSuccess(Boolean.FALSE);
                }
                //系统订单号
                Long orderId = (Long) request.getAttribute(BaseOpenController.SYS_API_LOG_ORDER_ID);
                sysApiLog.setOrderId(orderId);
                request.removeAttribute(BaseOpenController.SYS_API_LOG_ORDER_ID);
            } catch (Exception e) {
            }
            sysApiLog.setCostTime((int) (System.currentTimeMillis() - beginTime));
            sysApiLog.setResultContent(resp);
            setAndsaveApiLog(sysApiLog);
        }
    }

    private void setAndsaveApiLog(SysApiLog apiLog) {
        sysApiLogService.saveApiLog(apiLog);
    }

}