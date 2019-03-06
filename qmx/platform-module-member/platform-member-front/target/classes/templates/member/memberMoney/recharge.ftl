<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport"
          content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no"/>
    <title></title>
    <script src="${base}/member/js/mui.min.js"></script>
    <script src="${base}/member/js/jquery-3.2.0.min.js"></script>
    <link href="${base}/member/css/mui.css" rel="stylesheet"/>
    <link rel="stylesheet" type="text/css" href="${base}/member/css/member.css"/>
    <script type="text/javascript" charset="utf-8">
        mui.init();
    </script>
    <style>

    </style>
</head>
<body class="recharge">
<div class="member">
    <h4 class="member-title">会员充值<a href="rechargeList?id=${id!}" class="member-title-link">充值记录</a></h4>
    <div class="mui-content">
        <div class="member-part">
            <div class="member-row">
                <div class="member-flex-main">
                    <h4>请输入充值金额</h4>
                    <input onblur="amount1(this)" id="money" type="text" class="member-input-1" placeholder="0.00"/>
                    <script type="text/javascript">
                        //                        var bb = 0;//全局变量i
                        function amount1(th) {
                            if (th.value == "") {
                                return false;
                            }
//                            if (bb > 30) {
//                                alert("请稍后再充值")
//                                return false;
//                            }
                            var regStrs = [
                                ['^0(\\d+)$', '$1'], //禁止录入整数部分两位以上，但首位为0
                                ['[^\\d\\.]+$', ''], //禁止录入任何非数字和点
                                ['\\.(\\d?)\\.+', '.$1'], //禁止录入两个以上的点
                                ['^(\\d+\\.\\d{2}).+', '$1'] //禁止录入小数点后两位以上
                            ];
                            for (var i = 0; i < regStrs.length; i++) {
                                var reg = new RegExp(regStrs[i][0]);
                                th.value = th.value.replace(reg, regStrs[i][1]);
                            }
                            //所有的逻辑
                            $.post(
                                    "calculatingPrice",
                                    {id: '${id!}', money: th.value},
                                    function (data) {
                                        if (data.code == 400) {
                                            mui.toast(data.msg);
                                            document.getElementById("money").value = "";
//                                            $("#aaaa").hide();
                                        } else if (data.code == 200) {
//                                            $("#aaaa").show();
                                            if (data.type == "zengsong") {
                                                document.getElementById("span1").innerText = "赠送金额" + data.moneyPoint + "元";
                                                document.getElementById("span3").innerText = th.value + "元";
                                                document.getElementById("span3").value = th.value;
                                            } else if (data.type == "zhekou") {
                                                document.getElementById("span1").innerText = "优惠金额" + (Math.round((th.value - data.discountPoint) * 100) / 100) + "元";
                                                document.getElementById("span3").innerText = data.discountPoint + "元";
                                                document.getElementById("span3").value = data.discountPoint;
                                            }
                                            document.getElementById("span2").innerText = data.integralPoint + "积分";
                                        }
                                    },
                                    "json"
                            );
//                            bb++;
                        }
                    </script>
                </div>
            </div>
        </div>
        <div class="member-part">
            <div class="member-flex member-row">
                <div class="member-part-label">
                    <p>折扣优惠：</p>
                </div>
                <div class="member-flex-main">
                    <p class="fg-orange"><span id="span1"></span></p>
                </div>
            </div>
            <div class="member-flex member-row">
                <div class="member-part-label">
                    <p>获得积分：</p>
                </div>
                <div class="member-flex-main">
                    <p class="fg-orange"><span id="span2"></span></p>
                </div>
            </div>
            <div class="member-flex member-row member-border-none">
                <div class="member-part-label">
                    <p class="fg-black">实付金额：</p>
                </div>
                <div class="member-flex-main">
                    <p class="fg-black"><span id="span3"></span></p>
                </div>
            </div>
        </div>
        <a id="aaaa" <#--style="display: none" -->onclick="submit()" class="member-btn-3">确认充值</a>
    </div>
</div>
</body>
<script>
    function submit() {
        var money = document.getElementById("money").value;
        var actualMoney = document.getElementById("span3").value;
        if (money == 0.00 || money == "") {
            alert("输入金额有误!请重新输入!!!");
            return false;
        }
        if (actualMoney == undefined) {
            return false;
        }
        window.location.href = "pay?id=${id!}" + "&moeny=" + money + "&actualMoney=" + actualMoney;
    }
</script>
</html>