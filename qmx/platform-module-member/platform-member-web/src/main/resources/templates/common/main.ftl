<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>gds管理系统-${(userInfo.account)!}</title>
    <#include "/include/common_header_include.ftl">
    <link rel="stylesheet" href="${base}/resources/module/system/css/admin.css">
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <div class="layui-logo">
            <ul class="layui-nav">
                <li class="layui-nav-item">
                    <a href="javascript:;">${currentModule.moduleName!}</a>
                    <dl id="menu" class="layui-nav-child" style="display: none;">
                    <#if modules??>
                        <#list modules as module>
                            <#if !module.basic>
                                <dd><a href="/?m=${module.id!}"><i class="${module.className!}"></i>${module.moduleName!}</a></dd>
                            </#if>
                        </#list>
                    </#if>
                    </dl>
                </li>
            </ul>
        </div>
        <!-- 头部区域（可配合layui已有的水平导航） -->
        <ul class="layui-nav layui-layout-left">
            <#if modules??>
                <#list modules as module>
                    <#if module.basic>
                        <li class="layui-nav-item <#if module.id == currentModule.id> layui-this</#if>"><a href="/?m=${module.id!}">${module.moduleName!}</a></li>
                    </#if>
                </#list>
            </#if>
            <#--<li class="layui-nav-item">
                <a href="javascript:;">其它系统</a>
                <dl class="layui-nav-child">
                    <dd><a href="">邮件管理</a></dd>
                    <dd><a href="">消息管理</a></dd>
                    <dd><a href="">授权管理</a></dd>
                </dl>
            </li>-->
        </ul>
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item">
                <a href="javascript:;">
                    <img src="${base}/resources/common/images/default-avatar.jpg" class="layui-nav-img">
                ${userInfo.userType.getName()!}（${userInfo.account}）
                </a>
                <#if bindUserList??>
                    <dl class="layui-nav-child" style="overflow-y: scroll;height: 500px;">
                        <#--<dd><a href="/modifyUserPwd" target="mainFrame">修改密码</a></dd>-->
                        <#list bindUserList as lst>
                            <dd><a href="/changeAccount?account=${lst.account!}">【${lst.userType.getName()}】${lst.username!}(${lst.account!})</a></dd>
                        </#list>
                    </dl>
                </#if>
            </li>
            <li class="layui-nav-item"><a href="/sso/logout">退出</a></li>
        </ul>
    </div>

    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
            <ul class="layui-nav layui-nav-tree">
                <li class="layui-nav-item">
                    <a href="/homePage" target="mainFrame">首页</a>
                </li>
            <#list (menus)! as menu>
                <#assign hasSubMenu = false />
                <#if menu.menuBeans?? && menu.menuBeans?size gt 0>
                    <#assign hasSubMenu = true />
                </#if>
                <li class="layui-nav-item" title="${(menu.remark)!}">
                    <a class="" target="mainFrame" <#if hasSubMenu> href="javascript:;" <#else> href="${(menu.url)!}"</#if> >
                        <i class="icon-book"></i>
                        ${(menu.menuName)!}
                    </a>
                    <#if hasSubMenu>
                        <dl class="layui-nav-child">
                        <#list menu.menuBeans as submenu>
                            <dd><a title="${(submenu.remark)!}" href="${(submenu.url)!}" target="mainFrame">${(submenu.menuName)!}</a></dd>
                        </#list>
                        </dl>
                    </#if>
                </li>
            </#list>
            </ul>
        </div>
    </div>
    <div class="layui-body">
        <!-- 内容主体区域 -->
        <#--<div style="padding: 15px;">内容主体区域</div>-->
        <div id="main-content">
            <iframe id="mainFrame" name="mainFrame" src="/homePage" width="100%" frameborder="0" marginheight="0" marginwidth="0"></iframe>
        </div>
    </div>
    <#--<div class="layui-footer">
        © 2018 - 底部固定区域
    </div>-->
</div>
<script>
    //JavaScript代码区域
    layui.use('element', function(){
        var element = layui.element;
        var setIframeHeight = function() {
            var wh = $(window).height();
            $('#mainFrame').css({
                height: wh-56+'px',
                backgroundColor: '#fff'
            });
        }
        $(window).resize(setIframeHeight);
        setIframeHeight();
    });
</script>
</body>
</html>