<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8"/>
    <title>获取openId页面</title>
    <#include "/include/common_header_include.ftl">
    <style type="text/css">
        .layui-form-label2 {
            float: left;
            display: block;
            padding: 9px 0px;
            font-weight: 400;
            line-height: 20px;
            text-align: left;
        }
        .layui-form-item2 {
            clear: both;
        }
    </style>
    <script>
        layui.use(['form'], function () {
            var form = layui.form;
            var pindex = parent.layer.getFrameIndex(window.name); //获取窗口索引
            $("#btnCheckOpenId").click(function () {
                //parent.checkOpenId();
                $.ajax({
                    url:'/common/checkWxOpenId',
                    type:'GET', //GET
                    async:true,
                    //timeout:5000,//超时时间
                    dataType:'text',
                    success:function(resp){
                        if(resp){
                            parent.$('input[name=wxOpenId]').val(resp);
                            //$("input[name=wxOpenId]").val(resp);
                        }
                    },
                    error:function(xhr,textStatus){
                        layer.alert(xhr.responseText, {
                            title: '提示'
                        });
                    }
                });
                parent.layer.close(pindex);
            });
        });
    </script>
</head>
<body>
    <div class="layui-form-item" style="margin-bottom: 1px;margin-top: 10px;" >
        <div class="layui-inline" style="margin-bottom: 0px;margin-left: 20px;">
            注:用手机微信扫描下方二维码，提示成功后点击下方确定关闭该窗口
        </div>
        <div class="layui-inline" style="margin-bottom: 0px;">
            <label class="layui-form-label" style="width:100px;">&nbsp;</label>
            <div class="layui-input-inline">
                <img src="${codeUrl!}" alt="获取openId"/>
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label" style="width:100px;">&nbsp;</label>
        <div class="layui-input-inline" style="padding-left: 40%;">
            <button class="layui-btn layui-btn-normal" id="btnCheckOpenId" >确定</button>
        </div>
    </div>
</body>
</html>