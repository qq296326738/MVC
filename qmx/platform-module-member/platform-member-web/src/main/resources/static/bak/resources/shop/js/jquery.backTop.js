(function($){
	$.extend({
		backTop : function(o){
			$(document).ready(function(){
				var node = $('<a href="javascript:void(0);" class="backTop"><span>回&nbsp;到<br/>顶&nbsp;部</span></a>');
				$('div.foot').before(node);
				
				var star = 300,
				theight = $(document).height(),
				wheight = $(window).height();
				
				$(document).width() < 1460 ? $('.backTop').addClass('small') : '';
				
				$('.backTop').click(function(){
					$("html,body").animate({
						scrollTop : 0
					},500)
				})
				
				$(window).scroll(function(){
					var hidtop = $(document).scrollTop();
					hidtop > star ? $('.backTop').show() : $('.backTop').hide();
				})
			});
		}
	})
})(jQuery);