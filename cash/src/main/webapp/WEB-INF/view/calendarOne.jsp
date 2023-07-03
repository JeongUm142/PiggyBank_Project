<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- JSP컴파일시 자바코드로 c:...(제어문법코드) 커스텀태그 사용가능 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- jstl에서 subString 호출용 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!-- formatNumber 활용 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상세보기</title>
	<!-- css파일 -->
	<link href="<%=request.getContextPath() %>/style.css" type="text/css" rel="stylesheet">
	
	<!-- Latest compiled and minified CSS -->
	<link href="https://cdn.jsdelivr.net/npm/bootswatch@5.3.0/dist/litera/bootstrap.min.css" rel="stylesheet">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<div class="container center">	
	<div class="title">상세정보</div>
	<table class="table table-bordered">
		<tr>
			<td>카테고리</td>
			<td>날짜</td>
			<td>금액</td>
			<td>메모</td>
			<td>작성일</td>
			<td>수정일</td>
		</tr>
			<c:forEach var="c" items="${list}">
			<tr>
				<td>${c.category}</td>
				<td>${c.cashbookDate}</td>
				<td>
					<c:if test="${c.category == '수입'}">
						<span>+ <fmt:formatNumber value="${c.price}" pattern="###,###"/></span>
					</c:if>
					<c:if test="${c.category == '지출'}">
						<span style="color:red">- <fmt:formatNumber value="${c.price}" pattern="###,###"/></span>
					</c:if>
				</td>
				<td>${c.memo}</td>
				<td>${fn:substring(c.createdate,0,11)}</td>
				<td>${fn:substring(c.updatedate,0,11)}</td>
			</tr>
			</c:forEach>
	</table>	
	<a href="${pageContext.request.contextPath}/calendar?targetYear=${targetYear}&targetMonth=${targetMonth-1}">이전</a>
</div>
</body>
</html>