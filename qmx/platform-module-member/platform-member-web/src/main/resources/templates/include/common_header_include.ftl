<link rel="stylesheet" href="${base}/resources/assets/layui/css/layui.css">
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="${base}/resources/common/js/jquery.1.12.4.min.js"></script>
<script type="text/javascript" src="${base}/resources/assets/layui/layui.js"></script>
<script type="text/javascript" src="${base}/resources/common/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/resources/common/js/common.tools.js"></script>
<script type="text/javascript" src="${base}/resources/common/js/input-new.js"></script>
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

    <#if sysMessage??>
        //公告层
        layer.open({
            type: 1
            ,title: false //不显示标题栏
            ,closeBtn: false
            ,area: '800px;'
            ,shade: 0.8
            ,id: 'LAY_layuipro' //设定一个id，防止重复弹出
            ,btn: ['确认已知晓']
            ,btnAlign: 'c'
            ,moveType: 1 //拖拽模式，0或者1
            ,content: '<div style="padding: 50px; line-height: 22px; background-color: #393D49; color: #fff; font-weight: 300;">${sysMessage.title!}<br>${sysMessage.content!}</div>'
            ,success: function(layero){
                var btn = layero.find('.layui-layer-btn');
                var btnOk = btn.find('.layui-layer-btn0');
                $(document).on("click",btnOk,function () {
                    $.ajax({
                        url:'/sysMessage/readMessage?id=${sysMessage.id}',
                        type:'POST', //GET
                        dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
                        success:function(resp){
                            console.info(resp);
                        }
                    });
                })
            }
        });
    </#if>
    });
</script>