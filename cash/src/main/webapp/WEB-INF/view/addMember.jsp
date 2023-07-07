<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cash</title>
	<!-- css파일 -->
	<link href="${pageContext.request.contextPath}/style.css" type="text/css" rel="stylesheet">
	
	<!-- Latest compiled and minified CSS -->
	<link href="https://cdn.jsdelivr.net/npm/bootswatch@5.3.0/dist/litera/bootstrap.min.css" rel="stylesheet">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<div class="container center">
	<div class="join login-join">
		<h1>회원가입</h1>
		<div class="form-wrapper">
			<form method="post" action="${pageContext.request.contextPath}/addMember">
				<table>
					<tr>
						<td>
							<input type="text" name="memberId" placeholder="아이디" required="required" class="form-check form-control">
						</td>
					</tr>
					<tr>
						<td>
							<input type="password" name="memberPw" placeholder="비밀번호" required="required" class="form-check form-control">
						</td>
					</tr>
					<tr>
						<td>
							<input type="password" name="memberPwRe" placeholder="비밀번호 확인" required="required" class="form-check form-control">
							<button type="submit" class="btn btn-success">회원가입</button>
						</td>
					</tr>
					<tr>
						<td>
							<div class="nonedeco">
								이미 계정이 있으신가요?	
							  	<a href="${pageContext.request.contextPath}/login">로그인</a>
							</div>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</div>
</body>
</html>