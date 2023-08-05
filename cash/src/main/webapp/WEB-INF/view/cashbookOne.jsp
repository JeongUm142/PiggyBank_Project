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
	<link href="${pageContext.request.contextPath}/style.css" type="text/css" rel="stylesheet">
	
	<!-- Latest compiled and minified CSS -->
	<link href="https://cdn.jsdelivr.net/npm/bootswatch@5.3.0/dist/litera/bootstrap.min.css" rel="stylesheet">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
	
	<!-- jQuery -->
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	
	<!-- SweetAlert2 스타일시트와 스크립트 -->
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@11.4.10/dist/sweetalert2.min.css">
	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.4.10/dist/sweetalert2.min.js"></script>
	
	<script>
	$(document).ready(function(){
		// URLSearchParams 객체를 사용하여 주소창의 값 가져오기
		const urlParams = new URLSearchParams(window.location.search);

        // msg가 주소창에 있으면 가져와서 출력
		var msg = urlParams.get('msg');
		var errorMsg = urlParams.get('errorMsg');
	
		// msg 값이 비어있지 않으면 SweetAlert2 경고 팝업 표시 -> 성공
		if (msg != '') {
			const Toast = Swal.mixin({
				toast: true,
				position: 'center-center',
				showConfirmButton: false,
				timer: 1000,
				timerProgressBar: true,
				didOpen: (toast) => {
					toast.addEventListener('mouseenter', Swal.stopTimer)
					toast.addEventListener('mouseleave', Swal.resumeTimer)
				}
			})
			
			Toast.fire({
				icon: 'success',
				title: msg
			})
		}
		// 에러 
		if (errorMsg != '') {
			Swal.fire('Error!',errorMsg,'error');
		}
      
	});
	</script>
</head>
<body>
<!-- 상단 -->
<div class="info-top">
	<a href="${pageContext.request.contextPath}/logout" class="btn btn-outline-success">로그아웃</a>
	<a href="${pageContext.request.contextPath}/memberOne" class="btn btn-outline-success">회원정보</a>
	<a href="${pageContext.request.contextPath}/cashbook?targetYear=${targetYear}&targetMonth=${targetMonth-1}" class="btn btn-outline-success">캘린더</a>
</div>
<div class="con">
	<div class="row">
		<!-- 해시태그 -->
		<div class="col-lg-2">
			<div class="info">
				<h3>#이달의 해시태그</h3>
				<div class="hastag">
					<c:forEach var="m" items="${htList}">
						<ul>
							<li>
								<a href="${pageContext.request.contextPath}/cashbookTagList?word=${m.word}&targetYear=${targetYear}&targetMonth=${targetMonth}">#${m.word}(${m.cnt})</a>
							</li>
						</ul>
					</c:forEach>
				</div>
			</div>
		</div>
		
		<div class="col-lg-8">	
			<!-- 추가 -->
			<div class="addtable">
				<div class="title">${targetYear}년 ${targetMonth}월 ${date}일의 소비 내역을 입력해주세요!</div>
				<form action="${pageContext.request.contextPath}/addCash" method="post" id="yourForm">
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
										<option>수입</option>
										<option>지출</option>
								</select>
							</td>
							<td><input type="number" name="price" class="form-check form-control" min="1"></td>
							<td><input type="text" name="memo" placeholder="해시태그(#) 입력 가능" class="form-check form-control"></td>
						</tr>
					</table>
					<button type="submit" class="btn btn-success" id="toastStart">추가하기</button>
				</form>
			</div>
			<!-- 리스트 -->
			<div class="cashOne list">
				<form action="${pageContext.request.contextPath}/removeCash" method="post">
					<input type="hidden" name="targetYear" value="${targetYear}">
					<input type="hidden" name="targetMonth" value="${targetMonth}">
					<input type="hidden" name="date" value="${date}">
					<table class="table table-bordered">
						<tr class="title">
							<td>카테고리</td>
							<td class="w20">날짜</td>
							<td class="w20">금액</td>
							<td class="w40">메모</td>
							<td>작성일</td>
							<td>수정일</td>
							<td>&nbsp;</td>
						</tr>
							<c:forEach var="c" items="${list}">
							
							<tr class="center">
								<td>${c.category}</td>
								<td>${c.cashbookDate}</td>
								<td>
									<c:if test="${c.category == '수입'}">
										<span style="color:#4380C8">+ <fmt:formatNumber value="${c.price}" pattern="###,###,###"/></span>
									</c:if>
									<c:if test="${c.category == '지출'}">
										<span style="color:#D980C8">- <fmt:formatNumber value="${c.price}" pattern="###,###,###"/></span>
									</c:if>
								</td>	
								<td>${c.memo}</td>
								<td>${fn:substring(c.createdate,0,11)}</td>
								<td>${fn:substring(c.updatedate,0,11)}</td>
								<td>
									<!-- list내 여러 cashbookNo중에 내가 선택한 값이 넘어가도록 설정 -->
									<button type="submit" name="cashNo" value="${c.cashbookNo}" class="btn btn-success">삭제</button>
								</td>
							</tr>
							</c:forEach>
					</table>	
				</form>
			</div>
			<!-- 페이징  -->
			<div>
			</div>
		</div>
		<!-- 수입/지출 -->
		<div class="col-lg-2">
			<div class="total">
				<h3>오늘의 총 수입</h3>
				<div style="color:#4380C8"><fmt:formatNumber value="${incomeTotalToday}" pattern="###,###,###"/>원</div>
				<hr>
				<h3>오늘의 총 지출</h3>
				<div style="color:#D980C8"><fmt:formatNumber value="${spendTotalToday}" pattern="###,###,###"/>원</div>
			</div>
		</div>
		
	</div>
</div>

</body>
</html>