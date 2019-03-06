// 日期选择
function calendar(option,selector,p,json,number){		//option节点 selector显示子节点 p提示内容 number时间顺序
	var json = json;
	window.scrollTo(0,0);
	var cal = $('#calendar'),
		date = new Date(),
		yp = 0,
		ty = $(option).data('datetype');		//日期类型
	cal.show();
	cal.animate({
		right : 0,
	},300,'ease-out',function(){
		$(this).siblings('.wap_box').hide();
	});
	
	cal.data('create') == false ? createCalendar() : '';
	
	cal.find('.cal_tit_info span').text(p);
	bindEvent();
	
	/*创建日历*/
	function createCalendar(d_t){
		var dy_t = d_t ? parseInt(d_t.substr(0,4)) : date.getFullYear();
		var dm_t = d_t ? parseInt(d_t.substr(d_t.indexOf("-")+1,d_t.lastIndexOf("-")-d_t.indexOf("-"))) : date.getMonth()+1;
		var dd_t = d_t ? parseInt(d_t.substr(d_t.lastIndexOf("-")+1,d_t.length-d_t.lastIndexOf("-"))) : date.getDate();
		cal.find('.cal_box').each(function(){
			var m = date.getMonth() + yp;
			var y = date.getFullYear();
			var j = 1;
			m>11 ? (y=y+1) && (m=m-12) : '';
			var n = m < 9 ? '0'+(m+1) : m+1;
			yp++;
			$(this).find('.cal_ym').text(y+'年'+(m+1)+'月');
			var md = new Date(y,m+1,0).getDate();
				mz = new Date(y,m,1).getDay();
			mz == 0 ? mz = 7 : '';
			for(var i=mz;i<mz+md;i++){
				var k = j < 10 ? '0'+j : j;
				if(dy_t>y || (y==dy_t && (m+1)<=dm_t && j<dd_t) || typeof(json[y+"-"+n+"-"+k]) == "undefined"){
					$(this).find('td').eq(i).text(j);
				}else{
					$(this).find('td').eq(i).append("<div data-date='"+y+"-"+(n)+"-"+k+"'><span>"+j+"</span><p></p></div>");
				}
				j++;
			}
		})
		cal.data('create',true);
	}
	
	/*绑定点击事件赋值*/
	function bindEvent(){
		var d_t,n_t,nc_t;
		if(number){
			cal.find('.picth').each(function(){
				if($(this).data('level') == (number-1)){
					d_t = $(this).data('date');
				}else if($(this).data('level') == (number+1)){
					n_t = $(this).data('date');
					nc_t = $(this).attr('class');
				}
			})
		}
		cal.find('td div').each(function(){
			if(d_t && (new Date($(this).data('date')) > new Date(d_t))){
				$(this).on('click',function(){
					//离店时间 事件
					$('.'+ty).removeClass(ty + ' picth').find('p').text('');
					$(this).attr('class',ty).data('level',number).data('source',$(option).attr('id'));
					$('.'+ty).addClass('picth').find('p').text(p);
					$(option).attr('data-date',$(this).data('date')).find(selector).text($(this).data('date'));
					
					var startDate  = parseDate($("#ruzhu").attr("data-date"));
					var endDate = parseDate($(this).data('date'));
					var i = 0;
					var current = startDate;
					var $price = 0;
					while(i < startDate.DateDiff('d', endDate)) {
						var dstr = current.Format('YYYY-MM-DD');
						var pr = json[dstr];
						if(!!!pr) {
							alert("无该日价格:"+dstr);
							$("#lidian").attr("data-date",dstr);
							$("#edatespan").text(dstr);
							endDate = parseDate(dstr);
							break;
						}
						$price = $price + pr;
						current = current.DateAdd('d', 1);
						i++;
					}
					$("#daynum").text(startDate.DateDiff('d', endDate));
					
					$(".fg_orange_1").text("￥"+$price+"/"+startDate.DateDiff('d', endDate)+"晚");
					$(".total").text("￥"+$price*$("#size").val());
					cal.animate({
						right : "-100%"	
					},300,'ease-out',function(){
						$(this).hide();	
					}).siblings('.wap_box').show();
					cal.find('td div').each(function(){
						$(this).off('click');	
					});
				})
			}else{
				if(!d_t){
					$(this).on('click',function(){
						if(n_t && (new Date($(this).data('date')) >= new Date(n_t))){
							var nt_t,nid_t,date_t = $(this).data('date');
							$('.picth').each(function(){
								if($(this).data('level') == (number+1)){
									nt_t = $(this).find('p').text();
									nid_t = $(this).data('source');
									$(this).removeAttr('class').find('p').text('');
								}	
							});
							cal.find('td div').each(function(){
								if($(this).data('date') == getNextDay(date_t)){
									$(this).attr('class',nc_t).data('level',number+1).data('source',nid_t).find('p').text(nt_t);
									$('#'+nid_t).find(selector).text($(this).data('date'));
								}
							})
						}
						
						$('.'+ty).removeClass(ty + ' picth').find('p').text('');
						$(this).attr('class',ty).data('level',number).data('source',$(option).attr('id'));
						$('.'+ty).addClass('picth').find('p').text(p);
						$(option).attr('data-date',$(this).data('date')).find(selector).text($(this).data('date'));
						$(".fg_orange_1").text("￥"+json[$(this).data('date')]+"/张");
						$(".total").text("￥"+json[$(this).data('date')]*$("#size").val());
						cal.animate({
							right : "-100%"	
						},300,'ease-out',function(){
							$(this).hide();	
						}).siblings('.wap_box').show();
						cal.find('td div').each(function(){
							$(this).off('click');	
						});
					})	
				}
			}
		});
	}
	
	/*获取指定日期后一天*/
	function getNextDay(d_t){
		d_t = new Date(d_t);
		d_t = +d_t + 1000*60*60*24;
		d_t = new Date(d_t);
		d_m = d_t.getMonth() < 9 ? '0'+(d_t.getMonth()+1) : d_t.getMonth()+1;
		d_d = d_t.getDate() < 10 ? '0'+d_t.getDate() : d_t.getDate();
		return d_t.getFullYear()+"-"+d_m+"-"+d_d;
	}
}