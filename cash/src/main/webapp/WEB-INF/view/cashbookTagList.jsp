<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- JSP컴파일시 자바코드로 c:...(제어문법코드) 커스텀태그 사용가능 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- jstl에서 subString 호출용 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!-- formatNumber 활용 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>PiggyBank</title>
	<link href="${pageContext.request.contextPath}/img/cashfavicon2.png" rel="icon">
	<!-- css파일 -->
	<link href="${pageContext.request.contextPath}/style.css" type="text/css" rel="stylesheet">
	
	<!-- Latest compiled and minified CSS -->
	<link href="https://cdn.jsdelivr.net/npm/bootswatch@5.3.0/dist/litera/bootstrap.min.css" rel="stylesheet">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
	
	<!-- jQuery -->
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script>
		function hashlistClick(year, month, day){
			var targetYear = year;
			var targetMonth = month;
			var date = day;
		    window.location.href ="${pageContext.request.contextPath}/cashbookOne?targetYear="+ targetYear + "&targetMonth=" + targetMonth + "&date=" + date;
		}
	</script>
</head>
<body>
<div class="info-top">
	<a href="${pageContext.request.contextPath}/logout" class="btn btn-outline-success">로그아웃</a>
	<a href="${pageContext.request.contextPath}/memberOne" class="btn btn-outline-success">회원정보</a>
	<a href="${pageContext.request.contextPath}/cashbook" class="btn btn-outline-success">캘린더</a>
</div>
<div class="container center">
	<div class="taglist">#${word}</div>
	<div class="taglist-hastag nonedeco">
		<div>
			<c:forEach var="m" items="${htListByMonth}">
				<div>
					<a href="${pageContext.request.contextPath}/cashbookTagList?word=${m.word}&targetYear=${targetYear}&targetMonth=${targetMonth}">#${m.word}(${m.cnt})</a>
				</div>
			</c:forEach>
		</div>
	</div>
	<div class="tag list">
		<table class="table table-bordered hover">
			<thead>
				<tr class="title">
					<td class="w20">일자</td>
					<td class="w20">수입/지출</td>
					<td class="w20">금액</td>
					<td class="w40">메모</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="c" items="${list}">
					<tr class="hover" onclick="hashlistClick('${fn:substring(c.cashbookDate,0,4)}', '${fn:substring(c.cashbookDate,6,7)}', '${fn:substring(c.cashbookDate,8,10)}')">
						<td>${c.cashbookDate}</td>
						<td>
							<c:if test="${c.category == '수입'}">
								<span style="color:#4380C8">+ <fmt:formatNumber value="${c.price}" pattern="###,###,###"/></span>
							</c:if>
							<c:if test="${c.category == '지출'}">
								<span style="color:#D980C8">- <fmt:formatNumber value="${c.price}" pattern="###,###,###"/></span>
							</c:if>
						</td>
						<td><fmt:formatNumber value="${c.price}" pattern="###,###,###"/></td>
						<td>${c.memo}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>
<jsp:include page="/inc/copyright.jsp"></jsp:include>
</body>
</html>