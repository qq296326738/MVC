<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport"
          content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no"/>
    <title></title>
    <script src="${base}/member/js/mui.min.js"></script>
    <link href="${base}/member/css/mui.css" rel="stylesheet"/>
    <link rel="stylesheet" type="text/css" href="${base}/member/css/member.css"/>
    <script type="text/javascript" charset="utf-8">
        mui.init();
    </script>
    <style>

    </style>
</head>
<body class="userInfo">
<div class="member">
    <div class="mui-content">
        <div class="member-part">
            <div class="member-flex member-row member-border-none userInfo-header-box">
                <div class="member-part-label">
                    <div class="userInfo-header">
                    <#if member.image??>
                        <img id="huiXian" src="${member.image!}" onclick="addImage()"/>
                    <#else>
                        <img id="huiXian" src="${base}/member/images/index-user-header.jpg" onclick="addImage()">
                    </#if>
                    </div>
                </div>
                <div class="member-flex-main">
                    <a class="userInfo-header-upload">
                        <p>上传人脸识别凭证<span onclick="addImage()" id="upload"
                                         class="mui-icon mui-icon-forward fg-orange"></span></p>
                        <input type="file" onchange="changImg(event)" id="image" accept="image/*" name="file"
                               style="display:none;"/>
                        <script>

                        </script>
                    </a>
                </div>
            </div>
        </div>
        <div class="member-part userInfo-info">
            <div class="member-flex member-row">
                <div class="member-part-label">
                    <p>昵称：</p>
                </div>
                <div class="member-flex-main">
                    <input id="nickName" type="text" value="${member.nickName!}" placeholder="${member.nickName!}"/>
                </div>
            </div>
            <div class="member-flex member-row">
                <div class="member-part-label">
                    <p>卡号：</p>
                </div>
                <div class="member-flex-main">
                    <input id="cardNumber" type="text" value="${member.cardNumber!}" placeholder="${member.cardNumber!}"
                           <#if member.cardNumber??>disabled="disabled" style="color: #8e8e8e" </#if>/>
                </div>
            </div>
            <div class="member-flex member-row">
                <div class="member-part-label">
                    <p>会员等级：</p>
                </div>
                <div class="member-flex-main">
                    <input type="text" value="${member.levelName!}" placeholder="${member.levelName!}"
                           <#if member.levelName??>disabled="disabled" style="color: #8e8e8e"</#if>/>
                </div>
            </div>
            <div class="member-flex member-row">
                <div class="member-part-label">
                    <p>姓名：</p>
                </div>
                <div class="member-flex-main">
                    <input id="name" type="text" value="${member.name!}"
                           placeholder="${member.name!}" <#--<#if member.name??>disabled="disabled"</#if>-->/>
                </div>
            </div>
            <div class="member-flex member-row">
                <div class="member-part-label">
                    <p>手机号：</p>
                </div>
                <div class="member-flex-main">
                    <input id="mobile" type="text" value="${member.mobile!}" placeholder="${member.mobile!}"
                           <#if member.mobile??>disabled="disabled" style="color: #8e8e8e"</#if>/>
                </div>
            </div>
            <div class="member-flex member-row">
                <div class="member-part-label">
                    <p>身份证：</p>
                </div>
                <div class="member-flex-main">
                    <input id="idcard" type="text" value="${member.idcard!}" placeholder="${member.idcard!}"
                           <#if member.idcard??<#-- && member.idcard != ''-->>disabled="disabled" style="color: #8e8e8e"</#if>/>
                </div>
            </div>
            <div class="member-flex member-row">
                <div class="member-part-label">
                    <p>籍贯：</p>
                </div>
                <div class="member-flex-main">
                    <input id="origin" type="text" value="${member.origin!}" placeholder="${member.origin!}"/>
                </div>
            </div>
            <div class="member-flex member-row member-border-none">
                <div class="member-part-label">
                    <p>现居地：</p>
                </div>
                <div class="member-flex-main">
                    <input id="address" type="text" value="${member.address!}" placeholder="${member.address!}"/>
                </div>
            </div>
        </div>
        <a id="submit" class="member-btn-2">确认修改</a>
    </div>
</div>
</body>
<script type="text/javascript" charset="utf-8">
    var myFrom = new FormData();

    function addImage() {
        document.getElementById("image").click();
    }

    /**
     *回显图片
     * */
    var huabu = new Image();

    function changImg(e) {
        for (var i = 0; i < e.target.files.length; i++) {
            var file = e.target.files.item(i);
            if (!(/^image\/.*$/i.test(file.type))) {
                continue; //不是图片 就跳出这一次循环
            }
            //实例化FileReader API
            var freader = new FileReader();
            freader.readAsDataURL(file);
            freader.onload = function (e) {
                var element = document.getElementById('huiXian');
                /*获得base64,回显图片*/
                var result = e.target.result;
                element.src = result;
                var img = document.getElementById("image");
                huabu.src = e.target.result;
            }
        }
    }

    /**
     *创建画布,把图片放进去
     */
    huabu.onload = function () {
        // 当图片宽度超过 400px 时, 就压缩成 400px, 高度按比例计算
        // 压缩质量可以根据实际情况调整
        var w = Math.min(400, huabu.width);
        var h = huabu.height * (w / huabu.width);
        var canvas = document.createElement('canvas');
        var ctx = canvas.getContext('2d');
        // 设置 canvas 的宽度和高度
        canvas.width = w;
        canvas.height = h;
        // 把图片绘制到 canvas 中
        ctx.drawImage(huabu, 0, 0, w, h);
        // 取出 base64 格式数据
        var dataURL = canvas.toDataURL('image/png');
        // 转成blob对象,放入表单中
        var blob = dataURItoBlob(dataURL);
        myFrom.append("file", blob);
    };
    var elementById = document.getElementById("submit");
    /**
     * 提交
     */
    elementById.onclick = function () {
        var nickName = document.getElementById("nickName").value;
        var cardNumber = document.getElementById("cardNumber").value;
        var name = document.getElementById("name").value;
        var idcard = document.getElementById("idcard").value;
        if (idcard != "") {
            var fale = testid(idcard);
            if (!fale) {
                alert("身份证输入错误");
                return false;
            }
        }

//        var mobile = document.getElementById("mobile").value;
        var origin = document.getElementById("origin").value;
        var address = document.getElementById("address").value;
        myFrom.append("nickName", nickName);
        myFrom.append("cardNumber", cardNumber);
        myFrom.append("name", name);
        myFrom.append("idcard", idcard);
//        myFrom.append("mobile", mobile);
        myFrom.append("origin", origin);
        myFrom.append("address", address);
        var request = null;
        if (window.ActiveXObject) {
            request = new ActiveXObject("Microsoft.XMLHTTP");
        } else {
            if (window.XMLHttpRequest) {
                request = new XMLHttpRequest();
            }
        }
        request.open("post", "update?id=" + '${member.id!}' + "&fileType=image", false);
        request.send(myFrom);
        var code = request.responseText;
        if (code.indexOf("1") != -1) {
            window.location.href = "index?id=" + "${member.id!}";
        } else {
            alert("身份证以被占用,请重新填写!!!");
            window.location.reload();
        }
    }

    /**
     * base64 转 二进制文件
     * @param dataURI
     * @returns {*}
     */
    function dataURItoBlob(dataURI) {
        var byteString = atob(dataURI.split(',')[1]);
        var mimeString = dataURI.split(',')[0].split(':')[1].split(';')[0];
        var ab = new ArrayBuffer(byteString.length);
        var ia = new Uint8Array(ab);
        for (var i = 0; i < byteString.length; i++) {
            ia[i] = byteString.charCodeAt(i);
        }
        return new Blob([ab], {type: mimeString});
    }

    /**
     * 身份证校验
     * @param id
     * @returns {*}
     */
    function testid(id) {
        // 1 "验证通过!", 0 //校验不通过 // id为身份证号码
        var format = /^(([1][1-5])|([2][1-3])|([3][1-7])|([4][1-6])|([5][0-4])|([6][1-5])|([7][1])|([8][1-2]))\d{4}(([1][9]\d{2})|([2]\d{3}))(([0][1-9])|([1][0-2]))(([0][1-9])|([1-2][0-9])|([3][0-1]))\d{3}[0-9xX]$/;
        //号码规则校验
        if (!format.test(id)) {
            return false;
        }
        //区位码校验
        //出生年月日校验  前正则限制起始年份为1900;
        var year = id.substr(6, 4),//身份证年
                month = id.substr(10, 2),//身份证月
                date = id.substr(12, 2),//身份证日
                time = Date.parse(month + '-' + date + '-' + year),//身份证日期时间戳date
                now_time = Date.parse(new Date()),//当前时间戳
                dates = (new Date(year, month, 0)).getDate();//身份证当月天数
        if (time > now_time || date > dates) {
            return false;
        }
        //校验码判断
        var c = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2);  //系数
        var b = new Array('1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2'); //校验码对照表
        var id_array = id.split("");
        var sum = 0;
        for (var k = 0; k < 17; k++) {
            sum += parseInt(id_array[k]) * parseInt(c[k]);
        }
        if (id_array[17].toUpperCase() != b[sum % 11].toUpperCase()) {
            return false;
        }
        return true;
    }
</script>
</html>