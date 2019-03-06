<!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet" href="${base}/resources/assets/bootstrap/css/bootstrap.min.css" >
<!-- 可选的 Bootstrap 主题文件（一般不用引入） -->
<link rel="stylesheet" href="${base}/resources/assets/bootstrap/css/bootstrap-theme.min.css" >
<link rel="stylesheet" href="${base}/resources/assets/layui/css/layui.css">
<script type="text/javascript" src="${base}/resources/common/js/jquery.js"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="${base}/resources/assets/bootstrap/js/bootstrap.min.js" ></script>
<script type="text/javascript" src="${base}/resources/assets/layui/layui.js"></script>
<script type="text/javascript" src="${base}/resources/common/js/common.tools.js"></script>
<script type="text/javascript">
    layui.use('form', function() {
        var form = layui.form;
    <#if noticeMsg??>
        //弹框
        var msg =  "${noticeMsg!}";
        //弹框
        layer.confirm(msg, {
            btn: ['确定'] //按钮
        }, function(index){
            layer.close(index);
        });
    </#if>
    });
</script>