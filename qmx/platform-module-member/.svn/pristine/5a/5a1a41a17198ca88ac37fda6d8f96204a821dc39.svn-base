package com.qmx.member.front.interceptor;

import com.qmx.base.core.annotation.ServerDisposableToken;
import com.qmx.base.core.utils.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Token令牌
 * Created by liubin on 2017/9/21 16:06.
 */
public class TokenInterceptor extends HandlerInterceptorAdapter {

    /**
     * "令牌"属性名称
     */
    private static final String TOKEN_ATTRIBUTE_NAME = "token";
    /**
     * "令牌"Cookie名称
     */
    private static final String TOKEN_COOKIE_NAME = "token";
    /**
     * "令牌"参数名称
     */
    private static final String TOKEN_PARAMETER_NAME = "token";
    /**
     * 错误消息
     */
    private static final String ERROR_MESSAGE = "Bad or missing token!";

    //private static final Logger logger = LoggerFactory.getLogger(TokenInterceptor.class);

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return super.preHandle(request, response, handler);
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        ServerDisposableToken annotation = method.getAnnotation(ServerDisposableToken.class);
        if (annotation != null) {
            //注解不为空标识强制服务器一次性token
            return serverDisposableToken(request, response);
        }
        return normalTokenVerify(request, response);
    }

    /**
     * 普通token验证
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    private boolean normalTokenVerify(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String token = CookieUtil.getCookie(request, TOKEN_COOKIE_NAME);
        if ("POST".equalsIgnoreCase(request.getMethod())) {
            String requestType = request.getHeader("X-Requested-With");
            if (requestType != null && requestType.equalsIgnoreCase("XMLHttpRequest")) {
                if (token != null && token.equals(request.getHeader(TOKEN_PARAMETER_NAME))) {
                    return true;
                } else {
                    response.addHeader("tokenStatus", "accessDenied");
                }
            } else {
                if (token != null && token.equals(request.getParameter(TOKEN_PARAMETER_NAME))) {
                    return true;
                }
            }
            if (token == null) {
                token = UUID.randomUUID().toString();
                CookieUtil.addCookie(request, response, TOKEN_COOKIE_NAME, token);
            }
            response.sendError(HttpServletResponse.SC_FORBIDDEN, ERROR_MESSAGE);
            return false;
        } else {
            if (token == null) {
                token = UUID.randomUUID().toString();
                CookieUtil.addCookie(request, response, TOKEN_COOKIE_NAME, token);
            }
            request.setAttribute(TOKEN_ATTRIBUTE_NAME, token);
            return true;
        }
    }

    /**
     * 服务端一次性token验证
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    private boolean serverDisposableToken(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String tokenInRequest = request.getParameter(TOKEN_PARAMETER_NAME);
        String newToken = UUID.randomUUID().toString();
        String tokenInSession = getAndSetToken(request, response, newToken);
        if ("POST".equalsIgnoreCase(request.getMethod())) {
            String requestType = request.getHeader("X-Requested-With");
            if (requestType != null && requestType.equalsIgnoreCase("XMLHttpRequest")) {
                String tokenInHeader = request.getHeader(TOKEN_PARAMETER_NAME);
                if (tokenInSession != null && tokenInSession.equals(tokenInHeader)) {
                    return true;
                } else {
                    response.addHeader("tokenStatus", "accessDenied");
                }
            } else {
                if (tokenInSession != null && tokenInSession.equals(tokenInRequest)) {
                    return true;
                }
            }
            response.sendError(HttpServletResponse.SC_FORBIDDEN, ERROR_MESSAGE);
            return false;
        } else {
            if (tokenInSession == null) {
                getAndSetToken(request, response, newToken);
            }
            request.setAttribute(TOKEN_ATTRIBUTE_NAME, newToken);
            return true;
        }
    }

    private String getAndSetToken(HttpServletRequest request, HttpServletResponse response, String newToken) {
        HttpSession session = request.getSession();
        String key = "SESSION-TOKEN:" + session.getId();
        String tokenInSession = stringRedisTemplate.opsForValue().getAndSet(key, newToken);
        stringRedisTemplate.expire(key, 20, TimeUnit.MINUTES);//20分钟
        CookieUtil.addCookie(request, response, TOKEN_COOKIE_NAME, newToken);
        return tokenInSession;
    }
}
