package com.qmx.member.web.controller;

import com.qmx.appextend.controller.BaseController;
import com.qmx.base.api.base.BaseModel;
import com.qmx.base.api.base.RestResult;
import com.qmx.base.api.dto.PageDto;
import com.qmx.base.api.dto.RestResponse;
import com.qmx.base.api.exception.BusinessException;
import com.qmx.base.core.utils.DateUtil;
import com.qmx.member.enumerate.IntegralType;
import com.qmx.member.enumerate.ProductType;
import com.qmx.member.model.GdsMemberActivityIntegral;
import com.qmx.member.model.GdsMemberAssociated;
import com.qmx.member.model.GdsMemberLevel;
import com.qmx.member.query.GdsMemberActivityIntegralVO;
import com.qmx.member.remoteapi.ProductService;
import com.qmx.member.remoteapi.ReleaseService;
import com.qmx.member.service.GdsMemberActivityIntegralService;
import com.qmx.member.service.GdsMemberLevelService;
import com.qmx.shop.model.commodity.Release;
import com.qmx.shop.model.ticket.Product;
import com.qmx.shop.query.commodity.ReleaseVO;
import com.qmx.shop.query.ticket.ProductVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.lang.reflect.TypeVariable;
import java.net.URL;
import java.util.HashMap;
import java.util.List;

/**
 * 会员积分管理
 */
@Controller
@RequestMapping("/memberIntegral")
public class GdsMemberIntegralController extends BaseController {

    @Autowired
    private GdsMemberLevelService memberLevelService;
    @Autowired
    private GdsMemberActivityIntegralService activityIntegralService;
    @Autowired
    private ProductService shopProductService;
    @Autowired
    private ReleaseService releaseService;

    /**
     * 充值多倍积分   recharge
     * 消费多倍积分   consumption
     * 注册多倍积分   register
     *
     * @return 多倍积分页面
     */
    @RequestMapping(value = "/list/{url}")
    public String list(@PathVariable("url") String url, GdsMemberActivityIntegralVO vo, ModelMap model) {
        IntegralType type = IntegralType.valueOf(url);
        vo.setType(type);
        PageDto<GdsMemberActivityIntegral> pageDto = activityIntegralService.findList(getCurrentUser(), vo);
        RestResponse<List<GdsMemberLevel>> LevelDtos = memberLevelService.findLevelAll(getCurrentUser());
        model.addAttribute("levelAll", LevelDtos.getData());
        model.addAttribute("page", pageDto);
        model.addAttribute("type", type.name());
        model.addAttribute("vo", vo);
        return "/member/memberIntegral/" + "list_" + url;
    }

    @RequestMapping("/add/{url}")
    public String add(@PathVariable("url") String url, ModelMap model) {
        RestResponse<List<GdsMemberLevel>> restResponse = memberLevelService.findLevelAll(getCurrentUser());
        model.addAttribute("listL", restResponse.getData());
        return "/member/memberIntegral/" + "add_" + url;
    }

    @RequestMapping("/save/{url}")
    public String save(@PathVariable("url") String url, GdsMemberActivityIntegralVO vo, ModelMap model) {
        IntegralType type = IntegralType.valueOf(url);
        vo.setType(type);
        if (vo.getEndTime().compareTo(vo.getStartTime()) < 0) {
            throw new BusinessException("请选择正确的时间");
        }
        activityIntegralService.createDto(getCurrentUser(), vo);
        return "redirect:/memberIntegral/list/" + url;
    }

    //添加可以优惠门票
    @RequestMapping(value = "/productlist")
    public String productlist(ProductVO productVO, ModelMap model) {
        model.addAttribute("dto", productVO);
        RestResult<PageDto<Product>> data = shopProductService.queryList(productVO, getCurrentMember().getId());
        model.addAttribute("page", data.getData());
        return "/member/memberIntegral/productlist";
    }

    //添加可以优惠商品
    @RequestMapping(value = "/releaselist", method = RequestMethod.GET)
    public String releaselist(ReleaseVO shopReleaseVO, ModelMap modelMap) {
        modelMap.addAttribute("dto", shopReleaseVO);
        RestResult<PageDto<Release>> page = releaseService.findPage(shopReleaseVO, getCurrentMember().getId());
        modelMap.addAttribute("page", page.getData());
        return "/member/memberIntegral/releaselist";
    }


    @RequestMapping("/edit/{url}")
    public String edit(@PathVariable("url") String url, @RequestParam("id") Long id, ModelMap model) {
        Assert.notNull(id, "id不能为空");
        GdsMemberActivityIntegral integral = activityIntegralService.edit(id);
        model.addAttribute("data", integral);
        RestResponse<List<GdsMemberLevel>> restResponse = memberLevelService.findLevelAll(getCurrentUser());
        model.addAttribute("listL", restResponse.getData());
        HashMap<ProductType, List<GdsMemberAssociated>> map = integral.getMap();
        if (map != null) {
            List<GdsMemberAssociated> menpiao = map.get(ProductType.menpiao);
            List<GdsMemberAssociated> shangpin = map.get(ProductType.shangpin);
            model.addAttribute("menpiao", menpiao);
            model.addAttribute("shangpin", shangpin);
        }
        return "/member/memberIntegral/edit_" + url;
    }

    @RequestMapping("/update/{url}")
    public String update(@PathVariable("url") String url, GdsMemberActivityIntegralVO vo) {
        if (vo.getEndTime().compareTo(vo.getStartTime()) < 0) {
            throw new BusinessException("请选择正确的时间");
        }
        activityIntegralService.updateDto(getCurrentUser(), vo);
        return "redirect:/memberIntegral/list/" + url;
    }


    @RequestMapping("/delete/{url}")
    public String delete(@PathVariable("url") String url, Long id) {
        Assert.notNull(id, "id不能为空");
        activityIntegralService.deleteDto(id, getCurrentUser());
        return "redirect:/memberIntegral/list/" + url;
    }
}
