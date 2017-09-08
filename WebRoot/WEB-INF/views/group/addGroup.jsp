<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/shared/commons.jsp"%>

<!DOCTYPE>
<html>
<head>
<base href="<%=basePath%>">

<%@include file="/WEB-INF/shared/cssTag.jsp"%>

<link rel="stylesheet" type="text/css" href="${basePath }/static/css/group/addGroup.css?t=${random_uuid}">

<%@include file="/WEB-INF/shared/jsTag.jsp"%>
<script type="text/javascript" src="${basePath }/static/js/group/addGroup.js?t=${random_uuid}"></script>

</head>
<body>
	<form id="data_form" method="post">
		<table id="data_table" class="table table-striped">
			<tr>
				<td>&nbsp;</td>
				<td>组合编号</td>
				<td>
					<!-- <input type="number" name="groupNum" readonly/> -->
					<input type="number" name="groupNum" style="width:115px;" readonly/>
					<input type="button" id="btn_getGroupNum" value="设置编号">
				</td>
				<td>组合名</td>
				<td><input type="text" name="groupName" /></td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>组合联系人</td>
				<td><input type="text" name="groupContacts" /></td>
				<td>组合地址</td>
				<td><input type="text" name="groupAddress" /></td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>组合电话</td>
				<td><input type="text" name="groupPhone" /></td>
				<td>组合邮箱</td>
				<td><input type="text" name="groupEmail" /></td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>组合传真</td>
				<td><input type="text" name="groupFax" /></td>
				<td>备注</td>
				<td><input type="text" name="comment" /></td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td colspan="3"><input id="btn_save" type="button" class="btn btn-primary" value="保存" style="width: 180px;margin-left: 200px;"></td>
				<td colspan="3"><input type="reset" class="btn btn-primary" value="重置" style="width: 180px;margin-left: 200px;"></td>
			</tr>
		</table>
	</form>
</body>
</html>
