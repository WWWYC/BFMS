<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/shared/commons.jsp"%>

<!DOCTYPE>
<html>
<head>
<base href="<%=basePath%>">

<%@include file="/WEB-INF/shared/cssTag.jsp"%>

<link rel="stylesheet" type="text/css" href="${basePath }/static/css/vacation/addVacation.css?t=${random_uuid}">

<%@include file="/WEB-INF/shared/jsTag.jsp"%>
<script type="text/javascript" src="${basePath }/static/js/vacation/addVacation.js?t=${random_uuid}"></script>

</head>
<body>
	<form id="data_form" method="post">
		<table id="data_table" class="table table-striped">
			<tr>
				<td>&nbsp;</td>
				<td>选择组合</td>
				<td>
					<select id="group_list" name="groupId">
						<option>请选择</option>
					</select>
				</td>
				<td>选择企业</td>
				<td>
					<select id="company_list" name="companyId">
						<option>请选择</option>
					</select>
				</td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>选择学生</td>
				<td>
					<select id="student_list" name="stuId">
						<option>请选择</option>
					</select>
				</td>
				<td>请假原因</td>
				<td><input type="text" name="reason" /></td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>请假开始时间</td>
				<td><input class="easyui-datetimebox" name="startDate" /></td>
				<td>请假结束时间</td>
				<td><input class="easyui-datetimebox" name="endDate" /></td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>请假时长</td>
				<td><input type="text" name="duration" /></td>
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
