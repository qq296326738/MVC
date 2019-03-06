
// JavaScript Document
(function($){
	
	var $calendar = $("#calendarcontainer");
	var $calendardata = {};
	var n = new parseDate($calendar.attr('ndate'));
	creatCalendar();
	$("#datePriceData").val() && ($calendardata = JSON.parse($("#datePriceData").val()));
	//var stockType = $("input[name=stockType]").val();
	fillDom($calendardata);
	
	var array = null;
	function fillDom(data) {
		for(var key in data) {
			var value = data[key];
			var d = parseDate(key);
			var t = $("#date td[data-date="+key+"]");
			var str = '';
			if(value.status == 1){
				str += "<p data-ctype='saleoff' data-date='"+key+"' class='sale_online' style='display:none;'><a href='javascript:;'>下架</a></p>";
			//} else {
				//t.find('div.sale_on').removeClass('sale_on').addClass('out');
			}
			t.data('data', value);
			window.tdFillCallBack && (str += window.tdFillCallBack(t));
			/*
			str += "<p class='price'>市:￥<em></em></p>";
			str += "<p class='price'>售:￥<em></em></p>";
			str += "<p class='price'>库:<em></em></p>";
			*/
			t.find('.num').after(str);
//			t.find('.price').eq(0).find('em').text(value.marketPrice);
//			t.find('.price').eq(1).find('em').text(value.sellPrice);
//			t.find('.price').eq(2).find('em').text(value.quantity);
//			t.attr('marketPrice',value.marketPrice).attr('stockNum',value.quantity).attr('sellPrice',value.sellPrice).attr('childPrice',value.childPrice).attr('oldPrice',value.oldPrice).attr('distPrice',value.distPrice).attr('minBuyNum',value.minimum).attr('maxBuyNum',value.maximum);
			t.find('a').attr('useDate',key);
			t.mouseover(function(){
				$(this).find('.sale_online').show();
			}).mouseout(function(){
				$(this).find('.sale_online').hide();
			}).find('p[data-ctype="saleoff"]').click(function(event){
				event.stopPropagation();
				delete $calendardata[$(this).find('a').attr('useDate')];
				creatCalendar();
				fillDom($calendardata);
			});
		};
		var priceData = JSON.stringify($calendardata);
		$("#datePriceData").val(priceData);
	}
	
	$('#nextbutton').click(function(){
		var date = parseDate($calendar.attr('date'));
		date = date.getMonth() == 11 ? new Date(date.getFullYear()+1,0,1) : new Date(date.getFullYear(),date.getMonth()+1,1);
		var option = $('#date');
		var ty = date.getFullYear();
		var m = date.getMonth();
		var tm = doubleNum(m+1);

		if(m == 0){
			$("#prevmonth").text(12+"月");	
		}else{
			$("#prevmonth").text(m+"月");
		}
		if(m == 11){
			$("#nextmonth").text(1+"月");
		}else{
			$("#nextmonth").text(m+2+"月");
		}
		$("#year").text(ty+"年");
		$("#month").text(m+1+"月");
		$calendar.attr('month',doubleNum(m+1));
		$calendar.attr('date',ty+'-'+tm+'-01');
		creatCalendar();
		fillDom($calendardata);
	})
	
	$('#prevbutton').click(function(){
		var date = parseDate($calendar.attr('date'));
		date = date.getMonth() == 0 ? new Date(parseInt(date.getFullYear())-1,11,1) : new Date(date.getFullYear(),date.getMonth()-1,1);
		var option = $('.date');
		var ty = date.getFullYear();
		var m = date.getMonth();
		var tm = doubleNum(m+1);
		
		if(m == 0){
			$("#prevmonth").text(12+"月");
		}else{
			$("#prevmonth").text(m+"月");
		}
		if(m == 11){
			$("#nextmonth").text(1+"月");
		}else{
			$("#nextmonth").text(m+2+"月");
		}
		$("#year").text(ty+"年");
		$("#month").text(m+1+"月");
		$calendar.attr('month',doubleNum(m+1));
		$calendar.attr('date',ty+'-'+tm+'-01');
		creatCalendar();
		fillDom($calendardata);
	});
	
	$("#clearall").click(function(){
		creatCalendar();
		$calendardata={};
		fillDom($calendardata);
	});
	
	$('.dialog_close').click(function(){
		$(this).parents('.allBox').hide();
	});
	
	$('input.btn_gray').click(function(){
		$(this).parents('.allBox').hide();
	});
	
	$('#batchset,#needdate').click(function(){
		//showInfo("","");
		var cbox = $("#dateStock");
		var totalH = document.body.scrollHeight;
		cbox.find('.allMask').height(totalH);
		//cbox.find(':text').attr('value','');
		//cbox.find('#stockNum_1').val('9999');
		//cbox.find('#minimum_1').val('1');
		//cbox.find('#maximum_1').val('999');
		cbox.show();
		//$("#datePriceData").val('');
	});
	
	$("#batchunset").click(function(){
		var cbox = $("#unstock");
		var totalH = document.body.scrollHeight;
		cbox.find('.allMask').height(totalH);
		cbox.show();
	});
	
	$('#modifyprice,#dontneeddate').click(function(){
		//showInfo("","");
		var cbox = $("#totalStock");
		var totalH = document.body.scrollHeight;
		cbox.find('.allMask').height(totalH);
		//cbox.find(':text').attr('value','');
		//cbox.find('#stockNum_1').val('9999');
		//cbox.find('#minimum_1').val('1');
		//cbox.find('#maximum_1').val('999');
		cbox.show();
		//$("#datePriceData").val('');
	});
	
	$('#dateStock input.btn_orange').click(function(){
		var tag = $(this).parents('.allBox');
		var map = {};
		map.sdate = $("#startDate_1").val();
		map.edate = $("#endDate_1").val();
		map.stockNum = $("#stockNum_1").val();
		map.sellPrice = $("#sellPrice_1").val();
		map.childPrice = $("#childPrice_1").val();
		map.oldPrice = $("#oldPrice_1").val();
		map.minimum = $("#minimum_1").val();
		map.maximum = $("#maximum_1").val();
		map.distPrice = $("#distPrice_1").val();
		map.marketPrice = $("#marketPrice_1").val();
		var weeks = $("#dateStock .idsOfWeek input[name='week']:checked");
		var tmpStr = [];
		for ( var i = 0; i < weeks.length; i++) {
			tmpStr.push($(weeks[i]).val());
		}
		map.weeks = tmpStr.join(',');
		if(map.sdate == '' || map.edate == '' || map.stockNum == '' ||
				map.sellPrice == '' || map.childPrice == '' || map.oldPrice == '' || map.minimum == '' || map.maximum == ''||
				map.distPrice == ''){
			alert("所填信息不完整！");
			return;
		}
		//$("#weeks").val(tmpStr);
		var prices = [];
		var cnt = $("#HourPriceTable .hourPrice").length;
		for(var i=0;i<cnt;i++) {
			var beginHour = $("#HourPriceTable #hourBeginHour_"+i).val();
			var endHour = $("#HourPriceTable #hourEndHour_"+i).val();
			var marketPrice = $("#HourPriceTable #hourMarketPrice_"+i).val();
			var sellPrice = $("#HourPriceTable #hourSellPrice_"+i).val();
			var settlePrice = $("#HourPriceTable #hourSettlePrice_"+i).val();
			var stock = $("#HourPriceTable #hourStock_"+i).val();
			
			if(beginHour == '' || endHour == '' || 
					marketPrice == '' || sellPrice == '' || settlePrice == '' || stock == ''){
				alert("所填信息不完整！");
				return;
			}
			prices.push({
					beginHour: beginHour,
					endHour: endHour,
					marketPrice: marketPrice,
					sellPrice: sellPrice,
					distPrice: settlePrice,
					quantity: stock
			});
		}
		tag.hide();
		creatCalendar();
		var startDate = parseDate($("#startDate_1").val());
		var endDate = parseDate($("#endDate_1").val());
		var current = startDate;
		var i = 0;
		while(i <= startDate.DateDiff('d', endDate)) {
			var tmp = {};
			tmp.marketPrice = $("#marketPrice_1").val();
			//tmp.useDate = current.Format('YYYY-MM-DD');
			tmp.sellPrice = $("#sellPrice_1").val();
			tmp.childPrice = $("#childPrice_1").val();
			tmp.oldPrice = $("#oldPrice_1").val();
			tmp.distPrice = $("#distPrice_1").val();
			tmp.quantity = $("#stockNum_1").val();
			tmp.minimum = $("#minimum_1").val();
			tmp.maximum = $("#maximum_1").val();
			tmp.status  = "1";
			tmp.prices = prices;
			if(map.weeks.indexOf(current.getDay()) > -1) {
				$calendardata[current.Format('YYYY-MM-DD')] = tmp;
			} else {
				//delete $calendardata[current.Format('YYYY-MM-DD')];
			}
			current = current.DateAdd('d', 1);
			i++;
		}
		fillDom($calendardata);
		$(".needdate").show();
		$(".dontneeddate").hide();
		//$("#classifyPriceData").val('');
	});
	
	$('#unstock input.btn_orange').click(function(){
		var tag = $(this).parents('.allBox');
		var map = {};
		map.sdate = $("#startDate_3").val();
		map.edate = $("#endDate_3").val();
		var weeks = $("#unstock .idsOfWeek input[name='week']:checked");
		var tmpStr = [];
		for ( var i = 0; i < weeks.length; i++) {
			tmpStr.push($(weeks[i]).val());
		}
		map.weeks = tmpStr.join(',');
		if(map.sdate == '' || map.edate == ''){
			alert("所填信息不完整！");
			return;
		}
		
		tag.hide();
		creatCalendar();
		var startDate = parseDate($("#startDate_3").val());
		var endDate = parseDate($("#endDate_3").val());
		var current = startDate;
		var i = 0;
		while(i <= startDate.DateDiff('d', endDate)) {
			if(map.weeks.indexOf(current.getDay()) > -1) {
				delete $calendardata[current.Format('YYYY-MM-DD')];
			}
			current = current.DateAdd('d', 1);
			i++;
		}
		fillDom($calendardata);
		$(".needdate").show();
		$(".dontneeddate").hide();
		//$("#classifyPriceData").val('');
	});
	
	$('#totalStock input.btn_orange').click(function(){
		var tag = $(this).parents('.allBox');
		var map = {};
		map.sdate = $("#startDate_2").val();
		map.edate = $("#endDate_2").val();
		map.stockNum = $("#stockNum_2").val();
		map.sellPrice = $("#sellPrice_2").val();
		map.childPrice = $("#childPrice_2").val();
		map.oldPrice = $("#oldPrice_2").val();
		map.minimum = $("#minimum_2").val();
		map.maximum = $("#maximum_2").val();
		map.distPrice = $("#distPrice_2").val();
		map.marketPrice = $("#marketPrice_2").val();
		var weeks = $("#totalStock .idsOfWeek input[name='week']:checked");
		var tmpStr = [];
		for ( var i = 0; i < weeks.length; i++) {
			tmpStr.push($(weeks[i]).val());
		}
		//$("#weeks").val(tmpStr);
		map.weeks = tmpStr.join(',');
		if(map.sdate == '' || map.edate == '' || map.stockNum == '' ||
				map.sellPrice == '' || map.childPrice == '' || map.oldPrice == '' || map.minimum == '' || map.maximum == ''||
				map.distPrice == ''){
			alert("所填信息不完整！");
			return;
		}
		
		tag.hide();
		$calendardata = {};
		$calendardata.beginDate = $("#startDate_2").val();
		$calendardata.endDate = $("#endDate_2").val();
		//$calendardata.weekDays = map.weeks;
		$calendardata.marketPrice = $("#marketPrice_2").val();
		$calendardata.sellPrice = $("#sellPrice_2").val();
		$calendardata.childPrice = $("#childPrice_2").val();
		$calendardata.oldPrice = $("#oldPrice_2").val();
		$calendardata.distPrice = $("#distPrice_2").val();
		$calendardata.quantity = $("#stockNum_2").val();
		$calendardata.minimum = $("#minimum_2").val();
		$calendardata.maximum = $("#maximum_2").val();
		if(array==null || array.length == 0) {
			$calendardata.cannotUseDay = '';
		} else {
			$calendardata.cannotUseDay = array.join(',');
		}
		$("#timecontainer").text($calendardata.beginDate+" ~ "+$calendardata.endDate);
		$("#marketpricecontainer").text($calendardata.marketPrice);
		$("#pricecontainer").text($calendardata.sellPrice);
		$("#distpricecontainer").text($calendardata.distPrice);
		$("#stockcontainer").text($calendardata.quantity);
		$("#unusedate").text((array==null || array.length == 0)?'暂无':array.join("、"));
		
		var priceData = JSON.stringify($calendardata);
		$("#datePriceData").val(priceData);
		
		$(".needdate").hide();
		$(".dontneeddate").show();
		//$("#datePriceData").val('');
	});
	
	$("#pricesave").click(function(){
		var map = {};
		map.quantity = $("#stockNum").val();
		map.sellPrice = $("#sellPrice").val();
		map.childPrice = $("#childPrice").val();
		map.oldPrice = $("#oldPrice").val();
		map.minimum = $("#minBuyNum").val();
		map.maximum = $("#maxBuyNum").val();
		map.distPrice = $("#distPrice").val();
		map.marketPrice = $("#marketPrice").val();
		map.status = "1";
		if(map.quantity == '' || map.distPrice == '' || 
				map.sellPrice == '' || map.childPrice == '' || map.oldPrice == '' || map.minimum == '' || map.maximum == ''){
			alert("所填信息不完整！");
			return;
		}
		
		var prices = [];
		var cnt = $("#currentHourPrice .hourPrice").length;
		for(var i=0;i<cnt;i++) {
			var beginHour = $("#currentHourPrice #hourBeginHour_"+i).val();
			var endHour = $("#currentHourPrice #hourEndHour_"+i).val();
			var marketPrice = $("#currentHourPrice #hourMarketPrice_"+i).val();
			var sellPrice = $("#currentHourPrice #hourSellPrice_"+i).val();
			var settlePrice = $("#currentHourPrice #hourSettlePrice_"+i).val();
			var stock = $("#currentHourPrice #hourStock_"+i).val();
			
			if(beginHour == '' || endHour == '' || 
					marketPrice == '' || sellPrice == '' || settlePrice == '' || stock == ''){
				alert("所填信息不完整！");
				return;
			}
			
			prices.push({
					beginHour: beginHour,
					endHour: endHour,
					marketPrice: marketPrice,
					sellPrice: sellPrice,
					distPrice: settlePrice,
					quantity: stock
			});
		}
		map.prices = prices;
		
		creatCalendar();
		$calendardata[$("#useDate").val()] = map;
		fillDom($calendardata);
	});
	
	function creatCalendar(){
		var date = parseDate($calendar.attr('date'));
		var option = $("#date");
		var tm = $calendar.attr('month');
		var year = date.getFullYear();
		var month = parseInt(date.getMonth());
		var m = date.getMonth();

		if(m == 0){
			$("#prevmonth").text(12+"月");	
		}else{
			$("#prevmonth").text(m+"月");
		}
		if(m == 11){
			$("#nextmonth").text(1+"月");
		}else{
			$("#nextmonth").text(m+2+"月");
		}
		$("#year").text(year+"年");
		$("#month").text(m+1+"月");
		
		//var tm = doubleNum(month);
		var fristDay = parseInt(date.getDay());
		var lastDay = new Date(year,month+1,-1);
			lastDay = lastDay.getDate();
		var str = '';
		var day = 1;
		str += "<table>";
		str += "<tbody id='container'>";
		
		for(var h=0;h<6;h++){
			str += "<tr>";
			if(fristDay != 0 && h == 0){
				for(var i=0;i<fristDay;i++){
					str += "<td class='none'> </td>";
				}
				for(var i=0;i<7-fristDay;i++){
					var tmpdate = parseDate(year+"-"+tm+"-"+doubleNum(day));
					str += "<td data-ctype='date' data-date='"+year+"-"+tm+"-"+doubleNum(day)+"' data-isout='0'>";
					str += "<div class='sale_on'>";
					str += "<p class='num'>"+day+"</p>";
					/*str += "<p data-ctype='saleoff' data-date='"+year+"-"+tm+"-"+doubleNum(day)+"' class='sale_online' style='display:none;'>";
					str += "<a href='javascript:;'>下架</a></p>";
					str += "<p class='price'>市：￥<em></em></p>";
					str += "<p class='price'>售：￥<em></em></p>";
					str += "<p class='price'>库：</p>";*/
					str += "</div>";
					str += "</td>";
					day++;
				}
			}else{
				for(var i=0;i<7;i++){
					if(day <= lastDay+1){
						var tmpdate = parseDate(year+"-"+tm+"-"+doubleNum(day));
						str += "<td data-ctype='date' data-date='"+year+"-"+tm+"-"+doubleNum(day)+"' data-isout='0'>";
						str += "<div class='sale_on'>";
						str += "<p class='num'>"+day+"</p>";
						/*str += "<p data-ctype='saleoff' data-date='"+year+"-"+tm+"-"+doubleNum(day)+"' class='sale_online' style='display:none;'>";
						str += "<a href='javascript:;'>下架</a></p>";
						str += "<p class='price'>市：￥<em></em></p>";
						str += "<p class='price'>售：￥<em></em></p>";
						str += "<p class='price'>库：</p>";*/
						str += "</div>";
						str += "</td>";
						day++;
					}else{
						str += "<td class='none'> </td>";
					}
				}
			}
			str += "</tr>";
		}
		
		str += "</tbody>";
		str += "</table>";
		option.html(str);
		
		var tdate = option.find("td[data-ctype='date']");
		tdate.each(function(){
			var d = $(this).attr('data-date');
			d = parseDate(d);
			if(d.getTime() > (n.getTime() - 86400000)){
				$(this).click(function(){
					/*
					$("#marketPrice").val($(this).attr('marketPrice'));
					$("#sellPrice").val($(this).attr('sellPrice'));
					$("#distPrice").val($(this).attr('distPrice'));
					$("#stockNum").val($(this).attr('stockNum'));
					$("#minBuyNum").val($(this).attr('minBuyNum'));
					$("#maxBuyNum").val($(this).attr('maxBuyNum'));
					$("#useDate").val($(this).attr('data-date'));
					$("#showDate").text($(this).attr('data-date'));
					*/
					if(window.tdClickCallBack){
						window.tdClickCallBack(this);
					}
					$("#calender-right").show();
				})
			}else{
				$(this).find('div.sale_on').removeClass('sale_on').addClass('out');
			}
		})
	}
	
	function doubleNum(num){
		if(0<num && num<10){
			return "0"+num;	
		}
		return num;
	}
	
})(jQuery);