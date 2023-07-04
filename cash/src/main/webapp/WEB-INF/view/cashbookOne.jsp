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
<title>상세보기</title>
	<!-- css파일 -->
	<link href="<%=request.getContextPath() %>/style.css" type="text/css" rel="stylesheet">
	
	<!-- Latest compiled and minified CSS -->
	<link href="https://cdn.jsdelivr.net/npm/bootswatch@5.3.0/dist/litera/bootstrap.min.css" rel="stylesheet">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<div class="container center">	
	
	<!-- 추가 -->
	<div class="addtable">
		<div class="title">${targetYear}년 ${targetMonth}월 ${date}일의 소비 내역을 입력해주세요!</div>
		<form action="${pageContext.request.contextPath}/addCash" method="post">
			<input type="hidden" name="targetYear" value="${targetYear}">
			<input type="hidden" name="targetMonth" value="${targetMonth}">
			<input type="hidden" name="date" value="${date}">
			<table class="table">
				<tr class="title">
					<td>수입 / 지출</td>
					<td>가격</td>
					<td>메모</td>
				</tr>
				<tr>
					<td>
						<select name="category" class="form-check form-control">
								<option disabled selected>선택해주세요</option>
								<option>수입</option>
								<option>지출</option>
						</select>
					</td>
					<td><input type="number" name="price" class="form-check form-control"></td>
					<td><input type="text" name="memo" placeholder="해시태그(#) 입력 가능" class="form-check form-control"></td>
				</tr>
			</table>
			<button type="submit" class="btn btn-success">추가하기</button>
		</form>
	</div>
	<div class="list">
		<table class="table table-bordered">
			<tr class="title">
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
							<span style="color:blue">+ <fmt:formatNumber value="${c.price}" pattern="###,###"/></span>
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
	</div>
	<a href="${pageContext.request.contextPath}/cashbook?targetYear=${targetYear}&targetMonth=${targetMonth-1}" class="btn btn-success">이전</a>
</div>
</body>
</html>