<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8"/>
    <title>列表</title>
<#include "/include/common_header_include.ftl">
    <script>
        layui.use(['form', 'table', 'laydate'], function () {
            var table = layui.table;
            var laydate = layui.laydate;
            var form = layui.form;
        });
    </script>
</head>
<body>
<fieldset class="layui-elem-field layui-field-title">
    <legend>会员积分管理</legend>
</fieldset>

<div class="layui-tab layui-tab-brief">
    <ul class="layui-tab-title">
        <li <#if type=="recharge">class="layui-this"</#if>><a href="recharge">充值多倍积分</a></li>
        <li <#if type=="consumption">class="layui-this"</#if>><a href="consumption">消费多倍积分</a></li>
        <li <#if type=="register">class="layui-this"</#if>><a href="register">注册多倍积分</a></li>
    </ul>
</div>

<form class="layui-form" action="register" method="post">
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">活动内时间</label>
            <div class="layui-input-inline">
                <input id="endTime" name="endTime"
                       <#if vo.endTime??>value="${vo.endTime?date!}" </#if>placeholder="选择时间为活动中的时间" <#--lay-verify="required"-->
                       autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <div class="layui-input-inline" style=" width: 251px;">
                <button class="layui-btn layui-btn-normal" lay-submit lay-filter="submitQuery">查询</button>
                <button class="layui-btn layui-btn-primary" type="reset" onclick="location.href='register'">重置</button>
                &nbsp;&nbsp;&nbsp;&nbsp;
                <button class="layui-btn layui-btn-normal" type="button" onclick="location.href='../add/register';">新增
                </button>
            </div>
        </div>
    </div>
</form>

<table class="layui-table" style="width:98%;margin-left: 10px;margin-top: 10px;margin-right: 10px;" id="sysBalanceTable"
       lay-size="sm" lay-filter="sysBalanceTableEvent">
    <tr>
        <th class="check">
            <input type="checkbox" id="selectAll"/>
        </th>
        <th>
            类型
        </th>
        <th>
            积分倍数
        </th>
        <th>
            开始时间
        </th>
        <th>
            结束时间
        </th>
        <th>
            操作
        </th>
    </tr>
<#if page.records??>
    <#list page.records as dto>
        <tr>
            <td>
                <input type="checkbox" name="ids" value="${dto.id!}"/>
            </td>
            <td>
            ${dto.type.title!}
            </td>
            <td style="color: red">
            ${dto.multiple!}倍
            </td>
            <td>
            ${dto.startTime!?date}
            </td>
            <td>
            ${dto.endTime!?date}
            </td>

            <td>
                <input type="button" onclick="location.href='../edit/register?id=${dto.id!?c}';"
                       class="layui-btn layui-btn-normal layui-btn-sm" data-id="${dto.id!?c}" id="viewBtn"
                       value="编辑"/>
                <input type="button" onclick="del('${dto.id?c}')" class="layui-btn layui-btn-normal layui-btn-sm"
                       data-id="${dto.id!}" id="viewBtn"
                       value="删除"/>
            </td>
        </tr>
    </#list>
</#if>
</table>
<#include "/include/my_pagination.ftl">
<script type="text/javascript">
    function del(id) {
        var msg = "确定要删除吗？\n\n请确认！";
        layer.confirm(msg, {title: "注册多倍积分删除确认"}, function () {
            window.location.href = "../delete/register?id=" + id;
        })
    }

</script>
</form>
<script>
    layui.use('laydate', function () {
        var laydate = layui.laydate;

        //执行一个laydate实例
        laydate.render({
            elem: '#endTime' //指定元素
            , type: 'date'
        });
    });
</script>
</body>
</html>