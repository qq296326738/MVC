package com.qmx.member.service;

import com.alibaba.fastjson.JSONObject;
import com.qmx.base.api.annotation.GdsCacheConfig;
import com.qmx.base.core.base.BaseService;
import com.qmx.member.enumerate.RechargeType;
import com.qmx.member.mapper.GdsMemberMoneyMapper;
import com.qmx.member.model.GdsMember;
import com.qmx.member.model.GdsMemberExchangeOrder;
import com.qmx.member.model.GdsMemberMoney;
import com.qmx.member.query.GdsMemberMoneyVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.Assert;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 会员金额记录管理
 */
@Service
@GdsCacheConfig(cacheNS = "GDS-MEMBER", cacheName = "GdsMemberMoney")
public class GdsMemberMoneyService extends BaseService<GdsMemberMoney> {

    @Autowired
    private GdsMemberMoneyMapper gdsMemberMoneyMapper;
    @Autowired
    private GdsMemberService gdsMemberService;
    @Autowired
    private GdsMemberIntegeralService gdsMemberIntegeralService;
    @Autowired
    private GdsMemberLevelService gdsMemberLevelService;

    /**
     * 获得会员每月充值(消费)记录
     * id  用户id
     */
    public Map<String, List<GdsMemberMoney>> getMemberMoneyList(Long id, Boolean flag) {
        try {
            //查询该用户所有充值(消费)记录
            List<GdsMemberMoney> list = gdsMemberMoneyMapper.listrecordType(id, flag);
            Iterator<GdsMemberMoney> iterator = list.iterator();
            HashMap<String, List<GdsMemberMoney>> hashMap = new LinkedHashMap<>();
            SimpleDateFormat timeYM = new SimpleDateFormat("yyyy年MM月");
            while (iterator.hasNext()) {
                GdsMemberMoney gdsMemberMoney = iterator.next();
                String time = timeYM.format(gdsMemberMoney.getTime());
                if (!hashMap.containsKey(time)) {
                    hashMap.put(time, new ArrayList<GdsMemberMoney>());
                }
                hashMap.get(time).add(gdsMemberMoney);
            }
            return hashMap;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获得历史(充值/消费)总记录
     *
     * @param id
     * @return
     */
    public Map<String, Double> generalRecord(Long id) {
        return gdsMemberMoneyMapper.getGeneralRecord(id);
    }

    /**
     * 保存金额消费记录,积分记录 ,会员积分,余额记录
     *
     * @param id
     * @param vo
     * @return
     */
    @Transactional(rollbackFor = Throwable.class)
    public JSONObject createConsumption(Long id, GdsMemberMoneyVO vo) {
        JSONObject jsonObject = new JSONObject();
        try {
            GdsMember gdsMember = gdsMemberService.find(id);
            GdsMemberMoney memberMoney = new GdsMemberMoney();
            memberMoney.setSupplierId(gdsMember.getSupplierId());
            memberMoney.setMemberId(id);
            double money = gdsMember.getMoney();
            memberMoney.setBalanceMoney(money - vo.getMoney());
            memberMoney.setSn(vo.getSn());
            memberMoney.setSnText(vo.getSnText());
            memberMoney.setTime(vo.getTime());
            memberMoney.setMoney(vo.getMoney());
            memberMoney.setActualMoney(vo.getActualMoney());
            memberMoney.setProductId(vo.getProductId());
            memberMoney.setProductName(vo.getProductName());
//            memberMoney.setIntegral(vo.getIntegral());
            //消费金额方式
            memberMoney.setRechargeType(vo.getRechargeType());
            memberMoney.setStatus(true);
            memberMoney.setRecordType(false); //true充值,flase消费
            memberMoney.setSynState(true);
            GdsMemberMoney save = this.save(memberMoney);
            // TODO: 2018/7/27 线下消费暂时没有积分赠送
//            //3-保存积分记录
//            GdsMemberIntegeral integeral = new GdsMemberIntegeral();
//            integeral.setMemberId(id);
//            integeral.setSn(save.getSnText());
//            integeral.setTime(save.getTime());
//            integeral.setIntegeral(save.getIntegral());
//            integeral.setSourceType(SourceType.xiaofei);
//            int integral = gdsMember.getIntegral() == null ? 0 : gdsMember.getIntegral();
//            integeral.setBalanceIntegeral(integral + save.getIntegral());
//            integeral.setProductId(save.getProductId());
//            integeral.setProductName(save.getProductName());
//            integeral.setRecordId(save.getId());
//            integeral.setRecordType(true);
//            integeral.setStatus(true);
//            integeral.setRechargeType(RechargeType.hyye);
//            integeral.setSynState(false);
//            gdsMemberIntegeralService.save(integeral);
            //4-更新会员余额与积分
//            gdsMember.setMoney(vo.getMoney() + money);
//            gdsMember.setIntegral(integral + save.getIntegral());
//            gdsMember.setTotalIntegral(integral + save.getIntegral());
//            GdsMemberLevel level = gdsMemberLevelService.selectLevelUpdate(gdsMember.getTotalIntegral(), gdsMember.getLevelId());
//            gdsMember.setLevelId(level.getId());
//            gdsMember.setLevelName(level.getName());
//            gdsMemberService.update(gdsMember);
            jsonObject.put("code", 0);
            jsonObject.put("message", "success");
            return jsonObject;
        } catch (Exception e) {
            e.printStackTrace();
            //回滚事务
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            jsonObject.put("code", 400);
            jsonObject.put("message", "error");
            return jsonObject;
        }
    }

    /**
     * 保存金额充值记录,积分记录 ,会员积分,余额记录
     *
     * @param id 充值记录id
     * @return
     */
    @Transactional(rollbackFor = Throwable.class)
    public Boolean createRecharge(Long id) {
        try {
            GdsMemberMoney memberMoney = this.find(id);
            if (!memberMoney.getStatus()) {
                memberMoney.setStatus(true);
            } else {
                return true;
            }
            GdsMemberMoney money1 = this.update(memberMoney);
            //更新会员金额与积分
            int mi = memberMoney.getIntegral() == null ? 0 : money1.getIntegral();
            //如果积分赠送大于0,新增会员积分记录
            if (mi > 0) {
                gdsMemberIntegeralService.createMoneyRecord(money1);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
    }

    /**
     * 查询会员金额记录同步状态
     *
     * @param id 供应商id
     * @return
     */
    public List<GdsMemberMoney> queryMoneyIsUpdated(Long id) {
        List<GdsMemberMoney> list = gdsMemberMoneyMapper.queryMoneyIsUpdated(id);
        if (list != null && list.size() != 0) {
            return list;
        }
        return null;
    }

    /**
     * 同步会员金额记录
     *
     * @param id       金额记录id
     * @param synMoney
     * @return
     */
    @Transactional
    public GdsMemberMoney updateMemberMoneyOnLineSyn(Long id, Double synMoney) {
        GdsMemberMoney memberMoney = this.find(id);
        if (memberMoney != null) {
            if (memberMoney.getSynState()) {
                return memberMoney;
            }
            //充值或者消费金额
            double money = memberMoney.getMoney();
            //判断传递金额是否与充值金额一致
            if (!synMoney.equals(money)) {
                return null;
            }
            //如果有赠送金额,则加上赠送金额
            if (memberMoney.getDonationMoney() != null) {
                //赠送金额
                money += memberMoney.getDonationMoney();
            }
            //更新会员余额
            GdsMember gdsMember = gdsMemberService.find(memberMoney.getMemberId());
            if (gdsMember == null) {
                return null;
            }
            //计算会员更新后余额
            money = memberMoney.getRecordType() ? (gdsMember.getMoney() + money) : (gdsMember.getMoney() - money);
            gdsMember.setMoney((double) Math.round(money * 100) / 100);
            memberMoney.setBalanceMoney(gdsMember.getMoney());
            memberMoney.setSynState(true);
            memberMoney = this.update(memberMoney);
            gdsMemberService.update(gdsMember);
            return memberMoney;
        }
        return null;
    }

    /**
     * 删除会员删除对应的金额记录
     *
     * @param id
     * @param userId
     */
    @Transactional
    public void delBymemberId(Long id, Long userId) {
        gdsMemberMoneyMapper.delBymemberId(id, userId, new Date());
    }

    /**
     * 同步线下会员金额充值记录
     *
     * @param memberMoney
     * @return
     */
    @Transactional(rollbackFor = Throwable.class)
    public GdsMemberMoney updateMemberMoneyUnderLineSyn(GdsMemberMoney memberMoney) {
        GdsMember gdsMember = gdsMemberService.find(memberMoney.getMemberId());
        Assert.notNull(gdsMember, "没有查询到该用户");
        //会员余额
        Double money = gdsMember.getMoney();
        //充值金额
        Double money1 = memberMoney.getMoney();
        //赠送金额
        Double donationMoney = memberMoney.getDonationMoney() == null ? 0D : memberMoney.getDonationMoney();
        //同步余额
        double v = (double) Math.round((money + money1 + donationMoney) * 100) / 100;
        gdsMember.setMoney(v);
        gdsMemberService.update(gdsMember);
        memberMoney.setBalanceMoney(v);//会员余额
        GdsMemberMoney save = this.save(memberMoney);
        return save;
    }

    /**
     * 同步线下会员消费记录
     *
     * @param memberMoney
     * @return
     */
    @Transactional(rollbackFor = Throwable.class)
    public GdsMemberMoney updateMemberMoneyConsumptionSyn(GdsMemberMoney memberMoney) {
        GdsMember gdsMember = gdsMemberService.find(memberMoney.getMemberId());
        Assert.notNull(gdsMember, "没有查询到该用户");
        //会员余额
        Double money = gdsMember.getMoney();
        //消费金额
        Double money1 = memberMoney.getActualMoney();
        //同步余额
        double v = (double) Math.round((money - money1) * 100) / 100;
        if (v < 0) {
            return null;
        }
        gdsMember.setMoney(v);
        gdsMemberService.update(gdsMember);
        memberMoney.setBalanceMoney(v);//会员余额
        GdsMemberMoney save = this.save(memberMoney);
        return save;
    }

    /**
     * 根据订单号查询是否已经存在订单,防止重复更新
     *
     * @param sn 订单号
     * @param id 会员id
     * @return
     */
    public GdsMemberMoney findBySn(String sn, Long id) {
        return gdsMemberMoneyMapper.findBySn(sn, id);
    }

    /**
     * 会员兑换方式积分加金额,(消费金额可能会增加会员积分)
     *
     * @param data               订单记录
     * @param fzId               会员线下id
     * @param integralProportion 兑换比例
     * @param money              消耗金额
     */
    @Transactional
    public void createOrder(GdsMemberExchangeOrder data, String fzId, Double integralProportion, double money) {
        GdsMember gdsMember = gdsMemberService.find(data.getMemberId());
        GdsMemberMoney memberMoney = new GdsMemberMoney();
        memberMoney.setSupplierId(gdsMember.getSupplierId());
        memberMoney.setMemberId(gdsMember.getId());
        memberMoney.setSn(data.getSn());
        memberMoney.setSnText(data.getSnText());
        memberMoney.setTime(data.getTime());
        memberMoney.setMoney((double) Math.round((money * 100) / 100));     //应缴金额
        memberMoney.setActualMoney((double) Math.round((money * 100) / 100));//实缴金额
        memberMoney.setRechargeType(RechargeType.duihuan);
        memberMoney.setProductName(data.getProductName());
        memberMoney.setDonationMoney(0.0); //优惠金额
        memberMoney.setRecordType(false);
        memberMoney.setStatus(true);
        memberMoney.setSynState(false);
        memberMoney.setFzId(fzId);
        if (integralProportion > 0) {
            gdsMemberIntegeralService.createMoneyByOrder(gdsMember, data, money, integralProportion);
            memberMoney.setIntegral((int) Math.round(((money * integralProportion) * 100) / 100));
        }
        this.save(memberMoney);
    }

    /**
     * 邀请成功获得金额
     *
     * @param memberId  邀请人id
     * @param codeMoney 邀请获得金额
     */
    @Transactional
    public void invitationMoney(Long memberId, Double codeMoney) {
        GdsMember member = gdsMemberService.find(memberId);
        Assert.notNull(member, "邀请人不存在");
        GdsMemberMoney memberMoney = new GdsMemberMoney();
        memberMoney.setSupplierId(member.getSupplierId());
        memberMoney.setMemberId(memberId);
        memberMoney.setSn(UUID.randomUUID().toString().replace("-", ""));
        memberMoney.setSnText("邀请人成功");
        memberMoney.setTime(new Date());
        memberMoney.setMoney(codeMoney);
        memberMoney.setActualMoney(codeMoney);
        memberMoney.setRechargeType(RechargeType.huodong);
        memberMoney.setRecordType(true);
        memberMoney.setStatus(true);
        memberMoney.setSynState(false);
        memberMoney.setFzId(member.getFzId());
        this.save(memberMoney);
    }
}
