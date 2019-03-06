var $productImageScrollable;
jQuery().ready(function() {
	// 商品图片预览滚动栏

	jQuery(".productImageArea .scrollable").scrollable({
		speed: 600
	});
	
	// 显示商品图片预览操作层

	jQuery(".productImageArea li .productImageBox").live("mouseover", function() {
		jQuery(this).find(".productImageOperate").show();
	});
	
	// 隐藏商品图片预览操作层

	jQuery(".productImageArea li .productImageBox").live("mouseout", function() {
		jQuery(this).find(".productImageOperate").hide();
	});
	
	// 商品图片左移
	jQuery(".left").live("click", function() {
		var $productImageLi = jQuery(this).parent().parent().parent();
		var $productImagePrevLi = $productImageLi.prev("li");
		if ($productImagePrevLi.length > 0) {
			$productImagePrevLi.insertAfter($productImageLi);
		}
	});
	
	// 商品图片右移
	jQuery(".right").live("click", function() {
		var $productImageLi = jQuery(this).parent().parent().parent();
		var $productImageNextLi = $productImageLi.next("li");
		if ($productImageNextLi.length > 0) {
			$productImageNextLi.insertBefore($productImageLi);
		}
	});
	
	// 商品图片删除
	jQuery(".delete").live("click", function() {
		var $productImageLi = jQuery(this).parent().parent().parent();
		var $productImagePreview = $productImageLi.find(".productImagePreview");
		var $focusimagepath = $productImageLi.find("input[name='focusimagepath']");
		/*
		var $productImageIds = $productImageLi.find("input[name='productImageFiles']");
		var $productImageFiles = $productImageLi.find("input[name='productImages']");
		var $productImageParameterTypes = $productImageLi.find("input[name='productImageParameterTypes']");
		$productImageIds.remove();
		$productImageFiles.after('<input type="file" name="productImages" hidefocus="true" />');
		$productImageFiles.remove();
		$productImageParameterTypes.remove();
		*/
		$focusimagepath.val("");
		$productImagePreview.html("暂无图片");
		$productImagePreview.removeAttr("title");
		if (jQuery.browser.msie) {
			if(window.XMLHttpRequest) {
				$productImagePreview[0].style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod = 'scale', src='')";
			}
		}
	});
	
	jQuery(".productImageUploadButton").each(function() {
		var $this = $(this);
		uploadComplete($this);
	});

	//商品图片选择预览
	$productImageScrollable = jQuery(".productImageArea .scrollable").scrollable();
	
	/*
	jQuery(".productImageUploadButton input[type='file']").live("change", function() {
		var $this = jQuery(this);
		var $productImageLi = $this.parent().parent().parent();
		var $productImagePreview = $productImageLi.find(".productImagePreview");
		var fileName = $this.val().substr($this.val().lastIndexOf("\\") + 1);
		if (/(jpg|png|gif)$/i.test($this.val()) == false) {
			Dialog.alert("您选择的文件格式错误！");
			return false;
		}
		$productImagePreview.empty();
		$productImagePreview.attr("title", fileName);
		if (jQuery.browser.msie) {
			if(!window.XMLHttpRequest) {
				$productImagePreview.html('<img src="' + $this.val() + '" />');
			} else {
				$this[0].select();
				var imgSrc = document.selection.createRange().text;
				$productImagePreview[0].style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod = 'scale', src='" + imgSrc + "')";
			}
		} else if (jQuery.browser.mozilla) {
			$productImagePreview.html('<img src="' + $this[0].files[0].getAsDataURL() + '" />');
		} else {
			$productImagePreview.html(fileName);
		}
		if ($productImageLi.next().length == 0) {
			if ($productImageScrollable.getSize() < 5) {
				$productImageLi.after(productImageLiHtml);
			}
			if ($productImageScrollable.getSize() > 5) {
				$productImageScrollable.next();
			}
		}
		var $productImageIds = $productImageLi.find("input[name='productImageFiles']");
		//var $productImageParameterTypes = $productImageLi.find("input[name='productImageParameterTypes']");
		var $productImageUploadButton = $productImageLi.find(".productImageUploadButton");
		$productImageIds.remove();
		//if ($productImageParameterTypes.length > 0) {
		//	$productImageParameterTypes.val("productImageFile");
		//} else {
		//	$productImageUploadButton.append('<input type="hidden" name="productImageParameterTypes" value="productImageFile" />');
		//}
	});
	*/
	
});





//var productImageLiHtml = '<li><div class="productImageBox"><div class="productImagePreview">暂无图片</div><div class="productImageOperate"><a class="left" href="javascript: void(0);" alt="左移" hidefocus="true"></a><a class="right" href="javascript: void(0);" title="右移" hidefocus="true"></a><a class="delete" href="javascript: void(0);" title="删除" hidefocus="true"></a></div><a class="productImageUploadButton" href="javascript: void(0);"><input type="file" name="productImages" hidefocus="true" /><div>上传新图片</div></a></div></li>';
var productImageLiHtml = '<li><div class="productImageBox"><div class="productImagePreview">暂无图片</div><input type="hidden" name="focusimagepath" value=""/><div class="productImageOperate"><a class="left" href="javascript:;" alt="左移" hidefocus="true"></a><a class="right" href="javascript:;" title="右移" hidefocus="true"></a><a class="delete" href="javascript:;" title="删除" hidefocus="true"></a></div><a class="productImageUploadButton" href="javascript:;"><div>上传新图片</div></a></div></li>';
var productImageLiHtmlFour = '<li><div class="productImageBox"><div class="productImagePreview">暂无图片</div><input type="hidden" name="focusimagepath" value=""/><div class="productImageOperate"><a class="left" href="javascript:;" alt="左移" hidefocus="true"></a><a class="right" href="javascript:;" title="右移" hidefocus="true"></a><a class="delete" href="javascript:;" title="删除" hidefocus="true"></a></div><a class="productImageUploadButtonFour" href="javascript:;"><div>上传新图片</div></a></div></li>';

function uploadComplete($this) {
	var token = getCookie("token");
	new AjaxUpload($this, {
		action : '/file/upload?fileType=image&token='+token,
		name : 'file', 
		autoSubmit:true,
		responseType: "json",
		onChange: function(file, ext){
			if (!(ext && /^(jpg|png|gif|jpeg|bmp)$/.test(ext))){
				alert("文件格式错误");
				return false;
			}
		},
		onSubmit : function(file, ext) {
			 var $productImageLi = $this.parent().parent();
			 var $productImagePreview = $productImageLi.find(".productImagePreview");
			 $productImagePreview.empty();
			 $productImagePreview.html('<img style="width:32px;height:32px;line-height:30px;margin-top:30px;" src="/resources/admin/images/loading.gif" />');
		},
		onComplete : function(file, response) {
			if(response.errorCode == 0){
				var filepath = response.data;
				var $productImageLi = $this.parent().parent();
				var $productImagePreview = $productImageLi.find(".productImagePreview");
				var $focusimagepath = $productImageLi.find("input[name='focusimagepath']");
				$focusimagepath.val(filepath);
				$productImagePreview.empty();
				$productImagePreview.html('<img src="' + filepath + '" />');
		//		if(window.XMLHttpRequest) {
		//			$productImagePreview[0].style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod = 'scale', src='" + filepath + "')";
		//		}
				if ($productImageLi.next().length == 0) {
					if ($productImageScrollable.getSize() < 5) {
						var inner = $(productImageLiHtml);
						$productImageLi.after(inner);
						var uploadButton = inner.find(".productImageUploadButton");
						uploadComplete(uploadButton);
					}
					if ($productImageScrollable.getSize() > 5) {
						$productImageScrollable.next();
					}
				}
			}
		}
	});
}
function uploadCompleteFour($this) {
	var token = getCookie("token");
	new AjaxUpload($this, {
		action : '/file/upload?fileType=image&token='+token,
		name : 'file',
		autoSubmit:true,
		responseType: "json",
		onChange: function(file, ext){
			if (!(ext && /^(jpg|png|gif|jpeg|bmp)$/.test(ext))){
				alert("文件格式错误");
				return false;
			}
		},
		onSubmit : function(file, ext) {
			 var $productImageLi = $this.parent().parent();
			 var $productImagePreview = $productImageLi.find(".productImagePreview");
			 $productImagePreview.empty();
			 $productImagePreview.html('<img style="width:32px;height:32px;line-height:30px;margin-top:30px;" src="/resources/admin/images/loading.gif" />');
		},
		onComplete : function(file, response) {
			if(response.errorCode == 0){
				var filepath = response.data;
				var $productImageLi = $this.parent().parent();
				var $productImagePreview = $productImageLi.find(".productImagePreview");
				var $focusimagepath = $productImageLi.find("input[name='focusimagepath']");
				$focusimagepath.val(filepath);
				$productImagePreview.empty();
				$productImagePreview.html('<input type="text" name="url" style="position: absolute;left:0px;top:0px;width:90px;BACKGROUND-COLOR: #ffffffb5;" required="required"><img src="' + filepath + '" />');
		//		if(window.XMLHttpRequest) {
		//			$productImagePreview[0].style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod = 'scale', src='" + filepath + "')";
		//		}
				if ($productImageLi.next().length == 0) {
					if ($productImageScrollable.getSize() < 5) {
						var inner = $(productImageLiHtmlFour);
						$productImageLi.after(inner);
						var uploadButton = inner.find(".productImageUploadButtonFour");
						uploadCompleteFour(uploadButton);
					}
					if ($productImageScrollable.getSize() > 5) {
						$productImageScrollable.next();
					}
				}
			}
		}
	});
}


