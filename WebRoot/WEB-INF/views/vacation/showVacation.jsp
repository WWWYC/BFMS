<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/shared/commons.jsp"%>

<!DOCTYPE>
<html>
<head>
<base href="<%=basePath%>">

<%@include file="/WEB-INF/shared/cssTag.jsp"%>

<link rel="stylesheet" type="text/css" href="${basePath }/static/css/vacation/showVacation.css?t=${random_uuid}">

<%@include file="/WEB-INF/shared/jsTag.jsp"%>
<script type="text/javascript" src="${basePath }/static/js/vacation/showVacation.js?t=${random_uuid}"></script>

</head>
<body>
	<form id="data_form" method="post">
		<table id="data_table" class="table table-striped">
			<tr>
				<td>&nbsp;</td>
				<td>姓名</td>
				<td><input type="text" value="${nameZh }" readonly /> </td>
				<td>请假原因</td>
				<td><input type="text" value="${reason }" readonly /></td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>请假开始时间</td>
				<td><input type="text" value="${startDate }" readonly /></td>
				<td>请假结束时间</td>
				<td><input type="text" value="${endDate }" readonly /></td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>请假时长</td>
				<td><input type="text" value="${duration }" readonly /></td>
				<td>备注</td>
				<td><input type="text" value="${comment }" readonly /></td>
				<td>&nbsp;</td>
			</tr>
		</table>
	</form>
</body>
</html>
