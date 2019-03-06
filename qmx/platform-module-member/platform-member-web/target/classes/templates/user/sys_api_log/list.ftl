<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
<title>列表</title>
<#include "/include/header_include_old.ftl">
	<script type="text/javascript" src="${base!}/resources/assets/layer/layer.js"></script>
</head>
<body>
	<div class="path main">
	    <div class="con_head bg_deepblue">
			列表
	    </div>
	</div>
	<form id="listForm" action="list" method="get">
		<div class="bar">
			<br/>
			<input type="text" placeholder="账号" value="${(queryVo.account)!}" name="account" maxlength="200" />
			<input type="text" placeholder="订单号" value="${(queryVo.orderId)!}" name="orderId" maxlength="200" />
			<input type="text" placeholder="appKey" value="${(queryVo.appKey)!}" name="appKey" maxlength="200" />
			<input type="text" placeholder="apiPlat" value="${(queryVo.apiPlat)!}" name="apiPlat" maxlength="200" />
			<input type="text" placeholder="请求接口" value="${(queryVo.requestUri)!}" name="requestUri" maxlength="200" />
			<input type="text" placeholder="请求方法" value="${(queryVo.requestMethod)!}" name="requestMethod" maxlength="200" />
			<input type="text" placeholder="IP" value="${(queryVo.requestIp)!}" name="requestIp" maxlength="200" />
			<#--<input type="text" placeholder="success" value="${(queryVo.success)!}" name="success" maxlength="200" />-->
			<button type="submit" class="button">查询</button>
			<button type="button" class="button" onclick="location.href='list';">重置</button>
		</div>
		<div class="bar">
			&nbsp;
		</div>
		<table id="listTable" class="list">
			<tr>
				<th class="check">
					<input type="checkbox" id="selectAll" />
				</th>
				<th>账号</th>
				<th>订单ID</th>
				<th>appKey</th>
				<th>apiPlat</th>
				<th>请求接口</th>
				<th>请求方法</th>
				<th>请求时间</th>
				<th>请求耗时</th>
				<th>IP</th>
				<th>是否成功</th>
				<th>操作</th>
			</tr>
			<#list page.records as dto>
				<tr>
					<td>
						<input type="checkbox" name="ids" value="${dto.id}" />
					</td>
                    <td>${dto.account!}</td>
                    <td>${dto.orderId!}</td>
					<td>${dto.appKey!}</td>
					<td>${dto.apiPlat!}</td>
					<td>${dto.requestUri!}</td>
					<td>${dto.requestMethod!}</td>
					<td>${dto.createTime?datetime!}</td>
					<td>${dto.costTime!}</td>
					<td>${dto.requestIp!}</td>
					<td>${dto.success?string("是", "否")}</td>
					<td>
						<a class="viewApiLog" data-id="${dto.id!}">[查看]</a>
					</td>
				</tr>
			</#list>
		</table>
			<#include "/include/pagination.ftl">
	</form>
</body>
<script type="text/javascript">
    $(function () {
        $(".viewApiLog").click(function () {
            var data = $(this).attr("data-id");
            layer.open({
                type: 2,
                title: '详情',
                area: ['880px', '600px'], //宽高
                fix: true, //固定
                content: 'view?id=' + data
            });
        });
    })
</script>
</html>