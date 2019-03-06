package com.qmx.member.web.controller;

import com.qmx.appextend.controller.BaseController;
import com.qmx.appextend.remoreapisub.SysPayOrderClient;
import com.qmx.appextend.remoreapisub.SysUserClient;
import com.qmx.base.api.dto.PageDto;
import com.qmx.base.api.enumerate.SysUserType;
import com.qmx.coreservice.enumerate.APIPlatEnum;
import com.qmx.coreservice.enumerate.APPTypeEnum;
import com.qmx.coreservice.enumerate.PayMethodEnum;
import com.qmx.coreservice.model.SysUser;
import com.qmx.coreservice.query.UserQueryVo;
import com.qmx.member.model.SysUserApi;
import com.qmx.member.query.SysUserApiVO;
import com.qmx.member.service.SysUserApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author liubin
 * @Description 系统用户api服务
 * @Date Created on 2018/2/9 15:17.
 * @Modified By
 */
@Controller
@RequestMapping("/userApi")
public class SysUserApiController extends BaseController {

    @Autowired
    private SysUserApiService sysUserApiService;
    @Autowired
    private SysUserClient sysUserClient;
//        @Autowired
//    private SysOrderSourceService sysOrderSourceService;
//    @Autowired
//    private SysSightService sysSightService;

    /**
     * 查看景区接口信息
     *
     * @param request
     * @param sightId
     * @return
     */
    @RequestMapping(value = "/apiInfo", method = RequestMethod.GET)
    public String apiInfo(HttpServletRequest request, Long sightId, Long userId) {
        SysUserApi restResponse = null;
        SysUser currentMember = getCurrentMember();
        if (userId != null) {
            restResponse = sysUserApiService.findUserApiInfo(userId, currentMember);
        } else {
            Assert.notNull(sightId, "sightId和userId不能同时为空");
//            SysSight sysSight = sysSightService.find(sightId);
//            Assert.notNull(sysSight,"未找到sightId对应景区信息");
//            restResponse = sysUserApiService.findSightApiInfo(sightId,sysSight.getSupplierId(),sysSight.getSightName(),currentMember);
        }
        request.setAttribute("apiInfo", restResponse);
        return "/user/user_api/api_info";
    }

    /**
     * 获取用户系统用户api分页
     *
     * @param request
     * @param sysUserApiVO
     * @return
     */
    @RequestMapping(path = "/list", method = RequestMethod.GET)
    public String list(HttpServletRequest request, SysUserApiVO sysUserApiVO) {
        sysUserApiVO.setAppType(APPTypeEnum.USER);
        PageDto<SysUserApi> restResponse = sysUserApiService.findPage(sysUserApiVO, getCurrentMember());
        request.setAttribute("queryDto", sysUserApiVO);
        request.setAttribute("apiPlats", APIPlatEnum.values());
        request.setAttribute("page", restResponse);
        return "/user/user_api/list";
    }

    /**
     * 根据id获取系统用户api
     *
     * @return
     */
    @RequestMapping(path = "/add", method = RequestMethod.GET)
    public String add(HttpServletRequest request) {
        request.setAttribute("apiPlats", APIPlatEnum.values());

        List<PayMethodEnum> list = new ArrayList<>();
        list.add(PayMethodEnum.OFFLINE_PAY);
        list.add(PayMethodEnum.DEPOSIT);
        list.add(PayMethodEnum.DEPOSIT_CREDIT);
        request.setAttribute("payMethods", list);
//        List<SysOrderSource> listRestResponse = sysOrderSourceService.findSysOtaModuleId(null,getCurrentMember());
//        request.setAttribute("orderSources",listRestResponse);
        request.setAttribute("orderSources",null);
        return "/user/user_api/add";
    }

    /**
     * 添加系统用户接口
     *
     * @param sysUserApiDTO
     * @return
     */
    @RequestMapping(path = "/save", method = RequestMethod.POST)
    public String save(SysUserApi sysUserApiDTO) {
        Assert.notNull(sysUserApiDTO, "系统接口用户信息不能为空");
        Assert.notNull(sysUserApiDTO.getUserId(), "系统接口用户id不能为空");
        Assert.notNull(sysUserApiDTO.getPayMethod(), "系统接口用户支付方式不能为空");
//        Assert.notNull(sysUserApiDTO.getOrderSourceId(), "系统接口用户订单来源id不能为空");
        sysUserApiService.createUserApi(sysUserApiDTO, getCurrentMember());
        return "redirect:list";
    }

    /**
     * 根据id获取系统用户api
     *
     * @param id
     * @return
     */
    @RequestMapping(path = "/edit", method = RequestMethod.GET)
    public String edit(HttpServletRequest request, Long id) {
        Assert.notNull(id, "id不能为空");
        SysUserApi restResponse = sysUserApiService.find(id);
        List<PayMethodEnum> list = new ArrayList<>();
        list.add(PayMethodEnum.OFFLINE_PAY);
        list.add(PayMethodEnum.DEPOSIT);
        list.add(PayMethodEnum.DEPOSIT_CREDIT);
        request.setAttribute("payMethods", list);
        request.setAttribute("dto", restResponse);
//        List<SysOrderSource> listRestResponse = sysOrderSourceService.findSysOtaModuleId(null,getCurrentMember());
//        request.setAttribute("orderSources",listRestResponse);
        return "/user/user_api/edit";
    }

    /**
     * 修改系统用户接口
     *
     * @param sysUserApiDTO
     * @return
     */
    @RequestMapping(path = "/update", method = {RequestMethod.POST})
    public String update(SysUserApi sysUserApiDTO) {
        Assert.notNull(sysUserApiDTO, "系统接口用户信息不能为空");
        Assert.notNull(sysUserApiDTO.getId(), "系统接口用户id不能为空");
//        Assert.notNull(sysUserApiDTO.getOrderSourceId(), "系统接口用户订单来源id不能为空");
        sysUserApiService.updateUserApi(sysUserApiDTO, getCurrentMember());
        return "redirect:list";
    }

    /**
     * 通过id删除用户api
     *
     * @param id
     * @return
     */
    @RequestMapping(path = "/delete", method = {RequestMethod.POST, RequestMethod.GET})
    public String delete(Long id) {
        Assert.notNull(id, "id不能为空");
        sysUserApiService.delete(new Long[]{id}, getCurrentMember());
        return "redirect:list";
    }

    /**
     * 票型下拉菜单
     *
     * @param q
     * @param page
     * @param row
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/listForJson", method = RequestMethod.POST)
    public Object listForJson(
            @RequestParam(defaultValue = "") String q,
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer row) {
        Map<String, Object> result = new HashMap<>();
        List<Map<String, Object>> rows = new ArrayList<>();
        UserQueryVo userQueryVo = new UserQueryVo();
        userQueryVo.setPageIndex(page);
        userQueryVo.setPageSize(row);
        userQueryVo.setUserType(SysUserType.supplier);
        userQueryVo.setCurrentUserId(getCurrentUser().getId());
        PageDto<SysUser> pageDto = sysUserClient.findPage(/*getCurrentMember(),*/ userQueryVo);
        for (SysUser userDto : pageDto.getRecords()) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", userDto.getId() + "");
            map.put("name", userDto.getUsername());
            map.put("username", userDto.getAccount());
            rows.add(map);
        }
        result.put("total", pageDto.getTotal());
        result.put("rows", rows);
        result.put("footer", null);
        return result;
    }

}