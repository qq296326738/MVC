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
    <script>
        if (window.name != "bencalie") {
            location.reload();
            window.name = "bencalie";
        }
        else {
            window.name = "";
        }
    </script>
</head>
<body class="index">
<div class="member">
    <div class="index-header">
        <div class="header-img">
            <input type="hidden" id="refreshed" value="no">
        <#if member.image??>
            <img src="${member.image!}" onclick="add()"/>
        <#else >
            <img src="${base}/member/images/index-user-header.jpg" onclick="add()"/>
        </#if>
            <script>
                function add() {
                    window.location.href = "userInfo?id=${member.id!}";
                }
            </script>
        </div>
        <div class="mui-text-center">${member.name!} (${member.levelName!}会员)</div>
        <div class="member-table">
            <div class="member-table-cell">
                <a href="balance?id=${member.id!}&money=${member.money!}">
                    <i class="icon icon-wallet"></i>余额：${member.money!}元
                </a>
            </div>
            <div class="member-table-cell">
                <a href="exchange?id=${member.id!}&integral=${member.integral!}">
                    <i class="icon icon-gift"></i>积分：${member.integral!}分
                </a>
            </div>
        </div>
    </div>
    <div class="mui-content">
        <div>
            <a href="userInfo?id=${member.id!}">
                <div class="member-link-row">
                    <i class="icon icon-user"></i>我的会员
                    <i class="mui-pull-right mui-icon mui-icon-arrowright"></i>
                </div>
            </a>
            <a href="sign?id=${member.id!}">
                <div class="member-link-row">
                    <i class="icon icon-sign"></i>每日签到
                    <i class="mui-pull-right mui-icon mui-icon-arrowright"></i>
                </div>
            </a>
            <a href="recharge?id=${member.id!}">
                <div class="member-link-row">
                    <i class="icon icon-recharge"></i>我的充值
                    <i class="mui-pull-right mui-icon mui-icon-arrowright"></i>
                </div>
            </a>
            <a href="exchange?id=${member.id!}&integral=${member.integral!}">
                <div class="member-link-row">
                    <i class="icon icon-card"></i>积分兑换
                    <i class="mui-pull-right mui-icon mui-icon-arrowright"></i>
                </div>
            </a>
            <a href="consumeList?id=${member.id!}">
                <div class="member-link-row">
                    <i class="icon icon-consume"></i>消费记录
                    <i class="mui-pull-right mui-icon mui-icon-arrowright"></i>
                </div>
            </a>
            <a href="qRcode?id=${member.id!}">
                <div class="member-link-row">
                    <i class="icon icon-code"></i>扫码消费
                    <i class="mui-pull-right mui-icon mui-icon-arrowright"></i>
                </div>
            </a>
            <#--<a href="coupon?id=${member.id!}">-->
                <#--<div class="member-link-row">-->
                    <#--<i class="icon icon-discount"></i>优惠券-->
                    <#--<i class="mui-pull-right mui-icon mui-icon-arrowright"></i>-->
                <#--</div>-->
            <#--</a>-->
        </div>
        <div style="border-top: solid 10px #efeff4;">
            <a href="tel:${mobile!}">
                <div class="member-link-row">
                    <i class="icon icon-phone"></i>联系客服
                    <i class="mui-pull-right mui-icon mui-icon-arrowright"></i>
                </div>
            </a>
        </div>
    </div>
</div>
</body>
</html>