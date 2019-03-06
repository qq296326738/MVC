package com.qmx.member.service;

import com.qmx.base.api.annotation.GdsCacheConfig;
import com.qmx.base.api.dto.RestResponse;
import com.qmx.base.api.exception.BusinessException;
import com.qmx.base.core.base.BaseService;
import com.qmx.member.mapper.GdsMemberSignMapper;
import com.qmx.member.model.GdsInitialization;
import com.qmx.member.model.GdsMember;
import com.qmx.member.model.GdsMemberSign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 会员签到
 */
@Service
@GdsCacheConfig(cacheNS = "GDS-MEMBER", cacheName = "GdsMemberSign")
public class GdsMemberSignService extends BaseService<GdsMemberSign> {

    @Autowired
    private GdsMemberSignMapper gdsMemberSignMapper;
    @Autowired
    private GdsMemberService gdsMemberService;
    @Autowired
    private GdsMemberIntegeralService gdsMemberIntegeralService;
    @Autowired
    private GdsInitializationService initializationService;

    //查询今天有没有签到
    public GdsMemberSign findByMemberId(Long id) {
        GdsMemberSign sign = gdsMemberSignMapper.findByMemberId(id);
        return sign;
    }

    //查询昨天没有签到,获取记录的连续签到次数
    public Integer isContinuousSign(Long id) {
        GdsMemberSign sign = gdsMemberSignMapper.selectIsContinuousSign(id);
        Integer ContinuousSign;
        if (sign == null) {
            ContinuousSign = 0;
            return ContinuousSign;
        }
//        //判断是不是一个月
//        String day = new SimpleDateFormat("MM").format(new Date());
//        String time = new SimpleDateFormat("MM").format(sign.getTime());
//        if (!day.equals(time)) {
//            ContinuousSign = 0;
//            return ContinuousSign;
//        }
        ContinuousSign = sign.getContinuousSign();
        return ContinuousSign;
    }

    //  签到
    @Transactional
    public RestResponse<GdsMemberSign> add(Long id) {
        try {
            //查询今天有没有签到,有就不用签到
            GdsMemberSign memberSign = this.findByMemberId(id);
            if (memberSign != null) {
                return RestResponse.ok(Boolean.FALSE);
            }

            GdsMember gdsMember = gdsMemberService.find(id);
            GdsInitialization init = initializationService.findBySupplierId(gdsMember.getSupplierId());
            if (init == null) {
                init = new GdsInitialization();
            }
            GdsMemberSign model = new GdsMemberSign();
            //查询昨天有没有签到,有记录就取连续签到数,没有就重置为0
            Integer ContinuousSign = this.isContinuousSign(id);
            model.setContinuousSign(++ContinuousSign);
            if (init.getDaily() != null && init.getDaily() <= ContinuousSign) {
                if (init.getRewardIntegral() != null) {
                    model.setIntegral(init.getRewardIntegral());
                } else {
                    model.setIntegral(init.getDailyIntegral() == null ? 1 : init.getDailyIntegral());
                }
            } else {
                model.setIntegral(init.getDailyIntegral() == null ? 1 : init.getDailyIntegral());
            }
            model.setMemberId(id);
            model.setTime(new Date());
            model.setSupplierId(gdsMember.getSupplierId());
            GdsMemberSign sign = this.save(model);
            //新增积分记录
            gdsMemberIntegeralService.createSign(sign);
            return RestResponse.ok(sign);
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return RestResponse.fail("签到失败");
        }
    }

    /**
     * 前端查询签到
     *
     * @param id
     * @return
     */
    public GdsMemberSign getSignById(Long id) {
        try {
            //本月签到数
            Integer monthCount = gdsMemberSignMapper.findMonthById(id);
            //连续签到数
            GdsMemberSign memberSign = this.findByMemberId(id);
            if (memberSign == null) {
                Integer continuousSign = this.isContinuousSign(id);
                memberSign = new GdsMemberSign();
                memberSign.setContinuousSign(continuousSign);
            }
            memberSign.setMonthSign(monthCount);
            //累计签到与累计奖励
            Map<String, Object> map = gdsMemberSignMapper.findSignCount(id);
            //累计签到
            memberSign.setCumulativeRewards(map.get("cumulativeRewards") != null ? Integer.valueOf(map.get("cumulativeRewards").toString()) : 0);
            //累计奖励
            memberSign.setCumulativeSign(map.get("cumulativeSign") != null ? Integer.valueOf(map.get("cumulativeSign").toString()) : 0);
            return memberSign;
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException("查询用户签到失败");
        }
    }

    /**
     * 前端查询签到时间记录
     *
     * @param id
     * @return
     */
    public List<GdsMemberSign> getSignTimeById(Long id, int s) {
        try {
            List<GdsMemberSign> list = gdsMemberSignMapper.listTime(id, s);
            return list;
        } catch (Exception e) {
            throw new BusinessException("查询用户签到失败");
        }
    }

}
