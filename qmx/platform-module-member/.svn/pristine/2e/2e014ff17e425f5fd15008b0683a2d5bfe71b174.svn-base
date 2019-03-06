$(function(){
		var index = 1;
		var con = "${texts?size}";
		var isSupp = document.createElement("audio").canPlayType("audio/mpeg");
		if (isSupp==""){
    	}else{
			var r = new Audio("http://tsn.baidu.com/text2audio?tex=${texts[0]}&lan=zh&cuid=lxb&ctp=1&tok=24.5ce6be584bd7d0ce44975c22cef48636.2592000.1458377189.282335-7235898");
			var r2 = new Audio("http://tsn.baidu.com/text2audio?tex=${texts[1]}&lan=zh&cuid=lxb&ctp=1&tok=24.5ce6be584bd7d0ce44975c22cef48636.2592000.1458377189.282335-7235898");
			r.load();
			r2.load();
			r.onended =function(){
				$("#play-btn").attr("data-play","r2");
				index = index+1;
				if(index>con){
					index=1;
					r.src="http://tsn.baidu.com/text2audio?tex=${texts[0]}&lan=zh&cuid=lxb&ctp=1&tok=24.5ce6be584bd7d0ce44975c22cef48636.2592000.1458377189.282335-7235898";
					r.load();
					r2.src="http://tsn.baidu.com/text2audio?tex=${texts[1]}&lan=zh&cuid=lxb&ctp=1&tok=24.5ce6be584bd7d0ce44975c22cef48636.2592000.1458377189.282335-7235898";
					r2.load();
					$("#play-btn").attr("data-action","play");
					$("#play-btn").attr("src","http://yuyin.baidu.com/resources/online/common/widget/voice/img/speech_play.png");
					return;
				}
				r2.play();
				[#list texts as text]
					if(index == ${text_index}){
						r.src="http://tsn.baidu.com/text2audio?tex=${text}&lan=zh&cuid=lxb&ctp=1&tok=24.5ce6be584bd7d0ce44975c22cef48636.2592000.1458377189.282335-7235898";
						r.load();
					}
				[/#list]
            };
            r2.onended =function(){
            	$("#play-btn").attr("data-play","r");
				index = index+1;
				if(index>con){
					index=1;
					r.src="http://tsn.baidu.com/text2audio?tex=${texts[0]}&lan=zh&cuid=lxb&ctp=1&tok=24.5ce6be584bd7d0ce44975c22cef48636.2592000.1458377189.282335-7235898";
					r.load();
					r2.src="http://tsn.baidu.com/text2audio?tex=${texts[1]}&lan=zh&cuid=lxb&ctp=1&tok=24.5ce6be584bd7d0ce44975c22cef48636.2592000.1458377189.282335-7235898";
					r2.load();
					$("#play-btn").attr("data-action","play");
					$("#play-btn").attr("src","http://yuyin.baidu.com/resources/online/common/widget/voice/img/speech_play.png");
					return;
				}
            	r.play();
				[#list texts as text]
					if(index == ${text_index}){
						r2.src="http://tsn.baidu.com/text2audio?tex=${text}&lan=zh&cuid=lxb&ctp=1&tok=24.5ce6be584bd7d0ce44975c22cef48636.2592000.1458377189.282335-7235898";
						r2.load();
					}
				[/#list]
            };
			$("#play-btn").click(function(){
				var data = $(this).attr("data-action");
				var play = $(this).attr("data-play");
				if(data=="play"){
					$(this).attr("data-action","pause");
					if(play=="r2"){
						r2.play();
					}else{
						r.play();
					}
					$(this).attr("src","http://yuyin.baidu.com/resources/online/common/widget/voice/img/speech_pause.png");
				}else{
					$(this).attr("data-action","play");
					$(this).attr("src","http://yuyin.baidu.com/resources/online/common/widget/voice/img/speech_play.png");
					if(play=="r2"){
						r2.pause();
					}else{
						r.pause();
					}
				}
			});
    	}
	});