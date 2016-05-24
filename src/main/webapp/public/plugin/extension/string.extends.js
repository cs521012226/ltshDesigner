/********************************** String Start ************************************/
/**
 * 使用如下:
 * var mapStyle = "http://localhost:8080/module/{id}/{type}".format({id:10, type:'normal'}); // 按照字面量进行格式化，参数可以是对象
 * var indexStyle = "http://localhost:8080/module/{0}/{1}".format(10, 'normal'); // 按照参数的index进行格式化
 * @returns {*}
 */
String.prototype.format = function () {
    var s = this, i = arguments.length;
    while (i--) {
        var v = arguments[i];
        if (typeof v === 'object' && arguments.length == 1) {
            for (var k in v) {
                s = s.replace(new RegExp('\\{' + k + '\\}', 'gm'), v[k]);
            }
        } else {
            s = s.replace(new RegExp('\\{' + i + '\\}', 'gm'), v);
        }
    }
    return s;
};
/**
 * 使用如下:
 * var str = "abcdabcd".replaceAll('ab','@'); 会把所有含有ab的都替换为@
 * @params regexp	正则表达式或字符串
 * @params replacement	要替换的新内容
 * @returns {String} 替换后的字符串
 */
String.prototype.replaceAll = function (regexp, replacement) {
	var str = this;
	var s = null;
	if(typeof regexp == 'string'){
		s = str.replace(new RegExp(regexp, 'gm'), replacement);
	}else{
		s = str.replace(new RegExp(regexp), replacement);
	}
    return s;
};
/**
 * 是否包含子字符串
 * @param str
 * @returns {Boolean}
 */
String.prototype.contains = function (str) {
    return this.indexOf(str.toString()) > -1;
};
/********************************** String End ************************************/

/********************************** StringBuffer Start ************************************/
function StringBuffer(str) {
	this._strings_ = new Array();
	if (str != 'undefined' && str != null && str != '') {
		this._strings_.push(str);
	}
}

StringBuffer.prototype.append = function(str) {
	this._strings_.push(str);
};

StringBuffer.prototype.toString = function() {
	return this._strings_.join("");
};
/********************************** StringBuffer End ************************************/