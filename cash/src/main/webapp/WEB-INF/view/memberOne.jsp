<%@page import="cash.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>PiggyBank</title>
	<link href="${pageContext.request.contextPath}/img/cashfavicon2.png" rel="icon">

	<!-- css파일 -->
	<link href="${pageContext.request.contextPath}/style.css" type="text/css" rel="stylesheet">
	
	<!-- 부트스트랩 -->
	<link href="https://cdn.jsdelivr.net/npm/bootswatch@5.3.0/dist/litera/bootstrap.min.css" rel="stylesheet">
		
	<!-- jQuery -->
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	
	<!-- SweetAlert2 스타일시트와 스크립트 -->
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@11.4.10/dist/sweetalert2.min.css">
	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.4.10/dist/sweetalert2.min.js"></script>
</head>
<body>
<!-- 상단 -->
<div class="info-top">
	<a href="${pageContext.request.contextPath}/logout" class="btn btn-outline-success">로그아웃</a>
	<a href="${pageContext.request.contextPath}/memberOne" class="btn btn-outline-success">회원정보</a>
	<a href="${pageContext.request.contextPath}/cashbook" class="btn btn-outline-success">캘린더</a>
</div>
<div class="container center myPage">
	<h1>마이페이지</h1>
	<div class="profile">
		<div>
			<img src="${pageContext.request.contextPath}/img/profile.png">
			<div class="title">${id}</div>
			<div class="password nonedeco">
				<div>
					<a href="${pageContext.request.contextPath}/modifyPassword" class="btn btn-success">비밀번호 수정</a>
					<a href="${pageContext.request.contextPath}/removeMember" class="btn btn-success">회원탈퇴</a>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
</html>