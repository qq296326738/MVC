package com.qmx.member.front.controller.member;

import com.alibaba.fastjson.JSONObject;
import com.qmx.base.api.base.RestResult;
import com.qmx.base.api.exception.BusinessException;
import com.qmx.base.core.qrcode.QRCodeUtil;
import com.qmx.base.core.utils.Base64Util;
import com.qmx.member.enumerate.InviteType;
import com.qmx.member.front.utils.HttpUtil;
import com.qmx.member.model.GdsInitialization;
import com.qmx.member.model.GdsInvite;
import com.qmx.member.model.GdsMember;
import com.qmx.member.remoteapi.WxAuthorizationRemoteApi;
import com.qmx.member.service.GdsInitializationService;
import com.qmx.member.service.GdsInviteService;
import com.qmx.member.service.GdsMemberService;
import com.qmx.wxbasics.model.WxAuthorization;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.ByteArrayOutputStream;

/**
 * 会员分享
 */
@Controller
@RequestMapping("/member")
public class MemberShareController {

    @Autowired
    private GdsMemberService gdsMemberService;
    @Autowired
    private WxAuthorizationRemoteApi wxAuthorizationService;
    @Autowired
    private GdsInitializationService gdsInitializationService;
    @Autowired
    private GdsInviteService gdsInviteService;

    @Value("${com.qmx.front.siteUrl:http://192.168.3.6:9021/}")
    private String website;

    /**
     * 建立邀请二维码
     *
     * @return 生成二维码
     */
    @RequestMapping("/sharedCode/{type}")
    public String sharedCode(@PathVariable("type") String type, Long memberId, ModelMap model) {
        Assert.notNull(memberId, "用户id不能为空");
        String codeUrl;
        if ("money".equals(type)) {
            codeUrl = website + "member/scanCode/money?memberId=" + memberId;
            model.addAttribute("msg", "邀请获得金额二维码");
        } else if ("integral".equals(type)) {
            codeUrl = website + "member/scanCode/integral?memberId=" + memberId;
            model.addAttribute("msg", "邀请获得积分二维码");
        } else {
            throw new BusinessException("连接错误,请重新进入");
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        QRCodeUtil.encode(codeUrl, 440, 440, out);
        byte[] bytes = out.toByteArray();
        String base64Url = Base64Util.encodeToString(bytes);
        model.addAttribute("codeUrl", "data:image/png;base64," + base64Url);
        return "member/memberIndex/sharedCode";
    }

    /**
     * 扫描获得金额二维码
     */
    @RequestMapping("/scanCode/money")
    public String scanCodeMoney(Long memberId) {
        Assert.notNull(memberId, "邀请人id不能为空");
        GdsMember member = gdsMemberService.find(memberId);
        Assert.notNull(member, "邀请人不存在");
        Long supplierId = member.getSupplierId();
        return "redirect:/member/login?userId=" + supplierId + "memberId=" + memberId;
    }

    /**
     * 扫描积分分享二维码
     *
     * @param memberId 邀请人id
     * @param openid   openid
     * @return 进入公众号
     */
    @RequestMapping("/scanCode/integral")
    public String scanCodeIntegral(Long memberId, String openid) {
        Assert.notNull(memberId, "邀请人id不能为空");
        GdsMember member = gdsMemberService.find(memberId);
        Assert.notNull(member, "没有对应邀请人");
        RestResult<WxAuthorization> result = wxAuthorizationService.findByMemberId(member.getSupplierId());
        WxAuthorization wxAuthorization = result.getData();
        Assert.notNull(wxAuthorization, "当前供应商没配置微信账号");
        //获得openid
        if (StringUtils.isEmpty(openid)) {
            return "redirect:" + wxAuthorizationService.getRedirectUrl(wxAuthorization.getAuthorizerAppid(),
                    website + "member/scanCode?userId=" + memberId);
        }
        //判断是否关注
        RestResult<String> token = wxAuthorizationService.getAccessToken(wxAuthorization.getAuthorizerAppid());
        JSONObject temp = JSONObject.parseObject(HttpUtil.sendHttpsGET("https://api.weixin.qq.com/cgi-bin/user/info?access_token=" + token.getData() + "&openid=" + openid + "&lang=zh_CN"));
        String is = temp.getString("subscribe");//值为0时，代表此用户没有关注该公众号
        if ("0".equals(is)) {
            GdsInvite gdsInvite = gdsInviteService.findByUserIdAndOpenId(memberId, openid);
            if (gdsInvite == null) {
                GdsInitialization initialization = gdsInitializationService.findBySupplierId(member.getSupplierId());
                int codeIntegral = initialization.getCodeIntegral() == null ? 1 : initialization.getCodeIntegral();
                //建立邀请关系
                                            gdsInviteService.save(new GdsInvite(memberId, openid, false, codeIntegral, InviteType.GUANZHU));
            }
            //重定向到关注公众号页面,imgUrl会转成二维码
            return "redirect:wxsubscribe?imgurl=https://open.weixin.qq.com/qr/code?username=" +
                    wxAuthorization.getUserName() + "&memberId=" + memberId;
        }
        //进入公众号
        return "redirect:https://open.weixin.qq.com/qr/code?username=" + wxAuthorization.getUserName();
    }


    /**
     * 余额，查询订单，退票、收货等(重定向到票务系统)
     *
     * @param id 会员id
     */
    @RequestMapping("/redirectTicket")
    public String redirectTicket(Long id) {
        Assert.notNull(id, "会员id不能为空");
        GdsMember member = gdsMemberService.find(id);
        Assert.notNull(member, "会员id异常");
        return "redirect:" + website + "personal/center/index?userId=" + member.getSupplierId();
    }

}
