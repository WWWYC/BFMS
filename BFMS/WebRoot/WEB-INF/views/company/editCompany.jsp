<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/shared/commons.jsp"%>

<!DOCTYPE>
<html>
<head>
<base href="<%=basePath%>">

<%@include file="/WEB-INF/shared/cssTag.jsp"%>
<link rel="stylesheet" type="text/css" href="${basePath }/static/css/company/editCompany.css?t=${random_uuid}">

<%@include file="/WEB-INF/shared/jsTag.jsp"%>
<script type="text/javascript" src="${basePath }/static/js/company/editCompany.js?t=${random_uuid}"></script>

</head>
<body>
	<form id="data_form" method="post">
		<table id="data_table" class="table table-striped">
			<tr>
				<td>&nbsp;<input type="hidden" name="companyId" value="${companyId }" /></td>
				<td>企业编号</td>
				<td>
					<!-- <input type="number" name="companyNum" readonly/> -->
					<input type="number" name="companyNum" value="${companyNum }" readonly/>
				</td>
				<td>企业名称</td>
				<td><input type="text" name="companyName" value="${companyName }" /></td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>企业联系人</td>
				<td><input type="text" name="companyContacts" value="${companyContacts }" /></td>
				<td>企业地址</td>
				<td><input type="text" name="companyAddress" value="${companyAddress }" /></td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>企业电话</td>
				<td><input type="text" name="companyPhone" value="${companyPhone }" /></td>
				<td>企业邮箱</td>
				<td><input type="text" name="companyEmail" value="${companyEmail }" /></td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>企业传真</td>
				<td><input type="text" name="companyFax" value="${companyFax }" /></td>
				<td>备注</td>
				<td><input type="text" name="comment" value="${comment }" /></td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>选择组合</td>
				<td style="line-height: ">
					<select id="group_list" style="width: 199px;height: 30px;">
						<option value="${groupId }">${groupName }</option>
					</select>
				</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
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
