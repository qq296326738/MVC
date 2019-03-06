/* 显示评价星星 */
function score(num,o){
	var n,q,h,w;
	if(parseFloat(num) == num){
		n = num.toString();
		w = n.indexOf('.');
		if(w >= 0){
			q = parseInt(n.substr(0,w));
			h = parseInt(n.substr(w+1,n.length-w-1));
		}else{
			q = n;
			h = 0;
		}
		if(q <= 5 && q >= 0){
			var p = (h.toString().length == 1 ? h + '0%' : h.toString().substr(0,2) + '%');
			if(p == "00%"){
				for(var i=5;i>0;i--){
					if(i>q){
						o.find('.star_f').eq(i-1).width('0%');
					}else{
						o.find('.star_f').eq(i-1).width('100%');	
					}
				}
			}else{
				for(var i=5;i>0;i--){
					if(i>q){
						o.find('.star_f').eq(i-1).width('0%');
					}else if(i==q){
						o.find('.star_f').eq(i).width(p);
						q == 5 ? o.find('.star_f').eq(4).width('100%') : '';
					}else{
						o.find('.star_f').eq(i-1).width('100%');	
					}
				}
			}
		}
	}
	return 0;
}

/*计算总价*/
function getTotal(o){
	if(o == ''){
		return;	
	}
	/*o传入的计算总价的相关参数
	*	o.n 商品数量的类名
	*	o.p	商品单价的类名
	*	o.parents 每件商品的数量与单价的公共上级节点
	*	o.showN 订单的商品总量
	*	o.showT 订单的总价
	**/
	
	var total=0,number=0,p,n;
	$(o.n).each(function(){
		n = parseFloat($(this).val());
		p = parseFloat(($(this).parents(o.parents).find(o.p).text()).substring(1));
		number += n;
		total += n*p;
	})
	$(o.showT).text('￥'+total);
}

/*阻止事件默认动作*/
function stopEvent(event){
	if(event.target.type == 'range') return;
	event.preventDefault();
}

function deleteMove(){
	document.addEventListener('touchmove',stopEvent);
}
function addMove(){
	document.removeEventListener('touchmove',stopEvent);
}

/*显示弹层*/
function openPop(){
	var tp = document.body.scrollTop || document.documentElement.scrollTop;
	tp = tp + 200;
	$('#shade').show().on('touchmove',deleteMove);
	$('#pop').css('top',tp+'px');
}
/*隐藏弹层*/
function closePop(){
	$('#shade').hide().find('.num_box_number').val('1');	
	addMove();
}


(function($){
	/* 详情切换 */
	$('.det_nav div').on('touchstart',function(){
		var t = $(this).data('type');
		$(this).addClass('picth').siblings('div').removeClass('picth');
		$('.det_block').hide();
		$('.det_'+t).show();
	})
	
	$('.score').each(function(){
		score($(this).data('score'),$(this));	
	})
	
	/* 评价星级选择 */
	$('#eva_score').find('.starbox').each(function(){
		$(this).on('click',function(){
			score($(this).data('score'),$('#eva_score'));
		})	
	})
	
	/*数量加减*/
	$('.num_minus').on('touchstart',function(event){
		var node = $(this).siblings('.num_box_number');
		number = parseInt(node.val());
		number = number && number>2? number-1:1;
		node.val(number);
		if($('.total').length > 0){
			getTotal({
				'p':'.fil_buy .fg_orange_1',
				'n':'.num_box_number',
				'parent':'.fil_buy',
				'showT':'.total'
			})
		}
		event.stopPropagation();
	})
	$('.num_plus').on('touchstart',function(event){
		var node = $(this).siblings('.num_box_number');
		number = parseInt(node.val());
		number = number ? number+1:1;
		node.val(number);
		if($('.total').length > 0){
			getTotal({
				'p':'.fil_buy .fg_orange_1',
				'n':'.num_box_number',
				'parent':'.fil_buy',
				'showT':'.total'
			})
		}
		event.stopPropagation();
	})
	$('.num_box_number').on('input propertychange',function(){
		if(parseInt($(this).val()) > 0){
			getTotal({
				'p':'.fil_buy .fg_orange_1',
				'n':'.num_box_number',
				'parent':'.fil_buy',
				'showT':'.total'
			})
		}
	})
	
	/* 支付方式选择 */
	$('.pay_way_row').on('touchstart',function(){
		$('.pay_way_row').each(function(){
			$(this).find('.pay_ico_pitch').hide();
			$(this).find('.pay_way_radio').attr('checked','false');	
		});
		$(this).find('.pay_ico_pitch').show();
		$(this).find('.pay_way_radio').attr('checked','true');
	})
	
	/* 登录方式切换 */
	$('.log_box_nav li').click(function(){
		var index = $(this).index();
		$(this).addClass('picth').siblings('li').removeClass('picth');
		$('.login_box').eq(index).show().siblings('.login_box').hide();
	})
	
	/* 热门推荐切换 */
	$('.hotA_box_nav .width-4').click(function(){
		var index = $(this).index();
		$(this).addClass('picth').siblings('.width-4').removeClass('picth');
		$('.hotA_pro_type').eq(index).show().siblings('.hotA_pro_type').hide();
	})
	
	/*详情门票收缩展开*/
	$('.det_pro_title').click(function(){
		$(this).toggleClass('picth');
	})
})(Zepto);