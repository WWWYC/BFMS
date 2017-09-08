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
		<input type="hidden" name="stuId" value="${stuId }" >
		<table id="data_table" class="table table-striped">
			<tr>
				<td>&nbsp;</td>
				<%-- <td>学生姓名</td>
				<td><input type="text" value="${nameZh }" readonly/></td> --%>
				<td>管理费</td>
				<td><input type="number" name="manageExp" value="${manageExp }" /></td>
				<td>培训费</td>
				<td><input type="number" name="trainExp" value="${trainExp }" /></td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>培训合格证</td>
				<td><input type="number" name="qualifiedExp" value="${qualifiedExp }" /></td>
				<td>律师见证费</td>
				<td><input type="number" name="lawyerExp" value="${lawyerExp }" /></td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>第一次缴费</td>
				<td><input type="number" name="expenseSt" value="${expenseSt }" /></td>
				<td>第一次缴费时间</td>
				<%-- <td><input type="date" name="payDateSt" value="${payDateSt }" /></td> --%>
				<td><input class="easyui-datebox" name="payDateSt" value="${payDateSt }" /></td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>第二次缴费</td>
				<td><input type="number" name="expenseNd" value="${expenseNd }" /></td>
				<td>第二次缴费时间</td>
				<%-- <td><input type="date" name="payDateNd" value="${payDateNd }" /></td> --%>
				<td><input class="easyui-datebox" name="payDateNd" value="${payDateNd }" /></td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>第三次缴费</td>
				<td><input type="number" name="expenseRd" value="${expenseRd }" /></td>
				<td>第三次缴费时间</td>
				<%-- <td><input type="date" name="payDateRd" value="${payDateRd }" /></td> --%>
				<td><input class="easyui-datebox" name="payDateRd" value="${payDateRd }" /></td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>第四次缴费</td>
				<td><input type="number" name="expenseTh" value="${expenseTh }" /></td>
				<td>第四次缴费时间</td>
				<%-- <td><input type="date" name="payDateTh" value="${payDateTh }" /></td> --%>
				<td><input class="easyui-datebox" name="payDateTh" value="${payDateTh }" /></td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>备注</td>
				<td><input type="text" name="comment" value="${comment }" /></td>
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
