var basePath = $("#basePath").val();

// 字符串转JSON对象
function strToJson(str) {
	try {
		if(typeof(str) == "object")
			return str;
		return eval("(" + str + ")");
	} catch(e) {
		throw "数据格式错误";
	}
	
}

//打开一个新的选项卡
function addTab(tabTitle, url, icon) {
	console.log($('#tabs'));
	var tabs = $('#tabs');
	if (!$('#tabs').tabs('exists', tabTitle)) {
		$('#tabs').tabs('add', {
			title : tabTitle,
			content : createFrame(url),
			closable : true,
			icon : icon,
			tools : [ {
				iconCls : 'icon-reload',
				handler : function() {
					refreshTab({
						tabTitle : tabTitle
					});
				}
			} ]
		});
	} else {
		$('#tabs').tabs('select', tabTitle);
		$('#mm-tabupdate').click();
	}
}

// 创建一个iframe标签
function createFrame(url) {
	var tab = '<iframe scrolling="auto" frameborder="0"  src="' + url + '" style="width:100%;height:100%;"></iframe>';
	return tab;
}


// 刷新一个标签页
function refreshTab(cfg) {
	var refresh_tab = cfg.tabTitle ? $('#tabs').tabs('getTab', cfg.tabTitle) : $('#tabs').tabs('getSelected');
	if (refresh_tab && refresh_tab.find('iframe').length > 0) {
		var _refresh_ifram = refresh_tab.find('iframe')[0];
		var refresh_url = cfg.url ? cfg.url : _refresh_ifram.src;
		//_refresh_ifram.src = refresh_url;  
		//.contentWindow.location.reload(true);刷新当前页面
		_refresh_ifram.contentWindow.location.href = refresh_url;
	}
}

// 显示msg
function okMsg(msg){
	$("#okmodalContent").html(msg);
	// $("#okmodal").modal({backdrop:'static'});
	$("#okmodal").modal({backdrop:'static'});
	// $("body").children().not($("#yes, #no, #ok")).attr("disabled", "disabled");
}

function yesMsg(msg, callBack){
//	debugger;
	$("#callBack").val(callBack);
	$("#yesornoContent").html(msg);
	$("#yesorno").modal({backdrop:'static'});
	// $("body").children().not($("#yes, #no, #ok")).attr("disabled", "disabled");
}

// 隐藏yesMsg
$("#no").click(function(){
	$("#yesorno").modal('hide');
});

// 格式化时间
Date.prototype.format = function(fmt) { // author: meizz
	var o = {
		"M+" : this.getMonth() + 1, // 月份
		"d+" : this.getDate(), // 日
		"h+" : this.getHours(), // 小时
		"m+" : this.getMinutes(), // 分
		"s+" : this.getSeconds(), // 秒
		"q+" : Math.floor((this.getMonth() + 3) / 3), // 季度
		"S" : this.getMilliseconds()
	// 毫秒
	};  
	
	 if (/(y+)/.test(fmt)) {
		fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
	}
	for ( var k in o) {
		if (new RegExp("(" + k + ")").test(fmt))
			fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
	}
	return fmt;
};
