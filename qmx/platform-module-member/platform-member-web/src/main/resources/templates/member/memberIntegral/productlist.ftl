<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8"/>
    <title></title>
<#include "/include/common_header_include.ftl">
    <script>
        //注意：parent 是 JS 自带的全局对象，可用于操作父页面
        var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
        layui.use(['form', 'table', 'laydate'], function () {
            var table = layui.table;
            var laydate = layui.laydate;
            var form = layui.form;
        });
        //关闭iframe
        $(document).on("click", "#closeIframe", function () {
            parent.layer.close(index);
        });
        //提交iframe
        $(document).on("click", "#submitIframe", function () {
            var $checkedIds = $("input[name='ids']:enabled:checked");
            $checkedIds.each(function () {
                var id = $(this).val();
                var tr = $(this).parents("tr:eq(0)");
                addProductItem({
                    id: id,
                    name: tr.find("td:eq(1)").text()
                });
                parent.layer.close(index);
            });
        });

        function addProductItem(data) {
            var repeat = false;
            parent.$("#productTable input[name=ids]").each(function () {
                var tmp = $(this).val();
                var ids = tmp.split("&");
                if (ids[0].trim() == data.id) {
                    repeat = true;
                    return false;
                }
            });
            if (repeat) {
                return false;
            }
            var $tr = '<tr>' +
                    '<td>' + data.name + '<input type="hidden" name="ids" value="' + data.id +'&'+data.name +'&'+ 'menpiao'+'"></td>' +
                    '<td>' +
                    '<input type="button" class="layui-btn layui-btn-sm layui-btn-normal deleteItem" data-id="' + data.id + '" value="删除"/>' +
                    '</td>' +
                    '</tr>';
            parent.$('#productTable').append($tr);
        }
    </script>
</head>
<body>
<hr/>
<form class="layui-form" action="productlist" method="post">
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">产品名称</label>
            <div class="layui-input-inline">
                <input type="text" name="name" value="${dto.name!}" autocomplete="off"
                       class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <div class="layui-input-inline">
                <button class="layui-btn layui-btn-normal" lay-submit lay-filter="submitQuery">查询</button>
                <button type="reset" onclick="location.href='productlist';" class="layui-btn layui-btn-primary">重置
                </button>
            </div>
        </div>
    </div>
</form>
<table class="layui-table" style="width:98%;margin-left: 10px;margin-top: 10px;margin-right: 10px;" id="listTable"
       lay-size="sm" lay-filter="sysBalanceTableEvent">
    <thead>
    <tr>
        <th class="check">
            <input type="checkbox" id="selectAll"/>
        </th>
        <th>
            名称
        </th>
    </tr>
    <tbody>
    <#list page.records as dto>
    <tr>
        <td>
            <input type="checkbox" name="ids" value="${dto.id}"/>
        </td>
        <td>
        ${dto.name!}
        </td>
    </tr>
    </#list>
    </tbody>
</table>
<#include "/include/my_pagination.ftl">

<div class="layui-form-item">
    <div align="center">
        <div>
            <input id="submitIframe" type="button" class="layui-btn layui-btn-normal" value="确定"/>
            <input id="closeIframe" type="button" class="layui-btn layui-btn-primary" value="取消"/>
        </div>
    </div>
</div>
</body>
</html>