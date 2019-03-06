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
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <legend>记录列表</legend>
</fieldset>
<form class="layui-form" action="list" method="post">
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">会员名</label>
            <div class="layui-input-inline">
                <input type="text" name="name" value="${dto.name!}" autocomplete="off"
                       class="layui-input" placeholder="请输入会员名">
            </div>
            <label class="layui-form-label">会员卡号</label>
            <div class="layui-input-inline">
                <input type="text" name="cardNo" value="${dto.cardNo!}" autocomplete="off"
                       class="layui-input" placeholder="请输入会员卡号">
            </div>
            <label class="layui-form-label">会员手机号</label>
            <div class="layui-input-inline">
                <input type="text" name="mobile" value="${dto.mobile!}" autocomplete="off"
                       class="layui-input" placeholder="请输入会员手机号">
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label"> </label>
            <div class="layui-input-inline" style="width: 150px;">
                <button class="layui-btn layui-btn-normal" lay-submit lay-filter="submitQuery">查询</button>

                <button type="reset" onclick="location.href='list';" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>
    </div>
</form>
<table class="layui-table" style="width:80%;margin-left: 50px;margin-top: 10px;margin-right: 10px;" id="sysBalanceTable"
       lay-size="sm" lay-filter="sysBalanceTableEvent">
    <tr>
        <th class="check" style="width: 30px;">
            <input type="checkbox" id="selectAll"/>
        </th>
        <th>
            会员名
        </th>
        <th>
            会员卡号
        </th>
        <th>
            手机号
        </th>
        <th>
            操作
        </th>

    </tr>
<#list page.records as dto>
    <tr>
        <td>
            <input type="checkbox" name="ids" value="${dto.id!}"/>
        </td>
        <td>
            <span title="${dto.name!}">${dto.name!"未命名"}</span>
        </td>
        <td>
            <span title="${dto.cardNo!}">${dto.cardNo!"未设置卡号"}</span>
        </td>
        <td>
        ${dto.mobile!}
        </td>
        <td>
            <input type="button" onclick="location.href='memberMoneyList?memberId=${dto.id!?c}';"
                   class="layui-btn layui-btn-normal layui-btn-sm" data-id="${dto.id!?c}" id="viewBtn"
                   value="金额记录"/>
            <input type="button" onclick="location.href='memberIntegeralList?memberId=${dto.id!?c}';"
                   class="layui-btn layui-btn-normal layui-btn-sm" data-id="${dto.id!}" id="viewBtn"
                   value="积分记录"/>
            <#--<input type="button" onclick="location.href='exchangeOrderList?memberId=${dto.id!?c}';"-->
                   <#--class="layui-btn layui-btn-normal layui-btn-sm" data-id="${dto.id!}" id="viewBtn"-->
                   <#--value="兑换记录"/>-->
        </td>
    </tr>
</#list>
</table>
<#include "/include/my_pagination.ftl">
</form>

<script type="text/javascript">
    function del(id) {
        var msg = "确定要删除吗？\n\n请确认！";
        if (confirm(msg) == true) {
            window.location.href = "delete.jhtml?id=" + id;
        } else {
            return false;
        }
    }
</script>
</body>
</html>