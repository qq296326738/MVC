function lookMap(longitude, latitude) {
	if (longitude == "0" && latitude == "0") {
		longitude = 104.071330;//成都市经纬度
		latitude = 30.664721;
	}
	
	var browserFrameId = "browserFrame" + (new Date()).valueOf() + Math.floor(Math.random() * 1000000);
	var $browser = $('<div class="xxBrowser" style="height:330px;"><\/div>');
	$browserFrame = $('<iframe id="' + browserFrameId + '" name="' + browserFrameId + '" src="/resources/map.html#' + longitude + ',' + latitude + '" style="width:100%; height:100%;" frameborder="0"><\/iframe>').appendTo($browser);
	var $dialog = $.dialog({
		title: "地图查看",
		content: $browser,
		width: 650,
		modal: true,
		ok: null,
		cancel: null
	});
}

function layUIlookMap(longitude, latitude) {
    if (longitude == "0" && latitude == "0") {
        longitude = 104.071330;//成都市经纬度
        latitude = 30.664721;
    }

    var index = layer.open({
        type: 2,
        title: '地图查看',
        area: ['650px', '400px'], //宽高
        fix: true, //固定
        content: '/resources/map.html#' + longitude + ',' + latitude
    });
}

/**
 * 标记当前坐标位置
 * @param x
 * @param y
 * @return
 */
function oppenMap(x, y) {
	var map = new BMap.Map("allmap");
	map.centerAndZoom(new BMap.Point(x, y), 15);
	map.enableScrollWheelZoom();
	map.addControl(new BMap.NavigationControl()); //添加默认缩放平移控件
	map.addControl(new BMap.ScaleControl());
	map.addControl(new BMap.MapTypeControl( {
		mapTypes : [ BMAP_NORMAL_MAP, BMAP_HYBRID_MAP ]
	})); //2D图，卫星图
	var marker1 = new BMap.Marker(new BMap.Point(x, y)); // 创建标注
	map.addOverlay(marker1);
	//创建信息窗口
	var infoWindow1 = new BMap.InfoWindow("标注");
	marker1.addEventListener("click", function() {
		this.openInfoWindow(infoWindow1);
	});
	tt(map);
	map.addEventListener("click", function(e) {
		map.clearOverlays();
		var marker = new BMap.Marker(new BMap.Point(e.point.lng, e.point.lat)); // 创建标注
			map.addOverlay(marker);
			setText(e.point.lng, e.point.lat);
		});//添加鼠标点击事件
}

//设置文本
function setText(x, y) {
	//设置当前资源的经纬度
	var _parentWin = window.parent;
	try {
        _parentWin.inputForm.longitude.value = x;
        _parentWin.inputForm.latitude.value = y;
	}catch(e) {
        _parentWin.$("input[name=longitude]").val(x);
        _parentWin.$("input[name=latitude]").val(y);
	}

}

// 定义一个控件类,即function
function ZoomControl() {
	// 默认停靠位置和偏移量
	this.defaultAnchor = BMAP_ANCHOR_TOP_LEFT;
	this.defaultOffset = new BMap.Size(290, 0);
}

/**
 * 
 *自定义控件
 */
function tt(map) {
	ZoomControl.prototype = new BMap.Control();
	ZoomControl.prototype.initialize = function(map) {
		var html = '<div style="width:200px;height:70px;background:#ffca80;padding:10px;"><span style="font-size:12px;" class="clear warnInfo">请输入地址获取对应坐标位置</span>' + '<input style="width:150px;height:20px;"  type="text" name="wd"/>' + '<input type="button" id="wd" style="line-height:24px;text-align:center;' + 'cursor:pointer;color:#555555;font-size:12px;border:1px #ccc solid;border-radius:5px 5px 5px 5px;background:#fff;" value="查询" hidefocus=""></div>';
		var s = $(html);
		$("#wd").live('click', function() {
			search(map);
		});
		map.getContainer().appendChild(s.get(0));
		return s.get(0);
	}
	var myZoomCtrl = new ZoomControl();
	map.addControl(myZoomCtrl);
}
function search(map) {
	var $value = $("input[name='wd']");
	// 创建地址解析器实例
	var myGeo = new BMap.Geocoder();
	// 将地址解析结果显示在地图上,并调整地图视野
	myGeo.getPoint($value.val(), function(point) {
		if (point) {
			map.clearOverlays();
			map.centerAndZoom(point, 10);
			map.addOverlay(new BMap.Marker(point));
			setText(point.lng, point.lat);
		}
	}, "");
}
