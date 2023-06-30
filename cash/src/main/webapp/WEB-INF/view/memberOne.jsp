<%@page import="cash.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	//setAttribute("member", member);을 가져오기 위해 request
	Member member = (Member)request.getAttribute("member"); 
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>memberOne</title>
</head>
<body>
	<h1>회원 정보</h1>
	<div>아이디 : <%=member.getMemberId()%></div>
	<div>비밀번호 : **** </div>
	<a href="${pageContext.request.contextPath}/modifyMember">회원정보수정</a>
	<a href="${pageContext.request.contextPath}/removeMember">회원탈퇴</a>
	
</body>
</html>