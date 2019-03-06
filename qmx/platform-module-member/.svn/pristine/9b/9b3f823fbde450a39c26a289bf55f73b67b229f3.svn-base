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
    <h4 class="member-title">消费记录</h4>
    <div class="mui-content">
    <#list list?keys as key>
        <div class="member-part">
            <div class="member-flex member-part-title">${key!}</div>
            <#list list[key] as val>
                <a href="consumeDetails?id=${val.id!}&url=consumeDetails">
                    <div class="member-flex member-row">
                        <div class="member-flex-main">
                            <h4>${val.productName!}</h4>
                            <p class="fg-gray">${val.time?datetime!}</p>
                        </div>
                        <div>
                            <h5>-${val.actualMoney!}</h5>
                        </div>
                    </div>
                </a>
            </#list>
        </div>
    </#list>
    </div>
</body>
</html>