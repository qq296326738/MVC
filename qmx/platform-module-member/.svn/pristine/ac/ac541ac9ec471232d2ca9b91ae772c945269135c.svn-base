<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport"
          content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no"/>
    <title></title>
    <script src="${base}/member/js/mui.min.js"></script>
    <link href="${base}/member/css/mui.css" rel="stylesheet"/>
    <script type="text/javascript" src="${base}/member/js/jquery-3.2.0.min.js"></script>
    <link rel="stylesheet" href="${base}/pcshop/layui/css/layui.css">
    <script type="text/javascript" src="${base}/pcshop/layui/layui.js"></script>
    <link rel="stylesheet" type="text/css" href="${base}/member/css/member.css"/>
    <script type="text/javascript" charset="utf-8">
        mui.init();
    </script>
    <script>
        layui.use(['form', 'laydate'], function () {
            var form = layui.form, laydate = layui.laydate;

        });
    </script>
    <style>
        html, body {
            height: 100%;
        }
    </style>
</head>
<body class="pay">
<div class="member" id="saoma">
    <div class="mui-content" style="text-align: center;">
        <div class="member-part" style="padding-top: 0;">
            <h4 class="mui-text-center member-row">选择支付方式</h4>
            <div class="member-flex member-row member-border-none">
                <div class="member-part-label">
                    <p class="fg-orange">需支付：￥${actualMoney!}</p>
                </div>
            </div>
        </div>
    </div>
    <div class="mui-content">
        <div class="member-part">
        <#if type == "PC">
            <div class="member-flex member-row">
                <div class="member-part-label">
                    <p class="fg-black">
                        <i class="icon icon-weixin"></i>微信支付
                    </p>
                </div>
                <input type="radio" id="wx" name="pay" checked/>
                <div class="member-flex-main">
                    <i class="icon icon-radio"></i>
                </div>
            </div>

            <div class="member-flex member-row">
                <div class="member-part-label">
                    <p class="fg-black">
                        <i class="icon icon-zhifubao"></i>支付宝
                    </p>
                </div>
                <input type="radio" id="zfb" name="pay"/>
                <div class="member-flex-main">
                    <i class="icon icon-radio"></i>
                </div>
            </div>
        <#elseif type == "YDWX">
            <div class="member-flex member-row">
                <div class="member-part-label">
                    <p class="fg-black">
                        <i class="icon icon-weixin"></i>微信支付
                    </p>
                </div>
                <input type="radio" id="wx" name="pay" checked/>
                <div class="member-flex-main">
                    <i class="icon icon-radio"></i>
                </div>
            </div>
        <#elseif type == "YDWAP">
            <div class="member-flex member-row">
                <div class="member-part-label">
                    <p class="fg-black">
                        <i class="icon icon-zhifubao"></i>支付宝
                    </p>
                </div>
                <input type="radio" id="zfb" name="pay" checked/>
                <div class="member-flex-main">
                    <i class="icon icon-radio"></i>
                </div>
            </div>
        </#if>
            <a onclick="submit()" class="member-btn-1">立即支付</a>
        </div>
    </div>
</div>
</body>
<script type="text/javascript" src="${base}/wx/js/jweixin-1.0.0.js"></script>
<script>
    function submit() {
        var type;
    <#if type == "PC">
        var wx = document.getElementById("wx").checked;
        var zfb = document.getElementById("zfb").checked;
        if (wx) {
            type = "PCWX";
        } else if (zfb) {
            type = "PCZFB";
        }
    <#elseif type == "YDWX">
        type = "YDWX";
    <#elseif type == "YDWAP">
        type = "YDZFB";
    </#if>


        $.ajax({
            url: 'payorder',
            type: 'POST',
            async: true,
            data: {
                'moeny': '${moeny!}',
                "id": '${id!}',
                "actualMoney": '${actualMoney!}',
                "type": type
                <#--, "openId": '${openId!}'-->
            },
            success: function (result) {
                if (result.msg == "支付失败") {
                    return alert("系统繁忙,请稍后再试");
                }
                if ("wx" == result.platformType) {
                    if ("zfb" == result.paytype) {
                        $("body").append(result.payUrl);
                    } else if ("wxjsp" == result.paytype) {
                        function onBridgeReady() {
                            WeixinJSBridge.invoke('getBrandWCPayRequest', {
                                        "appId": result.payParams.appId,     //公众号名称，由商户传入
                                        "timeStamp": result.payParams.timeStamp,         //时间戳，自1970年以来的秒数
                                        "nonceStr": result.payParams.nonceStr, //随机串
                                        "package": result.payParams.package,
                                        "signType": result.payParams.signType,         //微信签名方式:
                                        "paySign": result.payParams.paySign //微信签名
                                    },
                                    function (res) {
                                        if (res.err_msg == "get_brand_wcpay_request:ok") {// 使用以上方式判断前端返回,微信团队郑重提示：res.err_msg将在用户支付成功后返回    ok，但并不保证它绝对可靠。
                                            alert("支付成功");
                                            window.location.href = "paySuccess?moneyId=" + result.moneyId;
                                        } else {
                                            alert("取消支付");
                                            window.location.href = "paySuccess?moneyId=" + result.moneyId;
                                        }
                                    }
                            )
                        }

                        if (typeof WeixinJSBridge == "undefined") {
                            if (document.addEventListener) {
                                document.addEventListener('WeixinJSBridgeReady', onBridgeReady, false);
                            } else if (document.attachEvent) {
                                document.attachEvent('WeixinJSBridgeReady', onBridgeReady);
                                document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);
                            }
                        } else {
                            onBridgeReady();
                        }
                    }
                } else if ("pc" == result.platformType) {
                    //pc端
                    document.getElementById("saoma").innerHTML = "<div class='mui-content' style='text-align: center;'>" +
                            "<h4 class='mui-text-center member-row'>扫码支付</h4>" +
                            "<div class='member-flex member-row member-border-none' style='display: inline-block;'>" +
                            "<div class='member-part-label'>" +
                            "<p class='fg-orange'>需支付：￥" + result.money + "</p>" +
                            "<img src='" + result.codeUrl + "' />" +
                            "</div></div></div> ";
                    //循环判断
                    var queryInterVal;
                    queryInterVal = setInterval(function () {
                        queryPayStatus();
                    }, 5000);

                    function queryPayStatus() {
                        $.ajax({
                            url: 'selectPayorderStatus?id=' + result.moneyId,
                            type: 'POST', //GET
                            async: false,    //或false,是否异步
                            //data:reqData,
                            //timeout:5000,    //超时时间
                            dataType: 'json',    //返回的数据格式：json/xml/html/script/jsonp/text
                            success: function (resp) {
                                if (resp == true) {
                                    window.clearInterval(queryInterVal);
                                    alert("充值成功");
                                    window.parent.location.href = 'paySuccess?moneyId=' + result.moneyId;
                                }
                            }
                        })
                    }
                }
            }
        })
    }
</script>
</html>