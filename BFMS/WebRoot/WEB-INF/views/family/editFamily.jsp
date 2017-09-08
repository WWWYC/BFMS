<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/shared/commons.jsp"%>

<!DOCTYPE>
<html>
<head>
<base href="<%=basePath%>">

<%@include file="/WEB-INF/shared/cssTag.jsp"%>
<link rel="stylesheet" type="text/css" href="${basePath }/static/css/family/editFamily.css?t=${random_uuid}">

<%@include file="/WEB-INF/shared/jsTag.jsp"%>
<script type="text/javascript" src="${basePath }/static/js/family/editFamily.js?t=${random_uuid}"></script>


</head>
<body>
	<input id="stuId" type="hidden" value="${stuId }" />
	<table id="data_table" class="table table-striped">
		<thead>
			<tr>
				<th>姓名</th>
				<th>关系</th>
				<th>电话</th>
				<th>住址</th>
				<th>备注</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			
		</tbody>
	</table>
	<hr style="margin: 0" />
	<div class="buttons">
		<!-- <a id="btn_saveFamily" href="javascript:void(0)" class="easyui-linkbutton" >保存修改</a> -->
		<button id="btn_saveFamily" class="btn btn-primary">保存修改</button>
	</div>
	
</body>
</html>