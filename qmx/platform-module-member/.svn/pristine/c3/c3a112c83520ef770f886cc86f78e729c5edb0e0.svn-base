<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
	<title>查看</title>
<#include "/include/header_include_old.ftl">
</head>
<body>
	<div class="path main">
	    <div class="con_head bg_deepblue">
			查看
	    </div>
	</div>
	<table class="input">
		<tr>
			<th>
				API名称
			</th>
			<td>
				${(dto.requestUri)!}
			</td>
		</tr>
		<tr>
			<th>
				appKey
			</th>
			<td>
				${dto.appKey}
			</td>
		</tr>
		<tr>
			<th>
				ip:
			</th>
			<td>
				${dto.requestIp!}
			</td>
		</tr>
		<tr>
			<th>
				是否成功:
			</th>
			<td>
				<#if dto.success?? && dto.success>
				是
				<#else>
				否
				</#if>
			</td>
		</tr>
		<tr>
			<th>
				请求时间
			</th>
			<td>
				${dto.createTime?datetime}
			</td>
		</tr>
		<tr>
			<th>
				请求参数：
			</th>
			<td>
				<textarea class="textarea" style="width: 90%; height: 300px;" readonly="readonly">${dto.requestContent!}</textarea>
			</td>
		</tr>
		<tr>
			<th>
				返回参数:
			</th>
			<td>
				<textarea class="textarea" style="width: 90%; height: 300px;" readonly="readonly">${dto.resultContent!}</textarea>
			</td>
		</tr>
		<tr>
			<th>
				&nbsp;
			</th>
			<td>
                &nbsp;
			</td>
		</tr>
	</table>
</body>
</html>