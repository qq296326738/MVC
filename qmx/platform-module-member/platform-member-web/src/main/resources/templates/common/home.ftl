<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!--<meta name="viewport" content="width=device-width, initial-scale=1">-->
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>首页</title>
    <!-- Bootstrap -->
    <link href="${base}/resources/assets/bootstrap/css/bootstrap-modify.css" rel="stylesheet"/>
    <!--<link href="css/bootstrap.min.css" rel="stylesheet" />-->
    <link href="${base}/resources/assets/bootstrap-select/css/bootstrap-select.css" rel="stylesheet"/>
    <link href="${base}/resources/module/system/bxslider/jquery.bxslider.min.css" rel="stylesheet"/>
    <link href="${base}/resources/module/system/css/gds.css" rel="stylesheet"/>
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <script src="${base}/resources/common/js/jquery-3.2.1.min.js"></script>
    <script src="${base}/resources/module/system/bxslider/jquery.bxslider.min.js"></script>
    <style>
        html, body {
            width: 100%;
            height: 100%;
        }
    </style>
</head>
<body class="container gds-pull">
<div class="gds-pull-index">
    <div class="gds-pull-head">
        <h4>
            <small class="pull-right">今天是 ${(dateTime)!}</small>
        </h4>
    </div>
    <div class="gds-welcome-box">
        <div class="gds-table">
            <div class="row">
                <div class="gds-welcome-cell col-xs-10">
                    <div class="gds-section">
                        <div class="gds-section-title">
                            <i class="gds-icon gds-icon-info"></i>
                            <h5>账号信息</h5>
                        </div>
                        <div class="gds-section-body">
                            <p class="gds-section-row">系统角色：${homeUserInfo.userType.getName()!}</p>
                            <p class="gds-section-row">账号：${homeUserInfo.account!}<a class="gds-btn-3"
                                                                                     href="/modifyUserPwd"
                                                                                     target="mainFrame">修改密码</a></p>
                            <p class="gds-section-row">用户名：${homeUserInfo.username!}</p>
                            <p class="gds-section-row">手机号：${homeUserInfo.phone!}<a class="gds-btn-3"
                                                                                    href="/modifyPhone">修改手机号</a></p>
                            <p class="gds-section-row">账号状态：<span class="gds-fg-success">启用</span></p>
                            <p class="gds-section-row">绑定邮箱：${homeUserInfo.email!}<a class="gds-btn-3"
                                                                                     href="/modifyUserEmail">更换绑定邮箱</a>
                            </p>
                            <p class="gds-section-row">最后一次登录时间：${homeUserInfo.lastLoginTime?datetime}</p>
                        </div>
                    </div>
                </div>
                <div class="gds-welcome-cell col-xs-10">
                    <div class="gds-section">
                        <div class="gds-section-title">
                            <i class="gds-icon gds-icon-info"></i>
                            <h5>通知<a class="pull-right" href="/sysMessage/myList">更多>></a></h5>
                        </div>
                        <div class="gds-section-body" style="min-height: 237px\0;">
                            <div class="gds-section-notice">
                                <div class="gds-section-notice-title"><#if lastSysMessage??>
                                    [${lastSysMessage.reciveUserType.getName()!}]${lastSysMessage.title!}   ${lastSysMessage.createTime?datetime}</#if></div>
                                <div class="gds-section-notice-body">
                                <#if lastSysMessage??>
                                    <p>${lastSysMessage.content!}</p>
                                <#else>
                                    <p>暂无消息</p>
                                </#if>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        <#if homeMemberInfo.userType =="supplier" || homeMemberInfo.userType =="group_supplier" || homeMemberInfo.userType =="admin">
            <div class="row">
                <div class="gds-welcome-cell col-xs-10">
                    <div class="gds-section">
                        <div class="gds-section-title">
                            <i class="gds-icon gds-icon-info"></i>
                            <h5>短信平台</h5>
                        </div>
                        <div class="gds-section-body">
                            <p class="gds-section-row">
                                当前短信使用总量：${smsCount!0}<span>（更新于<#if lastUpdateTime??>${lastUpdateTime!?datetime}</#if>）</span>
                            </p>
                            <p class="gds-section-row">
                                短信计费方式：${(userChargeInfo.normalSmsCharge)!'-'}元/条<span>（说明：每${(userChargeInfo.normalSmsWordsCount)!'-'}字为一条）</span>
                            </p>
                        </div>
                    </div>
                </div>
                <div class="gds-welcome-cell col-xs-10">
                    <div class="gds-section">
                        <div class="gds-section-title">
                            <i class="gds-icon gds-icon-info"></i>
                            <h5>使用说明</h5>
                        </div>
                        <div class="gds-section-pull">
                            <a class="gds-btn-4" href="/help/list;">立即进入使用说明</a>
                        </div>
                    </div>
                </div>
            </div>
        </#if>
        </div>
        <div class="gds-pull-banner">
            <ul class="bxslider">
                <li>
                    <div class="gds-banner-img">
                        <img src="${base}/resources/module/system/bxslider/banner/banner_1.jpg"/>
                    </div>
                </li>
                <li>
                    <div class="gds-banner-img">
                        <img src="${base}/resources/module/system/bxslider/banner/banner_2.jpg"/>
                    </div>
                </li>
            </ul>
        </div>
    </div>

</div>
</body>
<!--<script src="js/customer/js/minified/jquery-1.9.1.min.js"></script>
<script src="js/customer/jquery.mCustomScrollbar.concat.min.js"></script>-->
<script>
    $(document).ready(function () {
        $('.bxslider').bxSlider();
    });
</script>
</html>