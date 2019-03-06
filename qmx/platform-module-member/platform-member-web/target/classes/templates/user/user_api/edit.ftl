<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8"/>
    <title>添加</title>
<#include "/include/header_include_old.ftl">
    <link href="${base}/resources/admin/dropdown/dropdown.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${base}/resources/admin/dropdown/jquery.dropdown.js"></script>
    <script type="text/javascript" src="${base}/resources/admin/dropdown/jquery.dropqtable.js"></script>
    <style type="text/css">
        .roles label {
            width: 150px;
            display: block;
            float: left;
            padding-right: 6px;
        }
    </style>
    <script type="text/javascript">
        $().ready(function () {

            var $inputForm = $("#inputForm");
            var $selectAll = $("#inputForm .selectAll");
            var $areaId = $("#areaId");
        });
    </script>
</head>
<body>
<div class="path main">
    <div class="con_head bg_deepblue">
        添加
    </div>
</div>
<form id="inputForm" action="update" method="post">
    <ul id="tab" class="tab">
        <li>
            <input type="button" value="基本信息"/>
            <input value="${dto.id}" type="hidden" name="id"/>
        </li>
    </ul>
    <table class="input tabContent">
        <tr>
            <th>
                <span class="requiredField">*</span>选择订单来源:
            </th>
            <td>
                <select name="orderSourceId">
                <#if orderSources??>
                    <#list orderSources as a>
                        <option value="${a.id!}" <#if dto.orderSourceId = a.id>selected</#if>>${a.remark!}
                            -${a.name!}</option>
                    </#list>
                </#if>
                </select>
            </td>
        </tr>
        <tr>
            <th>
                <span class="requiredField">*</span>默认支付方式:
            </th>
            <td>
                <select name="payMethod">
                <#list payMethods as a>
                    <option value="${a!}"
                            <#if dto.payMethod?? && dto.payMethod == a>selected</#if>>${a.getName()!}</option>
                </#list>
                </select>
            </td>
        </tr>
        <input type="hidden" name="autoPay" value="false"/>
    <#--<tr>
        <th>
            <span class="requiredField">*</span>下单后自动支付:
        </th>
        <td>
            <input type="checkbox" name="autoPay" <#if dto.autoPay>checked</#if> value="true" />
        </td>
    </tr>-->
        <tr>
            <th>
                <span class="requiredField">*</span>支付后自动发码:
            </th>
            <td>
                <input type="checkbox" name="autoSendCode" <#if dto.autoSendCode>checked</#if> value="true"/>
            </td>
        </tr>
        <tr>
            <th>
                <span class="requiredField">*</span>已发货:
            </th>
            <td>
                <input type="checkbox" name="shippingStatus" <#if dto.shippingStatus>checked</#if> value="true"/>
            </td>
        </tr>
        <tr>
            <th>
                <span class="requiredField">*</span>clientId:
            </th>
            <td>
                <input type="text" name="clientId" value="${dto.clientId!}" style="width: 300px;" class="text"
                       maxlength="60"/>
            </td>
        </tr>
        <tr>
            <th>
                <span class="requiredField">*</span>appKey:
            </th>
            <td>
                <input type="text" name="appKey" value="${dto.appKey!}" style="width: 300px;" class="text"
                       maxlength="60"/>
            </td>
        </tr>
        <tr>
            <th>
                <span class="requiredField">*</span>secretKey:
            </th>
            <td>
                <input type="text" name="secretKey" value="${dto.secretKey!}" style="width: 300px;" class="text"
                       maxlength="80"/>
            </td>
        </tr>
        <tr>
            <th>
                <span class="requiredField">*</span>接口唯一标识:
            </th>
            <td>
                <input type="text" class="text" value="${dto.apiPrefix!}" style="width: 300px;" name="apiPrefix"/>
            </td>
        </tr>
        <tr>
            <th>
                <span class="requiredField">*</span>消费通知地址:
            </th>
            <td>
                <input type="text" style="width: 90%;" value="${dto.consumeNotifyUrl!}" class="text"
                       name="consumeNotifyUrl"/>
            </td>
        </tr>
        <tr>
            <th>
                <span class="requiredField">*</span>退款通知地址:
            </th>
            <td>
                <input type="text" class="text" value="${dto.refundNotifyUrl!}" style="width: 90%;"
                       name="refundNotifyUrl"/>
            </td>
        </tr>
        <tr>
            <th>
                <span class="requiredField">*</span>发码通知地址:
            </th>
            <td>
                <input type="text" class="text" value="${dto.sendCodeNotifyUrl!}" style="width: 90%;"
                       name="sendCodeNotifyUrl"/>
            </td>
        </tr>
        <tr>
            <th>
                <span class="requiredField">*</span>是否启用:
            </th>
            <td>
                <input type="checkbox" <#if dto.enable>checked</#if> name="enable" value="true"/>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                &nbsp;
            </td>
        </tr>

    </table>
    <table class="input">
        <tr>
            <th>
                &nbsp;
            </th>
            <td>
                <input type="submit" class="button" value="提交"/>
                <input type="button" class="button" value="返回" onclick="history.back();"/>
            </td>
        </tr>
    </table>
</form>
</body>
</html>