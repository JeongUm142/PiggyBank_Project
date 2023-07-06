<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- 상단 -->
<div class="info-top">
	<a href="${pageContext.request.contextPath}/logout" class="btn btn-outline-success">로그아웃</a>
	<a href="${pageContext.request.contextPath}/memberOne" class="btn btn-outline-success">회원정보</a>
	<a href="${pageContext.request.contextPath}/cashbook?targetYear=${targetYear}&targetMonth=${targetMonth-1}" class="btn btn-outline-success">캘린더</a>
</div>
   <h1>회원가입</h1>
   <form method="post" action="${pageContext.request.contextPath}/addMember">
      <table>
         <tr>
            <td>Id</td>
            <td><input type="text" name="memberId" id="memberId" required="required"></td>
         </tr>
         <tr>
            <td>Pw</td>
            <td><input type="password" name="memberPw" id="memberPw" required="required"></td>
         </tr>
      </table>
      <button type="submit">회원가입</button>
   </form>
</body>
</html>