<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/shared/commons.jsp"%>

<!DOCTYPE>
<html>
<head>
<base href="<%=basePath%>">

<%@include file="/WEB-INF/shared/cssTag.jsp"%>
<link rel="stylesheet" type="text/css" href="${basePath }/static/css/expense/editExpense.css?t=${random_uuid}">

<%@include file="/WEB-INF/shared/jsTag.jsp"%>
<script type="text/javascript" src="${basePath }/static/js/expense/editExpense.js?t=${random_uuid}"></script>

</head>
<body>
	<form id="data_form" method="post">
		<input type="hidden" name="uuid" value="${uuid }" />
		<input type="hidden" name="stuId" value="stuId" >
		<table id="data_table" class="table table-striped">
			<tr>
				<td>&nbsp;</td>
				<%-- <td>学生姓名</td>
				<td><input type="text" value="${nameZh }" readonly/></td> --%>
				<td>管理费</td>
				<td><input type="text" name="manageExp" value="${manageExp }" readonly /></td>
				<td>培训费</td>
				<td><input type="text" name="trainExp" value="${trainExp }" readonly /></td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>培训合格证</td>
				<td><input type="text" name="qualifiedExp" value="${qualifiedExp }" readonly /></td>
				<td>律师见证费</td>
				<td><input type="text" name="lawyerExp" value="${lawyerExp }" readonly /></td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>第一次缴费</td>
				<td><input type="text" name="expenseSt" value="${expenseSt }" readonly /></td>
				<td>第一次缴费时间</td>
				<td><input type="text" name="payDateSt" value="${payDateSt }" readonly /></td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>第二次缴费</td>
				<td><input type="text" name="expenseNd" value="${expenseNd }" readonly /></td>
				<td>第二次缴费时间</td>
				<td><input type="number" name="payDateNd" value="${payDateNd }" readonly /></td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>第三次缴费</td>
				<td><input type="number" name="expenseRd" value="${expenseRd }" readonly /></td>
				<td>第三次缴费时间</td>
				<td><input type="number" name="payDateRd" value="${payDateRd }" readonly /></td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>第四次缴费</td>
				<td><input type="number" name="expenseTh" value="${expenseTh }" readonly /></td>
				<td>第四次缴费时间</td>
				<td><input type="number" name="payDateTh" value="${payDateTh }" readonly /></td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>备注</td>
				<td><input type="text" name="comment" value="${comment }" readonly /></td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
		</table>
	</form>
</body>
</html>
