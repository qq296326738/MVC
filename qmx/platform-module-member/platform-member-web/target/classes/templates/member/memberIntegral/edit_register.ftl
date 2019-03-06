<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8"/>
    <title>多倍积分-充值-新增</title>
<#include "/include/common_header_include.ftl">
    <style type="text/css">

    </style>
    <script>
        layui.use(['form', 'table', 'laydate'], function () {
            var table = layui.table;
            var laydate = layui.laydate;
            var form = layui.form;

            form.on('select(aihao)', function (data) {
                var text = data.elem[data.elem.selectedIndex].text;
                $("#levelName").val(text);
            })
        });

    </script>
    <style type="text/css">
        .layui-form-label {
            width: 100px;
        }

        .layui-unselect layui-form-checkbox layui-form-checked {
            width: 20px;
        }
    </style>
</head>
<body>
<form class="layui-form" id="inputForm" action="../update/register" method="post">
    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
        <legend>基本信息</legend>
    </fieldset>
    <input type="hidden" name="id" value="${data.id!}"/>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">活动开始时间</label>
            <div class="layui-input-inline">
                <input id="startTime" name="startTime" value="${data.startTime?date!}" placeholder="活动开始时间" lay-verify="required"
                       autocomplete="off" class="layui-input">
            </div>
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">活动结束时间</label>
            <div class="layui-input-inline">
                <input id="endTime" name="endTime" value="${data.endTime?date!}" placeholder="活动结束时间" lay-verify="required"
                       autocomplete="off" class="layui-input">
            </div>
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">积分倍数</label>
            <div class="layui-input-inline">
                <input onkeyup="amount1(this)" value="${data.multiple!}" name="multiple" lay-verify="required|number" autocomplete="off"
                       class="layui-input">
            </div>
            <label class="layui-form-label" style=" padding-left: 0px; width: 20px;">倍</label>
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn layui-btn-normal"  lay-submit="" lay-filter="submit">立即提交</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            <input onclick="history.back();" type="button" class="layui-btn layui-btn-primary" value="返回"/>
        </div>
    </div>
</form>
</body>
<script type="text/javascript">
    function amount1(th) {
        var regStrs = [
            ['^0(\\d+)$', '$1'], //禁止录入整数部分两位以上，但首位为0
            ['[^\\d\\.]+$', ''], //禁止录入任何非数字和点
            ['\\.(\\d?)\\.+', '.$1'], //禁止录入两个以上的点
            ['^(\\d+\\.\\d{2}).+', '$1'] //禁止录入小数点后两位以上
        ];
        for (var i = 0; i < regStrs.length; i++) {
            var reg = new RegExp(regStrs[i][0]);
            th.value = th.value.replace(reg, regStrs[i][1]);
        }
    }
</script>
<script>
    $(document).on("click", "#productTable .deleteItem", function () {
        var $this = $(this);
        layer.confirm('确定删除吗？', {
            btn: ['确定', '取消'] //按钮
        }, function (index) {

            $this.closest("tr").remove();
            layer.close(index);
        }, function () {
        });
    });

    $(document).on("click", "#shopTable .deleteItem", function () {
        var $this = $(this);
        layer.confirm('确定删除吗？', {
            btn: ['确定', '取消'] //按钮
        }, function (index) {
            $this.closest("tr").remove();
            layer.close(index);
        }, function () {
        });
    });

</script>
<script>
    layui.use('laydate', function () {
        var laydate = layui.laydate;
        //执行一个laydate实例
        laydate.render({
            elem: '#endTime' //指定元素
            , type: 'date'
        });
        laydate.render({
            elem: '#startTime' //指定元素
            , type: 'date'
        });
    });
</script>
</html>