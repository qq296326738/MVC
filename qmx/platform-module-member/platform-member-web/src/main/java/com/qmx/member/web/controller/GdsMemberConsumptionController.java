package com.qmx.member.web.controller;

import com.qmx.appextend.controller.BaseController;
import com.qmx.base.api.base.RestResult;
import com.qmx.base.api.dto.PageDto;
import com.qmx.base.api.dto.RestResponse;
import com.qmx.base.api.exception.BusinessException;
import com.qmx.member.enumerate.ProductType;
import com.qmx.member.model.GdsMemberAssociated;
import com.qmx.member.model.GdsMemberConsumption;
import com.qmx.member.model.GdsMemberLevel;
import com.qmx.member.query.GdsMemberConsumptionVO;
import com.qmx.member.remoteapi.ProductService;
import com.qmx.member.remoteapi.ReleaseService;
import com.qmx.member.service.GdsMemberConsumptionService;
import com.qmx.member.service.GdsMemberLevelService;
import com.qmx.shop.model.commodity.Release;
import com.qmx.shop.model.ticket.Product;
import com.qmx.shop.query.commodity.ReleaseVO;
import com.qmx.shop.query.ticket.ProductVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;
import java.util.List;

/**
 * 会员消费管理
 */
@Controller
@RequestMapping("/consumption")
public class GdsMemberConsumptionController extends BaseController {
    @Autowired
    private GdsMemberConsumptionService consumptionService;
    @Autowired
    private GdsMemberLevelService memberLevelService;
    @Autowired
    private ProductService shopProductService;
    @Autowired
    private ReleaseService releaseService;

    @RequestMapping(value = "/list")
    public String list(GdsMemberConsumptionVO dto, ModelMap model) {
        PageDto<GdsMemberConsumption> consumption = consumptionService.findList(getCurrentUser(), dto);
        RestResponse<List<GdsMemberLevel>> restResponse = memberLevelService.findLevelAll(getCurrentUser());
        if (!restResponse.success()) {
            throw new BusinessException(restResponse.getErrorMsg());
        }
        model.addAttribute("levelAll", restResponse.getData());
        model.addAttribute("dto", dto);
        model.addAttribute("page", consumption);
        return "/member/Consumption/list";
    }

    @RequestMapping(value = "/add")
    public String add(ModelMap model) {
        RestResponse<List<GdsMemberLevel>> restResponse = memberLevelService.findLevelAll(getCurrentUser());
        if (!restResponse.success()) {
            throw new BusinessException(restResponse.getErrorMsg());
        }
        model.addAttribute("listL", restResponse.getData());
        return "/member/Consumption/add";
    }

    //添加可以消费门票
    @RequestMapping(value = "productlist")
    public String productlist(ProductVO dto, ModelMap model) {
        model.addAttribute("dto", dto);
        RestResult<PageDto<Product>> result = shopProductService.queryList(dto, getCurrentMember().getId());
        model.addAttribute("page", result.getData());
        return "/member/Consumption/productlist";
    }

    //添加可以消费商品
    @RequestMapping(value = "/releaselist", method = RequestMethod.GET)
    public String releaselist(ReleaseVO dto, ModelMap modelMap) {
        modelMap.addAttribute("dto", dto);
        RestResult<PageDto<Release>> page = releaseService.findPage(dto, getCurrentMember().getId());
        modelMap.addAttribute("page", page.getData());
        return "/member/Consumption/releaselist";
    }

    @RequestMapping(value = "/save")
    public String save(GdsMemberConsumptionVO dto) {
        RestResponse<GdsMemberConsumption> restResponse = consumptionService.createDto(getCurrentUser(), dto);
        if (!restResponse.success()) {
            throw new BusinessException(restResponse.getErrorMsg());
        }
        return "redirect:list";
    }

    @RequestMapping(value = "/edit")
    public String edit(Long id, ModelMap model) {
        RestResponse<GdsMemberConsumption> restResponse = consumptionService.findById(id);
        if (!restResponse.success()) {
            throw new BusinessException(restResponse.getErrorMsg());
        }
        RestResponse<List<GdsMemberLevel>> listL = memberLevelService.findLevelAll(getCurrentUser());
        if (!listL.success()) {
            throw new BusinessException(listL.getErrorMsg());
        }
        GdsMemberConsumption consumption = restResponse.getData();
        HashMap<ProductType, List<GdsMemberAssociated>> map = consumption.getMap();
        List<GdsMemberAssociated> menpiao = map.get(ProductType.menpiao);
        List<GdsMemberAssociated> shangpin = map.get(ProductType.shangpin);
        model.addAttribute("menpiao", menpiao);
        model.addAttribute("shangpin", shangpin);
        model.addAttribute("dto", consumption);
        model.addAttribute("listL", listL.getData());
        return "/member/Consumption/edit";
    }

    @RequestMapping(value = "/update")
    public String update(GdsMemberConsumptionVO dto) {
        RestResponse<GdsMemberConsumption> restResponse = consumptionService.updateDto(getCurrentUser(), dto);
        if (!restResponse.success()) {
            throw new BusinessException(restResponse.getErrorMsg());
        }
        return "redirect:list";
    }

    @RequestMapping(value = "/delete")
    public String delete(Long id) {
        RestResponse restResponse = consumptionService.deleteDto(getCurrentUser(), id);
        if (!restResponse.success()) {
            throw new BusinessException(restResponse.getErrorMsg());
        }
        return "redirect:list";
    }
}
