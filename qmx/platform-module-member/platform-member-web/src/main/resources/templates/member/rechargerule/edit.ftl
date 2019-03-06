<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8"/>
    <title>会员规则-编辑</title>
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
                title: '添加会员',
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
<form class="layui-form" id="inputForm" action="update" method="post">
    <input type="hidden" name="id" value="${dto.id!}"/>
    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
        <legend>基本信息</legend>
    </fieldset>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">会员等级</label>
            <div class="layui-input-inline">
                <select id="levelId" name="levelId" lay-filter="aihao" lay-verify="required">
                <#list lList as ls>
                    <option value="${ls.id}" <#if dto.levelId==ls.id>selected</#if>>${ls.name}</option>
                </#list>
                </select>
            </div>
            <input type="hidden" name="levelName" id="levelName" value="${dto.levelName!}"/>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">规则类型</label>
            <div class="layui-input-inline">
                <label><input id="gzlxQJ" type="radio" name="type" value="section" title="区间" <#if dto.type=="section">checked</#if>/></label>
                <label><input id="gzlxGD" type="radio" name="type" value="fixed" title="固定" <#if dto.type=="fixed">checked</#if>/></label>
            </div>
        </div>
    </div>
    <div  id="GDczje" class="layui-form-item" style="<#if dto.type=="section">display: none</#if>">
        <div class="layui-inline">
            <label class="layui-form-label">充值金额</label>
            <div class="layui-input-inline">
                <input onkeyup="amount1(this)" id="amount" name="amount" value="${dto.amount!}" lay-verify="required" autocomplete="off" class="layui-input">
            </div>
        </div>
    </div>
    <div  id="QJczje" class="layui-form-item" style="<#if dto.type=="fixed">display: none</#if>">
        <div class="layui-inline">
            <label class="layui-form-label">最小充值金额</label>
            <div class="layui-input-inline">
                <input onkeyup="amount1(this)" id="minAmount" name="minAmount" value="${dto.minAmount!}" lay-verify="required" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label"style="text-align: left;">最大充值金额</label>
            <div class="layui-input-inline">
                <input onkeyup="amount1(this)" id="maxAmount" name="maxAmount" value="${dto.maxAmount!}" lay-verify="required" autocomplete="off" class="layui-input">
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">赠送类型</label>
            <div class="layui-input-inline">
                <label><input id="zslxJF" type="radio" name="give" value="zengsong" title="赠送" <#if dto.give=="zengsong">checked</#if>/></label>
                <label><input id="zslxJE" type="radio" name="give" value="zhekou" title="折扣" <#if dto.give=="zhekou">checked</#if>/></label>
            </div>
        </div>
    </div>
    <div id="ZSjf" class="layui-form-item" style="<#if dto.give=="zhekou">display: none</#if>">
        <div class="layui-inline">
            <label class="layui-form-label">赠送金额比例<span style="color: red;font-weight: bold">1:</span></label>
            <div class="layui-input-inline">
                <input onkeyup="amount1(this)" id="integralPoint" name="moneyPoint" value="${dto.moneyPoint!}" lay-verify="required" autocomplete="off" class="layui-input">
            </div>
        </div>
    </div>
    <div id="ZSje" class="layui-form-item" style="<#if dto.give=="zengsong">display: none</#if>">
        <div class="layui-inline">
            <label class="layui-form-label">赠送金额折扣<span style="color: red;font-weight: bold">1:</span></label>
            <div class="layui-input-inline">
                <input onkeyup="amount1(this)" id="moneyPoint" name="discountPoint" value="${dto.discountPoint!}" lay-verify="required" autocomplete="off" class="layui-input">
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">赠送积分比例<span style="color: red;font-weight: bold">1:</span></label>
            <div class="layui-input-inline">
                <input onkeyup="amount1(this)"  id="JFBL" placeholder="充100得100积分" value="${dto.integralPoint!}" name="integralPoint" lay-verify="required" autocomplete="off" class="layui-input">
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn layui-btn-normal" onclick="return submitform();">立即提交</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            <input onclick="history.back();" type="button" class="layui-btn layui-btn-primary" value="返回"/>
        </div>
    </div>
</form>
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
<script type="text/javascript">
    $(function(){
        $("#gzlxGD").click(function(){
            $("#gzlxQJ").attr("checked",false);
            $("#GDczje").attr("style","");
            $("#QJczje").attr("style","display:none;");

        });
        $("#gzlxQJ").click(function(){
            $("#GDczje").attr("style","display:none;");
            $("#gzlxGD").attr("checked",false);
            $("#QJczje").attr("style","");
        });
        $("#zslxJF").click(function(){
            $("#ZSjf").attr("style","");
            $("#ZSje").attr("style","display:none;");
            $("#zslxJE").attr("checked",false);
        });
        $("#zslxJE").click(function(){
            $("#ZSjf").attr("style","display:none;");
            $("#zslxJF").attr("checked",false);
            $("#ZSje").attr("style","");
        });
    });
    function submitform(){
        var levelName = $('#levelId option:selected').text();//选中的文本
        var levelId = $('#levelId option:selected').val();//选中的值
        $("#levelName").val(levelName);
        if(levelId == ""){
            layer.msg('请选择会员等级');
            return false;
        }
        if(rechargeType == ""){
            layer.msg('请选择充值类型');
            return false;
        }

        var reg = /^[0-9]+([.]{1}[0-9]{1,2})?$/;
        if($("#gzlxQJ").attr('checked')){
            var minAmount=$("#minAmount").val();
            var maxAmount=$("#maxAmount").val();
            if (!reg.test(minAmount)) {
                layer.msg("请输入正确的最小充值金额");
                return false;
            }
            if (!reg.test(maxAmount)) {
                layer.msg("请输入正确的最大充值金额");
                return false;
            }

            if(minAmount >= maxAmount){
                layer.msg("请输入正确的区间金额");
                return false;
            }

        }
        if($("#gzlxGD").attr('checked')){
            var amount=$("#amount").val();
            if (!reg.test(amount)) {
//                alert(3);
                layer.msg("请输入正确的固定金额");
                return false;
            }
        }
        if($("#zslxJF").attr('checked')){
            var integralPoint=$("#integralPoint").val();
            if (!reg.test(integralPoint)) {
//                alert(4);
                layer.msg("请输入正确的积分");
                return false;
            }
        }
        if($("#zslxJE").attr('checked')){
            var moneyPoint=$("#moneyPoint").val();
            if (!reg.test(moneyPoint)) {
//                alert(5);
                layer.msg("请输入正确的金额");
                return false;
            }
        }

        $("#inputForm").submit();
    }

</script>
</body>
</html>