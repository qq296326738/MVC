package com.qmx.member.query;

import com.qmx.base.api.dto.QueryDto;
import com.qmx.member.enumerate.MemberSex;
import com.qmx.member.enumerate.MemberSource;
import com.qmx.member.enumerate.MemberState;

import java.util.Date;

public class GdsMemberVO extends QueryDto {
    private Long id;
    /**
     * 所属人id
     */
    private Long memberId;
    /**
     * 供应商id
     */
    private Long supplierId;
    /**
     * 密码
     */
    private String password;
    /**
     * 集团供应商id
     */
    private Long groupSupplierId;

    /**
     * 会员卡号（实体卡ID）
     */
    private String cardNo;
    /**
     * 卡面号码（虚拟卡号码）
     */
    private String cardNumber;
    /**
     * 会员姓名
     */
    private String name;
    /**
     * 昵称
     */
    private String nickName;
    /**
     * 会员手机号码
     */
    private String mobile;
    /**
     * 会员身份证
     */
    private String idcard;
    /**
     * 会员性别
     */
    private MemberSex sex;
    /**
     * 会员生日
     */
    private Date birthday;
    /**
     * 会员来源
     */
    private MemberSource source;
    /**
     * 会员等级
     */
    private Long levelId;
    /**
     * 会员等级名称
     */
    private String levelName;
    /**
     * 会员状态
     */
    private MemberState state;
    /**
     * 过期时间
     */
    private Date pastTime;
    /**
     * 当前积分
     */
    private Integer integral;
    /**
     * 总积分
     */
    private Integer totalIntegral;
    /**
     * 金额
     */
    private Double money;
    /**
     * 国籍
     */
    private String countries;
    /**
     * 名族
     */
    private String ethnic;
    /**
     * 籍贯
     */
    private String origin;
    /**
     * 工作单位
     */
    private String workUnit;
    /**
     * 省--简码(详细地址)
     */
    private String province;
    /**
     * 市--简码(详细地址)
     */
    private String city;
    /**
     * 区--简码(详细地址)
     */
    private String county;
    /**
     * 详细地址
     */
    private String address;
    /**
     * 头像图片地址
     */
    private String image;
    /**
     * 客户经理id
     */
    private String userId;
    /**
     * 客户经理名字
     */
    private String userName;
    /**
     * 微信openId
     */
    private String openid;
    /**
     * 同步状态(线上线下同步)
     */
    private Boolean synState;
    /**
     * 线下会员id
     */
    private String fzId;
    /**
     * 邮政编码
     */
    private String postalCode;

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getFzId() {
        return fzId;
    }

    public void setFzId(String fzId) {
        this.fzId = fzId;
    }

    public GdsMemberVO() {
    }

    public GdsMemberVO(Long id, String mobile, String idcard) {
        this.id = id;
        this.mobile = mobile;
        this.idcard = idcard;
    }

    public Boolean getSynState() {
        return synState;
    }

    public void setSynState(Boolean synState) {
        this.synState = synState;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Integer getTotalIntegral() {
        return totalIntegral;
    }

    public void setTotalIntegral(Integer totalIntegral) {
        this.totalIntegral = totalIntegral;
    }

    public String getCountries() {
        return countries;
    }

    public void setCountries(String countries) {
        this.countries = countries;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEthnic() {
        return ethnic;
    }

    public void setEthnic(String ethnic) {
        this.ethnic = ethnic;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getWorkUnit() {
        return workUnit;
    }

    public void setWorkUnit(String workUnit) {
        this.workUnit = workUnit;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public MemberSex getSex() {
        return sex;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public Long getGroupSupplierId() {
        return groupSupplierId;
    }

    public void setGroupSupplierId(Long groupSupplierId) {
        this.groupSupplierId = groupSupplierId;
    }

    public void setSex(MemberSex sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Long getLevelId() {
        return levelId;
    }

    public void setLevelId(Long levelId) {
        this.levelId = levelId;
    }

    public MemberState getState() {
        return state;
    }

    public void setState(MemberState state) {
        this.state = state;
    }

    public Date getPastTime() {
        return pastTime;
    }

    public void setPastTime(Date pastTime) {
        this.pastTime = pastTime;
    }

    public Integer getIntegral() {
        return integral;
    }

    public void setIntegral(Integer integral) {
        this.integral = integral;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public MemberSource getSource() {
        return source;
    }

    public void setSource(MemberSource source) {
        this.source = source;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }
}
