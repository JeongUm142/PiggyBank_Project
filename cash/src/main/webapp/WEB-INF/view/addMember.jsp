<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
   <h1>회원가입</h1>
   <form method="post" action="${pageContext.request.contextPath}/addMember">
      <table>
         <tr>
            <td>Id</td>
            <td><input type="text" name="memberId" id="memberId"></td>
         </tr>
         <tr>
            <td>Pw</td>
            <td><input type="password" name="memberPw" id="memberPw"></td>
         </tr>
      </table>
      <button type="submit">회원가입</button>
   </form>
</body>
</html>