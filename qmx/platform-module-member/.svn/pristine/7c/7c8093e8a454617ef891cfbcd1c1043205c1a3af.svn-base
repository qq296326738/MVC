package com.qmx.member.web.controller;

import com.qmx.appextend.controller.BaseController;
import com.qmx.base.api.dto.PageDto;
import com.qmx.base.api.dto.RestResponse;
import com.qmx.base.api.exception.BusinessException;
import com.qmx.member.model.GdsMemberLevel;
import com.qmx.member.query.GdsMemberLevelVO;
import com.qmx.member.service.GdsMemberLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 会员等级管理
 */
@Controller
@RequestMapping("/memberLevel")
public class GdsMemberLevelController extends BaseController {
    @Autowired
    private GdsMemberLevelService memberLevelRemoteService;

    @RequestMapping(value = "/list")
    public String list(GdsMemberLevelVO dto, ModelMap model) {
        PageDto<GdsMemberLevel> restResponse = memberLevelRemoteService.findList(getCurrentUser(), dto);
        model.addAttribute("dto", dto);
        model.addAttribute("page", restResponse);
        return "/member/memberLevel/list";
    }

    @RequestMapping(value = "/add")
    public String add(ModelMap model) {
        RestResponse<List<GdsMemberLevel>> levellist = memberLevelRemoteService.findLevelAll(getCurrentUser());
        model.addAttribute("lList", levellist.getData());
        return "/member/memberLevel/add";
    }

    @RequestMapping(value = "/save")
    public String save(GdsMemberLevelVO dto) {
        RestResponse<GdsMemberLevel> restResponse = memberLevelRemoteService.createDto(getCurrentUser(), dto);
        if (!restResponse.success()) {
            throw new BusinessException(restResponse.getErrorMsg());
        }
        return "redirect:list";
    }

    @RequestMapping(value = "/edit")
    public String edit(Long id, ModelMap model) {
        GdsMemberLevel gdsMemberLevel = memberLevelRemoteService.find(id);
        if (gdsMemberLevel == null) {
            throw new BusinessException("获取等级失败");
        }
        RestResponse<List<GdsMemberLevel>> levellist = memberLevelRemoteService.findLevelAll(getCurrentUser());
        if(!levellist.success()){
            throw new BusinessException(levellist.getErrorMsg());
        }
            for(GdsMemberLevel memberLevel : levellist.getData()){
                if (memberLevel.getId().equals(gdsMemberLevel.getId())){
                    levellist.getData().remove(memberLevel);
                    break;
                }
            }
        model.addAttribute("lList", levellist.getData());
        model.addAttribute("dto", gdsMemberLevel);
        return "/member/memberLevel/edit";
    }

    @RequestMapping(value = "/update")
    public String update(GdsMemberLevelVO dto) {
        Assert.notNull(dto, "数据不能为空");
        RestResponse<GdsMemberLevel> restResponse = memberLevelRemoteService.updateDto(getCurrentUser(), dto);
        if (!restResponse.success()) {
            throw new BusinessException(restResponse.getErrorMsg());
        }
        return "redirect:list";
    }

    @RequestMapping(value = "/delete")
    public String delete(Long id) {
        RestResponse restResponse = memberLevelRemoteService.deleteDto(getCurrentUser(), id);
        if (!restResponse.success()) {
            throw new BusinessException(restResponse.getErrorMsg());
        }
        return "redirect:list";
    }
}
