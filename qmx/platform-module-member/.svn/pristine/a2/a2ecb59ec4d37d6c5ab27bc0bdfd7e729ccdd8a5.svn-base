package com.qmx.member.web.controller;

import com.qmx.appextend.controller.BaseController;
import com.qmx.base.api.dto.PageDto;
import com.qmx.base.api.enumerate.SysUserType;
import com.qmx.coreservice.model.SysUser;
import com.qmx.member.model.SysApiLog;
import com.qmx.member.model.SysUserApi;
import com.qmx.member.query.SysApiLogVO;
import com.qmx.member.service.SysApiLogService;
import com.qmx.member.service.SysUserApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author liubin
 * @Description 系统接口日志
 * @Date Created on 2018/7/4 17:19.
 * @Modified By
 */
@Controller
@RequestMapping("/sysApiLog")
public class SysAPILogController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(SysAPILogController.class);
    @Autowired
    private SysApiLogService apiLogService;
    @Autowired
    private SysUserApiService sysUserApiService;

    @RequestMapping("/list")
    public String list(HttpServletRequest request, SysApiLogVO apiLogVO) {
        SysUser user = getCurrentUser();
        if (user.getUserType() != SysUserType.admin) {
            SysUserApi userApi = sysUserApiService.findByUserId(user.getId());
            if (userApi != null) {
                apiLogVO.setAppKey(userApi.getAppKey());
            }
        }
//        Assert.isTrue(currentMember.getUserType() == SysUserType.admin,"该账号没有权限");
        PageDto<SysApiLog> restResponse = apiLogService.findPage(apiLogVO);
        request.setAttribute("queryVo", apiLogVO);
        request.setAttribute("page", restResponse);
        return "/user/sys_api_log/list";
    }

    /**
     * 查看
     *
     * @param request
     * @param id
     * @return
     */
    @RequestMapping("/view")
    public String view(HttpServletRequest request, Long id) {
        Assert.notNull(id, "id不能为空");
        SysApiLog sysApiLog = apiLogService.find(id);
        request.setAttribute("dto", sysApiLog);
        return "/user/sys_api_log/view";
    }

}
