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
    <script src="${base}/member/js/jquery.min.js"></script>
    <script type="text/javascript" charset="utf-8">
        mui.init();
        $(document).ready(function (e) {
            var counter = 0;
            if (window.history && window.history.pushState) {
                $(window).on('popstate', function () {
                    window.history.pushState('forward', null, '#');
                    window.history.forward(1);
                    window.location.href = '/member/index?id=${data.memberId!}';//跳转到个人中心
                });
            }
            window.history.pushState('forward', null, '#'); //在IE中必须得有这两行
            window.history.forward(1);
        });
    </script>
</head>
<body class="exchange-success">
    <div class="member">
        <div class="pay-state success">
            <div class="member-flex">
                <i class="icon icon-wallet"></i>
                <div>
                    <h3>兑换成功！</h3>
                    <p>感谢您在我的商城兑换商品</p>
                </div>
            </div>
        </div>
        <div class="mui-content">
            <div class="member-part">
                <div class="member-flex member-row">
                    <div class="member-part-label">
                        <p>交易类型：</p>
                    </div>
                    <div class="member-flex-main">
                        <p>会员兑换</p>
                    </div>
                </div>
                <div class="member-flex member-row">
                    <div class="member-part-label">
                        <p>交易内容：</p>
                    </div>
                    <div class="member-flex-main">
                        <p>兑换：${data.productName!}*${data.count!}</p>
                        <#if data.deliverType == "DUIHUAN">
                        <p>兑换码：${data.redeemCode!}</p>
                        </#if>
                    </div>
                </div>
                <div class="member-flex member-row">
                    <div class="member-part-label">
                        <p>交易时间：</p>
                    </div>
                    <div class="member-flex-main">
                        <p>${data.time?datetime!}</p>
                    </div>
                </div>
                <div class="member-flex member-row">
                    <div class="member-part-label">
                        <p>交易人：</p>
                    </div>
                    <div class="member-flex-main">
                        <p>${data.name!}</p>
                    </div>
                </div>
                <div class="member-flex member-row">
                    <div class="member-part-label">
                        <p>联系方式：</p>
                    </div>
                    <div class="member-flex-main">
                        <p>${data.mobile!}</p>
                    </div>
                </div>
                <div class="member-flex member-row member-border-none">
                    <div class="member-part-label">
                        <p>收货地址：</p>
                    </div>
                    <div class="member-flex-main">
                        <p>${data.area!} ${data.address!}</p>
                    </div>
                </div>
            </div>
            <div class="member-part">
                <div class="member-flex member-row">
                    <div class="member-part-label">
                        <p>数量：</p>
                    </div>
                    <div class="member-flex-main">
                        <p>${data.count!}</p>
                    </div>
                </div>
                <div class="member-flex member-row member-border-none">
                    <div class="member-part-label">
                        <p>积分消耗：</p>
                    </div>
                    <div class="member-flex-main">
                        <p>${data.integral!}积分<#if data.money gt 0> ￥${data.money!}元</#if></p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>