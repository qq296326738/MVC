package com.qmx.member.front.controller.member;

import com.alibaba.fastjson.JSONObject;
import com.qmx.base.api.base.RestResult;
import com.qmx.base.api.dto.RestResponse;
import com.qmx.base.api.exception.BusinessException;
import com.qmx.base.core.qrcode.QRCodeUtil;
import com.qmx.base.core.service.MyFileUploadService;
import com.qmx.base.core.utils.Base64Util;
import com.qmx.coreservice.enumerate.FileType;
import com.qmx.member.common.GdsMemberRegisterService;
import com.qmx.member.enumerate.InviteType;
import com.qmx.member.enumerate.MemberState;
import com.qmx.member.front.utils.HttpUtil;
import com.qmx.member.front.utils.WeiXinUtil;
import com.qmx.member.model.GdsInitialization;
import com.qmx.member.model.GdsInvite;
import com.qmx.member.model.GdsMember;
import com.qmx.member.query.GdsMemberVO;
import com.qmx.member.remoteapi.WxAuthorizationRemoteApi;
import com.qmx.member.service.GdsInitializationService;
import com.qmx.member.service.GdsInviteService;
import com.qmx.member.service.GdsMemberService;
import com.qmx.wxbasics.model.WxAuthorization;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.ByteArrayOutputStream;

/**
 * 会员登录与首页
 */
@Controller
@RequestMapping("/member")
public class MemberIndexController {

    @Autowired
    private GdsMemberService gdsMemberService;
    @Autowired
    private MyFileUploadService myFileUploadService;
    @Autowired
    private GdsMemberRegisterService registerService;
    @Autowired
    private WxAuthorizationRemoteApi wxAuthorizationService;
    @Autowired
    private GdsInitializationService gdsInitializationService;
    @Autowired
    private GdsInviteService gdsInviteService;

    @Value("${com.qmx.front.siteUrl:http://192.168.3.6:9021/}")
    private String website;

    /**
     * 从供应商那里获得url地址,进去此方法
     *
     * @param userId   供应商id
     * @param memberId 邀请人id
     * @param model
     * @return
     */
    @RequestMapping("/login")
    public String login(Long userId, Long memberId, String openid, ModelMap model, HttpServletRequest request) {
        model.addAttribute("userId", userId);
        model.addAttribute("memberId", memberId);
        //如果是微信进入,首先判断是否已经注册,已注册就进入会员首页,否则进去登录页面
        if (WeiXinUtil.isWeiXin(request)) {
//            WxAuthorization wxAuthorizationDto = wxAuthorizationService.findByMId(userId);
            RestResult<WxAuthorization> result = wxAuthorizationService.findByMemberId(userId);//要删除
            WxAuthorization wxAuthorizationDto = result.getData();
            if (wxAuthorizationDto == null) {
                model.addAttribute("msg", "当前供应商没配置微信账号");
                return "error";
            }
            if (StringUtils.isEmpty(openid)) {
                return "redirect:" + wxAuthorizationService.getRedirectUrl(wxAuthorizationDto.getAuthorizerAppid(),
                        website + "member/login?userId=" + userId);
            }
            GdsMember member = gdsMemberService.findByOpenId(openid);
            //查询不为空,直接进去会员首页
            if (member != null) {
                if (member.getSupplierId() == null) {
                    member.setSupplierId(userId);
                }
                if (member.getState() != MemberState.normal) {
                    model.addAttribute("msg", "会员已" + member.getState().getTitle());
                    return "error";
                }
                request.getSession().setAttribute("user", true);
                request.getSession().setMaxInactiveInterval(15 * 60);
                return "redirect:index?id=" + member.getId();
            } else {
                //查询为空,进入登录页面,把openid传过去
                model.addAttribute("openid", openid);
                return "member/memberIndex/login";
            }
        }
        return "member/memberIndex/login";
    }

    /**
     * 发送验证码
     *
     * @param mobile 手机号
     * @return 发送状态
     */
    @ResponseBody
    @RequestMapping(value = "/sendMessage")
    public JSONObject sendMessage(String mobile, Long userId, HttpServletRequest request) {
        JSONObject object = new JSONObject();
        //生成验证码
        String code = RandomStringUtils.randomNumeric(6);
        HttpSession session = request.getSession();
        //将验证码放到session
        session.setAttribute("elctCode", code);
        //将电话号码放到session
        session.setAttribute("mobile", mobile);
        //设置过期时间为5分钟
        session.setMaxInactiveInterval(5 * 60);
        //发送短信
        try {
            registerService.send(code, mobile, userId);
            object.put("state", "success");
            object.put("msg", "发送成功");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("state", "error");
            object.put("msg", "发送失败" + e.getMessage());
        }
        return object;

    }

    /**
     * 手机验证码验证
     *
     * @param code   验证码
     * @param mobile 手机号
     * @param userId 供应商id
     * @return
     */
    @ResponseBody
    @RequestMapping("/findByMoblie")
    public JSONObject findByMoblie(String mobile, String openid, Long userId, Long memberId, String code, HttpServletRequest request) {
        JSONObject object = new JSONObject();
        HttpSession session = request.getSession();
        //获得验证码
        String existCode = (String) session.getAttribute("elctCode");
        //获得手机号
        String existMobile = (String) session.getAttribute("mobile");
        //判断手机号和验证码是否一致
        if (!StringUtils.isEmpty(existCode) && existCode.equals(code)) {
            if (!StringUtils.isEmpty(existMobile) && existMobile.equals(mobile)) {
                //根据手机号查询是否已经注册过
                GdsMember gdsMember;
                gdsMember = gdsMemberService.selectMobile(userId, mobile);
                //如果注册过,返回会员id
                if (gdsMember != null) {
                    if (gdsMember.getState() != MemberState.normal) {
                        object.put("state", "error");
                        object.put("msg", "会员以过期");
                        return object;
                    }
                    //如果该用户第一次微信登录,把openid设置进去
                    if (gdsMember.getOpenid() == null || StringUtils.isNotEmpty(openid)) {
                        gdsMember.setOpenid(openid);
                        gdsMember.setSynState(false);
                        gdsMemberService.update(gdsMember);
                    }
                } else {
                    gdsMember = gdsMemberService.createMember(userId, openid, mobile, memberId);
                    if (gdsMember == null) {
                        object.put("state", "error");
                        object.put("msg", "会员注册失败");
                        return object;
                    }
                }
                object.put("id", gdsMember.getId());
                object.put("state", "success");
                session.setAttribute("user", true);
                //会话期时间为15分钟
                session.setMaxInactiveInterval(15 * 60);
                session.removeAttribute("elctCode");
            } else {
                object.put("state", "error");
                object.put("msg", "手机号前后不一致,请重新获取验证码");
            }
        } else {
            object.put("state", "error");
            object.put("msg", "验证码错误");
        }
        return object;
    }

    /**
     * 会员首页
     */
    @RequestMapping(value = "/index")
    public String index(Long id, ModelMap model, HttpServletRequest request) {
        //http://127.0.0.1:9021/member/index?id=997042641197244417
        Assert.notNull(id, "用户id不能为空");
        RestResponse<GdsMember> response = gdsMemberService.findById(id);
        if (response.getData() == null) {
            throw new BusinessException("获取用户信息失败");
        }
        GdsInitialization bySupplierId = gdsInitializationService.findBySupplierId(response.getData().getSupplierId());
        if (bySupplierId != null) {
            model.addAttribute("mobile", bySupplierId.getMobile());
        }
        model.addAttribute("member", response.getData());
        return "member/memberIndex/index";
    }

    /**
     * 我的会员
     *
     * @param id    会员id
     * @param model request
     * @return 会员信息页面
     */
    @RequestMapping(value = "/userInfo")
    public String userInfo(Long id, ModelMap model) {
        Assert.notNull(id, "用户id不能为空");
        RestResponse<GdsMember> response = gdsMemberService.findById(id);
        if (response.getData() == null) {
            throw new BusinessException("获取用户信息失败");
        }

        model.addAttribute("member", response.getData());
        return "member/memberIndex/userInfo";
    }

    /**
     * 我的会员-更新会员信息
     */
    @ResponseBody
    @RequestMapping(value = "/update")
    public String update(GdsMemberVO vo, FileType fileType, MultipartFile file) {
        try {
            if (file != null) {
                JSONObject jsonObject = myFileUploadService.upload2(fileType.name(), file);
                String imageUrl = (String) jsonObject.get("url");
                vo.setImage(imageUrl);
            }
            Assert.notNull(vo.getId(), "用户id不能为空");
            RestResponse response = gdsMemberService.updateMemmber(vo);
            if (!response.success()) {
                return "0";
            }
            return "1";
        } catch (Exception e) {
            e.printStackTrace();
            return "0";
        }
    }

    /**
     * 优惠券
     *
     * @param id       会员id
     * @param modelMap 输出
     * @return
     */
    @RequestMapping(value = "/coupon")
    public String coupon(Long id, ModelMap modelMap) {
        Assert.notNull(id, "用户id不能为空");

        return "member/memberIndex/coupon";
    }

    @RequestMapping(value = "/error")
    public String error(HttpServletRequest request, ModelMap modelMap) {
        modelMap.addAttribute("msg", request.getAttribute("msg"));
        modelMap.addAttribute("code", request.getAttribute("code"));
        modelMap.addAttribute("time", request.getAttribute("time"));
        return "common/error";
    }

}

