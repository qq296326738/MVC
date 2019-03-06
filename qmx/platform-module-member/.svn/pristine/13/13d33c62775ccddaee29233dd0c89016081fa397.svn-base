package com.qmx.member.web.controller;

import com.qmx.appextend.controller.BaseController;
import com.qmx.base.api.dto.PageDto;
import com.qmx.base.api.dto.RestResponse;
import com.qmx.base.api.exception.BusinessException;
import com.qmx.member.model.GdsMemberLevel;
import com.qmx.member.model.GdsMemberRechargeRule;
import com.qmx.member.query.GdsMemberRechargeRuleVO;
import com.qmx.member.service.GdsMemberLevelService;
import com.qmx.member.service.GdsMemberRechargeRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 会员充值管理
 */
@Controller
@RequestMapping("/rechargerule")
public class GdsMemberRechargeRuleController extends BaseController {
    @Autowired
    private GdsMemberRechargeRuleService rechargeRuleeService;
    @Autowired
    private GdsMemberLevelService memberLeveleService;

    @RequestMapping(value = "/list")
    public String list(GdsMemberRechargeRuleVO dto, ModelMap model) {
        model.addAttribute("dto", dto);
        RestResponse<List<GdsMemberLevel>> levelAll = memberLeveleService.findLevelAll(getCurrentUser());
        model.addAttribute("levelAll", levelAll.getData());
        PageDto<GdsMemberRechargeRule> restResponse = rechargeRuleeService.findList(getCurrentUser(), dto);
        model.addAttribute("page", restResponse);
        return "/member/rechargerule/list";
    }

    @RequestMapping(value = "/add")
    public String add(ModelMap model) {
        RestResponse<List<GdsMemberLevel>> levellist = memberLeveleService.findLevelAll(getCurrentUser());
        if (!levellist.success()) {
            throw new BusinessException(levellist.getErrorMsg());
        }
        model.addAttribute("lList", levellist.getData());
        return "/member/rechargerule/add";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(GdsMemberRechargeRuleVO dto, HttpServletRequest request) {
        Assert.notNull(dto, "数据不能为空");
        RestResponse<GdsMemberRechargeRule> restResponse = rechargeRuleeService.createDto(getCurrentUser(), dto);
        if (!restResponse.success()) {
            throw new BusinessException(restResponse.getErrorMsg());
        }
        return "redirect:list";
    }

    @RequestMapping(value = "/edit")
    public String edit(Long id, ModelMap model) {
        RestResponse<GdsMemberRechargeRule> restResponse = rechargeRuleeService.findById(id);
        RestResponse<List<GdsMemberLevel>> levellist = memberLeveleService.findLevelAll(getCurrentUser());
        if (!restResponse.success()) {
            throw new BusinessException(restResponse.getErrorMsg());
        }
        GdsMemberRechargeRule dto = restResponse.getData();
        model.addAttribute("dto", dto);
        model.addAttribute("lList", levellist.getData());
        return "/member/rechargerule/edit";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(GdsMemberRechargeRuleVO dto) {
        Assert.notNull(dto, "数据不能为空");
        RestResponse<GdsMemberRechargeRule> restResponse = rechargeRuleeService.updateDto(getCurrentUser(), dto);
        if (!restResponse.success()) {
            throw new BusinessException(restResponse.getErrorMsg());
        }
        return "redirect:list";
    }

    @RequestMapping(value = "/delete")
    public String delete(Long id) {
        RestResponse restResponse = rechargeRuleeService.deleteDto(getCurrentUser(), id);
        if (!restResponse.success()) {
            throw new BusinessException(restResponse.getErrorMsg());
        }
        return "redirect:list";
    }
}
