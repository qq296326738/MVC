package com.qmx.member.web.controller;

import com.qmx.appextend.controller.BaseController;
import com.qmx.base.api.dto.PageDto;
import com.qmx.base.api.dto.RestResponse;
import com.qmx.base.api.exception.BusinessException;
import com.qmx.member.enumerate.DeliverType;
import com.qmx.member.enumerate.ExchangeProductType;
import com.qmx.member.model.GdsMemberExchange;
import com.qmx.member.query.GdsMemberExchangeVO;
import com.qmx.member.service.GdsMemberExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * 兑换商品管理
 */
@Controller
@RequestMapping("/exchange")
public class GdsMemberExchangeController extends BaseController {
    @Autowired
    private GdsMemberExchangeService gdsMemberExchangeService;

    @RequestMapping(value = "/list")
    public String list(GdsMemberExchangeVO vo, ModelMap model) {
        PageDto<GdsMemberExchange> list = gdsMemberExchangeService.findList(getCurrentUser(), vo);
        model.addAttribute("productTypes", ExchangeProductType.values());
        model.addAttribute("deliverTypes", DeliverType.values());
        model.addAttribute("dto", vo);
        model.addAttribute("page", list);
        return "/member/exchange/list";
    }

    @RequestMapping(value = "/add")
    public String add(ModelMap model) {
        ExchangeProductType[] productTypes = ExchangeProductType.values();
        DeliverType[] deliverTypes = DeliverType.values();
        model.addAttribute("productTypes", productTypes);
        model.addAttribute("deliverTypes", deliverTypes);
        return "/member/exchange/add";
    }


    @RequestMapping(value = "/save")
    public String save(GdsMemberExchangeVO dto) {
        RestResponse<GdsMemberExchange> restResponse = gdsMemberExchangeService.createDto(getCurrentUser(), dto);
        if (!restResponse.success()) {
            throw new BusinessException(restResponse.getErrorMsg());
        }
        return "redirect:list";
    }

    @RequestMapping(value = "/edit")
    public String edit(Long id, ModelMap model) {
        GdsMemberExchange exchange = gdsMemberExchangeService.find(id);
        model.addAttribute("dto", exchange);
        ExchangeProductType[] productTypes = ExchangeProductType.values();
        DeliverType[] deliverTypes = DeliverType.values();
        model.addAttribute("productTypes", productTypes);
        model.addAttribute("deliverTypes", deliverTypes);
        return "/member/exchange/edit";
    }

    @RequestMapping(value = "/update")
    public String update(GdsMemberExchangeVO dto) {
        RestResponse<GdsMemberExchange> restResponse = gdsMemberExchangeService.updateDto(getCurrentUser(), dto);
        if (!restResponse.success()) {
            throw new BusinessException(restResponse.getErrorMsg());
        }
        return "redirect:list";
    }

    @RequestMapping(value = "/delete")
    public String delete(Long id) {
        RestResponse restResponse = gdsMemberExchangeService.deleteDto(getCurrentUser(), id);
        if (!restResponse.success()) {
            throw new BusinessException(restResponse.getErrorMsg());
        }
        return "redirect:list";
    }

    /**
     * 定时更新商品过期状态
     * 每天定时查询一次是否有过期的商品
     */
    @Scheduled(cron = "0 0 1 * * ? ")
    public void updateState() {
        try {
            gdsMemberExchangeService.updateState();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
