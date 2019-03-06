package com.qmx.member.web;

import com.qmx.coreservice.model.SysUser;
import com.qmx.member.enumerate.IntegralType;
import com.qmx.member.model.GdsMemberMoney;
import com.qmx.member.query.GdsMemberActivityIntegralVO;
import com.qmx.member.service.GdsInviteService;
import com.qmx.member.service.GdsMemberActivityIntegralService;
import com.qmx.member.service.GdsMemberMoneyService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PlatformMemberWebApplicationTests {

    @Autowired
    private GdsMemberActivityIntegralService service;

    @Test
    public void contextLoads() throws ParseException {
        SysUser user = new SysUser();
        user.setId(980689529120899073L);
        GdsMemberActivityIntegralVO vo = new GdsMemberActivityIntegralVO();
        vo.setType(IntegralType.consumption);
        vo.setLevelName("AAAAAAAAAAA");
        vo.setLevelId(1064773755638284289L);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        vo.setStartTime(format.parse("2019-01-01"));
        vo.setEndTime(format.parse("2019-12-29"));
        vo.setMultiple(1.0);
        List<String> list = new ArrayList<>();
//        list.add("993299459963056130&青城山(指定游玩)&menpiao");
//        list.add("993741009046085633&火龙果&shangpin");
//        list.add("993741009046085634&测试门票1&menpiao");
//        list.add("993741009046085635&测试门票2&menpiao");
        list.add("993741009046080001&测试更新1&menpiao");
        list.add("993741009046080002&测试更新2&menpiao");
        list.add("993741009046080003&测试更新3&menpiao");
        list.add("993741009046080004&测试更新4&menpiao");
        list.add("993741009046080005&测试更新5&menpiao");
        list.add("993741009046080006&测试更新6&menpiao");
        list.add("993741009046080011&测试更新2-0&shangpin");
        list.add("993741009046080012&测试更新2-1&shangpin");
        list.add("993741009046080022&测试更新2-2&shangpin");
        list.add("993741009046080032&测试更新2-3&shangpin");
        list.add("993741009046080042&测试更新2-4&shangpin");
        list.add("993741009046080052&测试更新2-5&shangpin");
        vo.setIds(list);
        service.createDto(user, vo);
    }

    @Test
    public void createMemberActivityIntegeral() throws Exception {
        SysUser user = new SysUser();
        user.setId(980689529120899073L);
        GdsMemberActivityIntegralVO vo = new GdsMemberActivityIntegralVO();
        vo.setId(1082826127635607554L);
        vo.setType(IntegralType.consumption);
        vo.setLevelName("AAAAAAAAAAA");
        vo.setLevelId(1064773755638284289L);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        vo.setStartTime(format.parse("2018-12-28"));
        vo.setEndTime(format.parse("2018-12-29"));
        vo.setMultiple(2.0);
        List<String> list = new ArrayList<>();
        list.add("993741009046080001&测试更新1&menpiao");
        list.add("993741009046080002&测试更新2&menpiao");
        list.add("993741009046080003&测试更新3&menpiao");
        list.add("993741009046080004&测试更新4&menpiao");
        list.add("993741009046080005&测试更新5&menpiao");
        list.add("993741009046080006&测试更新6&menpiao");
        list.add("993741009046080011&测试更新2-0&shangpin");
        list.add("993741009046080012&测试更新2-1&shangpin");
        list.add("993741009046080022&测试更新2-2&shangpin");
        list.add("993741009046080032&测试更新2-3&shangpin");
        list.add("993741009046080042&测试更新2-4&shangpin");
        list.add("993741009046080052&测试更新2-5&shangpin");
        list.add("993741009046080152&测试更新12-14&shangpin");
        list.add("993741009046080252&测试更新13-14&shangpin");
        vo.setIds(list);
        service.updateDto(user, vo);
    }

    @Autowired
    private GdsInviteService gdsInviteService;

    @Test
    public void Test() {
        gdsInviteService.findByOpenIdSetCodeInvite(1L, "asdasd");
    }

    @Autowired
    private GdsMemberMoneyService gdsMemberMoneyService;

    @Test
    public void TestMoney() {
        List<GdsMemberMoney> list = gdsMemberMoneyService.queryMoneyIsUpdated(980689529120899073L);
        for (GdsMemberMoney money : list) {
            gdsMemberMoneyService.updateMemberMoneyOnLineSyn(money.getId(), money.getMoney());
        }

    }


}
