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
    <legend>会员兑换记录列表</legend>
</fieldset>
<form class="layui-form" action="exchangeOrderList" method="get">
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">订单号</label>
            <div class="layui-input-inline">
                <input type="text" name="sn" value="${dto.sn!}" autocomplete="off"
                       class="layui-input" placeholder="请输入订单号">
            <#--<#if dto.memberId??>-->
            <#--<input type="hidden" name="memberId" value="${dto.memberId!?c}"/>-->
            <#--</#if>-->
            <#--<#if dto.supplierId??>-->
            <#--<input type="hidden" name="supplierId" value="${dto.supplierId!?c}"/>-->
            <#--</#if>-->
            </div>
            <label class="layui-form-label">手机号</label>
            <div class="layui-input-inline">
                <input type="text" value="${dto.mobile!}" name="mobile"
                       placeholder="请输入手机号"
                       autocomplete="off" class="layui-input">
            </div>
            <label class="layui-form-label">兑换状态</label>
            <div class="layui-input-inline">
                <select name="stateType" lay-filter="aihao" class="layui-input">
                    <option value="">--兑换状态--</option>
                <#list stateType as type>
                    <option value="${type}"
                            <#if dto.stateType?? && dto.stateType == type >selected</#if>>  ${type.title}
                    </option>
                </#list>
                </select>
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label"> </label>
            <div class="layui-input-inline" style="width:300px">
                <button class="layui-btn layui-btn-normal" lay-submit lay-filter="submitQuery">查询</button>
                <button type="reset" class="layui-btn layui-btn-primary"
                        onclick="location.href='exchangeOrderList'">
                    重置
                </button>

            <#--<button type="reset" onclick="location.href='list';" class="layui-btn layui-btn-primary">返回</button>-->
            </div>
        </div>
    </div>
</form>
<table class="layui-table" style="width:90%;margin-left: 50px;margin-top: 10px;margin-right: 10px;" id="sysBalanceTable"
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
            商品名称
        </th>
        <th>
            收货人
        </th>
        <th>
            手机号
        </th>
        <th>
            详细地址
        </th>
        <th>
            兑换方式
        </th>
        <th>
            兑换时间
        </th>
        <th>
            兑换状态
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
        ${dto.sn!}
        </td>
        <td>
        ${dto.productName!}
        </td>
        <td>
        ${dto.name!}
        </td>
        <td>
        ${dto.mobile!}
        </td>
        <td>
        ${dto.address!}
        </td>
        <td>
        ${dto.deliverType.title!}
        </td>
        <td>
        ${dto.time!?datetime}
        </td>
        <td>
        ${dto.stateType.title!}
        </td>
        <td>
            <#if dto.stateType == "YLQ">
                <input type="button" class="layui-btn layui-btn-normal layui-btn-sm"
                       value="完成订单"/>
            <#elseif dto.stateType == "YTZFH">
                <input type="button" onclick="del('${dto.id?c}','YTZFH')"
                       class="layui-btn layui-btn-danger layui-btn-sm "
                       data-id="${dto.id!}" id="viewBtn"
                       value="发货"/>
            <#elseif dto.stateType == "YFDHM" >
                <input type="button" onclick="del('${dto.id?c}','YFDHM')"
                       class="layui-btn layui-btn-danger layui-btn-sm "
                       data-id="${dto.id!}" id="viewBtn"
                       value="确认订单"/>
            <#elseif dto.stateType == "YFH" >
                <input type="button" onclick="del('${dto.id?c}','YFH')"
                       class="layui-btn layui-btn-danger layui-btn-sm "
                       data-id="${dto.id!}" id="viewBtn"
                       value="确认订单"/>
            </#if>
        </td>
    </tr>
</#list>
</table>
<#include "/include/my_pagination.ftl">
</form>

<script type="text/javascript">
    function del(id, stateType) {
        if (stateType == "YFDHM") {
            layer.open({
                type: 1,
                anim: 2,
                area: ['30%', '30%'],
                shadeClose: true, //开启遮罩关闭
                content: '<hr/><label class="layui-form-label" style="width: 120px">请输入兑换码</label>\n' +
                '<div class="layui-input-inline">\n' +
                '    <input id="code" type="text" name="code" value="" autocomplete="off"\n' +
                '           class="layui-input" placeholder="请输入兑换码">\n' +
                '</div>',
                btn: ['保存', '取消'],
                btn1: function (index, layero) {
                    var val = $("#code").val();
                    if (val == '' || val.length != 6 ) {
                        layer.msg('兑换码长度不正确!', {
                            time: 1000
                        });
                        return false;
                    }
                    $.post(
                            "selectRedeemCode",
                            {
                                id: id,
                                code: val
                            },
                            function (data) {
                                if (data == true) {
                                    alert("兑换成功");
                                    window.location.href = "exchangeOrderState?id=" + id + "&stateType=" + stateType;
                                } else {
                                    layer.msg('兑换码错误,请重新输入', {
                                        time: 1000
                                    });
                                }
                            },
                            "json"
                    );
                },
                btn2: function (index, layero) {
                    layer.close(index);
                }
            })
        } else {
            var msg = "确定要提交订单吗？\n\n请确认！";
            layer.confirm(msg, {title: "订单提交"}, function () {
                window.location.href = "exchangeOrderState?id=" + id + "&stateType=" + stateType;
            })
        }
    }
</script>
</body>
</html>