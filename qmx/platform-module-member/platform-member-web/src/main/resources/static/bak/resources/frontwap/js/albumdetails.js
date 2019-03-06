;(function($){
	var b_w = document.body.offsetWidth,
		b_h = document.body.offsetHeight;
	$('.slide').css('height',b_h - 53);
	$('#total').text($('.slide-imgul li').length);
	getCurrent();
	$('.slide-imgul li img').each(function(){
		var w = $(this).width(),
			h = $(this).height(),
			p_h = $(this).parent().height();
		if(w/h >= 1){
			var m_h = p_h - b_w*h/w;
			$(this).css({
				"width" : "100%",
				"max-height" : "80%",
				"margin-top" : m_h/2-20
			})
		}else{
			$(this).css({
				"height" : "80%",
				"max-width" : "100%"	
			})	
		}
	})
	$('.slide').slider({
		interval : 10000,
		callback : function(){
			getCurrent();
		}
	});
	
	function getCurrent(){
		var num = $(".conter-ul .picth").index()+1;
		$('#current').text(num);
	}
})(Zepto);