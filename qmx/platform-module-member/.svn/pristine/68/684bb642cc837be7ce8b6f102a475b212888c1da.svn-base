package com.qmx.member.web.api;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.openservices.shade.org.apache.commons.codec.digest.DigestUtils;
import com.qmx.appextend.remoreapisub.SysUserClient;
import com.qmx.base.api.dto.RestResponse;
import com.qmx.base.core.base.BaseOpenController;
import com.qmx.base.core.constants.Constants;
import com.qmx.base.core.utils.DateUtil;
import com.qmx.coreservice.model.SysUser;
import com.qmx.member.enumerate.*;
import com.qmx.member.model.*;
import com.qmx.member.query.GdsMemberVO;
import com.qmx.member.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 会员同步接口
 */
@RestController
@RequestMapping("/open/member")
@SaveAPILog
public class MemberSynApi extends BaseOpenController {

    @Autowired
    private GdsMemberService gdsMemberService;
    @Autowired
    private GdsMemberMoneyService gdsMemberMoneyService;
    @Autowired
    private GdsMemberIntegeralService gdsMemberIntegeralService;
    @Autowired
    private SysUserApiService sysUserApiService;
    @Autowired
    private SysUserClient sysUserService;
    @Autowired
    private GdsMemberLevelService memberLevelService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response) {
        try {
            String appkey = request.getParameter("appkey");
            String sign = request.getParameter("sign");
            String timestamp = request.getParameter("timestamp");
            String body = request.getParameter("body");//{"appkey":"",data:..}
            SysUserApi userApiDTO = sysUserApiService.findByAppkey(appkey);
            if (userApiDTO == null) {
                JSONObject object = new JSONObject();
                object.put("code", "200");
                object.put("msg", "不存在的appkey");
                request.setAttribute(Constants.API_LOG_RESP_CONTENT_ATTRIBUTE_NAME, object.toString());
                return false;
            }
            SysUser sysUserDto = sysUserService.findById(userApiDTO.getUserId());
            if (sysUserDto == null) {
                JSONObject object = new JSONObject();
                object.put("code", "200");
                object.put("msg", "appkey未绑定用户");
                request.setAttribute(Constants.API_LOG_RESP_CONTENT_ATTRIBUTE_NAME, object.toString());
                return false;
            }
            request.setAttribute("userInfo", sysUserDto);
            Long s = System.currentTimeMillis() - Long.parseLong(timestamp);
            if (s > 10 * 60 * 1000) {
                JSONObject object = new JSONObject();
                object.put("code", "200");
                object.put("msg", "时间验证失败");
                request.setAttribute(Constants.API_LOG_RESP_CONTENT_ATTRIBUTE_NAME, object.toString());
                return false;
            }
            String str = appkey + body + userApiDTO.getSecretKey() + timestamp;
            String newSign = DigestUtils.md5Hex(str);
            if (!newSign.equalsIgnoreCase(sign)) {
                JSONObject object = new JSONObject();
                object.put("code", "200");
                object.put("msg", "签名验证失败");
                request.setAttribute(Constants.API_LOG_RESP_CONTENT_ATTRIBUTE_NAME, object.toString());
                return false;
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            JSONObject object = new JSONObject();
            object.put("code", "200");
            object.put("msg", "参数错误");
            request.setAttribute(Constants.API_LOG_RESP_CONTENT_ATTRIBUTE_NAME, object.toString());
            return false;
        }
    }

    @Override
    public String getAppKey(HttpServletRequest request) {
        return request.getParameter("appkey");
    }

    @Override
    public String getApiName(HttpServletRequest request) {
        return request.getRequestURI();
    }

    @Override
    public String getRequestStr(HttpServletRequest request) {
        String body = request.getParameter(API_BODY);
        //存放请求内容，如果本类其他方法要使用请求内容(如果以流的方式接收的再次获取会报错拿不到数据)，可以用这个属性获取
        request.setAttribute(Constants.API_LOG_REQ_CONTENT_ATTRIBUTE_NAME, body);
        //返回这个内容用于存储日志
        return body;
    }

    @Override
    public String getApiPlat(HttpServletRequest request) {
        String appKey = request.getParameter("appkey");
        if (StringUtils.isEmpty(appKey)) {
            return null;
        }
        SysUserApi userApiDTO = sysUserApiService.findByAppkey(appKey);
        if (userApiDTO == null) {
            return null;
        }
        return userApiDTO.getApiPlat().name();
    }

    /**
     * 查询线上会员信息同步状态
     *
     * @return 返回未同步会员信息
     */
    @RequestMapping(value = "/queryMemberIsUpdated", method = RequestMethod.POST)
    public Object queryMemberIsUpdated(HttpServletRequest request) {
        try {
            Long supplierId = getSupplierd(request);
            Assert.notNull(supplierId, "appkey错误");
            List<GdsMember> gdsMembers = gdsMemberService.queryMemberIsUpdated(supplierId);
            request.setAttribute(BaseOpenController.SYS_API_LOG_OP_SUCCESS, Boolean.TRUE);
            if (gdsMembers != null) {
                return RestResponse.ok(gdsMembers);
            }
            return RestResponse.fail(1, "暂未同步数据");
        } catch (Exception e) {
            e.printStackTrace();
            return RestResponse.fail(e.getMessage());
        }
    }

    /**
     * 同步线上会员信息到线下
     *
     * @return 返回会员同步信息
     */
    @RequestMapping(value = "/updaMemberSyn", method = RequestMethod.POST)
    public Object updaMemberSyn(HttpServletRequest request) {
        try {
            String body = request.getParameter("body");
            JSONObject json = JSONObject.parseObject(body);
            JSONObject data = json.getJSONObject("data");
            Assert.notNull(data, "data为空");
            // 返回会员信息,如果之前传递过去的手机号和线下会员的手机有相同的,则判断为成
            // 为同一个人,则线下把会员信息合并传递回来更新
            Long id = data.getLong("id");
            Assert.notNull(id, "会员id为空");
            String fzId = data.getString("fzId");
            Assert.hasText(fzId, "会员fzId为空");
            Boolean synState = data.getBoolean("synState");//同步标识
            Assert.notNull(synState, "同步状态为空");
            if (Boolean.TRUE.equals(synState)) {
                request.setAttribute(BaseOpenController.SYS_API_LOG_OP_SUCCESS, Boolean.TRUE);
                return RestResponse.fail(1, "已同步,请不要重复同步");
            }
            String cardNo = data.getString("cardNo");//卡面号码
            Assert.hasText(cardNo, "会员卡号不能为空");
            String name = data.getString("name");//姓名
            String mobile = data.getString("mobile");//手机
            Assert.hasText(mobile, "手机号为空");
            String sex = data.getString("sex");//性别
            MemberSex memberSex;
            if (MemberSex.male.name().equals(sex)) {
                memberSex = MemberSex.male;
            } else if (MemberSex.female.name().equals(sex)) {
                memberSex = MemberSex.female;
            } else {
                memberSex = MemberSex.unknown;
            }
            String birthday = data.getString("birthday");//生日
            Date date = DateUtil.parseDateTime(birthday);
            String state = data.getString("state");//会员状态
            MemberState memberState = null;
            for (MemberState s : MemberState.values()) {
                if (s.name().equals(state)) {
                    memberState = s;
                    break;
                }
            }
            Assert.notNull(memberState, "会员状态设置异常");
            String idcard = data.getString("idcard");//身份证
            String address = data.getString("address");//居住地址
            String remark = data.getString("remark");//备注
            Double money = data.getDouble("money");//余额
            Integer integral = data.getInteger("integral");//会员积分
            // 传递一系列参数封装,更新会员,记住判断手机和身份证号是否唯一
            GdsMember gdsMember = gdsMemberService.find(id);
            Assert.notNull(gdsMember, "查询id没有会员信息");
            Long count = gdsMemberService.selectMobileAndIdcard(gdsMember.getSupplierId(), new GdsMemberVO(gdsMember.getId(), mobile, idcard));
            if (count != null && count != 0) {
                request.setAttribute(BaseOpenController.SYS_API_LOG_OP_SUCCESS, Boolean.TRUE);
                return RestResponse.fail(1, "该手机号或身份证已有账号");
            }
            if (name != null) gdsMember.setName(name);
            if (date != null) gdsMember.setBirthday(date);
            if (idcard != null) gdsMember.setIdcard(idcard);
            if (address != null) gdsMember.setAddress(address);
            gdsMember.setCardNo(cardNo);
            gdsMember.setMobile(mobile);
            gdsMember.setSex(memberSex);
            gdsMember.setState(memberState);
            gdsMember.setRemark(remark);
            gdsMember.setSynState(true);
            //如果是第一次同步,会员fzid为空,则把线下的全部数据更新,否则,值更新资料
            if (StringUtils.isEmpty(gdsMember.getFzId())) {
                gdsMember.setFzId(fzId);
                if (money != null) {
                    gdsMember.setMoney(money);
                }
                if (integral != null) {
                    gdsMember.setIntegral(integral);
                    gdsMember.setTotalIntegral(integral);
                    // TODO: 2018/10/9 积分升级
                    GdsMemberLevel level = memberLevelService.selectLevelUpdate(gdsMember.getTotalIntegral(), gdsMember.getLevelId());
                    gdsMember.setLevelId(level.getId());
                    gdsMember.setLevelName(level.getName());
                }
            }
            gdsMember = gdsMemberService.update(gdsMember);
            request.setAttribute(BaseOpenController.SYS_API_LOG_OP_SUCCESS, Boolean.TRUE);
            return RestResponse.ok(gdsMember);
        } catch (Exception e) {
            e.printStackTrace();
            return RestResponse.fail(e.getMessage());
        }
    }

    /**
     * 查询线上会员金额充值记录同步状态
     *
     * @return 返回金额记录信息
     */
    @RequestMapping(value = "/queryMoneyIsUpdated", method = RequestMethod.POST)
    public Object queryMoneyIsUpdated(HttpServletRequest request) {
        try {
            Long supplierId = getSupplierd(request);
            Assert.notNull(supplierId, "appkey错误");
            List<GdsMemberMoney> list = gdsMemberMoneyService.queryMoneyIsUpdated(supplierId);
            request.setAttribute(BaseOpenController.SYS_API_LOG_OP_SUCCESS, Boolean.TRUE);
            if (list != null) {
                return RestResponse.ok(list);
            }
            return RestResponse.fail(1, "暂未同步数据");
        } catch (Exception e) {
            e.printStackTrace();
            return RestResponse.fail(e.getMessage());
        }
    }

    /**
     * 同步线上会员金额记录
     *
     * @return 返回同步金额记录信息
     */
    @RequestMapping(value = "/updaMemberMoneyOnLineSyn", method = RequestMethod.POST)
    public Object updateMemberMoneyOnLineSyn(HttpServletRequest request) {
        try {
            String body = request.getParameter("body");
            JSONObject json = JSONObject.parseObject(body);
            JSONObject data = json.getJSONObject("data");
            Assert.notNull(data, "data为空");
            Long id = data.getLong("id");
            Assert.notNull(id, "金额id为空");
            Double money = data.getDouble("money");
            Assert.notNull(money, "金额为空");
            GdsMemberMoney memberMoney = gdsMemberMoneyService.updateMemberMoneyOnLineSyn(id, money);
            Assert.notNull(memberMoney, "金额id或金额异常");
            request.setAttribute(BaseOpenController.SYS_API_LOG_OP_SUCCESS, Boolean.TRUE);
            return RestResponse.ok(memberMoney);
        } catch (Exception e) {
            e.printStackTrace();
            return RestResponse.fail(e.getMessage());
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    // 同步线下会员基本信息
    ///////////////////////////////////////////////////////////////////////////
    @RequestMapping(value = "/updaMemberUnderLineSyn", method = RequestMethod.POST)
    public Object updateMemberUnderLineSyn(HttpServletRequest request) {
        try {
            String body = request.getParameter("body");
            JSONObject json = JSONObject.parseObject(body);
            JSONObject data = json.getJSONObject("data");
            //获取参数
            Long supplierId = getSupplierd(request);
            Assert.notNull(supplierId, "appKey没有对应供应商");
            Boolean synState = data.getBoolean("synState");//同步标识
            Assert.notNull(synState, "同步状态为空");
            if (Boolean.TRUE.equals(synState)) {
                request.setAttribute(BaseOpenController.SYS_API_LOG_OP_SUCCESS, Boolean.TRUE);
                return RestResponse.fail(1, "已同步,请不要重复同步");
            }
            String fzId = data.getString("fzId");//线下会员id
            Assert.hasText(fzId, "会员fzId为空");
            String mobile = data.getString("mobile");//手机
            Assert.hasText(mobile, "手机号不能为空");
            String cardNo = data.getString("cardNo");//卡面号码
            Assert.hasText(cardNo, "会员卡号不能为空");
            String name = data.getString("name");//姓名
            String sex = data.getString("sex");//性别
            MemberSex memberSex;
            if (MemberSex.male.name().equals(sex)) {
                memberSex = MemberSex.male;
            } else if (MemberSex.male.name().equals(sex)) {
                memberSex = MemberSex.female;
            } else {
                memberSex = MemberSex.unknown;
            }
            String birthday = data.getString("birthday");//生日
            Date date = DateUtil.parseDateTime(birthday);
            String state = data.getString("state");//会员状态
            MemberState memberState = null;
            for (MemberState s : MemberState.values()) {
                if (s.name().equals(state)) {
                    memberState = s;
                    break;
                }
            }
            Assert.notNull(memberState, "会员状态为空");
            String idcard = data.getString("idcard");//身份证
            String address = data.getString("address");//居住地址
            String remark = data.getString("remark");//备注
            Double money = data.getDouble("money");//余额
            Integer integral = data.getInteger("integral");//会员积分
            GdsMember gdsMember = gdsMemberService.findByFzId(fzId);
            if (gdsMember != null) {
                Long count = gdsMemberService.selectMobileAndIdcard(supplierId, new GdsMemberVO(gdsMember.getId(), mobile, idcard));
                if (count == null || count != 0) {
                    request.setAttribute(BaseOpenController.SYS_API_LOG_OP_SUCCESS, Boolean.TRUE);
                    return RestResponse.fail(1, "该手机号或身份证已有账号");
                }
                if (cardNo != null) gdsMember.setCardNo(cardNo);
                if (name != null) gdsMember.setName(name);
                if (address != null) gdsMember.setAddress(address);
                if (remark != null) gdsMember.setRemark(remark);
                if (idcard != null) gdsMember.setIdcard(idcard);
                if (date != null) gdsMember.setBirthday(date);
                gdsMember.setMobile(mobile);
                gdsMember.setSex(memberSex);
                gdsMember.setState(memberState);
                gdsMember.setSynState(true);
                gdsMember = gdsMemberService.update(gdsMember);
            } else {
//                gdsMember = gdsMemberService.selectMobile(supplierId, mobile);
//                if (gdsMember != null) {
//                    return restResponse.fail(1, "该手机号已有账号");
//                }
                Long count = gdsMemberService.selectMobileAndIdcard(supplierId, new GdsMemberVO(null, mobile, idcard));
                if (count == null || count != 0) {
                    request.setAttribute(BaseOpenController.SYS_API_LOG_OP_SUCCESS, Boolean.TRUE);
                    return RestResponse.fail(1, "该手机号或身份证已有账号");
                }
                gdsMember = new GdsMember();
                gdsMember.setFzId(fzId);
                gdsMember.setSupplierId(supplierId);
                gdsMember.setCardNumber(gdsMemberService.selectCardNumberCount(supplierId));
                if (name != null) gdsMember.setName(name);
                if (idcard != null) gdsMember.setIdcard(idcard);
                if (address != null) gdsMember.setAddress(address);
                if (remark != null) gdsMember.setRemark(remark);
                if (date != null) gdsMember.setBirthday(date);
                gdsMember.setCardNo(cardNo);
                gdsMember.setMobile(mobile);
                gdsMember.setSex(memberSex);
                gdsMember.setState(memberState);
                gdsMember.setMoney(money == null ? 0 : money);
                //  默认过期时间99年
                Calendar instance = Calendar.getInstance();
                instance.add(Calendar.YEAR, 99);
                Date time = instance.getTime();
                gdsMember.setPastTime(time);
                gdsMember.setSource(MemberSource.xxht);
                gdsMember.setSynState(true);
                gdsMember.setIntegral(integral == null ? 0 : integral);
                gdsMember.setTotalIntegral(gdsMember.getIntegral());
                // TODO: zzq 2018/8/3  以后更改  新增会员设置等级,初始化金额积分
                GdsMemberLevel memberLevel = memberLevelService.selectionLevel(supplierId);
                if (memberLevel != null) {
                    GdsMemberLevel level = memberLevelService.selectLevelUpdate(gdsMember.getIntegral(), memberLevel.getId());
                    gdsMember.setLevelId(level.getId());
                    gdsMember.setLevelName(level.getName());
                } else {
                    request.setAttribute(BaseOpenController.SYS_API_LOG_OP_SUCCESS, Boolean.TRUE);
                    return RestResponse.fail(1, "线上系统没有初始化会员等级");
                }
                gdsMember = gdsMemberService.save(gdsMember);
            }
            request.setAttribute(BaseOpenController.SYS_API_LOG_OP_SUCCESS, Boolean.TRUE);
            return RestResponse.ok(gdsMember);
        } catch (Exception e) {
            e.printStackTrace();
            return RestResponse.fail(e.getMessage());
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    // 同步线下会员金额充值记录
    ///////////////////////////////////////////////////////////////////////////
    @RequestMapping(value = "/updaMemberMoneyUnderLineSyn", method = RequestMethod.POST)
    public Object updaMemberMoneyUnderLineSyn(HttpServletRequest request) {
        try {
            String body = request.getParameter("body");
            JSONObject json = JSONObject.parseObject(body);
            JSONObject data = json.getJSONObject("data");
            Assert.notNull(data, "data为空");
            String fzId = data.getString("fzId");//线下会员id
            Assert.hasText(fzId, "线下会员fzId为空");
            GdsMember member = gdsMemberService.findByFzId(fzId);
            Assert.notNull(member, "会员fzId不存在,请先同步会员信息");
            Boolean synState = data.getBoolean("synState");
            if (Boolean.TRUE.equals(synState)) {
                request.setAttribute(BaseOpenController.SYS_API_LOG_OP_SUCCESS, Boolean.TRUE);
                return RestResponse.fail(1, "请不要重复同步");
            }
            String time = data.getString("time");
            Date date = DateUtil.parseDateTime(time);//交易时间
            Assert.notNull(date, "交易时间为空");
            Double money = data.getDouble("money"); //应充值金额
            Assert.notNull(money, "应充值金额为空");
            Double actualMoney = data.getDouble("actualMoney"); //实际缴费金额
            Assert.notNull(actualMoney, "实际缴费金额为空");
            Double donationMoney = data.getDouble("donationMoney"); //赠送金额
            String rechargeType = data.getString("rechargeType"); //支付方式
            RechargeType type = null; //支付方式
            switch (rechargeType) {
                case "zfb":
                    type = RechargeType.xxzfb;
                    break;
                case "wx":
                    type = RechargeType.xxwx;
                    break;
                case "xj":
                    type = RechargeType.xxxinajin;
                    break;
                case "yhk":
                    type = RechargeType.xxyinhangka;
                    break;
                default:
                    type = RechargeType.xxqita;
            }
            Assert.notNull(type, "支付方式为空");
            String snText = data.getString("snText");//充值说明
            String sn = data.getString("sn");//订单号
            Assert.hasText(sn, "订单号为空或参数异常");
            //根据订单号查询是否已经存在订单,防止重复更新
            GdsMemberMoney snMoney = gdsMemberMoneyService.findBySn(sn, member.getId());
            if (snMoney != null) {
                request.setAttribute(BaseOpenController.SYS_API_LOG_OP_SUCCESS, Boolean.TRUE);
                return RestResponse.ok(snMoney);
            }
//            String integral = data.getString("integral");//赠送积分
            GdsMemberMoney memberMoney = new GdsMemberMoney();
            memberMoney.setMemberId(member.getId());
            memberMoney.setSupplierId(member.getSupplierId());
            memberMoney.setFzId(fzId);
            memberMoney.setSn(sn);
            memberMoney.setTime(date);
            memberMoney.setMoney(money);
            memberMoney.setActualMoney(actualMoney);
            memberMoney.setDonationMoney(donationMoney == null ? 0 : donationMoney);
            memberMoney.setRechargeType(type);
            memberMoney.setRecordType(true);
            memberMoney.setStatus(true);
            memberMoney.setSnText(snText);
            memberMoney.setSynState(true);
            GdsMemberMoney save = gdsMemberMoneyService.updateMemberMoneyUnderLineSyn(memberMoney);
            request.setAttribute(BaseOpenController.SYS_API_LOG_OP_SUCCESS, Boolean.TRUE);
            return RestResponse.ok(save);
        } catch (Exception e) {
            e.printStackTrace();
            return RestResponse.fail(e.getMessage());
        }
    }


    ///////////////////////////////////////////////////////////////////////////
    // 同步线下会员消费记录
    ///////////////////////////////////////////////////////////////////////////
    @RequestMapping(value = "/updaMemberMoneyConsumptionSyn", method = RequestMethod.POST)
    public Object updaMemberMoneyConsumptionSyn(HttpServletRequest request) {
        try {
            String body = request.getParameter("body");
            JSONObject json = JSONObject.parseObject(body);
            JSONObject data = json.getJSONObject("data");
            Assert.notNull(data, "data为空");
            String fzId = data.getString("fzId");//线下会员id
            Assert.hasText(fzId, "线下会员fzId为空");
            GdsMember member = gdsMemberService.findByFzId(fzId);
            Assert.notNull(member, "会员fzId不存在,请先同步会员信息");
            Boolean synState = data.getBoolean("synState");
            Assert.notNull(synState, "同步标识为空");
            if (Boolean.TRUE.equals(synState)) {
                return RestResponse.fail(1, "请不要重复同步");
            }
            String productName = data.getString("productName");//商品名字
            String time = data.getString("time");
            Date date = DateUtil.parseDateTime(time);//交易时间
            Double money = data.getDouble("money"); //应消费金额
            Double actualMoney = data.getDouble("actualMoney"); //实际消费金额
            Double donationMoney = data.getDouble("donationMoney"); //优惠金额
            String snText = data.getString("snText");//消费说明(消费地点+项目类型)
            String sn = data.getString("sn");//订单号
            Assert.hasText(productName, "商品名字为空");
            Assert.notNull(date, "交易时间为空");
            Assert.notNull(money, "应消费金额为空");
            Assert.notNull(actualMoney, "实际消费金额为空");
            Assert.hasText(sn, "订单号为空");
            //根据订单号查询是否已经存在订单,防止重复更新
            GdsMemberMoney snMoney = gdsMemberMoneyService.findBySn(sn, member.getId());
            if (snMoney != null) {
                request.setAttribute(BaseOpenController.SYS_API_LOG_OP_SUCCESS, Boolean.TRUE);
                return RestResponse.ok(snMoney);
            }
//            String integral = data.getString("integral");//赠送积分
            GdsMemberMoney memberMoney = new GdsMemberMoney();
            memberMoney.setMemberId(member.getId());
            memberMoney.setFzId(fzId);
            memberMoney.setSupplierId(member.getSupplierId());
            memberMoney.setProductName(productName);
            memberMoney.setTime(date);
            memberMoney.setMoney(money);
            memberMoney.setActualMoney(actualMoney);
            memberMoney.setDonationMoney(donationMoney == null ? (money - actualMoney) : donationMoney);
            memberMoney.setRechargeType(RechargeType.xxhyye);
            memberMoney.setSn(sn);
            memberMoney.setSnText(snText);
            memberMoney.setRecordType(false);
            memberMoney.setStatus(true);
            memberMoney.setSynState(true);
            GdsMemberMoney save = gdsMemberMoneyService.updateMemberMoneyConsumptionSyn(memberMoney);
            request.setAttribute(BaseOpenController.SYS_API_LOG_OP_SUCCESS, Boolean.TRUE);
            if (save == null) {
                return RestResponse.fail(1, "余额不足");
            }
            return RestResponse.ok(save);
        } catch (Exception e) {
            e.printStackTrace();
            return RestResponse.fail(e.getMessage());
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    // 同步线下会员积分记录
    ///////////////////////////////////////////////////////////////////////////
    @RequestMapping("/updaMemberIntegeralUnderLineSyn")
    public Object updaMemberIntegeralUnderLineSyn(HttpServletRequest request) {
        try {
            String body = request.getParameter("body");
            JSONObject json = JSONObject.parseObject(body);
            JSONObject data = json.getJSONObject("data");
            Assert.notNull(data, "data为空");
            String fzId = data.getString("fzId");
            Assert.notNull(fzId, "fzId为空");
            GdsMember member = gdsMemberService.findByFzId(fzId);
            Assert.notNull(member, "会员FZID不存在,请先同步会员信息");
            Boolean synState = data.getBoolean("synState");
            Assert.notNull(synState, "同步标识为空");
            if (Boolean.TRUE.equals(synState)) {
                return RestResponse.fail(1, "请不要重复同步");
            }
            String sn = data.getString("sn");//订单号  查询金额id, 供应商id
            Assert.hasText(sn, "订单号不能为空");
            GdsMemberIntegeral gdsMemberIntegeral = gdsMemberIntegeralService.findBySn(sn, member.getId());
            if (gdsMemberIntegeral != null) {
                request.setAttribute(BaseOpenController.SYS_API_LOG_OP_SUCCESS, Boolean.TRUE);
                return RestResponse.ok(gdsMemberIntegeral);
            }
            String snText = data.getString("snText");//订单内容
            String time = data.getString("time");//时间
            Date date = DateUtil.parseDateTime(time);//交易时间
            Assert.notNull(date, "交易时间为空");
            Integer integeral = data.getInteger("integeral");//充值/消费积分
            Assert.notNull(integeral, "积分为空");
            Boolean recordType = data.getBoolean("recordType");// 记录种类 false(消费积分)true(获得积分) balanceIntegeral
            Assert.notNull(recordType, "记录种类为空>>>false(消费积分)true(获得积分)");
            //根据订单号和会员id查询金额是否有金额记录
            // TODO: 2018/11/13 暂时线下积分与记录没有sn关联
//            GdsMemberMoney money = gdsMemberMoneyService.findBySn(sn, member.getId());
//            Assert.notNull(money, "请先同步会员金额记录");
            gdsMemberIntegeral = new GdsMemberIntegeral();
            gdsMemberIntegeral.setMemberId(member.getId());
//            gdsMemberIntegeral.setRecordId(money.getId());  产生积分类的id与积分表关联
            gdsMemberIntegeral.setSupplierId(member.getSupplierId());
            gdsMemberIntegeral.setSn(sn);
            gdsMemberIntegeral.setSnText(snText);
            gdsMemberIntegeral.setTime(date);
            gdsMemberIntegeral.setIntegeral(integeral);
            gdsMemberIntegeral.setRecordType(recordType);
            gdsMemberIntegeral.setSourceType(SourceType.xianxia);
            gdsMemberIntegeral.setStatus(true);
            gdsMemberIntegeral.setFzId(fzId);
            gdsMemberIntegeral.setSynState(true); //积分来源 充值消费  剩余积分
            gdsMemberIntegeral = gdsMemberIntegeralService.updaMemberIntegeralUnderLineSyn(gdsMemberIntegeral);
            request.setAttribute(BaseOpenController.SYS_API_LOG_OP_SUCCESS, Boolean.TRUE);
            return RestResponse.ok(gdsMemberIntegeral);
        } catch (Exception e) {
            e.printStackTrace();
            return RestResponse.fail(e.getMessage());
        }
    }

    /**
     * 查询线上会员积分记录同步状态
     *
     * @return
     */
    @RequestMapping(value = "/queryIntegeralIsUpdated", method = RequestMethod.POST)
    public Object queryIntegeralIsUpdated(HttpServletRequest request) {
        try {
            Long supplierId = getSupplierd(request);
            Assert.notNull(supplierId, "供应商id不能为空");
            List<GdsMemberIntegeral> list = gdsMemberIntegeralService.queryIntegeralIsUpdated(supplierId);
            request.setAttribute(BaseOpenController.SYS_API_LOG_OP_SUCCESS, Boolean.TRUE);
            if (list != null) {
                return RestResponse.ok(list);
            }
            return RestResponse.fail(1, "暂未同步数据");
        } catch (Exception e) {
            e.printStackTrace();
            return RestResponse.fail(e.getMessage());
        }
    }

    /**
     * 同步线上会员积分状态
     *
     * @return
     */
    @RequestMapping(value = "/updaMemberIntegeralSyn", method = RequestMethod.POST)
    public Object updaMemberIntegeralSyn(HttpServletRequest request) {
        try {
            String body = request.getParameter("body");
            JSONObject json = JSONObject.parseObject(body);
            JSONObject data = json.getJSONObject("data");
            Assert.notNull(data, "data为空");
            Long id = data.getLong("id");
            Assert.notNull(id, "积分id不能为空");
//            String fzId = data.getString("fzId");
            GdsMemberIntegeral integeral = gdsMemberIntegeralService.find(id);
            if (integeral != null) {
                if (integeral.getSynState()) {
                    request.setAttribute(BaseOpenController.SYS_API_LOG_OP_SUCCESS, Boolean.TRUE);
                    return integeral;
                }
            }
            integeral = gdsMemberIntegeralService.updaMemberIntegeralSyn(id/*, fzId*/);
            Assert.notNull(integeral, "请先同步会员信息");
            request.setAttribute(BaseOpenController.SYS_API_LOG_OP_SUCCESS, Boolean.TRUE);
            return RestResponse.ok(integeral);
        } catch (Exception e) {
            e.printStackTrace();
            return RestResponse.fail(e.getMessage());
        }
    }

    /**
     * 同步会员卡号
     *
     * @return
     */
    @RequestMapping(value = "/updateMemberCardNoSyn", method = RequestMethod.POST)
    public Object updateMemberCardNoSyn(HttpServletRequest request) {
        try {
            String body = request.getParameter("body");
            JSONObject json = JSONObject.parseObject(body);
            JSONObject data = json.getJSONObject("data");
            Assert.notNull(data, "data为空");
            String fzId = data.getString("fzId");
            String cardNo = data.getString("cardNo");
            Assert.hasText(fzId, "fzId不能为空");
            Assert.hasText(cardNo, "cardNo不能为空");
            GdsMember member = gdsMemberService.findByFzId(fzId);
            Assert.notNull(member, "该fzId没有会员信息,请先同步会员信息");
            if (cardNo.equals(member.getCardNo())) {
                request.setAttribute(BaseOpenController.SYS_API_LOG_OP_SUCCESS, Boolean.TRUE);
                return RestResponse.ok();
            }
            member.setCardNo(cardNo);
            gdsMemberService.update(member);
            request.setAttribute(BaseOpenController.SYS_API_LOG_OP_SUCCESS, Boolean.TRUE);
            return RestResponse.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return RestResponse.fail(e.getMessage());
        }
    }

    /**
     * 同步会员积分与金额
     *
     * @return
     */
    @RequestMapping(value = "/updateMemberMoneyAndIntegral", method = RequestMethod.POST)
    public Object updateMemberMoneyAndIntegral(HttpServletRequest request) {
        try {
            String body = request.getParameter("body");
            JSONObject json = JSONObject.parseObject(body);
            JSONObject data = json.getJSONObject("data");
            Assert.notNull(data, "data为空");
            String fzId = data.getString("fzId");
            Double money = data.getDouble("money");
            Integer integral = data.getInteger("integral");
            Assert.hasText(fzId, "fzId不能为空");
            Assert.notNull(money, "money不能为空");
            GdsMember member = gdsMemberService.findByFzId(fzId);
            Assert.notNull(member, "该fzId没有会员信息,请先同步会员信息");
            if (member.getMoney().equals(money) && integral != null && member.getIntegral().equals(integral)) {
                request.setAttribute(BaseOpenController.SYS_API_LOG_OP_SUCCESS, Boolean.TRUE);
                return RestResponse.ok("金额积分数据一致");
            }
            if (!member.getMoney().equals(money))
                member.setMoney(money);
            if (integral != null && !member.getIntegral().equals(integral))
                member.setIntegral(integral);
            gdsMemberService.update(member);
            request.setAttribute(BaseOpenController.SYS_API_LOG_OP_SUCCESS, Boolean.TRUE);
            return RestResponse.ok("金额积分修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            return RestResponse.fail(e.getMessage());
        }
    }


    //获得供应商id
    private Long getSupplierd(HttpServletRequest request) {
        String appKey = request.getParameter("appkey");
        if (StringUtils.isEmpty(appKey)) {
            return null;
        }
        SysUserApi userApiDTO = sysUserApiService.findByAppkey(appKey);
        if (userApiDTO == null) {
            return null;
        }
        SysUser sysUserDto = sysUserService.findById(userApiDTO.getUserId());
        if (sysUserDto == null) {
            return null;
        }
        return sysUserDto.getId();
    }

}
