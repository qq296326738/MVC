<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no"/>
    <link rel="stylesheet" href="${base}/member/css/mui.min.css"/>
    <link rel="stylesheet" type="text/css" href="${base}/member/font-awesome-4.7.0/css/font-awesome.css"/>
    <link rel="stylesheet" href="${base}/member/css/forum.css"/>
    <script type="text/javascript" src="${base}/member/js/jquery.min.js"></script>
    <script type="text/javascript" src="${base}/member/js/mui.min.js"></script>
    <title></title>
</head>
<body class="login">
<script type="text/javascript">
    mui.init();
</script>
<div class="mui-segmented-control">
    <a class="mui-control-item" href="#message">短信验证码登录</a>
</div>
<div id="message" class="login-box mui-control-content" style="display: block">
    <div class="flex login-row">
        <label style="width: 78px;">中国 +86</label>
        <input id="mobile" type="text" name="mobile" placeholder="输入手机号"/>
    </div>
    <div class="flex login-row">
        <label style="width: 78px;">短信验证码</label>
        <input id="code" type="text" name="code"/>
        <input type="hidden" id="userId" value="${userId!}"/>
        <input type="hidden" id="openid" value="${openid!}"/>
        <input type="hidden" id="memberId" value="${memberId!}"/>
        <a id="yzm" onclick="code()" class="login-code-btn">获取验证码</a>
    </div>
    <a id="btn" class="login-btn" style="margin-top: 13px;">登录</a>
</div>
<script>
    var InterValObj; //timer变量，控制时间
    var count = 60; //间隔函数，1秒执行
    var curCount;//当前剩余秒数
    //获取验证码
    function code() {
        var mobile = $("#mobile").val();
        var userId = $("#userId").val();
        if (mobile == null || mobile == undefined || mobile == "") {
            mui.toast("请输入手机号码!", {duration: 'short', type: 'div'});
            return;
        }
        var number = /^1[0-9]{10}$/;
        if (!number.test(mobile)) {
            mui.toast("输入的手机号码格式不对，请重新输入!", {duration: 'short', type: 'div'});
            return;
        }
        if (curCount > 0) {
            return;
        }
        curCount = count;
        //设置button效果，开始计时
        $("#yzm").attr("disabled", "true");
        $("#yzm").html("重新发送(" + curCount + ")");
        InterValObj = window.setInterval(SetRemainTime, 1000); //启动计时器，1秒执行一次
        $.ajax({
            url: 'sendMessage',
            type: 'GET',
            data: {'mobile': mobile, 'userId': userId},
            dataType: 'json',
            success: function (data) {
                mui.toast(data.msg, {duration: 'short', type: 'div'});
            }
        });
    }

    //timer处理函数
    function SetRemainTime() {
        if (curCount == 0) {
            window.clearInterval(InterValObj);//停止计时器
            $("#yzm").removeAttr("disabled");//启用按钮
            $("#yzm").html("发送验证码");
        }
        else {
            curCount--;
            $("#yzm").html("重新发送(" + curCount + ")");
        }
    }
</script>
<script>
    $("#btn").click(function () {
        var mobile = $("#mobile").val();
        var userId = $("#userId").val();
        var code = $("#code").val();
        var openid = $("#openid").val();
        var memberId = $("#memberId").val();
        if (mobile == null || mobile == undefined || mobile == "") {
            mui.toast("请输入手机号码!", {duration: 'short', type: 'div'});
            return;
        }
        if (code == null || code == undefined || code == "") {
            mui.toast("请输入验证码!", {duration: 'short', type: 'div'});
            return;
        }
        var number = /^(1+\d{10})$/;
        if (!number.test(mobile)) {
            mui.toast("输入的手机号码格式不对，请重新输入!", {duration: 'short', type: 'div'});
            return;
        }
        $.ajax({
            url: 'findByMoblie',
            type: 'GET',
            data: {
                'mobile': mobile,
                'userId': userId,
                'code': code,
                'openid': openid,
                'memberId': memberId
            },
            dataType: 'json',
            success: function (data) {
                if (data.state == 'success') {
                    window.location.href = "index?id=" + data.id;
                } else {
                    mui.toast(data.msg, {duration: 'short', type: 'div'});
                }
            }
        });
    })
</script>
</body>
</html>
