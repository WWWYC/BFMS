<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/shared/commons.jsp"%>

<!DOCTYPE>
<html>
<head>
<base href="<%=basePath%>">

<%@include file="/WEB-INF/shared/cssTag.jsp"%>
<link rel="stylesheet" type="text/css" href="${basePath }/static/css/student/editStudent.css?t=${random_uuid}">

<%@include file="/WEB-INF/shared/jsTag.jsp"%>
<script type="text/javascript" src="${basePath }/static/js/student/editStudent.js?t=${random_uuid}"></script>


</head>
<body>
<form id="data_form" method="post" enctype="multipart/form-data">
	<input value="${stuId }" name="stuId" hidden="hidden" />
	 <table id="data_table" class="table table-striped">
		<tr>
			<td>学号</td>
			<td><input type="text" name="stuNum" value="${stuNum }" readonly/></td>
			<td>中文名</td>
			<td><input  type="text" name="nameZh" value="${nameZh }" /></td>
			<td colspan="1" rowspan="3" style="vertical-align: middle;max-width: 148px;max-height: 118px;">
				<c:choose>
					<c:when test="${not empty stuPhoto}">
						<img id="img_photo" alt="照片" src="${basePath }${stuPhoto}" style="width : 115px; height : 115px; max-height: 115px;max-width: 115px;" >
					</c:when>
					<c:otherwise>
						<img id="img_photo" alt="照片" src="" style="width : 115px; height : 115px; max-height: 115px;max-width: 115px;" >
					</c:otherwise>
				</c:choose>
			</td>
			<td colspan="1" style="vertical-align: middle;">请选择照片</td>
		</tr>
		<tr>
			<td>日文名</td>
			<td><input type="text" name="nameJp" value="${nameJp }" /></td>
			<td>日文名（假名）</td>
			<td><input type="text" name="nameKana"  value="${nameKana }" /></td>
			<td><input type="file" name="stuPhoto" style="width:220px;" /></td>
		</tr>
		<tr>
			<td>年龄</td>
			<td><input type="number" name="age" value="${age }" /></td>
			<td>性别</td>
			<td>
				<c:choose>
					<c:when test="${gender == '男'}">
						<input id="male" type="radio" name="gender" checked="checked" value="男"/>
						<label for="male">男</label>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<input id="female" type="radio" name="gender" value="女"/>
						<label for="female">女</label>
					</c:when>
					<c:when test="${gender == '女'}">
						<input id="male" type="radio" name="gender" value="男"/>
						<label for="male">男</label>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<input id="female" type="radio" name="gender" checked="checked" value="女"/>
						<label for="female">女</label>
					</c:when>
				</c:choose>
			</td>
			<td></td>
		</tr>
		<tr>
			<td>身高（cm）</td>
			<td><input type="number" name="height" value="${height }" /></td>
			<td>体重（kg）</td>
			<td><input type="number" name="weight" value="${weight }" /></td>
			<td>血型</td>
			<td><input type="text" name="blood" value="${blood }" /></td>
		</tr>
		<tr>
			<td>工种</td>
			<td><input type="text" name="craft" value="${craft }" /></td>
			<td>左眼视力</td>
			<td><input type="number" name="eyeLeft" value="${eyeLeft }" /></td>
			<td>右眼视力</td>
			<td><input type="number" name="eyeRight" value="${eyeRight }" /></td>
		</tr>
		<tr>
			<td>手机号</td>
			<td><input type="number" name="stuPhone" value="${stuPhone }" /></td>
			<td>QQ</td>
			<td><input type="number" name="qq" value="${qq }" /></td>
			<td>同期生</td>
			<td><input type="number" name="sameClass" value="${sameClass }" /></td>
		</tr>
		<tr>
			<td>民族</td>
			<td><input type="text" name="nation" value="${nation }" /></td>
			<td>身份证号</td>
			<td><input type="text" name="identityNum" value="${identityNum }" /></td>
			<td>家庭联系方式</td>
			<td><input type="number" name="familyPhone" value="${familyPhone }" /></td>
		</tr>
		<tr>
			<td>有无户口本</td>
			<td>
				<c:choose>
					<c:when test="${isRegister == '有'}">
						<input id="isRegister" type="radio" name="isRegister" checked="checked" value="有" />
						<label for="isRegister" >有</label>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<input id="isNotRegister" type="radio" name="isRegister" value="无" />
						<label for="isNotRegister">无</label>
					</c:when>
					<c:when test="${isRegister == '无'}">
						<input id="isRegister" type="radio" name="isRegister" value="有" />
						<label for="isRegister" >有</label>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<input id="isNotRegister" type="radio" name="isRegister" checked="checked" value="无" />
						<label for="isNotRegister">无</label>
					</c:when>
				</c:choose>
			</td>
			<td>有无纹身</td>
			<td>
				<c:choose>
					<c:when test="${isTattoo == '有'}">
						<input id="isTattoo" type="radio" name="isTattoo" checked="checked" value="有" />
						<label for="isTattoo" >有</label>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<input id="isNotTattoo" type="radio" name="isTattoo" value="无" />
						<label for="isNotTattoo">无</label>
					</c:when>
					<c:when test="${isTattoo == '无'}">
						<input id="isTattoo" type="radio" name="isTattoo" value="有" />
						<label for="isTattoo" >有</label>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<input id="isNotTattoo" type="radio" name="isTattoo" checked="checked" value="无" />
						<label for="isNotTattoo">无</label>
					</c:when>
				</c:choose>
			</td>
			<td>有无健康证</td>
			<td>
				<c:choose>
					<c:when test="${isHealth == '有'}">
						<input id="isHealth" type="radio" name="isHealth" checked="checked" value="有" />
						<label for="isHealth" >有</label>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<input id="isNotHealth" type="radio" name="isHealth" value="无" />
						<label for="isNotHealth">无</label>
					</c:when>
					<c:when test="${isHealth == '无'}">
						<input id="isHealth" type="radio" name="isHealth" value="有" />
						<label for="isHealth" >有</label>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<input id="isNotHealth" type="radio" name="isHealth" checked="checked" value="无" />
						<label for="isNotHealth">无</label>
					</c:when>
				</c:choose>
			</td>
		</tr>
		<tr>
			<td>有无毕业证</td>
			<td>
				<c:choose>
					<c:when test="${isDiploma == '有'}">
						<input id="isDiploma" type="radio" name="isDiploma" checked="checked" value="有" />
						<label for="isDiploma" >有</label>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<input id="isNotDiploma" type="radio" name="isDiploma" value="无" />
						<label for="isNotDiploma">无</label>
					</c:when>
					<c:when test="${isDiploma == '无'}">
						<input id="isDiploma" type="radio" name="isDiploma" value="有" />
						<label for="isDiploma" >有</label>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<input id="isNotDiploma" type="radio" name="isDiploma" checked="checked" value="无" />
						<label for="isNotDiploma">无</label>
					</c:when>
				</c:choose>
			</td>
			<td>是否已婚</td>
			<td>
				<c:choose>
					<c:when test="${isMarried == '是'}">
						<input id="isMarried" type="radio" name="isMarried" checked="checked" value="是" />
						<label for="isMarried" >是</label>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<input id="isNotMarried" type="radio" name="isMarried" value="否" />
						<label for="isNotMarried">否</label>
					</c:when>
					<c:when test="${isMarried == '否'}">
						<input id="isMarried" type="radio" name="isMarried" value="是" />
						<label for="isMarried" >是</label>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<input id="isNotMarried" type="radio" name="isMarried" checked="checked" value="否" />
						<label for="isNotMarried">否</label>
					</c:when>
				</c:choose>
			</td>
			<td>去往地</td>
			<td><input type="text" name="destination" value="${destination }" /></td>
		</tr>
		<tr>
			<td>组合</td>
			<td>
				<select id="group_list" style="width: 199px;height: 30px;">
					<option value="${groupId}">${groupName }</option>
				</select>
			</td>
			<td>企业</td>
			<td>
				<select id="company_list" style="width: 199px;height: 30px;">
					<option value="${companyId}">${companyName }</option>
				</select>
			</td>
			<td>护照编号</td>
			<td><input type="number" name="passportNum" value="${passportNum }" /></td>
		</tr>
		<tr>
			<td>家庭住址（中国）</td>
			<td><input type="text" name="addressZh" value="${addressZh }" /></td>
			<td>家庭住址（日本）</td>
			<td><input type="text" name="addressJp" value="${addressJp }" /></td>
			<td>毕业证编号</td>
			<td><input type="number" name="diplomaNum" value="${diplomaNum }" /></td>
		</tr>
		<tr>
			<td>实际入校日</td>
			<%-- <td><input type="date" name="entryDate" value="${entryDate }"  /></td> --%>
			<td><input class="easyui-datebox" name="entryDate" value="${entryDate }"  /></td>
			<td>面试日期</td>
			<%-- <td><input type="date" name="interviewDate" value="${interviewDate }" /></td> --%>
			<td><input class="easyui-datebox" name="interviewDate" value="${interviewDate }" /></td>
			<td>预计出境日</td>
			<%-- <td><input type="date" name="exitDate" value="${exitDate }" /></td> --%>
			<td><input class="easyui-datebox" name="exitDate" value="${exitDate }" /></td>
		</tr>
		<tr>
			<td>备注</td>
			<td><input type="text" name="comment" value="${comment }" /></td>
		</tr>
		<tr>
			<td colspan="3"><input id="btn_save" type="button" class="btn btn-primary" value="保存" style="width: 180px;margin-left: 200px;"></td>
			<td colspan="3"><input type="reset" class="btn btn-primary" value="重置" style="width: 180px;margin-left: 200px;"></td>
		</tr>
	</table>
</form>
</body>

</html>