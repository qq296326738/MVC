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
    <legend>会员规则列表</legend>
</fieldset>
<form class="layui-form" action="list" method="post">
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">等级名称</label>
            <#--<div class="layui-input-inline">-->
                <#--<input type="text" name="levelName" value="${levelName!}" autocomplete="off"-->
                       <#--class="layui-input" placeholder="请输入等级名称">-->
            <#--</div>-->

            <div class="layui-input-inline">
                <select name="levelId" lay-filter="aihao" <#--lay-verify="required"-->>
                    <option value="">--请选择等级--</option>
                <#if levelAll??>
                    <#list levelAll as level>
                        <option value="${level.id!}"
                                <#if dto.levelId?? && dto.levelId==level.id>selected</#if>>${level.name!}</option>
                    </#list>
                </#if>
                </select>
            </div>

        </div>
        <div class="layui-inline">
            <div class="layui-input-inline">
                <button class="layui-btn layui-btn-normal" lay-submit lay-filter="submitQuery">查询</button>
                <button type="reset" onclick="location.href='list';" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>
    </div>
</form>
<div class="layui-form-item">
    &nbsp;
    <div class="layui-inline">
        <div class="layui-input-inline">
            <button onclick="location.href='add';" class="layui-btn layui-btn-normal">新增</button>
        </div>
    </div>
</div>
<table class="layui-table" style="width:98%;margin-left: 10px;margin-top: 10px;margin-right: 10px;" id="sysBalanceTable"
       lay-size="sm" lay-filter="sysBalanceTableEvent">
    <tr>
        <th class="check">
            <input type="checkbox" id="selectAll"/>
        </th>
        <th>
            <a href="javascript:;" class="sort" name="name">会员等级(ID)</a>
        </th>
        <th>
            规则类型
        </th>
        <th>
            固定充值金额
        </th>
        <th>
            最小充值金额
        </th>
        <th>
            最大充值金额(不包含)
        </th>
        <th>
            赠送类型
        </th>
        <th>
            金额比例
        </th>
        <th>
            赠送积分比例
        </th>
        <th>
            操作
        </th>
    </tr>
<#list page.records as dto>
    <tr>
        <td>
            <input type="checkbox" name="ids" value="${dto.id}"/>
        </td>
        <td>
        <#--<#list lList as l >-->
                <#--<#if l.id==dto.levelId>-->
                    <#--<span title="${l.name}">${l.name}</span>&lt;#&ndash;(${dto.levelId})&ndash;&gt;-->
                <#--</#if>-->
            <#--</#list>-->
        ${dto.levelName!}
        </td>
        <td>
            <#if dto.type=="section">区间<#elseif dto.type=="fixed">固定</#if>
        </td>
        <td>
            <#if !dto.amount??>
                /
            <#else >
            ${dto.amount!}
            </#if>
        </td>
        <td>
            <#if !dto.minAmount??>
                /
            <#else >
            ${dto.minAmount!}
            </#if>
        </td>
        <td>
            <#if !dto.maxAmount??>
                /
            <#else >
            ${dto.maxAmount!}
            </#if>
        </td>
        <td>
            <#if dto.give=="zengsong">赠送<#elseif dto.give=="zhekou">折扣</#if>
        </td>
        <td>
            <#if dto.give=="zengsong">
                <span style="color: red;<#--font-weight: bold"-->">1 : </span> ${dto.moneyPoint!}
            <#elseif dto.give=="zhekou">
                <#if dto.discountPoint??>
                    <span style="color: red;<#--font-weight: bold"-->">${dto.discountPoint!} </span>折
                </#if>
            </#if>
        </td>
        <td>
            <span style="color: red;<#--font-weight: bold"-->">1 : </span> ${dto.integralPoint!}
        </td>
        <td>
            <input type="button" onclick="location.href='edit?id=${dto.id!?c}';"
                   class="layui-btn layui-btn-normal layui-btn-sm" data-id="${dto.id!?c}" id="viewBtn"
                   value="编辑"/>
            <input type="button" onclick="del('${dto.id?c}')" class="layui-btn layui-btn-normal layui-btn-sm"
                   data-id="${dto.id!}" id="viewBtn"
                   value="删除"/>
        </td>
    </tr>
</#list>
</table>
<#include "/include/my_pagination.ftl">
</form>

<script type="text/javascript">
    function del(id) {
        var msg = "确定要删除吗？\n\n请确认！";
        layer.confirm(msg, {title: "会员删除确认"}, function () {
            window.location.href = "delete?id=" + id;
        })

    }
</script>
</body>
</html>