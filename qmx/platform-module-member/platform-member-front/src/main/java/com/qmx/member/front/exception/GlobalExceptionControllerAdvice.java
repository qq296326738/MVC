package com.qmx.member.front.exception;


import com.fasterxml.jackson.core.JsonParseException;
import com.netflix.hystrix.exception.HystrixBadRequestException;
import com.qmx.base.api.exception.BaseException;
import com.qmx.base.api.exception.BusinessException;
import com.qmx.base.api.exception.ValidationException;
import com.qmx.base.core.utils.SpringUtil;
import org.mybatis.spring.MyBatisSystemException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

/**
 * Spring mvc controller类异常处理扩展点,对于系统controller中会抛出的一次的统一处理
 *
 * @author liubin
 *
 */
@ControllerAdvice
public class GlobalExceptionControllerAdvice {

    private Logger logger = LoggerFactory.getLogger(GlobalExceptionControllerAdvice.class);

    /**
     * mvc框架进行数据验证失败抛出的异常处理
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(BindException.class)
    public String hanldFrameworkValidationException(HttpServletRequest request, BindException ex) {
        String data = null;
        BindingResult bindingResult = ex.getBindingResult();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            String field = fieldError.getField();
            String errorMesssage = fieldError.getDefaultMessage();
            String code = fieldError.getCode();
            Object[] arguments = fieldError.getArguments();
            data += "field:"+field+",errorMesssage:"+errorMesssage+"code:"+code+",arguments:"+arguments+"\r\n";
        }
        logger.debug("catch ValidationException: {}, errors: {}", ex, data);
        request.setAttribute("content","数据绑定异常，请检查。");
        return "/common/error";
    }

    /**
     * 自定义数据验证异常处理类
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(ValidationException.class)
    public String handleValidationException(HttpServletRequest request,ValidationException ex) {
        logger.debug("catch ValidationException: {}", ex);
        request.setAttribute("content",ex.getMessage());
        return "/common/error";
    }

    /**
     * 自定义BusinessException异常处理类
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(BusinessException.class)
    public String handleBusinessException(HttpServletRequest request,BusinessException ex) {
        logger.debug("catch ValidationException: {}, errors: {}", ex, ex.getMessage());
        request.setAttribute("content",ex.getMessage());
        return "/common/error";
    }

    /**
     * http请求方法不匹配
     * @param ex
     * @return
     */

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public String handleHttpRequestMethodNotSupportedException(
            HttpServletRequest request,HttpRequestMethodNotSupportedException ex){
        logger.warn(ex.getMessage(),ex);
        request.setAttribute("content",ex.getMessage());
        return "/common/error";
    }

    /**
     * 捕捉类型错误Assert.notNull(password,"password不能为空");
     * @param ex
     * @return
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public String handleIllegalArgumentException(HttpServletRequest request,IllegalArgumentException ex){
        logger.warn(ex.getMessage(),ex);
        request.setAttribute("content",ex.getMessage());
        return "/common/error";
    }

    @ExceptionHandler(HystrixBadRequestException.class)
    public String handleHystrixBadRequestException(HttpServletRequest request,HystrixBadRequestException ex){
        logger.warn(ex.getMessage(),ex);
        request.setAttribute("content",ex.getMessage());
        return "/common/error";
    }

    /**
     * 数据DataIntegrityViolationException异常
     * @param ex
     * @return
     */
    @ExceptionHandler(DataIntegrityViolationException.class)
    public String handleDataIntegrityViolationException(HttpServletRequest request,DataIntegrityViolationException ex){
        logger.error(ex.getMessage(),ex);
        request.setAttribute("content","DataIntegrityViolation请稍后重试。");
        return "/common/error";
    }

    /**
     * 数据SQLException异常
     * @param ex
     * @return
     */
    @ExceptionHandler(SQLException.class)
    public String handleSQLException(HttpServletRequest request,SQLException ex){
        logger.error(ex.getMessage(),ex);
        request.setAttribute("content","SQLException，请稍后重试。");
        return "/common/error";
    }

    /**
     * 数据BadSqlGrammarException异常
     * @param ex
     * @return
     */
    @ExceptionHandler(BadSqlGrammarException.class)
    public String handleBadSqlGrammarException(HttpServletRequest request,BadSqlGrammarException ex){
        logger.error(ex.getMessage(),ex);
        request.setAttribute("content","BadSqlGrammar请稍后重试。");
        return "/common/error";
    }

    @ExceptionHandler(JsonParseException.class)
    public String handleJsonParseException(HttpServletRequest request,JsonParseException ex){
        logger.error(ex.getMessage(),ex);
        request.setAttribute("content","JSON格式错误："+ex.getMessage());
        return "/common/error";
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public String handleHttpMessageNotReadableException(HttpServletRequest request,HttpMessageNotReadableException ex){
        logger.error(ex.getMessage(),ex);
        request.setAttribute("content","数据格式错误："+ex.getMessage());
        return "/common/error";
    }

    /**
     * mybitis错误
     * @param ex
     * @return
     */
    @ExceptionHandler(MyBatisSystemException.class)
    public String handleMyBatisSystemException(HttpServletRequest request,MyBatisSystemException ex){
        logger.error(ex.getMessage(),ex);
        request.setAttribute("content","系统内部错误，请稍后重试");
        return "/common/error";
    }

    /**
     * UncategorizedSQLException
     * 类似Cause: java.sql.SQLException: Incorrect integer value: 'BACK' for column 'consume_type' at row 1
     * @param ex
     * @return
     */
    @ExceptionHandler(UncategorizedSQLException.class)
    public String handleUncategorizedSQLException(HttpServletRequest request,UncategorizedSQLException ex){
        logger.error(ex.getMessage(),ex);
        request.setAttribute("content","UncategorizedSQL请稍后重试");
        return "/common/error";
    }

    /**
     * 门票异常
     * @param request
     * @param ex
     * @return
     */
  /*  @ExceptionHandler(TicketException.class)
    public String handleTicketException(HttpServletRequest request,TicketException ex){
        logger.error(ex.getMessage(),ex);
        request.setAttribute("content",ex.getMessage());
        return "/common/error";
    }*/

    @ExceptionHandler(BaseException.class)
    public String handleBaseException(HttpServletRequest request,BaseException ex){
        logger.error(ex.getMessage(),ex);
        request.setAttribute("content",ex.getMessage());
        return "/common/error";
    }



    /**
     * 其他所有抛出异常处理类
     * @param ex
     * @return
     */
    @ExceptionHandler(Throwable.class)
    public String hanldSystemException(HttpServletRequest request,Throwable ex) {
        logger.error("System error happen: (classType:"+ex.getClass().getName()+") " + ex.getMessage(), ex);
        String currentModule = SpringUtil.getApplicationContext().getEnvironment()
                .getProperty("spring.application.name");
        if (logger.isDebugEnabled()) {
            StackTraceElement[] stackTraceElements = ex.getStackTrace();
            if (stackTraceElements != null) {
                StringBuffer sb = new StringBuffer();
                for (int i = 0; i < stackTraceElements.length; i++) {
                    if (i > 5) {
                        break;
                    }
                    sb.append(stackTraceElements[i].toString());
                }
                logger.error("Error from " + currentModule + "=> " + ex.getMessage() + ", ####stackTrace:" + sb.toString() + "#####");
            }
        }
        request.setAttribute("content","出错啦，请稍后重试");
        return "/common/error";
    }
}
