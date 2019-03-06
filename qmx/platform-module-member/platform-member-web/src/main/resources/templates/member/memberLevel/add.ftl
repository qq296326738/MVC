<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8"/>
    <title>会员等级-新增</title>
<#include "/include/common_header_include.ftl">
    <script>
        layui.use(['form', 'table', 'laydate'], function () {
            var table = layui.table;
            var laydate = layui.laydate;
            var form = layui.form;
        });
        //添加会员
        $(document).on("click", "#addProduct", function () {
            var index = layer.open({
                type: 2,
                title: '添加会员等级',
                area: ['60%', '80%'], //宽高
                fix: true, //固定
                content: 'getProducts'
            });
        });
    </script>
    <style type="text/css">
        .layui-form-label {
            width: 100px;
        }
    </style>
</head>
<body>
<form class="layui-form" id="inputForm" action="save" method="post">
    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
        <legend>基本信息</legend>
    </fieldset>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">名称</label>
            <div class="layui-input-inline">
                <input name="name" lay-verify="required" autocomplete="off" class="layui-input">
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">是否可升级</label>
            <div class="layui-input-inline">
                <label><input id="sheng" name="levelLock" value="true" title="是" type="radio" checked></label>
                <label><input id="jiang" name="levelLock" value="false" title="否" type="radio"></label>
            </div>
        </div>
    </div>
    <div id="jifen" class="layui-form-item" style="">
        <div class="layui-inline">
            <label class="layui-form-label">升级所需积分</label>
            <div class="layui-input-inline">
                <input id="integral" name="integral" lay-verify="required|number" autocomplete="off" class="layui-input">
            </div>
        </div>
    </div>
    <div id="shengji" class="layui-form-item" style="">
        <div class="layui-inline">
            <label class="layui-form-label">升级等级</label>
            <div class="layui-input-inline">
                <select id="upgrade" name="upgradeId" lay-filter="aihao">
                    <option value="">请选择</option>
                <#list lList as ls>
                    <option value="${ls.id}">${ls.name}</option>
                </#list>
                </select>
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn layui-btn-normal" lay-submit="" lay-filter="submit">立即提交</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            <input onclick="history.back();" type="button" class="layui-btn layui-btn-primary" value="返回"/>
        </div>
    </div>
</form>
<script type="text/javascript">
    $("#sheng").click(function () {
        $("#jiang").attr("checked", false);
        $("#shengji").attr("style", "");
        $("#jifen").attr("style", "");
    });
    $("#jiang").click(function () {
        $("#sheng").attr("checked", false);
        $("#shengji").attr("style", "display:none;");
        $("#jifen").attr("style", "display:none;");
        $("#integral").removeAttr("lay-verify");
    });
</script>
</body>
</html>