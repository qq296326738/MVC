<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
	    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
	    <title></title>
	    <script src="${base}/member/js/mui.min.js"></script>
	    <link href="${base}/member/css/mui.css" rel="stylesheet"/>
	    <link rel="stylesheet" type="text/css" href="${base}/member/css/member.css"/>
	    <script type="text/javascript" charset="utf-8">
	      	mui.init();
	    </script>
	    <style>
	    	html, body, .member {
	    		width: 100%;
	    		height: 100%;
	    	}
	    </style>
	</head>
	<body class="balance">
		<div class="member">
			<div class="mui-content full-white">
				<div class="pay-state success">
					<div class="member-flex">
						<div>
							<h1>${money!}</h1>
							<p>当前余额(元)</p>
						</div>
					</div>
				</div>
			    <div class="member-table">
					<div class="member-table-cell">历史充值总额：${recharge!0}</div>
					<div class="member-table-cell">历史消费总额：${consumption!0}</div>
				</div>
				<a href="recharge?id=${id!}" class="member-btn-1">充值</a>
                <a href="qRcode?id=${id!}" class="member-btn-2">去消费</a>
			</div>
		</div>
	</body>
</html>
