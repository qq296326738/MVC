<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport"
          content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no"/>
    <title></title>
    <script src="${base}/member/js/mui.min.js"></script>
    <link href="${base}/member/css/mui.css" rel="stylesheet"/>
    <link rel="stylesheet" type="text/css" href="${base}/member/css/member.css"/>
    <script type="text/javascript" charset="utf-8">
        mui.init();
    </script>
    <style>

    </style>
</head>
<body class="record">
<div class="member">
    <h4 class="member-title">充值记录</h4>
    <div class="mui-content">
    <#list map?keys as key>
        <div class="member-part">
            <div class="member-flex member-part-title">${key!}</div>
            <#list map[key] as val>
                <a href="rechargeMoneyDetails?id=${val.id!}&url=rechargeDetails">
                    <div class="member-flex member-row">
                        <div class="member-flex-main">
                            <h4><#--${val.rechargeType?contains("xx")}充值-->
                                <#if val.rechargeType?contains("xx")>
                                    线下充值
                                <#else>
                                    线上充值
                                </#if>
                            </h4>
                            <p class="fg-gray">${val.time?datetime!}</p>
                        </div>
                        <div>
                            <h5>${val.actualMoney!}</h5>
                            <p class="fg-gray">
                            <#--<#if val.donationMoney??>-->
                                <#if val.money == val.actualMoney>
                                    赠送￥${val.donationMoney!}元
                                <#else >
                                    优惠￥${val.money - val.actualMoney}
                                </#if>
                            </p>
                        </div>
                    </div>
                </a>
            </#list>
        </div>
    </#list>
    </div>
</div>
</body>
</html>