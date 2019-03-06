// Zepto.slider.js V1.1

;(function($){	
	$.fn.slider = function(option){
		var opts = $.extend({},$.fn.slider.defaults,option),	//配置选项
		$slider = this,
		$slideImgBox = $slider.find(opts.slideImgBox),			//获取滑动图片容器
		$slidePromptBox = $slider.find(opts.slidePromptBox),	//获取提示器容器
		isAnimate = false,
		step = 1,
		$slideImgArray = $slideImgBox.find(opts.slideimg),		//当前轮播滑动图片的数组
		number = parseInt($slideImgArray.length),				//滑动图片的个数
		eachFixWidth = numToFix(100 / (number+2)),
		eachNumberWidth = 0,
		oldClientX = 0,
		curClientX = 0,
		oldImgBoxLeft = 0,
		curimgBoxLeft = 0,
		totalWidth = 0,
		gap = 0;
		
		
		
		//初始化		
		if(opts.diretion == 'v'){
			$slideImgBox.css('width',numToFix(100*(number+2)));
			$slideImgArray.css('width',eachFixWidth);
			$slideImgArray.css('float','left');
			$slideImgArray.first().before($slideImgArray.eq(number-1).clone());
			$slideImgBox.append($slideImgArray.eq(0).clone());
		}
		
			
		//注册滑动事件
		$slideImgArray.on('touchstart',function(e){
			oldClientX = e.touches[0].clientX;
			//totalWidth = $slideImgBox.width();		
		}).on('touchmove',function(e){
			//eachNumberWidth = $(this).width();
			curClientX = e.touches[0].clientX;		
			gap = curClientX-oldClientX;
			//$slideImgBox.css('margin-Left',oldImgBoxLeft + gap);
		})/*.on('touchend',function(e){
			curClientX = e.touches[0].clientX;
			gap = curClientX -oldClientX;		
			curimgBoxLeft = parseInt($slideImgBox.css('margin-Left'));
			if(Math.abs(gap/$(this).width()) >= opts.slidePercent){			
				if(gap < 0){
					$slideImgBox.animate({'margin-Left' : oldImgBoxLeft - eachNumberWidth},200,function(){
						step += 1;
						if(step > number){
							$slideImgBox.css('margin-Left',-eachNumberWidth);
							step = 1;
						}
						oldImgBoxLeft = -step*eachNumberWidth;
						$slidePromptBox.find(opts.slidePrompt).eq(step-1).addClass(opts.promptClass).siblings().removeClass(opts.promptClass);
					});			
				}
				if(gap > 0){
					$slideImgBox.animate({'margin-Left' : oldImgBoxLeft + eachNumberWidth},200,function(){
						step -= 1;
						if(step < 1){
							$slideImgBox.css('margin-Left',-number*eachNumberWidth);
							step = number;
						}
						oldImgBoxLeft = -step*eachNumberWidth;
						$slidePromptBox.find(opts.slidePrompt).eq(step-1).addClass(opts.promptClass).siblings().removeClass(opts.promptClass);
					});
				}
			}else{
				$slideImgBox.animate({'margin-Left' : oldImgBoxLeft + 'px'},200);
			}
			e.preventDefault();
		});*/
		$slideImgArray.swipe(function(e){
			gap = curClientX -oldClientX;
			curimgBoxLeft = $slideImgBox.css('margin-Left');
			if(Math.abs(gap/$(this).width()) >= opts.slidePercent){			
				if(gap < 0){
					$slideImgBox.animate({'margin-Left' : fixOper(oldImgBoxLeft,eachNumberWidth,'-')},100,function(){
						step += 1;
						if(step > number){
							$slideImgBox.css('margin-Left','-' + eachNumberWidth);
							step = 1;
						}
						oldImgBoxLeft = -step*fixToNum(eachNumberWidth) + '%';
						$slidePromptBox.find(opts.slidePrompt).eq(step-1).addClass(opts.promptClass).siblings().removeClass(opts.promptClass);
						opts.callback();
					});		
				}
				if(gap > 0){
					$slideImgBox.animate({'margin-Left' : fixOper(oldImgBoxLeft,eachNumberWidth,'+')},100,function(){
						step -= 1;
						if(step < 1){
							$slideImgBox.css('margin-Left','-' + (number*fixToNum(eachNumberWidth)) + '%');
							step = number;
						}
						oldImgBoxLeft = -step*fixToNum(eachNumberWidth) + '%';
						$slidePromptBox.find(opts.slidePrompt).eq(step-1).addClass(opts.promptClass).siblings().removeClass(opts.promptClass);
						opts.callback();
					});
				}
			}else{
				$slideImgBox.animate({'margin-Left' : oldImgBoxLeft},200);
			}
			e.preventDefault();
		})
		
		
		//左移函数
		$(document).ready(function(e) {
			totalWidth = $slideImgBox.width();
			eachNumberWidth = parseInt($slideImgBox.css('width')) / (number+2) + '%';
			//eachNumberWidth = $slideImgArray.eq(0).width();
            $slideImgBox.css('margin-Left','-' + eachNumberWidth);
			oldImgBoxLeft = $slideImgBox.css('margin-Left');
			
			function leftMove(){
				$slideImgBox.animate({'margin-Left' : fixOper(oldImgBoxLeft,eachNumberWidth,'-')},100,function(){
					step += 1;
					if(step > number){
						$slideImgBox.css('margin-Left','-' + eachNumberWidth);
						step = 1;
					}
					oldImgBoxLeft = -step*fixToNum(eachNumberWidth) + '%';
					$slidePromptBox.find(opts.slidePrompt).eq(step-1).addClass(opts.promptClass).siblings().removeClass(opts.promptClass);
					opts.callback();
				});	
			}
			
			//var autoSlide = 
			setInterval(leftMove,opts.interval);
			//注册暂停/开始事件
			
        });	
		
		function fixedToFloat(fix){
			var length = fix.length-1;
			var number = fix.substr(0,length);
			return parseInt(number / 100);
		}
		
		function numToFix(num){
			return num + "%";	
		}
		
		function fixToNum(fix){
			return parseInt(fix);	
		}
		
		function fixOper(num1,num2,oper){
			var n1 = parseInt(num1.substr(0,num1.length-1));
			var n2 = parseInt(num2.substr(0,num2.length-1));
			switch(oper){
				case '+':
					return n1+n2+'%';
					break;
				case '-':
					return n1-n2+'%';
					break;
				default:
					alert('暂不支持该运算');
					break;
			}
		}
		
	}
	$.fn.slider.defaults = {
		diretion : 'v', //'h'纵向,'v'横向
		slidePercent : 0.01, //拖动超过多少百分比后才能翻页
		slideImgBox : '.slide-imgul', //装所有滑动图片的元素
		slideimg : 'li', //装滑动图片的元素
		imgWidth : '100%',	//图片宽度
		//imgHeight : 'auto', //图片高度
		slidePromptBox : '.slide-control', //装提示器的元素
		slidePrompt : 'li', //提示器的具体元素
		promptClass : 'picth', //提示样式名
		interval : 5000,		//自动切换的时间毫秒
		callback : function(){}		//滑动以后执行的回调函数
	}
})(Zepto);