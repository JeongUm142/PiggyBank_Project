<%@page import="cash.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cashbook</title>
	<!-- css파일 -->
	<link href="${pageContext.request.contextPath}/style.css" type="text/css" rel="stylesheet">
	
	<!-- Latest compiled and minified CSS -->
	<link href="https://cdn.jsdelivr.net/npm/bootswatch@5.3.0/dist/litera/bootstrap.min.css" rel="stylesheet">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<div class="container center login">
	<h1>회원탈퇴 </h1>
	<form method="post" action="${pageContext.request.contextPath}/removeMember">
	   <table>
	      <tr>
	         <td>Id</td>
	         <td><input type="text" name="memberId" id="memberId" value="${id}" readonly="readonly"></td>
	      </tr>
	      <tr>
	         <td>비밀번호 확인</td>
	         <td><input type="password" name="memberPw"></td>
	      </tr>
	   </table>
	   <button type="submit">탈퇴</button>
	</form>
</div>
</body>
</html>