package com.qmx.member.web.controller;

import com.qmx.appextend.controller.BaseController;
import com.qmx.appextend.remoreapisub.SysUserClient;
import com.qmx.base.api.dto.PageDto;
import com.qmx.base.api.dto.RestResponse;
import com.qmx.base.api.enumerate.SysUserType;
import com.qmx.base.api.exception.BusinessException;
import com.qmx.coreservice.model.SysUser;
import com.qmx.coreservice.query.UserQueryVo;
import com.qmx.member.model.GdsInitialization;
import com.qmx.member.model.GdsMember;
import com.qmx.member.model.GdsMemberLevel;
import com.qmx.member.query.GdsInitializationVO;
import com.qmx.member.query.GdsMemberVO;
import com.qmx.member.service.GdsInitializationService;
import com.qmx.member.service.GdsMemberLevelService;
import com.qmx.member.service.GdsMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 会员个人管理
 */
@Controller
@RequestMapping("/member")
public class GdsMemberController extends BaseController {
    @Autowired
    private GdsMemberService memberService;
    @Autowired
    private GdsMemberLevelService memberLevelService;
    @Autowired
    private SysUserClient sysUserService;
    @Autowired
    private GdsInitializationService gdsInitializationService;
    @Value("${com.qmx.front.siteUrl:http://192.168.3.6:9021/}")
    private String website;

    /**
     * 会员列表
     *
     * @param dto
     * @param model
     * @return
     */
    @RequestMapping(value = "/list")
    public String list(GdsMemberVO dto, ModelMap model) {
        PageDto<GdsMember> restResponse = memberService.findList(getCurrentUser(), dto);
        model.addAttribute("dto", dto);
        model.addAttribute("page", restResponse);
        model.addAttribute("url", website + "member/login?userId=" + getCurrentUser().getId());
        return "/member/member/list";
    }

    /**
     * 进入添加页面
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/add")
    public String add(ModelMap model) {
        RestResponse<List<GdsMemberLevel>> restResponse = memberLevelService.findLevelAll(getCurrentUser());
        model.addAttribute("listL", restResponse.getData());
        return "/member/member/add";
    }

    /**
     * 选择客户经理
     *
     * @param request
     * @param userQueryVo
     * @return
     */
    @RequestMapping(value = "adduser")
    public String addUser(HttpServletRequest request, UserQueryVo userQueryVo) {
        userQueryVo.setUserType(SysUserType.employee);
        userQueryVo.setCurrentUserId(getCurrentUser().getId());
        PageDto<SysUser> page = sysUserService.findPage(/*getCurrentUser(),*/ userQueryVo);
        request.setAttribute("userQueryVo", userQueryVo);
        request.setAttribute("page", page);
        return "/member/member/userlist";
    }

    @RequestMapping(value = "/save")
    public String save(GdsMemberVO dto) {
        Assert.notNull(dto.getMobile(), "手机号不能为空哦");
        Long count = memberService.selectMobileAndIdcard(getCurrentUser().getId(), dto);
        if (count != null && !count.equals(0L)) {
            throw new BusinessException("该用户已注册");
        }
        RestResponse<GdsMember> restResponse = memberService.createDto(getCurrentUser(), dto);
        if (!restResponse.success()) {
            throw new BusinessException(restResponse.getErrorMsg());
        }
        return "redirect:list";
    }

    @RequestMapping(value = "/edit")
    public String edit(Long id, ModelMap model) {
        RestResponse<GdsMember> restResponse = memberService.findById(id);
        RestResponse<List<GdsMemberLevel>> listL = memberLevelService.findLevelAll(getCurrentUser());
        if (!restResponse.success()) {
            throw new BusinessException(restResponse.getErrorMsg());
        }
        GdsMember dto = restResponse.getData();
        model.addAttribute("dto", dto);
        model.addAttribute("listL", listL.getData());
        return "/member/member/edit";
    }

    @RequestMapping(value = "/update")
    public String update(GdsMemberVO dto) {

        RestResponse<GdsMember> restResponse = memberService.updateDto(getCurrentUser(), dto);
        if (!restResponse.success()) {
            throw new BusinessException(restResponse.getErrorMsg());
        }
        return "redirect:list";
    }

    /**
     * 会员初始化参数设置页面
     *
     * @return
     */
    @RequestMapping("/Initialization")
    public String Initialization(ModelMap model) {
        GdsInitialization gdsInitialization = gdsInitializationService.findBySupplierId(getCurrentUser().getId());
        if (gdsInitialization == null) {
            gdsInitialization = new GdsInitialization();
        }
        model.addAttribute("dto", gdsInitialization);
        return "/member/member/Initialization";
    }

    /**
     * 保存会员初始化参数设置
     *
     * @param vo
     * @return
     */
    @ResponseBody
    @RequestMapping("/addInitialization")
    public String addInitialization(GdsInitializationVO vo) {
        Assert.notNull(vo.getIntegral(), "设置积分有误!!");
        RestResponse<GdsInitialization> gdsInitialization = gdsInitializationService.addInitialization(getCurrentUser(), vo);
        if (!gdsInitialization.success()) {
            return "false";
        }
        return "true";
    }

    @RequestMapping(value = "/delete")
    public String delete(Long id) {
        RestResponse restResponse = memberService.deleteDto(getCurrentUser(), id);
        if (!restResponse.success()) {
            throw new BusinessException(restResponse.getErrorMsg());
        }
        return "redirect:list";
    }

    //自动检查会员过期时间,过期会更新会员过期状态
//    @Scheduled(fixedRate = 1000 * 60 * 120)
    @Scheduled(cron = "0 0 1 * * ? ")
    public void updateState() {
        try {
            memberService.updateState();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
