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
<body class="details">
<div class="member">
    <h4 class="member-title">积分详情</h4>
    <div class="mui-content">
        <div class="member-part">
            <div class="member-flex member-row">
                <div class="member-part-label">
                    <p>交易类型：</p>
                </div>
                <div class="member-flex-main">
                    <p>积分兑换</p>
                </div>
            </div>
            <div class="member-flex member-row">
                <div class="member-part-label">
                    <p>交易内容：</p>
                </div>
                <div class="member-flex-main">
                    <p>兑换：${data.snText!}</p>
                <#if data.remark?? && data.remark !=''>
                    <p>兑换码：${data.remark!}</p>
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
                    <p>交易单号：</p>
                </div>
                <div class="member-flex-main">
                    <p>${data.sn!}</p>
                </div>
            </div>
            <div class="member-flex member-row member-border-none">
                <div class="member-part-label">
                    <p class="fg-black">兑换积分：</p>
                </div>
                <div class="member-flex-main">
                    <p class="fg-black">${data.integeral!}积分</p>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>