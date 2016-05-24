<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:set var="baseURI" value="${pageContext.request.scheme}${'://'}${pageContext.request.serverName}${':'}${pageContext.request.serverPort}${pageContext.request.contextPath}"/>

<script type="text/javascript" src="${ctx}/public/plugin/jquery-1.7.2.min.js"></script>

 
<link rel="stylesheet" type="text/css" href="${ctx}/public/plugin/easyUI/themes/gray/easyui.css">
<link rel="stylesheet" type="text/css" href="${ctx}/public/plugin/easyUI/themes/icon.css">
<script type="text/javascript" src="${ctx}/public/plugin/easyUI/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${ctx}/public/plugin/easyUI/jquery.easyui.extend.js"></script>

<link rel="stylesheet" href="${ctx}/public/plugin/jqueryUI/themes/base/jquery.ui.all.css">
<script src="${ctx}/public/plugin/jqueryUI/ui/jquery.ui.core.js"></script>
<script src="${ctx}/public/plugin/jqueryUI/ui/jquery.ui.widget.js"></script>

<link rel="stylesheet" href="${ctx}/public/framework/common/css/style.css">

