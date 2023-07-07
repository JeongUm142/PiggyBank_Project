<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>home</title>
	<!-- css파일 -->
	<link href="${pageContext.request.contextPath}/style.css" type="text/css" rel="stylesheet">
	
	<!-- Latest compiled and minified CSS -->
	<link href="https://cdn.jsdelivr.net/npm/bootswatch@5.3.0/dist/litera/bootstrap.min.css" rel="stylesheet">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<div class="container center">
	<div class="login login-join">
		<h1>로그인</h1>
		<div class="form-wrapper">
			<form method="post" action="${pageContext.request.contextPath}/login">
				<table>
					<tr>
						<td>
							<input type="text" name="memberId" value="${loginMember}" placeholder="아이디" class="form-check form-control">
						</td>
					</tr>
					<tr>
						<td>
							<input type="password" name="memberPw" placeholder="비밀번호" class="form-check form-control">
						</td>
					</tr>
					<tr>
						<td>
							<div class="login-save">
								<input type="checkbox" name="idSave" value="Y" class="form-check-input"> 
								아이디 저장
							</div>
						</td>	
					</tr>
					<tr>
						<td>
							<button type="submit" class="btn btn-success">로그인</button>
						</td>	
					</tr>
					<tr>
						<td>
							<div class="nonedeco">
								계정이 없으신가요?
								<a href="${pageContext.request.contextPath}/addMember">회원가입</a>
							</div>
						</td>	
					</tr>
				</table>
				<br>
			</form>
		</div>
	</div>
</div>
</body>
</html>