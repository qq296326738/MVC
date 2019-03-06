/*
 * JavaScript - Common-tools
 */

var config = {
    base: "",
    locale: "zh_CN"
};

var setting = {
    priceScale: "2",
    priceRoundType: "roundHalfUp",
    currencySign: "￥",
    currencyUnit: "元",
    uploadImageExtension: "jpg,jpeg,bmp,gif,png",
    uploadFlashExtension: "swf,flv",
    uploadMediaExtension: "swf,flv,mp3,wav,avi,rm,rmvb",
    uploadFileExtension: "zip,rar,7z,doc,docx,xls,xlsx,ppt,pptx"
};

var messages = {
    "admin.message.success": "操作成功",
    "admin.message.error": "操作错误",
    "admin.dialog.ok": "确&nbsp;&nbsp;定",
    "admin.dialog.cancel": "取&nbsp;&nbsp;消",
    "admin.dialog.deleteConfirm": "您确定要删除吗？",
    "admin.dialog.clearConfirm": "您确定要清空吗？",
    "admin.browser.title": "选择文件",
    "admin.browser.upload": "本地上传",
    "admin.browser.parent": "上级目录",
    "admin.browser.orderType": "排序方式",
    "admin.browser.name": "名称",
    "admin.browser.size": "大小",
    "admin.browser.type": "类型",
    "admin.browser.select": "选择文件",
    "admin.upload.sizeInvalid": "上传文件大小超出限制",
    "admin.upload.typeInvalid": "上传文件格式不正确",
    "admin.upload.invalid": "上传文件格式或大小不正确",
    "admin.validate.required": "必填",
    "admin.validate.email": "E-mail格式错误",
    "admin.validate.url": "网址格式错误",
    "admin.validate.date": "日期格式错误",
    "admin.validate.dateISO": "日期格式错误",
    "admin.validate.pointcard": "信用卡格式错误",
    "admin.validate.number": "只允许输入数字",
    "admin.validate.digits": "只允许输入零或正整数",
    "admin.validate.minlength": "长度不允许小于{0}",
    "admin.validate.maxlength": "长度不允许大于{0}",
    "admin.validate.rangelength": "长度必须在{0}-{1}之间",
    "admin.validate.min": "不允许小于{0}",
    "admin.validate.max": "不允许大于{0}",
    "admin.validate.range": "必须在{0}-{1}之间",
    "admin.validate.accept": "输入后缀错误",
    "admin.validate.equalTo": "两次输入不一致",
    "admin.validate.remote": "输入错误",
    "admin.validate.integer": "只允许输入整数",
    "admin.validate.positive": "只允许输入正数",
    "admin.validate.negative": "只允许输入负数",
    "admin.validate.decimal": "数值超出了允许范围",
    "admin.validate.pattern": "格式错误",
    "admin.validate.extension": "文件格式错误"
};

// 添加Cookie
function addCookie(name, value, options) {
    if (arguments.length > 1 && name != null) {
        if (options == null) {
            options = {};
        }
        if (value == null) {
            options.expires = -1;
        }
        if (typeof options.expires == "number") {
            var time = options.expires;
            var expires = options.expires = new Date();
            expires.setTime(expires.getTime() + time * 1000);
        }
        document.cookie = encodeURIComponent(String(name)) + "=" + encodeURIComponent(String(value)) + (options.expires ? "; expires=" + options.expires.toUTCString() : "") + (options.path ? "; path=" + options.path : "") + (options.domain ? "; domain=" + options.domain : ""), (options.secure ? "; secure" : "");
    }
}

// 获取Cookie
function getCookie(name) {
    if (name != null) {
        var value = new RegExp("(?:^|; )" + encodeURIComponent(String(name)) + "=([^;]*)").exec(document.cookie);
        return value ? decodeURIComponent(value[1]) : null;
    }
}

// 移除Cookie
function removeCookie(name, options) {
    addCookie(name, null, options);
}

function setFaviconTag(url) {
    var link = document.createElement('link');
    link.type = 'image/x-icon';
    link.rel = 'icon';
    link.href = url;
    document.getElementsByTagName('head')[0].appendChild(link);
}

// 货币格式化
function currency(value, showSign, showUnit) {
    if (value != null) {
        var price;
        if (setting.priceRoundType == "roundHalfUp") {
            price = (Math.round(value * Math.pow(10, setting.priceScale)) / Math.pow(10, setting.priceScale)).toFixed(setting.priceScale);
        } else if (setting.priceRoundType == "roundUp") {
            price = (Math.ceil(value * Math.pow(10, setting.priceScale)) / Math.pow(10, setting.priceScale)).toFixed(setting.priceScale);
        } else {
            price = (Math.floor(value * Math.pow(10, setting.priceScale)) / Math.pow(10, setting.priceScale)).toFixed(setting.priceScale);
        }
        if (showSign) {
            price = setting.currencySign + price;
        }
        if (showUnit) {
            price += setting.currencyUnit;
        }
        return price;
    }
}

// 多语言
function message(code) {
    if (code != null) {
        var content = messages[code] != null ? messages[code] : code;
        if (arguments.length == 1) {
            return content;
        } else {
            if ($.isArray(arguments[1])) {
                $.each(arguments[1], function (i, n) {
                    content = content.replace(new RegExp("\\{" + i + "\\}", "g"), n);
                });
                return content;
            } else {
                $.each(Array.prototype.slice.apply(arguments).slice(1), function (i, n) {
                    content = content.replace(new RegExp("\\{" + i + "\\}", "g"), n);
                });
                return content;
            }
        }
    }
}

/**
 *删除数组指定下标或指定对象
 */
Array.prototype.remove = function (obj) {
    for (var i = 0; i < this.length; i++) {
        var temp = this[i];
        if (!isNaN(obj)) {
            temp = i;
        }
        if (temp == obj) {
            for (var j = i; j < this.length; j++) {
                this[j] = this[j + 1];
            }
            this.length = this.length - 1;
        }
    }
}
//---------------------------------------------------  
// 判断闰年  
//---------------------------------------------------  
Date.prototype.isLeapYear = function () {
    return (0 == this.getYear() % 4 && ((this.getYear() % 100 != 0) || (this.getYear() % 400 == 0)));
}

//---------------------------------------------------  
// 日期格式化  
// 格式 YYYY/yyyy/YY/yy 表示年份  
// MM/M 月份  
// W/w 星期  
// dd/DD/d/D 日期  
// hh/HH/h/H 时间  
// mm/m 分钟  
// ss/SS/s/S 秒  
//---------------------------------------------------  
Date.prototype.Format = function (formatStr) {
    var str = formatStr;
    var Week = ['日', '一', '二', '三', '四', '五', '六'];

    str = str.replace(/yyyy|YYYY/, this.getFullYear());
    str = str.replace(/yy|YY/, (this.getYear() % 100) > 9 ? (this.getYear() % 100).toString() : '0' + (this.getYear() % 100));

    var month = this.getMonth() + 1;
    str = str.replace(/MM/, month > 9 ? month.toString() : '0' + month);
    str = str.replace(/M/g, month);

    str = str.replace(/w|W/g, Week[this.getDay()]);

    str = str.replace(/dd|DD/, this.getDate() > 9 ? this.getDate().toString() : '0' + this.getDate());
    str = str.replace(/d|D/g, this.getDate());

    str = str.replace(/hh|HH/, this.getHours() > 9 ? this.getHours().toString() : '0' + this.getHours());
    str = str.replace(/h|H/g, this.getHours());
    str = str.replace(/mm/, this.getMinutes() > 9 ? this.getMinutes().toString() : '0' + this.getMinutes());
    str = str.replace(/m/g, this.getMinutes());

    str = str.replace(/ss|SS/, this.getSeconds() > 9 ? this.getSeconds().toString() : '0' + this.getSeconds());
    str = str.replace(/s|S/g, this.getSeconds());

    return str;
}

//+---------------------------------------------------  
//| 求两个时间的天数差 日期格式为 YYYY-MM-dd   
//+---------------------------------------------------  
function daysBetween(DateOne, DateTwo) {
    var OneMonth = DateOne.substring(5, DateOne.lastIndexOf('-'));
    var OneDay = DateOne.substring(DateOne.length, DateOne.lastIndexOf('-') + 1);
    var OneYear = DateOne.substring(0, DateOne.indexOf('-'));

    var TwoMonth = DateTwo.substring(5, DateTwo.lastIndexOf('-'));
    var TwoDay = DateTwo.substring(DateTwo.length, DateTwo.lastIndexOf('-') + 1);
    var TwoYear = DateTwo.substring(0, DateTwo.indexOf('-'));

    var cha = ((Date.parse(OneMonth + '/' + OneDay + '/' + OneYear) - Date.parse(TwoMonth + '/' + TwoDay + '/' + TwoYear)) / 86400000);
    return Math.abs(cha);
}


//+---------------------------------------------------  
//| 日期计算  
//+---------------------------------------------------  
Date.prototype.DateAdd = function (strInterval, Number) {
    var dtTmp = this;
    switch (strInterval) {
        case 's' :
            return new Date(dtTmp.getFullYear(), dtTmp.getMonth(), dtTmp.getDate(), dtTmp.getHours(), dtTmp.getMinutes(), dtTmp.getSeconds() + Number);//new Date(Date.parse(dtTmp) + (1000 * Number));
        case 'n' :
            return new Date(dtTmp.getFullYear(), dtTmp.getMonth(), dtTmp.getDate(), dtTmp.getHours(), dtTmp.getMinutes() + Number, dtTmp.getSeconds());//new Date(Date.parse(dtTmp) + (60000 * Number));
        case 'h' :
            return new Date(dtTmp.getFullYear(), dtTmp.getMonth(), dtTmp.getDate(), dtTmp.getHours() + Number, dtTmp.getMinutes(), dtTmp.getSeconds());//new Date(Date.parse(dtTmp) + (3600000 * Number));
        case 'd' :
            return new Date(dtTmp.getFullYear(), dtTmp.getMonth(), dtTmp.getDate() + Number, dtTmp.getHours(), dtTmp.getMinutes(), dtTmp.getSeconds());//new Date(Date.parse(dtTmp) + (86400000 * Number));
        case 'w' :
            return new Date(dtTmp.getFullYear(), dtTmp.getMonth(), dtTmp.getDate() + (Number * 7), dtTmp.getHours(), dtTmp.getMinutes(), dtTmp.getSeconds());//new Date(Date.parse(dtTmp) + ((86400000 * 7) * Number));
        case 'q' :
            return new Date(dtTmp.getFullYear(), (dtTmp.getMonth()) + Number * 3, dtTmp.getDate(), dtTmp.getHours(), dtTmp.getMinutes(), dtTmp.getSeconds());
        case 'm' :
            return new Date(dtTmp.getFullYear(), (dtTmp.getMonth()) + Number, dtTmp.getDate(), dtTmp.getHours(), dtTmp.getMinutes(), dtTmp.getSeconds());
        case 'y' :
            return new Date((dtTmp.getFullYear() + Number), dtTmp.getMonth(), dtTmp.getDate(), dtTmp.getHours(), dtTmp.getMinutes(), dtTmp.getSeconds());
    }
}

//+---------------------------------------------------  
//| 比较日期差 dtEnd 格式为日期型或者 有效日期格式字符串  
//+---------------------------------------------------  
Date.prototype.DateDiff = function (strInterval, dtEnd) {
    var dtStart = this;
    if (typeof dtEnd == 'string')//如果是字符串转换为日期型
    {
        dtEnd = StringToDate(dtEnd);
    }
    switch (strInterval) {
        case 's' :
            return parseInt((dtEnd - dtStart) / 1000);
        case 'n' :
            return parseInt((dtEnd - dtStart) / 60000);
        case 'h' :
            return parseInt((dtEnd - dtStart) / 3600000);
        case 'd' :
            return parseInt((dtEnd - dtStart) / 86400000);
        case 'w' :
            return parseInt((dtEnd - dtStart) / (86400000 * 7));
        case 'm' :
            return (dtEnd.getMonth() + 1) + ((dtEnd.getFullYear() - dtStart.getFullYear()) * 12) - (dtStart.getMonth() + 1);
        case 'y' :
            return dtEnd.getFullYear() - dtStart.getFullYear();
    }
}

//+---------------------------------------------------  
//| 日期输出字符串，重载了系统的toString方法  
//+---------------------------------------------------  
Date.prototype.toString = function (showWeek) {
    var myDate = this;
    var str = myDate.toLocaleDateString();
    if (showWeek) {
        var Week = ['日', '一', '二', '三', '四', '五', '六'];
        str += ' 星期' + Week[myDate.getDay()];
    }
    return str;
}

//+---------------------------------------------------  
//| 日期合法性验证  
//| 格式为：YYYY-MM-DD或YYYY/MM/DD  
//+---------------------------------------------------  
function IsValidDate(DateStr) {
    var sDate = DateStr.replace(/(^\s+|\s+$)/g, ''); //去两边空格;
    if (sDate == '') return true;
    //如果格式满足YYYY-(/)MM-(/)DD或YYYY-(/)M-(/)DD或YYYY-(/)M-(/)D或YYYY-(/)MM-(/)D就替换为''   
    //数据库中，合法日期可以是:YYYY-MM/DD(2003-3/21),数据库会自动转换为YYYY-MM-DD格式   
    var s = sDate.replace(/[\d]{ 4,4 }[\-/]{ 1 }[\d]{ 1,2 }[\-/]{ 1 }[\d]{ 1,2 }/g, '');
    if (s == '') //说明格式满足YYYY-MM-DD或YYYY-M-DD或YYYY-M-D或YYYY-MM-D
    {
        var t = new Date(sDate.replace(/\-/g, '/'));
        var ar = sDate.split(/[-/:]/);
        if (ar[0] != t.getYear() || ar[1] != t.getMonth() + 1 || ar[2] != t.getDate()) {
            //alert('错误的日期格式！格式为：YYYY-MM-DD或YYYY/MM/DD。注意闰年。');   
            return false;
        }
    }
    else {
        //alert('错误的日期格式！格式为：YYYY-MM-DD或YYYY/MM/DD。注意闰年。');   
        return false;
    }
    return true;
}

//+---------------------------------------------------  
//| 日期时间检查  
//| 格式为：YYYY-MM-DD HH:MM:SS  
//+---------------------------------------------------  
function CheckDateTime(str) {
    var reg = /^(\d+)-(\d{ 1,2 })-(\d{ 1,2 }) (\d{ 1,2 }):(\d{ 1,2 }):(\d{ 1,2 })$/;
    var r = str.match(reg);
    if (r == null) return false;
    r[2] = r[2] - 1;
    var d = new Date(r[1], r[2], r[3], r[4], r[5], r[6]);
    if (d.getFullYear() != r[1]) return false;
    if (d.getMonth() != r[2]) return false;
    if (d.getDate() != r[3]) return false;
    if (d.getHours() != r[4]) return false;
    if (d.getMinutes() != r[5]) return false;
    if (d.getSeconds() != r[6]) return false;
    return true;
}

//+---------------------------------------------------  
//| 把日期分割成数组  
//+---------------------------------------------------  
Date.prototype.toArray = function () {
    var myDate = this;
    var myArray = Array();
    myArray[0] = myDate.getFullYear();
    myArray[1] = myDate.getMonth();
    myArray[2] = myDate.getDate();
    myArray[3] = myDate.getHours();
    myArray[4] = myDate.getMinutes();
    myArray[5] = myDate.getSeconds();
    return myArray;
}

//+---------------------------------------------------  
//| 取得日期数据信息  
//| 参数 interval 表示数据类型  
//| y 年 m月 d日 w星期 ww周 h时 n分 s秒  
//+---------------------------------------------------  
Date.prototype.DatePart = function (interval) {
    var myDate = this;
    var partStr = '';
    var Week = ['日', '一', '二', '三', '四', '五', '六'];
    switch (interval) {
        case 'y' :
            partStr = myDate.getFullYear();
            break;
        case 'm' :
            partStr = myDate.getMonth() + 1;
            break;
        case 'd' :
            partStr = myDate.getDate();
            break;
        case 'w' :
            partStr = Week[myDate.getDay()];
            break;
        case 'ww' :
            partStr = myDate.WeekNumOfYear();
            break;
        case 'h' :
            partStr = myDate.getHours();
            break;
        case 'n' :
            partStr = myDate.getMinutes();
            break;
        case 's' :
            partStr = myDate.getSeconds();
            break;
    }
    return partStr;
}

//+---------------------------------------------------  
//| 取得当前日期所在月的最大天数  
//+---------------------------------------------------  
Date.prototype.MaxDayOfDate = function () {
    var myDate = this;
    var ary = myDate.toArray();
    var date1 = (new Date(ary[0], ary[1] + 1, 1));
    var date2 = date1.dateAdd(1, 'm', 1);
    var result = dateDiff(date1.Format('yyyy-MM-dd'), date2.Format('yyyy-MM-dd'));
    return result;
}

//+---------------------------------------------------  
//| 字符串转成日期类型   
//| 格式 MM/dd/YYYY MM-dd-YYYY YYYY/MM/dd YYYY-MM-dd  
//+---------------------------------------------------  
function StringToDate(DateStr) {
    var converted = Date.parse(DateStr);
    var myDate = new Date(converted);
    if (isNaN(myDate)) {
        //var delimCahar = DateStr.indexOf('/')!=-1?'/':'-';  
        var arys = DateStr.split('-');
        myDate = new Date(arys[0], --arys[1], arys[2]);
    }
    return myDate;
}

function parseDate(d) {
    var arr = d.split("-");
    return new Date(arr[0], arr[1] - 1, arr[2]);
}

function DateDiff(sDate1, sDate2) {    //sDate1和sDate2是2006-12-18格式
    var aDate, oDate1, oDate2, iDays
    aDate = sDate1.split("-")
    oDate1 = new Date(aDate[1] + '-' + aDate[2] + '-' + aDate[0])    //转换为12-18-2006格式
    aDate = sDate2.split("-")
    oDate2 = new Date(aDate[1] + '-' + aDate[2] + '-' + aDate[0])
    iDays = parseInt(Math.abs(oDate1 - oDate2) / 1000 / 60 / 60 / 24)    //把相差的毫秒数转换为天数
    return iDays;
}

(function($) {

    var zIndex = 100;

    // 消息框
    var $message;
    var messageTimer;
    $.message = function() {
        var message = {timeout:3000};
        var options = {};
        if ($.isPlainObject(arguments[0])) {
            options = arguments[0];
        } else if (typeof arguments[0] === "string" && typeof arguments[1] === "string") {
            options.type = arguments[0];
            options.content = arguments[1];
        } else {
            return false;
        }

        $.extend(message, options);

        if (message.type == null || message.content == null) {
            return false;
        }

        if ($message == null) {
            $message = $('<div class="xxMessage"><div class="messageContent message' + message.type + 'Icon"><\/div><\/div>');
            if (!window.XMLHttpRequest) {
                $message.append('<iframe class="messageIframe"><\/iframe>');
            }
            $message.appendTo("body");
        }

        $message.children("div").removeClass("messagewarnIcon messageerrorIcon messagesuccessIcon").addClass("message" + message.type + "Icon").html(message.content);
        $message.css({"margin-left": - parseInt($message.outerWidth() / 2), "z-index": zIndex ++}).show();

        clearTimeout(messageTimer);
        if(message.timeout > 0) {
            messageTimer = setTimeout(function() {
                $message.hide();
            }, message.timeout);
        }
        return $message;
    }

    // 对话框
    $.dialog = function(options) {
        var settings = {
            width: 320,
            height: "auto",
            modal: true,
            close: true,
            ok: message("admin.dialog.ok"),
            cancel: message("admin.dialog.cancel"),
            onShow: null,
            onClose: null,
            onOk: null,
            onCancel: null
        };
        $.extend(settings, options);

        if (settings.content == null) {
            return false;
        }

        var $dialog = $('<div class="xxDialog"><\/div>');
        var $dialogTitle;
        var $dialogClose;
        if(settings.close) {
            $dialogClose = $('<div class="dialogClose"><\/div>').appendTo($dialog);
        }
        var $dialogContent;
        var $dialogBottom;
        var $dialogOk;
        var $dialogCancel;
        var $dialogOverlay;
        if (settings.title != null) {
            $dialogTitle = $('<div class="dialogTitle"><\/div>').appendTo($dialog);
        }
        if (settings.type != null) {
            $dialogContent = $('<div class="dialogContent dialog' + settings.type + 'Icon"><\/div>').appendTo($dialog);
        } else {
            $dialogContent = $('<div class="dialogContent"><\/div>').appendTo($dialog);
        }
        if (settings.ok != null || settings.cancel != null) {
            $dialogBottom = $('<div class="dialogBottom"><\/div>').appendTo($dialog);
        }
        if (settings.ok != null) {
            $dialogOk = $('<input type="button" class="button" value="' + settings.ok + '" \/>').appendTo($dialogBottom);
        }
        if (settings.cancel != null) {
            $dialogCancel = $('<input type="button" class="button" value="' + settings.cancel + '" \/>').appendTo($dialogBottom);
        }
        if (!window.XMLHttpRequest) {
            $dialog.append('<iframe class="dialogIframe"><\/iframe>');
        }
        $dialog.appendTo("body");
        if (settings.modal) {
            $dialogOverlay = $('<div class="dialogOverlay"><\/div>').insertAfter($dialog);
        }

        var dialogX;
        var dialogY;
        if (settings.title != null) {
            $dialogTitle.html(settings.title);
        }
        $dialogContent.html(settings.content);
        //$dialog.css({"width": settings.width, "height": settings.height, "margin-left": - parseInt(settings.width / 2), "z-index": zIndex ++});
        $dialog.css({"width": settings.width,
            "height": settings.height,
            "position": "absolute",
            "top": "50%",
            "left": "50%",
            "marginLeft": - ( settings.width / 2 ),
            "marginTop": - ( $dialog.height() / 2 - $(document).scrollTop()),
            "z-index": zIndex ++});
        dialogShow();
//		$("body").css({overflow:"hidden"});

        if ($dialogTitle != null) {
            $dialogTitle.mousedown(function(event) {
                $dialog.css({"z-index": zIndex ++});
                var offset = $(this).offset();
                if (!window.XMLHttpRequest) {
                    dialogX = event.clientX - offset.left;
                    dialogY = event.clientY - offset.top;
                } else {
                    dialogX = event.pageX - offset.left;
                    dialogY = event.pageY - offset.top;
                }
                $("body").bind("mousemove", function(event) {
                    $dialog.css({"top": event.clientY - dialogY, "left": event.clientX - dialogX, "margin": 0});
                });
                return false;
            }).mouseup(function() {
                $("body").unbind("mousemove");
                return false;
            });
        }

        if ($dialogClose != null) {
            $dialogClose.click(function() {
                dialogClose();
                return false;
            });
        }

        if ($dialogOk != null) {
            $dialogOk.click(function() {
                if (settings.onOk && typeof settings.onOk == "function") {
                    if (settings.onOk($dialog) != false) {
                        dialogClose();
                    }
                } else {
                    dialogClose();
                }
                return false;
            });
        }

        if ($dialogCancel != null) {
            $dialogCancel.click(function() {
                if (settings.onCancel && typeof settings.onCancel == "function") {
                    if (settings.onCancel($dialog) != false) {
                        dialogClose();
                    }
                } else {
                    dialogClose();
                }
                return false;
            });
        }

        function dialogShow() {
            if (settings.onShow && typeof settings.onShow == "function") {
                if (settings.onShow($dialog) != false) {
                    $dialog.show();
                    $dialogOverlay.show();
                }
            } else {
                $dialog.show();
                $dialogOverlay.show();
            }
        }
        function dialogClose() {
            if (settings.onClose && typeof settings.onClose == "function") {
                if (settings.onClose($dialog) != false) {
                    $dialogOverlay.remove();
                    $dialog.remove();
                }
            } else {
                $dialogOverlay.remove();
                $dialog.remove();
            }
        }
        //this.close = dialogClose;
        return $dialog;
    }

    // 文件浏览
    $.fn.extend({
        browser: function(options) {
            var settings = {
                type: "image",
                title: message("admin.browser.title"),
                isUpload: true,
                browserUrl: config.base + "/admin/file/browser.jhtml",
                uploadUrl: config.base + "/file/upload",
                callback: null
            };
            $.extend(settings, options);

            var token = getCookie("token");
            var cache = {};
            return this.each(function() {
                var browserFrameId = "browserFrame" + (new Date()).valueOf() + Math.floor(Math.random() * 1000000);
                var $browserButton = $(this);
                $browserButton.click(function() {
                    var $browser = $('<div class="xxBrowser"><\/div>');
                    var $browserBar = $('<div class="bar"><\/div>').appendTo($browser);
                    var $browserFrame;
                    var $browserForm;
                    var $browserUploadButton;
                    var $browserUploadInput;
                    var $browserParentButton;
                    var $browserOrderType;
                    var $browserLoadingIcon;
                    var $browserList;
                    if (settings.isUpload) {
                        $browserFrame = $('<iframe id="' + browserFrameId + '" name="' + browserFrameId + '" style="display: none;"><\/iframe>').appendTo($browserBar);
                        $browserForm = $('<form action="' + settings.uploadUrl + '" method="post" encType="multipart/form-data" target="' + browserFrameId + '"><input type="hidden" name="token" value="' + token + '" \/><input type="hidden" name="fileType" value="' + settings.type + '" \/><\/form>').appendTo($browserBar);
                        $browserUploadInput = $('<input type="file" name="file" style="display:none;" \/>').appendTo($browserForm);
                        $browserUploadButton = $('<button type="button" class="browserUploadButton button">' + message("admin.browser.upload") + '<\/button>').appendTo($browserBar);
                    }
                    $browserParentButton = $('<button type="button" class="button">' + message("admin.browser.parent") + '</button>').appendTo($browserBar);
                    $browserBar.append(message("admin.browser.orderType") + ": ");
                    $browserOrderType = $('<select name="orderType" class="browserOrderType"><option value="name">' + message("admin.browser.name") + '<\/option><option value="size">' + message("admin.browser.size") + '<\/option><option value="type">' + message("admin.browser.type") + '<\/option><\/select>').appendTo($browserBar);
                    $browserLoadingIcon = $('<span class="loadingIcon" style="display: none;">&nbsp;<\/span>').appendTo($browserBar);
                    $browserList = $('<div class="browserList"><\/div>').appendTo($browser);

                    var $dialog = $.dialog({
                        title: settings.title,
                        content: $browser,
                        width: 470,
                        modal: true,
                        ok: null,
                        cancel: null
                    });

                    browserList("/");

                    function browserList(path) {
                        var key = settings.type + "_" + path + "_" + $browserOrderType.val();
                        if (cache[key] == null) {
                            $.ajax({
                                url: settings.browserUrl,
                                type: "GET",
                                data: {fileType: settings.type, orderType: $browserOrderType.val(), path: path},
                                dataType: "json",
                                cache: false,
                                beforeSend: function() {
                                    $browserLoadingIcon.show();
                                },
                                success: function(data) {
                                    createBrowserList(path, data);
                                    cache[key] = data;
                                },
                                complete: function() {
                                    $browserLoadingIcon.hide();
                                }
                            });
                        } else {
                            createBrowserList(path, cache[key]);
                        }
                    }

                    function createBrowserList(path, data) {
                        var browserListHtml = "";
                        $.each(data, function(i, fileInfo) {
                            var iconUrl;
                            var title;
                            if (fileInfo.isDirectory) {
                                iconUrl = config.base + "/resources/admin/images/folder_icon.gif";
                                title = fileInfo.name;
                            } else if (new RegExp("^\\S.*\\.(jpg|jpeg|bmp|gif|png)$", "i").test(fileInfo.name)) {
                                iconUrl = fileInfo.url;
                                title = fileInfo.name + " (" + Math.ceil(fileInfo.size / 1024) + "KB, " + new Date(fileInfo.lastModified).toLocaleString() + ")";
                            } else {
                                iconUrl = config.base + "/resources/admin/images/file_icon.gif";
                                title = fileInfo.name + " (" + Math.ceil(fileInfo.size / 1024) + "KB, " + new Date(fileInfo.lastModified).toLocaleString() + ")";
                            }
                            browserListHtml += '<div class="browserItem"><img src="' + iconUrl + '" title="' + title + '" url="' + fileInfo.url + '" isDirectory="' + fileInfo.isDirectory + '" \/><div>' + fileInfo.name + '<\/div><\/div>';
                        });
                        $browserList.html(browserListHtml);

                        $browserList.find("img").bind("click", function() {
                            var $this = $(this);
                            var isDirectory = $this.attr("isDirectory");
                            if (isDirectory == "true") {
                                var name = $this.next().text();
                                browserList(path + name + "/");
                            } else {
                                var url = $this.attr("url");
                                if (settings.input != null) {
                                    settings.input.val(url);
                                } else {
                                    $browserButton.prev(":text").val(url);
                                }
                                if (settings.callback != null && typeof settings.callback == "function") {
                                    settings.callback(url);
                                }
                                $dialog.next(".dialogOverlay").andSelf().remove();
                            }
                        });

                        if (path == "/") {
                            $browserParentButton.unbind("click");
                        } else {
                            var parentPath = path.substr(0, path.replace(/\/$/, "").lastIndexOf("/") + 1);
                            $browserParentButton.unbind("click").bind("click", function() {
                                browserList(parentPath);
                            });
                        }
                        $browserOrderType.unbind("change").bind("change", function() {
                            browserList(path);
                        });
                    }

                    $browserUploadButton.click(function(){
                        $browserUploadInput.click();
                    });

                    $browserUploadInput.change(function() {
                        var allowedUploadExtensions;
                        if (settings.type == "flash") {
                            allowedUploadExtensions = setting.uploadFlashExtension;
                        } else if (settings.type == "media") {
                            allowedUploadExtensions = setting.uploadMediaExtension;
                        } else if (settings.type == "file") {
                            allowedUploadExtensions = setting.uploadFileExtension;
                        } else {
                            allowedUploadExtensions = setting.uploadImageExtension;
                        }
                        if ($.trim(allowedUploadExtensions) == "" || !new RegExp("^\\S.*\\.(" + allowedUploadExtensions.replace(/,/g, "|") + ")$", "i").test($browserUploadInput.val())) {
                            $.message("warn", message("admin.upload.typeInvalid"));
                            return false;
                        }
                        $browserLoadingIcon.show();
                        $browserForm.submit();
                    });

                    $browserFrame.load(function() {
                        var text;
                        var io = document.getElementById(browserFrameId);
                        if(io.contentWindow) {
                            text = io.contentWindow.document.body ? io.contentWindow.document.body.innerHTML : null;
                        } else if(io.contentDocument) {
                            text = io.contentDocument.document.body ? io.contentDocument.document.body.innerHTML : null;
                        }
                        if ($.trim(text) != "") {
                            $browserLoadingIcon.hide();
                            var data = $.parseJSON(text);
                            if (data.message.type == "success") {
                                if (settings.input != null) {
                                    settings.input.val(data.url);
                                } else {
                                    $browserButton.prev(":text").val(data.url);
                                }
                                if (settings.callback != null && typeof settings.callback == "function") {
                                    settings.callback(data.url);
                                }
                                cache = {};
                                $dialog.next(".dialogOverlay").andSelf().remove();
                            } else {
                                $.message(data.message);
                            }
                        }
                    });

                });
            });
        }
    });
    $.fn.extend({
        browser2: function(options) {
            var settings = {
                type: "image",
                title: message("admin.browser.title"),
                isUpload: true,
                browserUrl: config.base + "/admin/file/browser.jhtml",
                uploadUrl: config.base + "/file/upload?zoom=0",
                callback: null
            };
            $.extend(settings, options);

            var token = getCookie("token");
            var cache = {};
            return this.each(function() {
                var browserFrameId = "browserFrame" + (new Date()).valueOf() + Math.floor(Math.random() * 1000000);
                var $browserButton = $(this);
                $browserButton.click(function() {
                    var $browser = $('<div class="xxBrowser"><\/div>');
                    var $browserBar = $('<div class="bar"><\/div>').appendTo($browser);
                    var $browserFrame;
                    var $browserForm;
                    var $browserUploadButton;
                    var $browserUploadInput;
                    var $browserParentButton;
                    var $browserOrderType;
                    var $browserLoadingIcon;
                    var $browserList;
                    if (settings.isUpload) {
                        $browserFrame = $('<iframe id="' + browserFrameId + '" name="' + browserFrameId + '" style="display: none;"><\/iframe>').appendTo($browserBar);
                        $browserForm = $('<form action="' + settings.uploadUrl + '" method="post" encType="multipart/form-data" target="' + browserFrameId + '"><input type="hidden" name="token" value="' + token + '" \/><input type="hidden" name="fileType" value="' + settings.type + '" \/><\/form>').appendTo($browserBar);
                        $browserUploadInput = $('<input type="file" name="file" style="display:none;" \/>').appendTo($browserForm);
                        $browserUploadButton = $('<button type="button" class="browserUploadButton button">' + message("admin.browser.upload") + '<\/button>').appendTo($browserBar);
                    }
                    $browserParentButton = $('<button type="button" class="button">' + message("admin.browser.parent") + '</button>').appendTo($browserBar);
                    $browserBar.append(message("admin.browser.orderType") + ": ");
                    $browserOrderType = $('<select name="orderType" class="browserOrderType"><option value="name">' + message("admin.browser.name") + '<\/option><option value="size">' + message("admin.browser.size") + '<\/option><option value="type">' + message("admin.browser.type") + '<\/option><\/select>').appendTo($browserBar);
                    $browserLoadingIcon = $('<span class="loadingIcon" style="display: none;">&nbsp;<\/span>').appendTo($browserBar);
                    $browserList = $('<div class="browserList"><\/div>').appendTo($browser);

                    var $dialog = $.dialog({
                        title: settings.title,
                        content: $browser,
                        width: 470,
                        modal: true,
                        ok: null,
                        cancel: null
                    });

                    browserList("/");

                    function browserList(path) {
                        var key = settings.type + "_" + path + "_" + $browserOrderType.val();
                        if (cache[key] == null) {
                            $.ajax({
                                url: settings.browserUrl,
                                type: "GET",
                                data: {fileType: settings.type, orderType: $browserOrderType.val(), path: path},
                                dataType: "json",
                                cache: false,
                                beforeSend: function() {
                                    $browserLoadingIcon.show();
                                },
                                success: function(data) {
                                    createBrowserList(path, data);
                                    cache[key] = data;
                                },
                                complete: function() {
                                    $browserLoadingIcon.hide();
                                }
                            });
                        } else {
                            createBrowserList(path, cache[key]);
                        }
                    }

                    function createBrowserList(path, data) {
                        var browserListHtml = "";
                        $.each(data, function(i, fileInfo) {
                            var iconUrl;
                            var title;
                            if (fileInfo.isDirectory) {
                                iconUrl = config.base + "/resources/admin/images/folder_icon.gif";
                                title = fileInfo.name;
                            } else if (new RegExp("^\\S.*\\.(jpg|jpeg|bmp|gif|png)$", "i").test(fileInfo.name)) {
                                iconUrl = fileInfo.url;
                                title = fileInfo.name + " (" + Math.ceil(fileInfo.size / 1024) + "KB, " + new Date(fileInfo.lastModified).toLocaleString() + ")";
                            } else {
                                iconUrl = config.base + "/resources/admin/images/file_icon.gif";
                                title = fileInfo.name + " (" + Math.ceil(fileInfo.size / 1024) + "KB, " + new Date(fileInfo.lastModified).toLocaleString() + ")";
                            }
                            browserListHtml += '<div class="browserItem"><img src="' + iconUrl + '" title="' + title + '" url="' + fileInfo.url + '" isDirectory="' + fileInfo.isDirectory + '" \/><div>' + fileInfo.name + '<\/div><\/div>';
                        });
                        $browserList.html(browserListHtml);

                        $browserList.find("img").bind("click", function() {
                            var $this = $(this);
                            var isDirectory = $this.attr("isDirectory");
                            if (isDirectory == "true") {
                                var name = $this.next().text();
                                browserList(path + name + "/");
                            } else {
                                var url = $this.attr("url");
                                if (settings.input != null) {
                                    settings.input.val(url);
                                } else {
                                    $browserButton.prev(":text").val(url);
                                }
                                if (settings.callback != null && typeof settings.callback == "function") {
                                    settings.callback(url);
                                }
                                $dialog.next(".dialogOverlay").andSelf().remove();
                            }
                        });

                        if (path == "/") {
                            $browserParentButton.unbind("click");
                        } else {
                            var parentPath = path.substr(0, path.replace(/\/$/, "").lastIndexOf("/") + 1);
                            $browserParentButton.unbind("click").bind("click", function() {
                                browserList(parentPath);
                            });
                        }
                        $browserOrderType.unbind("change").bind("change", function() {
                            browserList(path);
                        });
                    }

                    $browserUploadButton.click(function(){
                        $browserUploadInput.click();
                    });

                    $browserUploadInput.change(function() {
                        var allowedUploadExtensions;
                        if (settings.type == "flash") {
                            allowedUploadExtensions = setting.uploadFlashExtension;
                        } else if (settings.type == "media") {
                            allowedUploadExtensions = setting.uploadMediaExtension;
                        } else if (settings.type == "file") {
                            allowedUploadExtensions = setting.uploadFileExtension;
                        } else {
                            allowedUploadExtensions = setting.uploadImageExtension;
                        }
                        if ($.trim(allowedUploadExtensions) == "" || !new RegExp("^\\S.*\\.(" + allowedUploadExtensions.replace(/,/g, "|") + ")$", "i").test($browserUploadInput.val())) {
                            $.message("warn", message("admin.upload.typeInvalid"));
                            return false;
                        }
                        $browserLoadingIcon.show();
                        $browserForm.submit();
                    });

                    $browserFrame.load(function() {
                        var text;
                        var io = document.getElementById(browserFrameId);
                        if(io.contentWindow) {
                            text = io.contentWindow.document.body ? io.contentWindow.document.body.innerHTML : null;
                        } else if(io.contentDocument) {
                            text = io.contentDocument.document.body ? io.contentDocument.document.body.innerHTML : null;
                        }
                        if ($.trim(text) != "") {
                            $browserLoadingIcon.hide();
                            var data = $.parseJSON(text);
                            if (data.message.type == "success") {
                                if (settings.input != null) {
                                    settings.input.val(data.url);
                                } else {
                                    $browserButton.prev(":text").val(data.url);
                                }
                                if (settings.callback != null && typeof settings.callback == "function") {
                                    settings.callback(data.url);
                                }
                                cache = {};
                                $dialog.next(".dialogOverlay").andSelf().remove();
                            } else {
                                $.message(data.message);
                            }
                        }
                    });

                });
            });
        }
    });

    // 令牌
    $(document).ajaxSend(function(event, request, settings) {
        if (!settings.crossDomain && settings.type != null && settings.type.toLowerCase() == "post") {
            var token = getCookie("token");
            if (token != null) {
                request.setRequestHeader("token", token);
            }
        }
    });

    $(document).ajaxComplete(function(event, request, settings) {
        var loginStatus = request.getResponseHeader("loginStatus");
        var tokenStatus = request.getResponseHeader("tokenStatus");

        if (loginStatus == "accessDenied") {
            $.message("warn", "登录超时，请重新登录");
            setTimeout(function() {
                location.reload(true);
            }, 2000);
        } else if (loginStatus == "unauthorized") {
            $.message("warn", "对不起，您无此操作权限！");
        } else if (tokenStatus == "accessDenied") {
            var token = getCookie("token");
            if (token != null) {
                $.extend(settings, {
                    global: false,
                    headers: {token: token}
                });
                $.ajax(settings);
            }
        }
    });

})(jQuery);

// 令牌
$().ready(function () {

    var $selectAll = $("#selectAll");
    var $contentRow = $("#listTable tr:gt(0)");
    var $ids = $("input[name='ids']");

    // 全选
    $selectAll.click(function () {
        var $this = $(this);
        var $enabledIds = $("#listTable input[name='ids']:enabled");
        if ($this.prop("checked")) {
            $enabledIds.prop("checked", true);
        } else {
            $enabledIds.prop("checked", false);
        }
    });

    // 点击行
    $contentRow.click(function (event) {
        var $idsCheck = $(this).find("input[name='ids']");
        //alert($idsCheck.prop("checked"));
        if ($idsCheck.prop("checked")) {
            $idsCheck.prop("checked", false);
        } else {//alert();
            $idsCheck.prop("checked", true);
        }
        //event.preventDefault();
    });

    // 选择
    $ids.click(function (event) {
        event.stopPropagation();
    });


    // ajax令牌
    $(document).ajaxSend(function (event, request, settings) {
        if (!settings.crossDomain && settings.type != null && settings.type.toLowerCase() == "post") {
            var token = getCookie("token");
            if (token != null) {
                request.setRequestHeader("token", token);
            }
        }
    });

    //form令牌
    $("form").attr("autocomplete", "off");
    $("form").submit(function () {
        var $this = $(this);
        //$this.find(":submit").prop("disabled", true);
        $('input[placeholder]', this).each(function () {
            var input = $(this);
            if (input.val() == input.attr('placeholder')) {
                input.val('');
                input.removeClass('placeholder');
            }
        });
        var inputLength = $this.find("input[name='token']").length;//size(),1.8以后已经废弃了
        if ($this.attr("method") != null && $this.attr("method").toLowerCase() == "post" && inputLength == 0) {
            var token = getCookie("token");
            if (token != null) {
                $this.append('<input type="hidden" name="token" value="' + token + '" \/>');
            }
        }
    });

    if (!placeholderSupport()) {   // 判断浏览器是否支持 placeholder
        $('input[placeholder]').focus(function () {
            var input = $(this);
            if (input.val() == input.attr('placeholder')) {
                input.val('');
                input.removeClass('placeholder');
            }
        }).blur(function () {
            var input = $(this);
            if (input.val() == '' || input.val() == input.attr('placeholder')) {
                input.addClass('placeholder');
                input.val(input.attr('placeholder'));
            }
        }).blur();
    }

    function placeholderSupport() {
        return 'placeholder' in document.createElement('input');
    }
    var filename = location.pathname;
    filename = filename.substr(filename.lastIndexOf('/')+1);
    // 列表查询
    console.info('filename:'+filename);
    if (location.search != "" && filename == "list") {
        var sh = location.search;
        var index = sh.indexOf('flash_message_attribute_name');
        if(index != -1){
            sh = sh.substr(0,index-1);
        }
        addCookie("listQuery", sh);
        //var ck = getCookie("listQuery");
        //console.info('ck2:'+ck);
    } else {
        //removeCookie("listQuery");
    }
});