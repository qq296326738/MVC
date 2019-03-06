$("#banners").Xslider({
	// 默认配置
	affect: 'scrollx', //效果  有scrollx|scrolly|fade|none
	speed: 500, //动画速度
	space: 6000, //时间间隔
	auto: true, //自动滚动
	trigger: 'mouseover', //触发事件 注意用mouseover代替hover
	conbox: '#banner_list', //内容容器id或class
	ctag: 'div', //内容标签 默认为<a>
	switcher: '#banner_li', //切换触发器id或class
	stag: 'li', //切换器标签 默认为a
	current:'picth', //当前切换器样式名称
	rand:false //是否随机指定默认幻灯图片
});

/*经典案例左右切换*/
$('.det_sli_con').click(function(){
	var type = $(this).attr('id'),
		index = $('#banners .sli_c .picth').index(),
		cnode = $('#banners .sli_c li'),
		length = cnode.length;
	switch(type){
		case 'prev':
			if(index == 0){
				cnode.eq(length-1).mouseover();	
			}else{
				cnode.eq(index-1).mouseover();
			}
		break;
		case 'next':
			if(index+1 == length){
				cnode.eq(0).mouseover();	
			}else{
				cnode.eq(index+1).mouseover();	
			}
		break;
	}
})

/*产品详细选择*/
$('.det_cla_table .odd').click(function(){
	var pitch = $('.det_cla_table .odd.picth');
	if(pitch.attr('class') == $(this).attr('class')){
		$(this).removeClass('picth').next('.even').find('.br').hide();
	}else{
		$(this).addClass('picth').next('.even').find('.br').show().css('background','#fff9e5');
	}
})
$('.det_cla_table .odd .button_4').click(function(e){
	stopPropagation(e);
})

/*选择*/
$(".spotDetails .det_cla_title span").click(function(){
	var index = $(this).index();
	$(this).addClass('picth').siblings('span').removeClass('picth');
	$('.det_cla_table').eq(index).show().siblings('.det_cla_table').hide();
})
$(".det_box_con_nav li").click(function(){
	var index = $(this).index();
	$(this).addClass('picth').siblings('li').removeClass('picth');
	$('.det_box_con_list').eq(index).show().siblings('.det_box_con_list').hide();
})

/*快速跳转*/
$('#det_nav li').click(function(){
	$(this).addClass('picth').siblings('li').removeClass('picth');
})

function det_nav_scroll(){
	var oldTop = $('#det_nav').offset().top;
	$(window).unbind('scroll');
	$(window).bind('scroll',function(){
		var hidTop = $(document).scrollTop();
		if(hidTop >= oldTop){
			$('#det_nav').css({
				'position' : 'fixed',
				'top' : '0'
			});
		}else if(hidTop < oldTop){
			$('#det_nav').css('position','static');
		}
	});
}
det_nav_scroll();


/*阻止冒泡*/
function stopPropagation(e) {
    e = e || window.event;  
    if(e.stopPropagation) { //W3C阻止冒泡方法  
        e.stopPropagation();
    } else {  
        e.cancelBubble = true; //IE阻止冒泡方法 
    }  
} 

//微信我们hover 展开
$(".det_cla_tab_thwx").hover(function(e) {
	$(this).children(".det_cla_tab_code").stop().fadeIn(300);
},function(){
	$(this).children(".det_cla_tab_code").stop().fadeOut(300);
});
