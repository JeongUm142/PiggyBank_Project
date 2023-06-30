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
<title>Insert title here</title>
</head>
<body>
	<h1>회원탈퇴 </h1>
	<form method="post" action="${pageContext.request.contextPath}/removeMember">
	   <table>
	      <tr>
	         <td>Id</td>
	         <td><input type="text" name="memberId" id="memberId" value="<%=member.getMemberId()%>" readonly="readonly"></td>
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