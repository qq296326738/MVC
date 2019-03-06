<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>404页面</title>
    <meta name="keywords" content="">
    <meta name="description" content="">
</head>
<body class="gray-bg">
<div class="middle-box text-center animated fadeInDown">
    <h1><a href="javascript:history.back(-1)">返回上一页</a></h1>
    <div class="error-desc">
        <#if content??>msg:${content!}<#else >msg:出了点小问题~</#if><br/>
        <#if msg??>code:${code!}<#else >code:9999</#if><br/>
        <#if msg??>time:${time!}<#else >time:${.now}</#if><br/>
    </div>
</div>
</body>
</html>
