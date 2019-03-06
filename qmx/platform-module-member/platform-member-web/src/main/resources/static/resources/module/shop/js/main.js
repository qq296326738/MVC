$(function(){
	//头部nav点击
	$(".head_nav>li").click(function(){
		$(".head_nav>li").removeClass("curr");
		$(this).addClass("curr");
		});
	//关注我们hover 展开
	$(".focus").hover(function(e) {
        $(this).children(".focusHover").stop().fadeIn(300);
    },function(){
		$(this).children(".focusHover").stop().fadeOut(300);
		});
	
	//首页banner搜索hover
	$(".Indexsearch>li").click(function(){
		$(".Indexsearch>li").removeClass("curr");
		$(this).addClass("curr");
		var indexI= $(this).index();
		$(".searchCont").find(".searchBox").addClass("none");
		$(".searchCont").find(".searchBox").eq(indexI).removeClass("none");
		});
	//景区页热门区域hover
	$(".hotSe>a").mouseenter(function(e) {
        $(".hotSe>a").removeClass("curr");
		$(this).addClass("curr");
		$(".hotSe>a").find("i").fadeIn(300);
		$(this).find("i").fadeOut(300);
		$(".hotSe>a").find(".hotse_t01").addClass("none");
		$(".hotSe>a").find(".hotse_t02").removeClass("none");
		$(this).find(".hotse_t01").removeClass("none");
		$(this).find(".hotse_t02").addClass("none");
    });
	

	var wid = $(window).width();
	var suv_box = $('.sub_sou');
	if(wid > 1200){
		suv_box.css({left:(wid-1200)/2+"px"});
	}else{
		suv_box.css({left:"0px"});
	}

	$(window).resize(function(){
		var wid = $(window).width();
		console.log(wid);
		if(wid > 1200){
			suv_box.css({left:(wid-1200)/2+"px"});
		}else{
			suv_box.css({left:"0px"});
		}
	});

	$('.jd_subnav>span').bind('click',function(){
		$('.jd_subnav>span').removeClass('curr');
		$(this).addClass('curr');
	});
	
	$("#homeScenicSearchBtn").click(function(){
		$("#homeScenicForm").submit();
	});
	$("#homeHotelSearchBtn").click(function(){
		$("#homeHotelForm").submit();
	});
	$("#homeTravelSearchBtn").click(function(){
		$("#homeTravelForm").submit();
	});
	
	$("#homeScenicForm input").keydown(function(event){
		if(event.keyCode == 13){
			$("homeScenicForm").submit();
		}
	});
	$("#homeHotelForm input").keydown(function(event){
		if(event.keyCode == 13){
			$("#homeHotelForm").submit();
		}
	});
	$("#homeTravelForm input").keydown(function(event){
		if(event.keyCode == 13){
			$("#homeTravelForm").submit();
		}
	});
});

var index = 1;
var set_val = '';
function banner(){
	set_val = setInterval(function(){
		console.log(index);
		$('.horel_cont').stop().animate({"left":-(100*index)+"%"},600);
		$('.bottom_nav >li>a').removeClass('active');
		$('.bottom_nav >li').eq(index).find('a').addClass('active');
		if(index<=2){
			index++
		}else{
			index=0;
		}
	},3000)
}
banner();

$('.bottom_nav >li>a').bind('click',function(){
	$('.bottom_nav> li> a').removeClass('active');
	$(this).addClass('active');
	index = $(this).parent().index();
	$('.horel_cont').stop().animate({"left":-(100*index)+"%"},600);
	clearInterval(set_val);
});
$('.bottom_nav').mouseenter(function(){
	clearInterval(set_val);
});

$('.bottom_nav').mouseleave(function(){
	banner();
});