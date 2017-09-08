<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/shared/commons.jsp"%>

<!DOCTYPE>
<html>
<head>
<base href="<%=basePath%>">

<%@include file="/WEB-INF/shared/cssTag.jsp"%>
<link rel="stylesheet" type="text/css" href="${basePath }/static/css/student/showStudent.css?t=${random_uuid}">

<%@include file="/WEB-INF/shared/jsTag.jsp"%>
<script type="text/javascript" src="${basePath }/static/js/student/showStudent.js?t=${random_uuid}"></script>


</head>
<body>
	 <table id="data_table" class="table table-striped">
		<tr>
			<td>学号</td>
			<td>
				<input type="text" name="stuNum" value="${stuNum }" readonly />
			</td>
			<td>中文名</td>
			<td><input  type="text" name="nameZh" value="${nameZh }" readonly /></td>
			<td colspan="2" rowspan="3" style="vertical-align: middle;max-height: 118px; max-width: 148px;">
				<c:choose>
					<c:when test="${not empty stuPhoto}">
						<img id="img_photo" alt="照片" src="${basePath }${stuPhoto}" style="width : 115px; height : 115px; max-height: 115px;max-width: 115px;" >
					</c:when>
					<c:otherwise>
						<img id="img_photo" alt="照片" src="" style="width : 115px; height : 115px; max-height: 115px;max-width: 115px;" >
					</c:otherwise>
				</c:choose>
			</td>
		</tr>
		<tr>
			<td>日文名</td>
			<td><input type="text" name="nameJp" value="${nameJp }" readonly /></td>
			<td>日文名（假名）</td>
			<td><input type="text" name="nameKana"  value="${nameKana }" readonly /></td>
		</tr>
		<tr>
			<td>年龄</td>
			<td><input type="text" name="age" value="${age }" readonly /></td>
			<td>性别</td>
			<td><input type="text" name="gender" value="${gender }" readonly  /></td>
		</tr>
		<tr>
			<td>身高（cm）</td>
			<td><input type="text" name="height" value="${height }" readonly /></td>
			<td>体重（kg）</td>
			<td><input type="text" name="weight" value="${weight }" readonly /></td>
			<td>血型</td>
			<td><input type="text" name="blood" value="${blood }" readonly /></td>
		</tr>
		<tr>
			<td>工种</td>
			<td><input type="text" name="craft" value="${craft }" readonly /></td>
			<td>左眼视力</td>
			<td><input type="text" name="eyeLeft" value="${eyeLeft }" readonly /></td>
			<td>右眼视力</td>
			<td><input type="text" name="eyeRight" value="${eyeRight }" readonly /></td>
		</tr>
		<tr>
			<td>手机号</td>
			<td><input type="text" name="stuPhone" value="${stuPhone }" readonly /></td>
			<td>QQ</td>
			<td><input type="text" name="qq" value="${qq }" readonly /></td>
			<td>同期生</td>
			<td><input type="text" name="sameClass" value="${sameClass }" readonly /></td>
		</tr>
		<tr>
			<td>民族</td>
			<td><input type="text" name="nation" value="${nation }" readonly /></td>
			<td>身份证号</td>
			<td><input type="text" name="identityNum" value="${identityNum }" readonly /></td>
			<td>家庭联系方式</td>
			<td><input type="text" name="familyPhone" value="${familyPhone }" readonly /></td>
		</tr>
		<tr>
			<td>有无户口本</td>
			<td><input type="text" name="isRegister" value="${isRegister }" readonly /></td>
			<td>有无纹身</td>
			<td><input type="text" name="isNotTattoo" value="${isTattoo }" readonly /></td>
			<td>有无健康证</td>
			<td><input type="text" name="isHealth" value="${isHealth }" readonly /></td>
		</tr>
		<tr>
			<td>有无毕业证</td>
			<td><input type="text" name="isDiploma" value="${isDiploma }" readonly /></td>
			<td>是否已婚</td>
			<td><input type="text" name="isMarried" value="${isMarried }" readonly /></td>
			<td>去往地</td>
			<td><input type="text" name="destination" value="${destination }" readonly /></td>
		</tr>
		<tr>
			<td>组合</td>
			<td><input type="text" name="groupName" value="${groupName }" readonly /></td>
			<td>企业</td>
			<td><input type="text" name="companyName" value="${companyName}" readonly /></td>
			<td>护照编号</td>
			<td><input type="text" name="passportNum" value="${passportNum }" readonly /></td>
		</tr>
		<tr>
			<td>家庭住址（中国）</td>
			<td><input type="text" name="addressZh" value="${addressZh }" readonly /></td>
			<td>家庭住址（日本）</td>
			<td><input type="text" name="addressJp" value="${addressJp }" readonly /></td>
			<td>毕业证编号</td>
			<td><input type="text" name="diplomaNum" value="${diplomaNum }" readonly /></td>
		</tr>
		<tr>
			<td>实际入校日</td>
			<td><input type="text" name="entryDate" value="${entryDate }" readonly  /></td>
			<td>面试日期</td>
			<td><input type="text" name="interviewDate" value="${interviewDate }" readonly /></td>
			<td>预计出境日</td>
			<td><input type="text" name="exitDate" value="${exitDate }" readonly /></td>
		</tr>
		<tr>
			<td>备注</td>
			<td><input type="text" name="comment" value="${comment }" readonly /></td>
		</tr>
	</table>
</body>

</html>