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
<title>Cashbook</title>
	<!-- css파일 -->
	<link href="<%=request.getContextPath() %>/style.css" type="text/css" rel="stylesheet">
	
	<!-- Latest compiled and minified CSS -->
	<link href="https://cdn.jsdelivr.net/npm/bootswatch@5.3.0/dist/litera/bootstrap.min.css" rel="stylesheet">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<div class="info">
	<div>
	<a href="${pageContext.request.contextPath}/logout" class="btn btn-success">로그아웃</a>
	<a href="${pageContext.request.contextPath}/memberOne" class="btn btn-success">회원정보</a>
	</div>
	<div>
		<h3>#이달의 해시태그</h3>
		<div class="hastag">
			<c:forEach var="m" items="${htList}">
				<a href="${pageContext.request.contextPath}/cashbookTagList?word=${m.word}">#${m.word}(${m.cnt})</a>
			</c:forEach>
		</div>
	</div>
</div>
	<div class="total">
		<h3>이달 총 수입</h3>
		<div style="color:#4380C8">
			<fmt:formatNumber value="${incomeTotal}" pattern="###,###,###"/>원
		</div>
		<hr>
		<h3>이달 총 지출</h3>
		<div style="color:#D980C8">
			<fmt:formatNumber value="${spendTotal}" pattern="###,###,###"/>원
		</div>
	</div>
<div class="container">
	<div class="center title nonedeco">
		<a href="${pageContext.request.contextPath}/cashbook?targetYear=${targetYear}&targetMonth=${targetMonth - 1}">≪ &nbsp;&nbsp;&nbsp;&nbsp;</a>
		${targetYear}년 ${targetMonth+1}월 캘린더
		<a href="${pageContext.request.contextPath}/cashbook?targetYear=${targetYear}&targetMonth=${targetMonth + 1}">&nbsp;&nbsp;&nbsp;&nbsp; ≫</a>
	</div>
	<!-- 변수값 or 반환값 : EL사용 ＄{표현식} -->
	<!-- 속성값 대신 EL사용 
		ex) request.getAttribute("targetYear") 
		=> ＄{reqeustScope.targetYear} * reqeustScope는 생략가능
		=> 형변환연산이 필요없다(EL이 자동으로 처리) -->
	<!-- 자바코드(제어문) : JSTL 사용 -->
	
	<div class="list">
		<table class="table table-bordered">
			<tr class="title">
				<td>일</td>
				<td>월</td>
				<td>화</td>
				<td>수</td>
				<td>목</td>
				<td>금</td>
				<td>토</td>
			</tr>
			<tr>
				<c:forEach var= "i" begin="0" end="${totalCell - 1}" step="1">
					<!-- i인 0을 앞의 공백만큼 빼고 +1 -->
					<c:set var="date" value="${i-beginBlank+1}"></c:set>
					
					<!-- 7칸 마다 줄바꿈 -->
					<c:if test="${i != 0 && i % 7 == 0}">
	        			</tr><tr>
	   				</c:if>
					
					<!--i-beginBlank+1가 1보다 작거나(0~-i) , i-beginBlank+1가 마지막날보다 큰 경우 공백 -->
					<c:if test="${date < 1 || date > lastDate}">
						<td></td>
					</c:if>
					
					<c:if test="${!(date < 1 || date > lastDate)}">
						<!-- 토,일,오늘 폰트색상 및 배경 변수 생성 -->
						<c:set var="textColor" value=""></c:set>
						<c:set var="backColor" value=""></c:set>
						<c:set var="textWeight" value=""></c:set>
								
						<!-- 오늘 --> <!-- 배경색을 위해 위에 생성 -->
						<c:if test="${date == todayDate && targetMonth == todayMonth && targetYear == todayYear}">
							<c:set var="backColor" value="2px solid #333"></c:set>
							<c:set var="textWeight" value="bold"></c:set>
						</c:if>
						
						<td style="border: ${backColor}">
							<div>
								<!-- 토요일 -->
								<c:if test="${i % 7 == 6}">
									<c:set var="textColor" value="blue"></c:set>
								</c:if>
								
								<!-- 일요일 -->
								<c:if test="${i % 7 == 0}">
									<c:set var="textColor" value="red"></c:set>
								</c:if>
								
								<!-- 각 조건에 맞게 변수의 값을 사용 -->
								<span class="nonedeco">
									<a href="${pageContext.request.contextPath}/cashbookOne?targetYear=${targetYear}&targetMonth=${targetMonth+1}&date=${date}" style="color: ${textColor}; font-weight: ${textWeight};">${date}</a>
								</span>
							</div>
							<div>
								<!-- 자동 형변환 가능 -->
								<c:forEach var="c" items="${list}">
									<c:if test="${date == fn:substring(c.cashbookDate, 8, 10)}">
										<div>
											<c:if test="${c.category == '수입'}">
												<span style="color:#4380C8">+ <fmt:formatNumber value="${c.price}" pattern="###,###,###"/></span>
											</c:if>
											<c:if test="${c.category == '지출'}">
												<span style="color:#D980C8">- <fmt:formatNumber value="${c.price}" pattern="###,###,###"/></span>
											</c:if>
										</div>
									</c:if>
								</c:forEach>
							</div>
						</td>
					</c:if>
				</c:forEach>
			</tr>
		</table>
	</div>
</div>
</body>
</html>