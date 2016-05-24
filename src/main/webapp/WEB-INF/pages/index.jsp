<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<meta http-equiv=Content-Type content=text/html;charset=utf-8>
	<title>连堂市话演示项目</title>
</head>
<frameset rows="64,*"  frameborder="NO" border="0" framespacing="0">
  <frame src="${ctx}/login/headerPage" noresize="noresize" frameborder="NO" name="topFrame" scrolling="no" marginwidth="0" marginheight="0" />
  <frameset cols="200,*"  rows="560,*" id="frame">
	<frame src="${ctx}/login/leftPage" name="leftFrame" noresize="noresize" marginwidth="0" marginheight="0" frameborder="0" scrolling="no" target="main" />
	<frame src="${ctx}/login/rightPage" name="main" marginwidth="0" marginheight="0" frameborder="0" scrolling="auto" />
</frameset>
<noframes>
  <body></body>
    </noframes>
</html>

