/*同一字符串重复输出*/
/* str:需要重复的字符串 */
/* n:重复的次数*/
/* endStr:重复输出以后的字符串*/
/***************************/
function repeatStr(str,n){
	var endStr = "";
	for(var i = 0; i<n ; i++){
		endStr += str;
	}
	return endStr;
}

/*追加层级html元素*/
/* e:事件源dom对象 */
/* a:需要追加事件源的上a层 */
/**************************/
addElement = function (e,a){
	var rootNode = eval("$(e)" + repeatStr(".parent()",a+1));
	var obj = rootNode.find('p:last').clone();
	obj.find('input').val("");
	obj.find('a.mws_bg_box').html("+");
	obj.find('select option').eq(0).attr("selected",true);
	rootNode.append(obj);
}

/*删除父级元素*/
deleteParent = function(e){
	var parent = $(e).parent();
	var num = parent.parent().children().length;
	if(num>2){
		parent.remove();
	}
}

/*上移元素*/
shiftUp = function(e){
	var parent = $(e).parent();
	if(parent.prev() && parent.attr('class') == parent.prev().attr('class')){
		/*var inputLength = parent.find('input').length;
		for(var i=0;i<inputLength;i++){
			var str = parent.prev().find('input').eq(i).val();
			parent.prev().find('input').eq(i).attr('value',parent.find('input').eq(i).val());
			parent.find('input').eq(i).attr('value',str);
		}*/
		parent.prev().before(parent);
	}
}

/*下移元素*/
shiftDown = function(e){
	var parent = $(e).parent();
	if(parent.next() && parent.attr('class') == parent.next().attr('class')){
		parent.next().after(parent);
	}	
}	