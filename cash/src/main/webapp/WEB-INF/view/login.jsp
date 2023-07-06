<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>home</title>
	<!-- css파일 -->
	<link href="<%=request.getContextPath() %>/style.css" type="text/css" rel="stylesheet">
	
	<!-- Latest compiled and minified CSS -->
	<link href="https://cdn.jsdelivr.net/npm/bootswatch@5.3.0/dist/litera/bootstrap.min.css" rel="stylesheet">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<div class="container center login">
	<h1>로그인</h1>
	<div class="form-wrapper">
		<form method="post" action="${pageContext.request.contextPath}/login">
			<table class="loginTable">
				<tr>
					<td>
						<input type="text" name="memberId" value="${loginMember}" placeholder="Enter Id" class="form-check form-control form-control-sm">
					</td>
				</tr>
				<tr>
					<td>
						<input type="password" name="memberPw" placeholder="Enter PassWord" class="form-check form-control form-control-sm">
					</td>
				</tr>
				<tr>
					<td>
						<input type="checkbox" name="idSave" value="Y"> 아이디 저장
					</td>	
				</tr>
			</table>
			<br>
			<div>
				<button type="submit" class="btn btn-success">로그인</button>
				<a href="${pageContext.request.contextPath}/addMember" class="btn btn-success">회원가입</a>
			</div>
		</form>
	</div>
</div>
</body>
</html>