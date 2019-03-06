(function($) {
	$.fn.dropqtable = function(o) {
		if (!this.dropdown) {
			alert("请引用jquery.dropdown.js");
			return;
		}
		var tableoption = {
			qtitletext : "请输入关键字",
			pagesize : 10,
			pageindex : 1,
			autoload : true
		};
		$.extend(tableoption, o.tableoptions);
		var qtparse = {
			name : "qtable",
			render : function(parent) {
				var target = this.target;
				var qpanel = $("<div class='querytainer'/>"); // querypanel
				var qtext = $("<input type='text' placeholder='"
						+ tableoption.qtitletext + "'/>");
				qtext.data("watermark", tableoption.qtitletext);
				var qbtn = $("<a href='javascript:void(0);' class='qbtn'></a>");
				var qktn = $("<button type='button'>清空</button>");
				qtext.focus(function() {
					var mark = $(this).data("watermark");
					if ($(this).val() == mark) {
						$(this).val("").removeClass("watermark");
						;
					}
				}).blur(function() {
					var mark = $(this).data("watermark");
					if ($(this).val() == "") {
						$(this).val(mark).addClass("watermark");
					}
				}).keypress(function(e) {
					if (e.keyCode == 13) {
						query(this.value);
						return false;
					}
				});
				qbtn.click(function() {
					var v = $(this).prev().val();
					query(v);
					return false;
				});
				qktn.click(function() {
					target.Clear();
					if (tableoption.onSelect) {
						tableoption.onSelect(null);
					}
				});
				qpanel.append(qtext).append(qbtn).append(qktn);

				var tbpanel = $("<div class='tablecontaienr'/>"); // tablepanel
				var thtml = [];
				thtml
						.push("<table width='100%' cellpadding='2' cellspacing='0'><thead><tr>");
				for ( var i = 0, l = tableoption.colmodel.length; i < l; i++) {
					thtml.push("<th><div style='width:",
							tableoption.colmodel[i].width, "'>",
							tableoption.colmodel[i].displayname, "</div></th>");
				}
				thtml.push("</tr></thead>");
				thtml.push("<tbody></tbody></table>");
				tbpanel.html(thtml.join(""));
				var ppanel = $("<div class='pagecontainer'/>"); // pagepanel
				// <a class='pagenext' href="javascript:void(0);">下一页</a><a
				// class="pageprev"
				// href="javascript:void(0);">上一页</a><span>1/10</span>
				var pnexta = $(
						"<a class='pagenext' href='javascript:void(0);'>下一页</a>")
						.click(function() {
							var v = qtext.val();
							query(v, 1);
						});
				var ppreva = $(
						"<a class='pageprev' href='javascript:void(0);'>上一页</a>")
						.click(function() {
							var v = qtext.val();
							query(v, -1);
						});
				ppanel.append(ppreva).append(pnexta).append("<span>0/0</span>");
				parent.append(qpanel).append(tbpanel).append(ppanel);
				if (tableoption.autoload) {
					query("", 0);
				}
				function query(v, ptype) {
					if (v == tableoption.qtitletext) {
						v = "";
					}
					if (ptype == 0) {
						tableoption.pageindex = 1;
					} else if (ptype == 1) {
						if (tableoption.pageindex + 1 < tableoption.pageTotal) {
							tableoption.pageindex++;
						} else {
							tableoption.pageindex = tableoption.pageTotal;
						}
					} else if (ptype == -1) {
						if (tableoption.pageindex - 1 >= 1) {
							tableoption.pageindex--;
						} else {
							tableoption.pageTotal = 1;
						}
					}
					var p = {
						"page" : tableoption.pageindex,
						"rows" : tableoption.pagesize,
						"q" : v
					};
					var purl = tableoption.url;
					$
							.ajax({
								type : "POST",
								url : purl,
								data : p,
								dataType : "json",
								success : function(data) {
									if (data.total >= 0) {
										tableoption.total = data.total;
										tableoption.pageTotal = Math
												.ceil(tableoption.total
														/ tableoption.pagesize);
									}
									ppanel.find(">span").text(
											tableoption.pageindex + "/"
													+ tableoption.pageTotal);
									var tbody = tbpanel.find(">table tbody");
									tbody.empty();
									for ( var i = 0, l = data.rows.length; i < l; i++) {
										var thtml = [];
										thtml.push("<tr>");
										var row = data.rows[i];
										var cellmodels = tableoption.colmodel;
										for ( var j = 0, k = cellmodels.length; j < k; j++) {
											var cellmodel = cellmodels[j];
											var celldata = row[cellmodel.name];
											if (cellmodel.formatter) {
												thtml.push("<td>", cellmodel
														.formatter(celldata),
														"</td>");
											} else {
												thtml.push("<td>", celldata
														|| "&nbsp;", "</td>");
											}
										}
										thtml.push("</tr>");
										$(thtml.join(""))
												.data('row', row)
												.DhoverClass("hover")
												.click(
														function(o) {
															var rowdata = $(
																	this).data(
																	'row');
															target
																	.SelectedChanged({
																		text : rowdata[tableoption.textField],
																		value : rowdata[tableoption.valueField]
																	});

															// 当选中某行数据后执行自定义方法
															if (tableoption.onSelect) {
																tableoption
																		.onSelect(rowdata);
															}
														}).appendTo(tbody);
									}
								},
								error : function(xhr) {
									// alert("error");
								}
							});
				}
			},
			items : [],
			setValue : function(item) {
			},
			onshow : function(parent) {
				var input = parent.find("input.watermark").val("");
				if (tableoption.autoload) {
					parent.find("a.qbtn").click();
				}
			},
			target : null
		};
		$.extend(o, {
			parse : qtparse,
			containerCssClass : "qtableContainer",
			autoheight : true
		});
		return $(this).dropdown(o);
	};
})(jQuery);