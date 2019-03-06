<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
    <title></title>
    <script src="${base}/member/js/mui.min.js"></script>
    <script src="${base}/member/js/jquery-3.2.0.min.js" ></script>
    <link href="${base}/member/css/mui.css" rel="stylesheet"/>
    <link rel="stylesheet" type="text/css" href="${base}/member/css/member.css"/>
    <script type="text/javascript" charset="utf-8">
      	mui.init();
    </script>
    <style>
    	html, body, .member {
    		height: 100%;
    	}
    </style>
</head>
<body class="coupon">
	<div class="member">
		<div class="mui-content">
			<h4 class="member-title">优惠券</h4>
		    <div class="member-coupon-box">
		    	<div class="member-coupon-header">
		    		<div class="mui-segmented-control">
			    	    <a class="mui-control-item mui-active" href="#item1">未使用</a>
			    	    <a class="mui-control-item" href="#item2">已使用</a>
			    	</div>
		    	</div>
		    	<div id="item1" class="member-coupon-body mui-control-content mui-active">
		    		<!--<div class="personal-result-box">
		    			<div class="personal-list">
			    			<div>
			    				<div class="personal-coupon">
			    					<img src="images/coupon.jpg" />
			    					<div class="personal-coupon-box">
			    						<h5>启明芯智能通用门票</h5>
			    						<p>兑换码:AJKDH123145</p>
			    						<p>优惠券名称：青年活动优惠券</p>
			    					</div>
			    					<div class="personal-coupon-info">
			    						<a class="coupon-btn" href="javascript:void(0);">未使用</a>
			    						<p style="font-size: 12px;">使用期限</p>
			    						<p>2017.12.25</p>
			    						<p>2018.12.31</p>
			    					</div>
			    				</div>
			    			</div>
			    			<div>
			    				<div class="personal-coupon">
			    					<img src="images/coupon.jpg" />
			    					<div class="personal-coupon-box">
			    						<h5>启明芯智能通用门票</h5>
			    						<p>兑换码:AJKDH123145</p>
			    						<p>优惠券名称：青年活动优惠券</p>
			    					</div>
			    					<div class="personal-coupon-info">
			    						<a class="coupon-btn" href="javascript:void(0);">未使用</a>
			    						<p style="font-size: 12px;">使用期限</p>
			    						<p>2017.12.25</p>
			    						<p>2018.12.31</p>
			    					</div>
			    				</div>
			    			</div>
			    		</div>
		    		</div>-->
		    		<div class="member-center fg-orange">您暂时未获得任何优惠券</div>
		    	</div>
		    	<div id="item2" class="member-coupon-body mui-control-content">
		    		<div class="personal-result-box">
		    			<div class="personal-list">
			    			<div>
			    				<a href="#popDetails" class="popBtn">
			    					<div class="personal-coupon">
				    					<img src="${base}/member/images/coupon.jpg" />
				    					<div class="personal-coupon-box">
				    						<h5>启明芯智能通用门票</h5>
				    						<p>兑换码:AJKDH123145</p>
				    						<p>优惠券名称：青年活动优惠券</p>
				    					</div>
				    					<div class="personal-coupon-info">
				    						<a class="coupon-btn coupon-disable" href="javascript:void(0);">已使用</a>
				    						<p style="font-size: 12px;">使用期限</p>
				    						<p>2017.12.25</p>
				    						<p>2018.12.31</p>
				    					</div>
				    				</div>
			    				</a>
			    			</div>
			    			<div>
			    				<a href="#popDetails" class="popBtn">
				    				<div class="personal-coupon">
				    					<img src="${base}/member/images/coupon.jpg" />
				    					<div class="personal-coupon-box">
				    						<h5>启明芯智能通用门票</h5>
				    						<p>兑换码:AJKDH123145</p>
				    						<p>优惠券名称：青年活动优惠券</p>
				    					</div>
				    					<div class="personal-coupon-info">
				    						<a class="coupon-btn coupon-disable" href="javascript:void(0);">已使用</a>
				    						<p style="font-size: 12px;">使用期限</p>
				    						<p>2017.12.25</p>
				    						<p>2018.12.31</p>
				    					</div>
				    				</div>
				    			</div>
			    			</div>
			    		</div>
		    		</div>
		    	</div>
		    </div>
		</div>
	</div>
	<div id="popDetails" class="mui-popover mui-popover-bottom">
		<div id="popCoupon" class="member-pop-coupon">
			<div class="member-flex">
				<label>券类型：</label>
				<div class="member-flex-main">
					<p>优惠券</p>
				</div>
			</div>
			<div class="member-flex">
				<label>有效期限：</label>
				<div class="member-flex-main">
					<p>2018/04/01-2019/04/01</p>
				</div>
			</div>
			<div class="member-flex">
				<label>使用平台：</label>
				<div class="member-flex-main">
					<p>微信会员中心</p>
				</div>
			</div>
			<div class="member-flex">
				<label>使用范围：</label>
				<div class="member-flex-main">
					<p>仅针对在微信会员中心内进行支付时使用</p>
				</div>
			</div>
		</div>
	</div>
	<script>
		$('.popBtn').on('tap',function() {
			couponDetailes({
				'券类型': '优惠券1111',
				'有限期限': '2020/04/01-2020/04/01',
				'使用平台': '微信会员中心',
				'使用范围': '仅针对在微信会员中心内进行支付时使用'
			});
		});
		
		function couponDetailes(obj) {
			var coupon = $('#popCoupon');
			
			coupon.html('');
			
			for(var label in obj) {
				var row = $('<div class="member-flex"><div>');
				row.html('<label>'+label+'：</label>'+'<div class="member-flex-main"><p>'+obj[label]+'</p></div>');
				coupon.append(row);
			}
		}
	</script>
</body>
</html>