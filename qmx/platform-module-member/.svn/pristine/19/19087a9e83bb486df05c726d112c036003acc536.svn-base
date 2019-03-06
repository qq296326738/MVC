<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
<title>列表</title>
<#include "/include/header_include_old.ftl">
</head>
<body>
	<div class="path main">
	    <div class="con_head bg_deepblue">
           列表
	    </div>
	</div>
	<form id="listForm" action="list.jhtml" method="get">
		<div class="bar">
			<br/>
			<input type="text" placeholder="接口名称" value="${(queryDto.name)!}" name="name" maxlength="200" />
			<input type="text" placeholder="appKey" value="${(queryDto.appKey)!}" name="appKey" maxlength="200" />
            <select name="apiPlat">
                <option value="">--接口类型--</option>
				<#list apiPlats as a>
                    <option <#if queryDto?? && queryDto.apiPlat?? && queryDto.apiPlat == a>selected</#if> value="${a!}">${a.getName()!}</option>
				</#list>
            </select>
			<button type="submit" class="button">查询</button>
			<button type="button" class="button" onclick="location.href='list';">重置</button>
		</div>
		<div class="bar">
			&nbsp;
                <a href="add" class="button">
                    添加接口
                </a>
		</div>
		<table id="listTable" class="list">
			<tr>
				<th class="check">
					<input type="checkbox" id="selectAll" />
				</th>
				<th>对应接口</th>
				<th>对应用户</th>
				<th>appKey</th>
                <th>订单来源</th>
				<th>支付方式</th>
                <th>下单后自动支付</th>
				<th>支付后自动发码</th>
                <th>是否已发货</th>
                <th>所属供应商</th>
                <th>是否启用</th>
                <th>添加日期</th>
                <th>添加人</th>
				<th>
					<span>操作</span>
				</th>
			</tr>
			<#list page.records as dto>
				<tr>
					<td>
						<input type="checkbox" name="ids" value="${dto.id}" />
					</td>
					<td>
						<#if dto.apiPlat??>
                            ${dto.apiPlat.getName()!}
						<#else>
						-
						</#if>
					</td>
					<td>
						${dto.name!'-'}
					</td>
					<td>
						${dto.appKey!}
					</td>
                    <td>
						${dto.orderSourceName!'-'}
                    </td>
                    <td>
					<#if dto.payMethod??>
					${dto.payMethod.getName()!}
					    <#else>
					-
					</#if>
					</td>
                    <td>${dto.autoPay?string('是','否')}</td>
                    <td>${dto.autoSendCode?string('是','否')}</td>
                    <td>${dto.shippingStatus?string('是','否')}</td>
                    <td>
					${dto.memberName!}
                    </td>
					<td>
                        <span class="${dto.enable?string("true", "false")}Icon">&nbsp;</span>
					</td>
					<td>${dto.createTime?string("yyyy-MM-dd HH:mm:ss")}</td>
                    <td>${dto.createBy!'-'}</td>
					<td>
                        <a href="edit?id=${dto.id}">[编辑]</a>
						<a class="del" url="delete?id=${dto.id}">[删除]</a>
					</td>
				</tr>
			</#list>
		</table>
			<#include "/include/pagination.ftl">
	</form>
</body>
<script type="text/javascript">
    $(function () {
        $(".del").click(function () {
            var flag = window.confirm("确定删除？");
            if(flag == true){
                var url = $(this).attr("url");
                location.href = url;
            }
        });
    })
</script>
</html>