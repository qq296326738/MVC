<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
    <title>提示</title>
    <link rel="stylesheet" href="${base}/resources/assets/layui/css/layui.css">
    <script type="text/javascript" src="${base}/resources/assets/layui/layui.js"></script>
    <script>
        layui.use(['layer', 'form'], function(){
            var msg =  "${content!}";
            layer.confirm(msg, {
                btn: ['确定'] //按钮
            }, function(){
                window.history.back();
                return;
            });
        });
    </script>
</head>
<body>
<h1 align="center">${content!}</h1>
</body>
</html>