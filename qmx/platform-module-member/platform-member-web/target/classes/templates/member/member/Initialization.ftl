<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8"/>
    <title></title>
<#include "/include/common_header_include.ftl">
    <script>
        layui.use(['form', 'table', 'laydate'], function () {
            var table = layui.table;
            var laydate = layui.laydate;
            var form = layui.form;
        });
    </script>
    <script>
        //注意：parent 是 JS 自带的全局对象，可用于操作父页面
        var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
        //关闭iframe
        $(document).on("click", "#closeIframe", function () {
            parent.layer.close(index);
        });

        function jiaoyan(integral) {
            if (integral != "") {
                if (!(/^(\+|-)?\d+$/.test(integral)) || integral < 0) {
                    return false;
                }
            }
            return true;
        }

        //提交iframe
        $(document).on("click", "#submitIframe", function () {
            var integral = $("#integral").val();
            var mobile = $("#mobile").val();
            var dailyIntegral = $("#dailyIntegral").val();
            var daily = $("#daily").val();
            var rewardIntegral = $("#rewardIntegral").val();
            var codeIntegral = $("#codeIntegral").val();
            var codeMoney = $("#codeMoney").val();
            if (!jiaoyan(integral) || !jiaoyan(codeIntegral) || !jiaoyan(dailyIntegral) || !jiaoyan(daily) || !jiaoyan(rewardIntegral)) {
                layer.msg('积分请输入正整数！');
                return false;
            }
            if (codeMoney != "" && !/(^[1-9]([0-9]+)?(\.[0-9]{1,2})?$)|(^(0){1}$)|(^[0-9]\.[0-9]([0-9])?$)/.test(codeMoney)){
                layer.msg('请输入正确的金额!');
                return false;
            }
            if (mobile != "") {
                if (!/^\d{6,19}/.test(mobile)) {
                    layer.msg('请输入正确的电话号码！');
                    return false;
                }
            }

            $.post(
                    "addInitialization",
                    {
                        integral: integral,
                        mobile: mobile,
                        dailyIntegral: dailyIntegral,
                        daily: daily,
                        rewardIntegral: rewardIntegral,
                        codeIntegral: codeIntegral,
                        codeMoney: codeMoney
                    },
                    function (data) {
                        if (data == "true") {
                            layer.msg('修改成功！');
                            parent.layer.close(index);
                        }
                    },
                    "json"
            );
            return true;
        });
    </script>
</head>
<body>
<hr/>
<div class="layui-form-item">
    <label class="layui-form-label" style="width: 120px">会员注册赠送积分</label>
    <div class="layui-input-inline">
        <input id="integral" type="text" name="integral" value="${dto.integral!}" autocomplete="off"
               class="layui-input" placeholder="请输入赠送积分">
    </div>
<#--&nbsp;&nbsp;客服电话设置-->
    <label class="layui-form-label" style="width: 120px">客服电话设置</label>
    <div class="layui-input-inline">
        <input id="mobile" type="text" name="mobile" value="${dto.mobile!}" autocomplete="off"
               class="layui-input" placeholder="请输入客服电话">
    </div>
</div>
<div class="layui-form-item">

    <label class="layui-form-label" style="width: 120px">会员每日签到积分</label>
    <div class="layui-input-inline" style="width: 100px">
        <input id="dailyIntegral" type="text" name="dailyIntegral" value="${dto.dailyIntegral!}" autocomplete="off"
               class="layui-input" placeholder="签到积分">
    </div>
    <label class="layui-form-label" style="width: 60px">连续签到</label>
    <div class="layui-input-inline" style="width: 80px">
        <input id="daily" type="text" name="daily" value="${dto.daily!}" autocomplete="off"
               class="layui-input" placeholder="天数">
    </div>
    <label class="layui-form-label" style="width: 120px">签到将获得积分</label>
    <div class="layui-input-inline" style="width: 100px">
        <input id="rewardIntegral" type="text" name="rewardIntegral" value="${dto.rewardIntegral!}" autocomplete="off"
               class="layui-input" placeholder="连续积分">
    </div>
</div>
<div class="layui-form-item">
    <label class="layui-form-label" style="width: 120px">分享二维码积分</label>
    <div class="layui-input-inline" style="width: 105px">
        <input id="codeIntegral" type="text" name="codeIntegral" value="${dto.codeIntegral!}" autocomplete="off"
               class="layui-input" placeholder="分享获得积分">
    </div>
    <label class="layui-form-label" style="width: 120px">分享二维码金额</label>
    <div class="layui-input-inline" style="width: 105px">
        <input id="codeMoney" type="text" name="codeMoney" value="${dto.codeMoney!}" autocomplete="off"
               class="layui-input" placeholder="分享获得金额">
    </div>
</div>
<div class="layui-form-item">
    <div align="center">
        <div style="padding-top: 10px">
            <input id="submitIframe" type="button" class="layui-btn layui-btn-normal" value="确定"/>
            <input id="closeIframe" type="button" class="layui-btn layui-btn-primary" value="取消"/>
        </div>
    </div>
</div>
</body>
</html>