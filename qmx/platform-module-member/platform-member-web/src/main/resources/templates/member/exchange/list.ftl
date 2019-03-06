<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8"/>
    <title>列表</title>
<#include "/include/common_header_include.ftl">

</head>
<body>
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <legend>会员兑换列表</legend>
</fieldset>
<form class="layui-form" action="list" method="post">
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">商品类别</label>
            <div class="layui-input-inline">
                <select name="exchangeProductType" lay-filter="aihao" <#--lay-verify="required"-->>
                    <option value="">--商品类别--</option>
                <#list productTypes as info>
                    <option value="${info}"
                            <#if dto.exchangeProductType?? && dto.exchangeProductType==info>selected</#if>>${info.title}</option>
                </#list>
                </select>
            </div>

            <label class="layui-form-label">商品名字</label>
            <div class="layui-input-inline">
                <input type="text" name="productName" value="${dto.productName!}" autocomplete="off"
                       class="layui-input" placeholder="请输入商品名字">
            </div>
            <div class="layui-input-inline">
                <select name="deliverType" lay-filter="aihao" <#--lay-verify="required"-->>
                    <option value="">--发货方式--</option>
                <#list deliverTypes as deliver>
                    <option value="${deliver}"
                            <#if dto.deliverType?? && dto.deliverType==deliver>selected</#if>>${deliver.title}</option>
                </#list>
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
            商品类别
        </th>
        <th>
            商品名称
        </th>
        <th>
            兑换积分
        </th>
        <th>
            兑换金额
        </th>
        <th>
            兑换比例
        </th>
        <th>
            发货方式
        </th>
        <th>
            状态
        </th>
        <th>
            过期时间
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
        ${dto.exchangeProductType.title!}
        </td>
        <td>
        ${dto.productName!}
        </td>
        <td>
        ${dto.integeral!}
        </td>

        <th>
            <#if dto.money == 0>
                /
            <#else >
            ${dto.money!}
            </#if>

        </th>
        <th>
            <#if dto.integralProportion?? && dto.money != 0>
                <#if dto.integralProportion != 0>
                    <span style="color: red;font-weight: bold">&nbsp;1&nbsp;:&nbsp;</span>${dto.integralProportion!}
                <#else >
                    /
                </#if>
            <#else >
                /
            </#if>
        </th>

        <td>
        ${dto.deliverType.title!}
        </td>
        <td>
            <#if dto.state ==true>
                正常
            <#elseif dto.state==false>
                <span style="color: red">过期</span>
            </#if>
        </td>
        <td>
        ${dto.expiryTime?datetime!}
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
<script>
    layui.use(['form', 'table', 'laydate'], function () {
        var table = layui.table;
        var laydate = layui.laydate;
        var form = layui.form;
    });
</script>
<script type="text/javascript">
    function del(id) {
        var msg = "确定要删除吗？\n\n请确认！";
        layer.confirm(msg, {title: "会员删除确认"}, function () {
            window.location.href = "delete.jhtml?id=" + id;
        })

    }
</script>
</body>
</html>