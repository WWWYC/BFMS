<%@ page language="java" import="java.util.*,cn.bf.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
	String path = request.getContextPath();

	String basePath = request.getScheme() + "://"
	                + request.getServerName() + ":"
	                + request.getServerPort() + path + "/";

	// 保存项目名
	application.setAttribute("contextPath", path);
	// 保存完整URL路径
	application.setAttribute("basePath", basePath);

	// 设置不缓存
	response.setHeader("Pragma", "No-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setDateHeader("Expires", -10);
	
	// 设置时间戳
	//////////////////////////////////测试使用
	request.setAttribute("random_uuid", UUIDUtil.getUuid());
%>

<input id="basePath" type="text" hidden="hidden" value="${basePath }" />
<input id="uuid" type="text" hidden="hidden" value="${requestScope.uuid }" />

<%@include file="footer.jsp" %>
