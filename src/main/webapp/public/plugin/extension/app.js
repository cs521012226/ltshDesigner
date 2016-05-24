/**
 * 	JQuery插件使用方法：
 * 	如：
 * 	$("#test").app("test",{
			width:"300",
			height:"1"
	});
 */
(function ($) {

	/**
     * 构造器
     * @param methodStr 方法名称字符串，对应$.fn.app.methods中定义的方法
     * @param params 对options方法的参数
     */
	$.fn.app = function (methodStr, params) {
        if (typeof methodStr === "string") {
            var method = $.fn.app.methods[methodStr];
            if (method) {
                return method(window, params);
            }
        }
    };
	
    /**
     * 方法对象列表
     */
	$.fn.app.methods = {
			/**	
			 * 获取参数对象方法
             * @param w		当前页面window对象
             * @param param	参数对象
             */
			options : function(w, param) {
				var opts = $.extend({}, $.fn.app.defaults, param);
				return opts;
			},
            /**
             * 测试方法
             * @param w		当前页面window对象
             * @param param
             */
			test: function (w, param) {
				var opt = $.fn.app("options", param);	//获取传进来的参数
            	alert(opt.height);
            	alert(opt.width);
            	alert(w.document.getElementById("w").innerHTML);
            }
	};
        /**
         * 默认配置
         */
	$.fn.app.defaults = {
       height: "100",
       width : "200"
	};
	
})(jQuery);