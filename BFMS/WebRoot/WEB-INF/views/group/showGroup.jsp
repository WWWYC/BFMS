<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/shared/commons.jsp"%>

<!DOCTYPE>
<html>
<head>
<base href="<%=basePath%>">

<%@include file="/WEB-INF/shared/cssTag.jsp"%>

<link rel="stylesheet" type="text/css" href="${basePath }/static/css/group/showGroup.css?t=${random_uuid}">

<%@include file="/WEB-INF/shared/jsTag.jsp"%>
<script type="text/javascript" src="${basePath }/static/js/group/showGroup.js?t=${random_uuid}"></script>

</head>
<body>
	<form id="data_form" method="post">
		<table id="data_table" class="table table-striped">
			<tr>
				<td>&nbsp;</td>
				<td>组合编号</td>
				<td>
					<input type="number" name="groupNum" value="${groupNum }" readonly/>
				</td>
				<td>组合名</td>
				<td><input type="text" name="groupName" value="${groupName }" readonly /></td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>组合联系人</td>
				<td><input type="text" name="groupContacts" value="${groupContacts }" readonly /></td>
				<td>组合地址</td>
				<td><input type="text" name="groupAddress" value="${groupAddress }" readonly /></td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>组合电话</td>
				<td><input type="text" name="groupPhone" value="${groupPhone }" readonly /></td>
				<td>组合邮箱</td>
				<td><input type="text" name="groupEmail" value="${groupEmail }" readonly /></td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>组合传真</td>
				<td><input type="text" name="groupFax" value="${groupFax }" readonly /></td>
				<td>备注</td>
				<td><input type="text" name="comment" value="${comment }" readonly /></td>
				<td>&nbsp;</td>
			</tr>
		</table>
	</form>
</body>
</html>
