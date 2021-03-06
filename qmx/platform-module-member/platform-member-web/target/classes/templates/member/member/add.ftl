<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8"/>
    <title>会员-新增</title>
    <#include "/include/common_header_include.ftl">
    <#--<script type="text/javascript" src="${base}/resources/common/js/area.js"></script>-->
    <script type="text/javascript" src="${base}/resources/common/js/area-data.js"></script>
    <script type="text/javascript" src="${base}/resources/common/js/picker.js"></script>
    <script>
        //图片上传
        layui.use('upload', function () {
            var $ = layui.jquery
                    , upload = layui.upload;

            //普通图片上传
            var uploadInst = upload.render({
                elem: '#test1'
                , url: '/file/upload2?fileType=image'
                , before: function (obj) {
                    //预读本地文件示例，不支持ie8
                    obj.preview(function (index, file, result) {
                        $('#demo1').attr('src', result); //图片链接（base64）
                        $('#demo1').show();
                    });
                }
                , accept: "images"
                , exts: 'jpg|png'
                , size: 1000
                , acceptMime: 'image/jpg, image/png'
                , done: function (res) {
                    //如果上传失败
                    if (res.code > 0) {
                        return layer.msg('上传失败');
                    }
                    //上传成功
                    $("#fileImage").val(res.url);
                    return layer.msg('上传成功');
                }
                , error: function () {
                    //演示失败状态，并实现重传
                    var demoText = $('#demoText');
                    demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-mini demo-reload">重试</a>');
                    demoText.find('.demo-reload').on('click', function () {
                        uploadInst.upload();
                    });
                }
            });
        });

        //会员客户经理
        $(document).on("click", "#userIdBtn", function () {
            var index = layer.open({
                type: 2,
                title: '会员客户经理',
                area: ['60%', '80%'], //宽高
                fix: true, //固定
                content: 'adduser'
            });
        });

        layui.config({
            base: '${base}/resources/common/js/' //你存放新模块的目录，注意，不是layui的模块目录
        }).use('picker'); //加载入口


        layui.use(['form', 'table','picker', 'laydate'], function () {
            var table = layui.table;
            var picker = layui.picker;
            var laydate = layui.laydate;
            var form = layui.form;
//            loadArea();
            var areaDiv = new picker();
            areaDiv.set({
                elem: '#areaDiv',
                data: Areas,

                canSearch: true
            }).render();

//            var a = {"province": 140000,"city": 140100,"county": 140106};
//            bindArea(a);

            form.verify({
                idcard: [/^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$|^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}([0-9]|X)$/, "请输入正确的身份证号码"]
            });
            form.render();
            //身份证验证
            $("#idcard").change(function () {
                var val = this.value;
                var regExp = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
                if (!regExp.test(val)) {
                    layer.alert('身份证输入错误');
                    this.value = "";
                }
            });
            //手机验证
            $("#mobile").change(function () {
                var val = this.value;
                var regExp = /^1[34578]\d{9}$/;
                if (!regExp.test(val)) {
                    layer.alert('手机号输入错误');
                    this.value = "";
                }
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
<form class="layui-form" id="inputForm" action="save" method="post">
    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
        <legend>基本信息</legend>
    </fieldset>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">姓名</label>
            <div class="layui-input-inline">
                <input name="name" lay-verify="required" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">手机号</label>
            <div class="layui-input-inline">
                <input name="mobile" id="mobile" lay-verify="required" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">身份证号</label>
            <div class="layui-input-inline">
                <input name="idcard" id="idcard" autocomplete="off" class="layui-input">
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">性别</label>
            <div class="layui-input-inline">
                <input name="sex" value="male" title="男" type="radio" checked>
                <input name="sex" value="female" title="女" type="radio">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">国籍</label>
            <div class="layui-input-inline">
                <input name="countries" <#--lay-verify="required"--> autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">名族</label>
            <div class="layui-input-inline">
                <input name="ethnic" <#--lay-verify="required"--> autocomplete="off" class="layui-input">
            </div>
        </div>

    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">出生年月日</label>
            <div class="layui-input-inline">
                <input id="birthday" name="birthday" lay-verify="required" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">有效日期至</label>
            <div class="layui-input-inline">
                <input id="pastTime" name="pastTime" lay-verify="required" autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="layui-inline">
            <label class="layui-form-label">邮政编码</label>
            <div class="layui-input-inline">
                <input name="postalCode" <#--lay-verify="required"--> autocomplete="off" class="layui-input">
            </div>
        </div>

    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">籍贯</label>
            <div class="layui-input-inline">
                <input name="origin" <#--lay-verify="required"--> autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="layui-inline">
            <label class="layui-form-label">实体卡号</label>
            <div class="layui-input-inline">
                <input name="cardNo" <#--lay-verify="required"--> autocomplete="off" class="layui-input"
                       <#--style="width: 280%"-->>
            </div>
        </div>

        <div class="layui-inline">
            <label class="layui-form-label">会员级别</label>
            <div class="layui-input-inline">
                <select name="levelId" lay-filter="aihao" lay-verify="required">
                <#list listL as ls>
                    <option value="${ls.id}" selected>${ls.name}</option>
                </#list>
                </select>
            </div>
        </div>

    </div>

    <div class="layui-form-item">
        <div class="layui-input-inline" style="width: auto;">
            <div id="areaDiv"></div>
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">详细地址</label>
            <div class="layui-input-inline">
                <input name="address" <#--lay-verify="required"--> autocomplete="off" class="layui-input"
                       style="width: 280%">
            </div>
        </div>
    </div>
    <div class="layui-form-item">

        <div class="layui-inline">
            <label class="layui-form-label">工作单位</label>
            <div class="layui-input-inline">
                <input name="workUnit" <#--lay-verify="required"--> autocomplete="off" class="layui-input"
                       style="width: 281%">
            </div>
        </div>

    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">会员客户经理</label>
            <div class="layui-input-inline" style="width: auto;">
                <input id="userId" name="userId" type="hidden" <#--lay-verify="distributorId"--> autocomplete="off"
                       class="layui-input" value="">
                <input id="userName" type="hidden" name="userName" <#--lay-verify="required"--> autocomplete="off"
                       class="layui-input" value=""/>
                <div id="userIdName" name="userIdName" class="layui-form-mid"></div>
            </div>
            <div class="layui-input-inline">
                <button id="userIdBtn" type="button" class="layui-btn">
                    选择
                </button>
            </div>
        </div>
    </div>
    <div class="layui-form-item" style="width: 300px">
        <div class="layui-upload" style="margin-left: 111px">
            <div class="layui-upload-list">
                <img class="layui-upload-img" id="demo1" style="height: 100px;width: 100px;display: none">
                <input type="hidden" name="image" id="fileImage"/>
                <p id="demoText"></p>
            </div>
            <button type="button" class="layui-btn" id="test1">上传头像</button>
        <#--<button type="button" class="layui-btn" id="test2">上传头像</button>-->
            <div style="color: #46a110">(只支持jpg或png格式)</div>
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn layui-btn-normal" lay-submit="" lay-filter="submit">立即提交</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            <input onclick="history.back();" type="button" class="layui-btn layui-btn-primary" value="返回"/>
        </div>
    </div>
</form>
<script>
    layui.use('laydate', function () {
        var laydate = layui.laydate;

        //执行一个laydate实例
        laydate.render({
            elem: '#birthday' //指定元素
            , type: 'date'
//            ,value: '1980-01-01'
        });
        laydate.render({
            elem: '#pastTime' //指定元素
            , type: 'date'
        });
    });
</script>
</body>
</html>