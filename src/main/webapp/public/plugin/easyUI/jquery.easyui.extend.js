// 重载EasyUI.Tree控件与后台整合相关的参数调整
$.extend($.fn.tree.defaults, {
    /**
     *    重写 loader 方法，实现返回的data以符合tree要求
     */
    loader: function (param, success, error) {
        var opts = $(this).tree("options");
        if (!opts.url) {
            return false;
        }
        var url = opts.url + "/" + (param.id ? param.id : '0');
        $.ajax({
        	type: opts.method, 
        	url: url, 
        	dataType: "json", 
        	success: function (data) {
        		var menuArr = [];
        		$.each(data, function (index, menu){
        			var view = {
        				id: menu.id,
    	                text: menu.name,
    	                state: menu.isLeaf || menu.url ? 'open' : 'closed',
    	                attributes: menu
        			};
        			menuArr[index] = view;
        		});
	            success(menuArr);
        	}, 
        	error: function () {
        		error.apply(this, arguments);
        	}
        });
    }
});
