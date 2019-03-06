<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport"
          content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no"/>
    <title></title>
    <script src="${base}/member/js/mui.min.js"></script>
    <link href="${base}/member/css/mui.css" rel="stylesheet"/>
    <link rel="stylesheet" type="text/css" href="${base}/member/css/mui.picker.css"/>
    <link rel="stylesheet" type="text/css" href="${base}/member/css/mui.poppicker.css"/>
    <link rel="stylesheet" type="text/css" href="${base}/member/css/member.css"/>
    <script type="text/javascript" charset="utf-8">
        mui.init();
    </script>

</head>
<body class="exchange">
<div class="member">
    <div class="exchange-header">
        <h1>${integral!}</h1>
        <h3>当前积分</h3>
        <div class="member-table">
            <a href="integralList?id=${id!}" class="member-table-cell">积分明细</a>
            <a href="exchangeList?id=${id!}" class="member-table-cell">兑换记录</a>
        </div>
        <p><span id="ttt"></span>将有${integral!}积分过期，请尽快兑换</p>
    </div>
    <script>
        var s = new Date().getFullYear() + "年12月31号后";
        document.getElementById("ttt").innerText = s;
    </script>

    <div class="mui-content">
        <div class="member-part">
            <div id="slider" class="mui-slider">
                <div class="mui-slider-group mui-slider-loop">
                    <!-- 额外增加的一个节点(循环轮播：第一个节点是最后一张轮播) -->
                    <div class="mui-slider-item mui-slider-item-duplicate">
                        <a href="#">
                            <img src="${base}/member/images/echange-slider-img-1.jpg">
                        </a>
                    </div>
                    <!-- 第一张 -->
                    <div class="mui-slider-item">
                        <a href="#">
                            <img src="${base}/member/images/echange-slider-img-1.jpg">
                        </a>
                    </div>
                    <!-- 第二张 -->
                    <div class="mui-slider-item">
                        <a href="#">
                            <img src="${base}/member/images/echange-slider-img-1.jpg">
                        </a>
                    </div>
                    <!-- 第三张 -->
                    <div class="mui-slider-item">
                        <a href="#">
                            <img src="${base}/member/images/echange-slider-img-1.jpg">
                        </a>
                    </div>
                    <!-- 第四张 -->
                    <div class="mui-slider-item">
                        <a href="#">
                            <img src="${base}/member/images/echange-slider-img-1.jpg">
                        </a>
                    </div>
                    <!-- 额外增加的一个节点(循环轮播：最后一个节点是第一张轮播) -->
                    <div class="mui-slider-item mui-slider-item-duplicate">
                        <a href="#">
                            <img src="${base}/member/images/echange-slider-img-1.jpg">
                        </a>
                    </div>
                </div>
                <div class="mui-slider-indicator">
                    <div class="mui-indicator mui-active"></div>
                    <div class="mui-indicator"></div>
                    <div class="mui-indicator"></div>
                    <div class="mui-indicator"></div>
                </div>
            </div>
        </div>
        <div class="member-part">
            <div id="fl" class="member-picker-1">商品分类</div>
            <div id='userResult' class="ui-alert"></div>
        </div>

        <div class="member-part">
            <div class="member-shop-list">
                <div id="box" class="member-shop-box">
                <#--循环开始-->
                <div id="flex" class="member-flex">
                <#list list as info>
                    <div class="member-flex-item">
                        <div class="member-shop-obj">
                            <div class="member-shop-img">
                                <img src="${info.image!}"/>
                            </div>
                            <h4>${info.productName!}</h4>
                            <h3>${info.integeral!}积分<#if info.money gt 0>+${info.money!}元</#if></h3>
                            <a href="exchangeAddress?id=${info.id!}&userId=${id!}" class="member-btn-4">
                                立即兑换
                            </a>
                        </div>
                    </div>
                    <#if (info_index+1) % 3 == 0>
                    </div>
                    <div class="member-flex">
                    </#if>
                </#list>
                </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="${base}/member/js/mui.picker.js"></script>
<script type="text/javascript" src="${base}/member/js/mui.poppicker.js"></script>
<script>
    //轮播
    mui("#slider").slider({
        interval: 5000
    });

    //选择器
    (function ($) {
        $.ready(function () {
            var userPicker = new $.PopPicker();
            userPicker.setData([
                {
                    value: '',
                    text: '全部商品'
                }
            <#list type as info>
                , {
                value: '${info}',
                text: '${info.title}'
            }
            </#list>
            ]);

            var showBtn = document.getElementById('fl');

            showBtn.addEventListener('tap', function (event) {
                //选择分类
                userPicker.show(function (items) {
                    var value = items[0].value;
                    showBtn.innerText = items[0].text;
                    $.post(
                            "findExchangeList",
                            {
                                type: value,
                                userId: '${id!}'
                            },
                            function (data) {
                                var val = '<div class="member-flex">';
                                $.each(data, function (index, value) {
                                    val += ' <div class="member-flex-item">' +
                                            '<div class="member-shop-obj">' +
                                            '<div class="member-shop-img">' +
                                            '<img src=' + value.image + '/>' +
                                            '</div>' +
                                            "<h4>" + value.productName + "</h4>" +
                                            "<h3>" + value.integeral + "积分";
                                    if (value.money > 0) {
                                        val += "+"+value.money + "元";
                                    }
                                    val += "</h3>" +
                                            '<a href="exchangeAddress?id=' + value.id + '&userId=${id!}"' + 'class="member-btn-4">立即兑换</a>' +
                                            '</div>' +
                                            '</div>';
                                    if ((index + 1) % 3 == 0) {
                                        val += '</div><div class="member-flex">'
                                    }
                                });
                                val += '</div>';
                                document.getElementById("box").innerHTML = "";
                                document.getElementById("box").innerHTML = val;
//                                $("#box").append(val);
                            },
                            "json"
                    )
                });
            }, false);
        });
    })(mui);
</script>
</body>
</html>