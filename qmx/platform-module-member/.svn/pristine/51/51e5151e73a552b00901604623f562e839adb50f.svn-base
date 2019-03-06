package com.qmx.member.web.controller;

import com.qmx.appextend.controller.BaseController;
import com.qmx.base.api.dto.PageDto;
import com.qmx.base.api.dto.RestResponse;
import com.qmx.base.api.exception.BusinessException;
import com.qmx.member.enumerate.StateType;
import com.qmx.member.model.GdsMember;
import com.qmx.member.model.GdsMemberExchangeOrder;
import com.qmx.member.model.GdsMemberIntegeral;
import com.qmx.member.model.GdsMemberMoney;
import com.qmx.member.query.GdsMemberExchangeOrderVO;
import com.qmx.member.query.GdsMemberIntegeralVO;
import com.qmx.member.query.GdsMemberMoneyVO;
import com.qmx.member.query.GdsMemberVO;
import com.qmx.member.service.GdsMemberExchangeOrderService;
import com.qmx.member.service.GdsMemberRechargeRecordService;
import com.qmx.member.service.GdsMemberService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 会员金额,积分,兑换记录
 */
@Controller
@RequestMapping("/rechargerecord")
public class GdsMemberRechargeRecordController extends BaseController {
    @Autowired
    private GdsMemberRechargeRecordService rechargeRecordService;
    @Autowired
    private GdsMemberService memberService;
    @Autowired
    private GdsMemberExchangeOrderService exchangeOrderService;

    @RequestMapping(value = "/list")
    public String list(GdsMemberVO dto, ModelMap model) {
        PageDto<GdsMember> restResponse = memberService.findList(getCurrentUser(), dto);
        dto.setSupplierId(getCurrentUser().getId());
        model.addAttribute("dto", dto);
        model.addAttribute("page", restResponse);
        return "/member/rechargerecord/list";
    }

    /**
     * 获得用户金额记录
     *
     * @param dto
     * @return
     */
    @RequestMapping(value = "/memberMoneyList")
    public String memberMoneyList(GdsMemberMoneyVO dto, Model model) {
        PageDto<GdsMemberMoney> restResponse = rechargeRecordService.memberMoneyList(dto);
        model.addAttribute("dto", dto);
        model.addAttribute("page", restResponse);
        return "/member/rechargerecord/memberMoneyList";
    }

    /**
     * 获得用户积分记录
     *
     * @param dto
     * @return
     */
    @RequestMapping(value = "/memberIntegeralList")
    public String memberIntegeralList(GdsMemberIntegeralVO dto, Model model) {
        PageDto<GdsMemberIntegeral> restResponse = rechargeRecordService.memberIntegeralList(dto);
        model.addAttribute("dto", dto);
        model.addAttribute("page", restResponse);
        return "/member/rechargerecord/memberIntegeralList";
    }

    /**
     * 获得用户兑换记录
     *
     * @param dto
     * @return
     */
    @RequestMapping(value = "/exchangeOrderList")
    public String exchangeOrderList(GdsMemberExchangeOrderVO dto, Model model) {
        PageDto<GdsMemberExchangeOrder> restResponse = exchangeOrderService.findList(getCurrentUser(), dto);
        model.addAttribute("dto", dto);
        model.addAttribute("page", restResponse);
        model.addAttribute("stateType", StateType.values());
        return "/member/rechargerecord/exchangeOrderList";
    }

    /**
     * 查询兑换码验证
     *
     * @param id 兑换记录id
     * @return
     */
    @ResponseBody
    @RequestMapping("/selectRedeemCode")
    public Boolean selectRedeemCode(Long id, String code) {
        GdsMemberExchangeOrder order = exchangeOrderService.find(id);
        String redeemCode = order == null ? "error" : order.getRedeemCode();
        if (StringUtils.isEmpty(code)) {
            return false;
        }
        return code.equals(redeemCode);
    }

    /**
     * 更新订单发货状态
     *
     * @param vo
     * @return
     */
    @RequestMapping(value = "/exchangeOrderState")
    public String exchangeOrderState(GdsMemberExchangeOrderVO vo) {
        RestResponse restResponse = exchangeOrderService.updateStateType(vo);
        if (!restResponse.success()) {
            throw new BusinessException(restResponse.getErrorMsg());
        }
        //TODO zzq修改订单状态成功,发送短信通知发货

        return "redirect:exchangeOrderList";
    }
}