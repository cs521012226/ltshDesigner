<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="/WEB-INF/pages/framework/common/meta.jsp" %>
<title>管理页面</title>

<script src="${ctx}/public/framework/newTheme/js/prototype.lite.js" type="text/javascript"></script>
<script src="${ctx}/public/framework/newTheme/js/moo.fx.js" type="text/javascript"></script>
<script src="${ctx}/public/framework/newTheme/js/moo.fx.pack.js" type="text/javascript"></script>
<style>
body {
	font:12px Arial, Helvetica, sans-serif;
	color: #000;
	background-color: #EEF2FB;
	margin: 0px;
}
#container {
	width: 182px;
}
H1 {
	font-size: 12px;
	margin: 0px;
	width: 182px;
	cursor: pointer;
	height: 30px;
	line-height: 20px;	
}
H1 a {
	display: block;
	width: 182px;
	color: #000;
	height: 30px;
	text-decoration: none;
	moz-outline-style: none;
	background: url("${ctx}/public/framework/newTheme/images/menu_bgs.gif");
	background-repeat: no-repeat;
	line-height: 30px;
	text-align: center;
	margin: 0px;
	padding: 0px;
}
.content{
	width: 182px;
	height: 26px;
	
}
.MM ul {
	list-style-type: none;
	margin: 0px;
	padding: 0px;
	display: block;
}
.MM li {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 12px;
	line-height: 26px;
	color: #333333;
	list-style-type: none;
	display: block;
	text-decoration: none;
	height: 26px;
	width: 182px;
	padding-left: 0px;
}
.MM {
	width: 182px;
	margin: 0px;
	padding: 0px;
	left: 0px;
	top: 0px;
	right: 0px;
	bottom: 0px;
	clip: rect(0px,0px,0px,0px);
}
.MM a:link {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 12px;
	line-height: 26px;
	color: #333333;
	background: url("${ctx}/public/framework/newTheme/images/menu_bg1.gif");
	background-repeat: no-repeat;
	height: 26px;
	width: 182px;
	display: block;
	text-align: center;
	margin: 0px;
	padding: 0px;
	overflow: hidden;
	text-decoration: none;
}
.MM a:visited {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 12px;
	line-height: 26px;
	color: #333333;
	background: url("${ctx}/public/framework/newTheme/images/menu_bg1.gif");
	background-repeat: no-repeat;
	display: block;
	text-align: center;
	margin: 0px;
	padding: 0px;
	height: 26px;
	width: 182px;
	text-decoration: none;
}
.MM a:active {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 12px;
	line-height: 26px;
	color: #333333;
	background: url("${ctx}/public/framework/newTheme/images/menu_bg1.gif");
	background-repeat: no-repeat;
	height: 26px;
	width: 182px;
	display: block;
	text-align: center;
	margin: 0px;
	padding: 0px;
	overflow: hidden;
	text-decoration: none;
}
.MM a:hover {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 12px;
	line-height: 26px;
	font-weight: bold;
	color: #006600;
	background: url("${ctx}/public/framework/newTheme/images/menu_bg2.gif");
	background-repeat: no-repeat;
	text-align: center;
	display: block;
	margin: 0px;
	padding: 0px;
	height: 26px;
	width: 182px;
	text-decoration: none;
}
</style>
</head>

<body>
<table width="100%" height="280" border="0" cellpadding="0" cellspacing="0" bgcolor="#EEF2FB">
  <tr>
    <td width="182" valign="top">
    <div id="container">
      <!-- <h1 class="type"><a href="javascript:void(0)">网站常规管理</a></h1>
      <div class="content">
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td><img src="${ctx}/public/framework/newTheme/images/menu_topline.gif" width="182" height="5" /></td>
          </tr>
        </table>
        <ul class="MM">
          <li><a href="#" target="main">基本设置</a></li>
          <li><a href="#" target="main">邮件设置</a></li>
        </ul>
      </div>
    </div>
        <h1 class="type"><a href="javascript:void(0)">其它参数管理</a></h1>
      <div class="content">
          <table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td><img src="${ctx}/public/framework/newTheme/images/menu_topline.gif" width="182" height="5" /></td>
            </tr>
          </table>
        <ul class="MM">
            <li><a href="#" target="main">管理设置</a></li>
          <li><a href="#" target="main">主机状态</a></li>
        </ul>
      </div> -->
      </div>
        <script type="text/javascript">
		/*	var contents = document.getElementsByClassName('content');
			var toggles = document.getElementsByClassName('type');
		
			var myAccordion = new fx.Accordion(
				toggles, contents, {opacity: true, duration: 400}
			);
			myAccordion.showThisHideOpen(contents[0]); */
		</script>
        </td>
  </tr>
</table>
<script type="text/javascript">
jQuery(document).ready(function(){
	getMenuTree();
});

function getMenuTree(){
	jQuery.ajax({
  	    url: '${ctx}/framework/modules/admin/menu/getTreeList',
  	    type: 'POST',
  	  	async: true,
  	  	cache: false,
  	  	dataType: 'json',
  	    error: function(){ return;  },
  	  	success:function(data){
  	  	  var text = "";
	  	  for(var i=0; i<data.length; i++){
	  			text += "<h1 class=\"type\"><a href=\"javascript:void(0)\">"+ data[i].name +"</a></h1>";
	  			text += "<div class=\"content\">";
	  			text += "<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">";
	  			text += "<tr>";
	  			text += "<td><img src=\"${ctx}/public/framework/newTheme/images/menu_topline.gif\" width=\"182\" height=\"5\" /></td>";
	  			text += "</tr>";
	  			text += "</table>";
	  			text += "<ul class=\"MM\">";
	  			for(var j=0; j<data[i].childrenMenu.length; j++){
	  				var data2 = data[i].childrenMenu;
	  				text += "<li><a href=\"#\" onClick=\"javascript:openUrl('${ctx}" + data2[j].url +"')\" >"+data2[j].name+"</a></li>";
	  			}
	  			text += "</ul>";
	  			text += "</div>";
	  	  }
	  	 jQuery("#container").append(text);
      	}
  	});
}

function openUrl(url){
	top.main.location.href = url;
}

</script>
</body>
</html>