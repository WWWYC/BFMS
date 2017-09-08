$(function() {

	// 菜单
	$('#menu').tree({
		url : basePath + 'getMenuList',
		state : "closed",
		onClick : function(node) {
			// 如果当前菜单不是子菜单，则不执行操作
			if (node["perLevel"] != 4) {
				return;
			}
			var tabTitle = node["text"];
			var url = basePath + node["url"];
			var id = node["id"];
			var icon = node["iconCls"];

			addTab(tabTitle, url, icon);
		},
		onSelect:function(node){
			$(this).tree(node.state === 'closed' ? 'expand' : 'collapse', node.target);
        },
	});
});