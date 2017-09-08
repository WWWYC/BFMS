<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/shared/commons.jsp"%>

<!DOCTYPE>
<html>
<head>
<base href="<%=basePath%>">

<%@include file="/WEB-INF/shared/cssTag.jsp"%>
<link rel="stylesheet" type="text/css" href="${basePath }/static/css/student/findStudent.css?t=${random_uuid}">

<%@include file="/WEB-INF/shared/jsTag.jsp"%>
<script type="text/javascript" src="${basePath }/static/js/student/findStudent.js?t=${random_uuid}"></script>


</head>
<body>
	<div id="grid_tb" style="height:auto">
		<!-- <a id="btn_addStudent" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" >添加学生</a> -->
		<a id="btn_showStudent" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" >查看学生</a>
		<a id="btn_editStudent" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" >编辑学生</a>
		<a id="btn_rmStudent" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" >删除学生</a>
	</div>
	
	<form id="data_form">
		<table id="data_table" class="table table-striped">
			<tr>
				<td>学号</td>
				<td><input name="stuNum" type="text" /></td>
				<td>中文名</td>
				<td><input  type="text" name="nameZh" /></td>
				<td>性别</td>
				<td>
					<input id="male" type="radio" name="gender" value="男"/>
					<label for="male">男</label>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<input id="female" type="radio" name="gender" value="女"/>
					<label for="female">女</label>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<input id="no_sel" type="radio" name="gender" value="" checked="checked"/>
					<label for="no_sel">不选择</label>
				</td>
			</tr>
			<tr>
				<td>工种</td>
				<td><input type="text" name="craft" /></td>
				<td>组合</td>
				<td>
					<select id="group_list" style="width: 199px;height: 30px;">
						<option></option>
					</select>
				</td>
				<td>企业</td>
				<td>
					<select id="company_list" style="width: 199px;height: 30px;">
						<option></option>
					</select>
				</td>
			</tr>
			<tr>
				<td colspan="3"><input id="btn_search" type="button" class="btn btn-primary" value="查询" style="width: 180px;margin-left: 200px;"></td>
				<td colspan="3"><input type="reset" class="btn btn-primary" value="重置" style="width: 180px;margin-left: 200px;"></td>
			</tr>
		</table>
	</form>
	 <!-- data-options="url:'datagrid_data.json',fitColumns:true,singleSelect:true" -->
	<table id="data_grid" style="width:100%;height:339px"></table>
	<div id="tabs" class="easyui-tabs" style="width:100%;"></div>
	
	
</body>

</html>