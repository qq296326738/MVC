<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport"
          content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no"/>
    <title></title>
    <script src="${base}/member/js/mui.min.js"></script>
    <script type="text/javascript" src="${base}/member/js/jquery-3.2.0.min.js"></script>
    <link href="${base}/member/css/mui.css" rel="stylesheet"/>
    <link href="${base}/member/css/kxg.css" rel="stylesheet"/>
    <script type="text/javascript" charset="utf-8">
        //时间戳转换成时间
        function timeStamp2String(time) {
            var datetime = new Date();
            datetime.setTime(time);
            var year = datetime.getFullYear();
            var month = datetime.getMonth() + 1 < 10 ? "0" + (datetime.getMonth() + 1) : datetime.getMonth() + 1;
            var date = datetime.getDate() < 10 ? "0" + datetime.getDate() : datetime.getDate();
            var hour = datetime.getHours() < 10 ? "0" + datetime.getHours() : datetime.getHours();
            var minute = datetime.getMinutes() < 10 ? "0" + datetime.getMinutes() : datetime.getMinutes();
            var second = datetime.getSeconds() < 10 ? "0" + datetime.getSeconds() : datetime.getSeconds();
            return year + "-" + month + "-" + date + " " + hour + ":" + minute + ":" + second;
        }

        mui.init();
        $(function () {
            $("#add").click(function () {
                var size = $("#addValue").val();
                $.post(
                        "addTimeSign",
                        {
                            id: '${id!}', size: size
                        },
                        function (data) {
                            var val = "";
                            $.each(data, function (index, value) {
                                val += '<li class="mui-row">' +
                                        '<p class="mui-col-sm-8 mui-col-xs-8">' + timeStamp2String(value.time)/*.substring(0, 19)*/ + '</p>' +
                                        '<p class="mui-col-sm-4 mui-col-xs-4 g-fg-orange">' + value.integral + '积分</p>' +
                                        '</li>';
                            });
                            $("#ul").append(val);
                            $("#addValue").val(parseInt(size) + 1);
                            if (data.length != 5) {
                                $("#add").css('display', 'none');
                                return false;
                            }
                        }
                )
            })
        });
        <#if fale == false>
        $(function () {
            $("#qianDao").click(function () {
                $.post(
                        "addSign",
                        {id: '${id!}'},
                        function (data) {
                            if (data != null) {
                                $("#monthSign").text(parseInt($("#monthSign").text()) + 1);
                                $("#cumulativeSign").text(parseInt($("#cumulativeSign").text()) + 1);
                                $("#continuousSign").text(parseInt($("#continuousSign").text()) + 1);
                                $("#cumulativeRewards").text(parseInt($("#cumulativeRewards").text()) + data.integral);
                            }
                        }
                )
            })
        });
        </#if>
    </script>
</head>
<body class="kxg sign">
<header class="mui-bar mui-bar-nav">
    <a href="index.html" class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left"></a>
    <h1 class="mui-title">每日签到</h1>
    <a href="index?id=${id!}" class="icon icon-house mui-pull-right"></a>
</header>
<div class="mui-content">
    <div class="kxg-img-box">
        <img src="${base}/member/images/sign_bg.jpg"/>
    <#if fale == false>
        <input id="qianDao" class="sign-radio" type="radio"/>
        <div class="kxg-sign-button"></div>
    <#else>
        <div class="kxg-sign-button kxg-active"></div>
    </#if>
    </div>
    <div class="kxg-sign-title">签到记录</div>
    <div class="mui-row kxg-sign-nav">
        <div class="mui-col-sm-3 mui-col-xs-3">
            <h5>本月签到</h5>
            <p id="monthSign" class="g-fg-orange">${data.monthSign!0}</p>
        </div>
        <div class="mui-col-sm-3 mui-col-xs-3">
            <h5>累计签到</h5>
            <p id="cumulativeSign" class="g-fg-orange">${data.cumulativeSign!0}</p>
        </div>
        <div class="mui-col-sm-3 mui-col-xs-3">
            <h5>连续签到</h5>
            <p id="continuousSign" class="g-fg-orange">${data.continuousSign!0}</p>
        </div>
        <div class="mui-col-sm-3 mui-col-xs-3">
            <h5>累计奖励</h5>
            <p id="cumulativeRewards" class="g-fg-orange">${data.cumulativeRewards!0}</p>
        </div>
    </div>
    <ul id="ul" class="kxg-sign-list">
        <li class="mui-row">
            <h5 class="mui-col-sm-8 mui-col-xs-8">签到日期</h5>
            <h5 class="mui-col-sm-4 mui-col-xs-4">奖励</h5>
        </li>
    <#list list as ll>
        <li class="mui-row">
            <p class="mui-col-sm-8 mui-col-xs-8">${ll.time?datetime!}</p>
            <p class="mui-col-sm-4 mui-col-xs-4 g-fg-orange">${ll.integral!}积分</p>
        </li>
    </#list>
    </ul>
    <input type="hidden" value="2" name="size" id="addValue">
    <div class="kxg-more" id="add">查看更多>></div>
    <!--<div class="mui-row g-fg-orange">
        <i class="icon icon-price"></i>余额
        <span class="mui-pull-right g-fg-grey">2.50</span>
    </div>
    <div class="mui-row g-fg-red">
        <i class="icon icon-integral"></i>积分
        <span class="mui-pull-right g-fg-grey">0(分)</span>
    </div>-->
</div>
</body>
</html>