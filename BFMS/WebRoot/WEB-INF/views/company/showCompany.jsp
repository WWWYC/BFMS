<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/shared/commons.jsp"%>

<!DOCTYPE>
<html>
<head>
<base href="<%=basePath%>">

<%@include file="/WEB-INF/shared/cssTag.jsp"%>
<link rel="stylesheet" type="text/css" href="${basePath }/static/css/company/showCompany.css?t=${random_uuid}">

<%@include file="/WEB-INF/shared/jsTag.jsp"%>
<script type="text/javascript" src="${basePath }/static/js/company/showCompany.js?t=${random_uuid}"></script>

</head>
<body>
	<form id="data_form" method="post">
		<table id="data_table" class="table table-striped">
			<tr>
				<td>&nbsp;</td>
				<td>企业编号</td>
				<td>
					<input type="text" name="companyNum" value="${companyNum }" readonly/>
				</td>
				<td>企业名称</td>
				<td><input type="text" name="companyName" value="${companyName }" readonly /></td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>企业联系人</td>
				<td><input type="text" name="companyContacts" value="${companyContacts }" readonly /></td>
				<td>企业地址</td>
				<td><input type="text" name="companyAddress" value="${companyAddress }" readonly /></td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>企业电话</td>
				<td><input type="text" name="companyPhone" value="${companyPhone }" readonly /></td>
				<td>企业邮箱</td>
				<td><input type="text" name="companyEmail" value="${companyEmail }" readonly /></td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>企业传真</td>
				<td><input type="text" name="companyFax" value="${companyFax }" readonly /></td>
				<td>备注</td>
				<td><input type="text" name="comment" value="${comment }" readonly /></td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>组合名称</td>
				<td style="line-height: ">
					<input type="hidden" name="groupId"  value="${groupId }" readonly />
					<input type="text" name="groupName"  value="${groupName }" readonly />
				</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
		</table>
	</form>
</body>



</html>
