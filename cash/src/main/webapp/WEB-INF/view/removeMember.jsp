<%@page import="cash.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cashbook</title>
	<!-- Latest compiled and minified CSS -->
	<link href="https://cdn.jsdelivr.net/npm/bootswatch@5.3.0/dist/litera/bootstrap.min.css" rel="stylesheet">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
	
	<!-- css파일 -->
	<link href="${pageContext.request.contextPath}/style.css" type="text/css" rel="stylesheet">
</head>
<body>
<div class="container center">
	<div class="login login-join">
		<h1>회원탈퇴</h1>
		<div class="form-wrapper">
			<form method="post" action="${pageContext.request.contextPath}/removeMember">
				<table>
					<tr>
					   <td><input type="text" name="memberId" value="${id}" readonly="readonly" class="form-check form-control"></td>
					</tr>
				    <tr>
				       <td><input type="password" name="memberPw" placeholder="비밀번호를 입력해주세요" class="form-check form-control"></td>
				    </tr>
				    <tr>
						<td>
							<button type="submit" class="btn btn-success">탈퇴</button>
						</td>	
					</tr>
					<tr>
						<td>
							<a href="${pageContext.request.contextPath}/memberOne" class="btn btn-success aBtn">돌아가기</a>
						</td>	
					</tr>
				</table>
			  
			</form>
		</div>
	</div>
</div>
</body>
</html>