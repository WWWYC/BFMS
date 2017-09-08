<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/shared/commons.jsp"%>

<!DOCTYPE>
<html>
<head>
<base href="<%=basePath%>">

<%@include file="/WEB-INF/shared/cssTag.jsp"%>

<link rel="stylesheet" type="text/css" href="${basePath }/static/css/student/addStudent.css?t=${random_uuid}">

<%@include file="/WEB-INF/shared/jsTag.jsp"%>
<script type="text/javascript" src="${basePath }/static/js/student/addStudent.js?t=${random_uuid}"></script>

</head>
<body>
<form id="data_form" method="post" enctype="multipart/form-data">
	<table id="data_table" class="table table-striped">
		<tr>
			<td>学号</td>
			<td>
				<input type="number" name="stuNum" style="width: 125px;" readonly/>
				<input id="btn_getStuNum" type="button" value="设置学号" style="width: 70px;padding: 1px;"/>
			</td>
			<td>中文名</td>
			<td><input  type="text" name="nameZh" value="王"/></td>
			<td>日文名</td>
			<td><input type="text" name="nameJp" /></td>
		</tr>
		<tr>
			<td>日文名（假名）</td>
			<td><input type="text" name="nameKana" /></td>
			<td>工种</td>
			<td><input type="text" name="craft" value="啊啊"/></td>
			<td>年龄</td>
			<td><input type="number" name="age" /></td>
		</tr>
		<tr>
			<td>性别</td>
			<td>
				<input id="male" type="radio" name="gender" checked="checked" value="男"/>
				<label for="male">男</label>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<input id="female" type="radio" name="gender" value="女"/>
				<label for="female">女</label>
			</td>
			<td>身高（cm）</td>
			<td><input type="number" name="height" /></td>
			<td>体重（kg）</td>
			<td><input type="number" name="weight" /></td>
		</tr>
		<tr>
			<td>血型</td>
			<td><input type="text" name="blood" /></td>
			<td>手机号</td>
			<td><input type="number" name="stuPhone" value="13311111"/></td>
			<td>QQ</td>
			<td><input type="number" name="qq" /></td>
		</tr>
		<tr>
			<td>照片</td>
			<td><input type="file" name="stuPhoto" /></td>
			<td>实际入校日</td>
			<!-- <td><input type="date" name="entryDate" value="2017-08-10"/></td> -->
			<td><input class="easyui-datebox" name="entryDate" value="2017-08-10"/></td>
			<td>家庭联系方式</td>
			<td><input type="text" name="familyPhone" /></td>
		</tr>
		<tr>
			<td>家庭住址（中国）</td>
			<td><input type="text" name="addressZh" /></td>
			<td>家庭住址（日本）</td>
			<td><input type="text" name="addressJp" /></td>
			<td>同期生</td>
			<td><input type="number" name="sameClass" /></td>
		</tr>
		<tr>
			<td>面试日期</td>
			<!-- <td><input type="date" name="interviewDate" /></td> -->
			<td><input class="easyui-datebox" name="interviewDate" /></td>
			<td>预计出境日</td>
			<!-- <td><input type="date" name="exitDate" /></td> -->
			<td><input class="easyui-datebox" name="exitDate" /></td>
			<td>身份证号</td>
			<td><input type="text" name="identityNum" value="1122"/></td>
		</tr>
		<tr>
			<td>护照编号</td>
			<td><input type="number" name="passportNum" /></td>
			<td>毕业证编号</td>
			<td><input type="number" name="diplomaNum" /></td>
			<td>去往地</td>
			<td><input type="text" name="destination" /></td>
		</tr>
		<tr>
			<td>有无户口本</td>
			<td>
				<input id="isRegister" type="radio" name="isRegister" checked="checked" value="有" />
				<label for="isRegister" >有</label>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<input id="isNotRegister" type="radio" name="isRegister" value="无" />
				<label for="isNotRegister">无</label>
			</td>
			<td>有无纹身</td>
			<td>
				<input id="isTattoo" type="radio" name="isTattoo" value="有" />
				<label for="isTattoo" >有</label>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<input id="isNotTattoo" type="radio" name="isTattoo" checked="checked" value="无" />
				<label for="isNotTattoo">无</label>
			</td>
			<td>有无健康证</td>
			<td>
				<input id="isHealth" type="radio" name="isHealth" value="有" />
				<label for="isHealth" >有</label>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<input id="isNotHealth" type="radio" name="isHealth" checked="checked" value="无" />
				<label for="isNotHealth">无</label>
			</td>
		</tr>
		<tr>
			<td>有无毕业证</td>
			<td>
				<input id="isDiploma" type="radio" name="isDiploma" checked="checked" value="有" />
				<label for="isDiploma" >有</label>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<input id="isNotDiploma" type="radio" name="isDiploma" value="无" />
				<label for="isNotDiploma">无</label>
			</td>
			<td>是否已婚</td>
			<td>
				<input id="isMarried" type="radio" name="isMarried" value="是" />
				<label for="isMarried" >是</label>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<input id="isNotMarried" type="radio" name="isMarried" checked="checked" value="否" />
				<label for="isNotMarried">否</label>
			</td>
			<td>左眼视力</td>
			<td><input type="number" name="eyeLeft" /></td>
		</tr>
		<tr>
			<td>右眼视力</td>
			<td><input type="number" name="eyeRight" /></td>
			<td>民族</td>
			<td><input type="text" name="nation" /></td>
			<td>备注</td>
			<td><input type="text" name="comment" /></td>
		</tr>
		<tr>
			<td>组合</td>
			<td>
				<select id="group_list" style="width: 199px;height: 30px;">
					<option></option>
				</select>
			</td>
			<td>企业</td>
			<td>
				<select id="company_list" name="companyId" style="width: 199px;height: 30px;">
					<option></option>
				</select>
			</td>
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
