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
<body class="list">
<div class="member">
    <h4 class="member-title">积分记录</h4>
    <div class="mui-content">
        <div class="member-part">
        <#list list as data>
            <#if data.sourceType == 'chongzhi'>
                <a href="rechargeDetails?id=${data.id!}&url=rechargeDetails">
                    <div class="member-flex member-row">
                        <div class="member-flex-main">
                            <h4>充值</h4>
                            <p class="fg-gray">${data.time?datetime!}</p>
                        </div>
                        <div>
                            <h5>+${data.integeral!}</h5>
                        </div>
                    </div>
                </a>
            <#elseif data.sourceType == 'xiaofei'>
                <a href="integralConsume?id=${data.id!}&url=integralConsume">
                    <div class="member-flex member-row">
                        <div class="member-flex-main">
                            <h4>消费：${data.productName!}</h4>
                            <p class="fg-gray">${data.time?datetime!}</p>
                        </div>
                        <div>
                            <h5>+${data.integeral!}</h5>
                        </div>
                    </div>
                </a>
            <#elseif data.sourceType == 'huodong'>
                <a href="integralSign?id=${data.id!}&url=integralSign">
                    <div class="member-flex member-row">
                        <div class="member-flex-main">
                            <h4>签到</h4>
                            <p class="fg-gray">${data.time?datetime!}</p>
                        </div>
                        <div>
                            <h5>+${data.integeral!}</h5>
                        </div>
                    </div>
                </a>
            <#elseif data.sourceType == 'fenxiang'>
                <a href="integralSign?id=${data.id!}&url=integralSign">
                    <div class="member-flex member-row">
                        <div class="member-flex-main">
                            <h4>分享</h4>
                            <p class="fg-gray">${data.time?datetime!}</p>
                        </div>
                        <div>
                            <h5>+${data.integeral!}</h5>
                        </div>
                    </div>
                </a>
            <#elseif data.sourceType == 'zhuce'>
                <a href="integralSign?id=${data.id!}&url=integralSign">
                    <div class="member-flex member-row">
                        <div class="member-flex-main">
                            <h4>注册</h4>
                            <p class="fg-gray">${data.time?datetime!}</p>
                        </div>
                        <div>
                            <h5>+${data.integeral!}</h5>
                        </div>
                    </div>
                </a>
            <#elseif data.sourceType == 'duihuan'>
                <a href="integralDetails?id=${data.id!}&url=integralDetails"">
                <div class="member-flex member-row">
                    <div class="member-flex-main">
                        <h4>兑换: ${data.productName!}</h4>
                        <p class="fg-gray">${data.time?datetime!}</p>
                    </div>
                    <div>
                        <#--<h5>-${data.integeral!}</h5>-->
                            <#if data.recordType>
                                <h5>+${data.integeral!}</h5>
                                <p class="fg-gray">金额兑换获得积分</p>
                            <#else>
                                <h5>-${data.integeral!}</h5>
                            </#if>
                    </div>
                </div>
                </a>
            <#elseif data.sourceType == 'xianxia'>
                <a href="integralSign?id=${data.id!}&url=integralSign"">
                <div class="member-flex member-row">
                    <div class="member-flex-main">
                        <h4>线下 ${data.productName!}</h4>
                        <p class="fg-gray">${data.time?datetime!}</p>
                    </div>
                    <div>
                        <h5>
                            <#if data.recordType>
                                +
                            <#else>
                                -
                            </#if>
                        ${data.integeral!}
                        </h5>
                    </div>
                </div>
                </a>
            </#if>
        </#list>
        </div>
    </div>
</div>
</body>
</html>