<%@page import="cash.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cashbook</title>
</head>
<body>
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
</body>
</html>