<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8"/>
    <title>会员积分记录</title>
<#include "/include/common_header_include.ftl">
    <script>
        layui.use(['form', 'table', 'laydate'], function () {
            var table = layui.table;
            var laydate = layui.laydate;
            var form = layui.form;
            laydate.render({
                elem: '#time' //指定元素
                , type: 'datetime'
            });
        });
    </script>
</head>
<body>
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <legend>会员积分记录列表</legend>
</fieldset>
<form class="layui-form" action="memberIntegeralList" method="get">
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">订单号</label>
            <div class="layui-input-inline">
                <input type="text" name="sn" value="${dto.sn!}" autocomplete="off"
                       class="layui-input" placeholder="请输入订单号">
                <input type="hidden" name="memberId" value="${dto.memberId!?c}"/>
            </div>
            <label class="layui-form-label">时间</label>
            <div class="layui-input-inline">
                <input type="text" name="time" id="time" <#--value="${dto.time?datetime!}"--> placeholder="时间查询记录"
                       autocomplete="off" class="layui-input">
            </div>
            <label class="layui-form-label">记录状态</label>
            <div class="layui-input-inline">
                <select name="recordType" lay-filter="aihao" class="layui-input">
                    <option value="">--获取或消费--</option>
                    <option value="true" <#if dto.recordType??&&dto.recordType>selected</#if>>获取积分记录</option>
                    <option value="false" <#if dto.recordType??&&!dto.recordType>selected</#if>>消费积分记录</option>
                </select>
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">同步状态</label>
            <div class="layui-input-inline">
                <select name="synState" lay-filter="aihao" <#--lay-verify="required"-->>
                    <option value="">--同步状态--</option>
                    <option value="true" <#if dto.synState?? && dto.synState>selected</#if>>已同步</option>
                    <option value="false" <#if dto.synState?? && !dto.synState>selected</#if>>未同步</option>
                </select>
            </div>
            <label class="layui-form-label"> </label>
            <div class="layui-input-inline" style="width:300px">
                <button class="layui-btn layui-btn-normal" lay-submit lay-filter="submitQuery">查询</button>
                <button type="reset" onclick="location.href='memberIntegeralList?memberId=${dto.memberId!?c}';"
                        class="layui-btn layui-btn-primary">
                    重置
                </button>
                <button type="reset" onclick="location.href='list';" class="layui-btn layui-btn-primary">返回</button>
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
    <#--<th>-->
    <#--<a href="javascript:;" class="sort" name="name">订单号</a>-->
    <#--</th>-->
        <th>
            订单号
        </th>
        <th>
            记录类型
        </th>
        <th>
            积分
        </th>
        <th>
            积分来源
        </th>
        <th>
            剩余积分
        </th>
        <th>
            交易时间
        </th>
        <th>
            同步状态
        </th>
    </tr>
<#list page.records as dto>
    <tr>
        <td>
            <input type="checkbox" name="ids" value="${dto.id!}"/>
        </td>
        <td>
        <#--<span title="${dto.name!}">${dto.name!}</span>-->
            ${dto.sn!}
        </td>
        <td>
            <#if dto.recordType?? && dto.recordType>
                获得积分
            <#elseif dto.recordType?? && !dto.recordType>
                消费积分
            </#if>
        </td>
        <td>
        ${dto.integeral!}
        </td>
        <td>
        ${dto.sourceType.title!}
        </td>
        <td>
        ${dto.balanceIntegeral!}
        </td>
        <td>
        ${dto.time!?datetime}
        </td>
        <td>
            <#if dto.synState?? && dto.synState == true>
                已同步
            <#else>
                <span style="color: #ff000d;">未同步</span>
            </#if>
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