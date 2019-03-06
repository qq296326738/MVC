package com.qmx.member.front.controller.member;

import com.qmx.member.model.GdsMemberIntegeral;
import com.qmx.member.service.GdsMemberIntegeralService;
import com.qmx.member.service.GdsMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

/**
 * 积分记录
 */
@Controller
@RequestMapping("/member")
public class MemberIntegeralController {

    @Autowired
    private GdsMemberIntegeralService gdsMemberIntegeralService;
    @Autowired
    private GdsMemberService gdsMemberService;

    /**
     * 历史总充值->积分明细
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/integralList")
    public String integralList(Long id, ModelMap model) {
        Assert.notNull(id, "用户id不能为空");
        List<GdsMemberIntegeral> list = gdsMemberIntegeralService.getMemberIntegeralList(id);
        model.addAttribute("list", list);
        return "member/memberIntegeral/integralList";
    }

    /**
     * 历史总充值->积分明细->详细类型
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = {"/rechargeDetails", "/integralSign", "/integralConsume", "integralDetails"})
    public String rechargeDetails(Long id, String url, ModelMap model) {
        Assert.notNull(id, "用户id不能为空");
        GdsMemberIntegeral data = gdsMemberIntegeralService.findByRecordId(id);
        model.addAttribute("data", data);
        return "member/memberIntegeral/" + url;
    }

    /**
     * 历史总充值->兑换记录
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/exchangeList")
    public String exchangeList(Long id, ModelMap model) {
        Assert.notNull(id, "用户id不能为空");
        Map<String, List<GdsMemberIntegeral>> map = gdsMemberIntegeralService.getExchangeList(id);
        model.addAttribute("map", map);
        return "member/memberIntegeral/exchangeList";
    }

    /**
     * 每年定时消除会员积分
     */
    @Scheduled(cron = "0 0 0 1 1 ? ")
    public void updateExchangeState() {
        gdsMemberService.timingUpdate();
    }
}

