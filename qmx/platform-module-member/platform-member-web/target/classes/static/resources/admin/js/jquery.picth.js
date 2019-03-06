/*picth切换插件*/
(function($){
	$.fn.picth = function(o){
		
		var op = $.extend({
			'addClass' : 'picth',
			'event' : 'click',
			'dom' : ''
		},o);
		
		op.dom = this.selector;
		
		return this.each(function(){
			$(this).bind(op.event,function(){
				$(this).addClass(op.addClass).siblings(op.dom).removeClass(op.addClass);
			})
		})
	}
})(jQuery);