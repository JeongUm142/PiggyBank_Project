<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>PiggyBank</title>
	<link href="${pageContext.request.contextPath}/img/cashfavicon2.png" rel="icon">

	<!-- css파일 -->
	<link href="${pageContext.request.contextPath}/style.css" type="text/css" rel="stylesheet">
	
	<!-- 부트스트랩 -->
	<link href="https://cdn.jsdelivr.net/npm/bootswatch@5.3.0/dist/litera/bootstrap.min.css" rel="stylesheet">
		
	<!-- jQuery -->
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<!-- 차트 -->
	<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
	<!-- SweetAlert2 스타일시트와 스크립트 -->
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@11.4.10/dist/sweetalert2.min.css">
	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.4.10/dist/sweetalert2.min.js"></script>
	
	<script>
		$(document).ready(function() {
   
			const targetYear = ${targetYear};
			const targetMonth = ${targetMonth+1};
			const memberId = "${id}";
			
			//동기호출로 x와 y 값을 셋팅한다
			const x = [];
			const y = [];
		
			$.ajax({
				async: false,
				url: '${pageContext.request.contextPath}/monthGraph',
				type: 'get',
				datatype: "json",
				contentType: "application/x-www-form-urlencoded; charset=UTF-8",
				data: {
				    targetYear: targetYear,
				    targetMonth: targetMonth,
				    memberId: memberId
				},
				success: function(model) {
				console.log(model);
				const parsedData = JSON.parse(model);
				console.log(parsedData);
				
				if (parsedData.length === 0) {
			        Swal.fire('Oops', targetMonth+'월에 수입/지출이 없습니다', 'warning');
			        return;
			    }

				for (const item of parsedData) {
					// chart모델 생성
					x.push(item.category);
					y.push(item.price);
					console.log(item.category);
				}
			
				console.log("ajax성공");
				
				new Chart("target", {
					type: "doughnut",
					data: {
						labels: x,
						datasets: [{
							data: y,
							backgroundColor: [
								"rgba(67, 128, 200, 0.8)", // 수입 카테고리 색상
								"rgba(217, 128, 200, 0.7)" // 지출 카테고리 색상
					         ],
						}]
					}
				});
			},
			error: function() {
				console.log("아작스실패");
			} 
		});
	});
	</script>

</head>
<body>
<!-- 상단 -->
<div class="info-top">
	<a href="${pageContext.request.contextPath}/logout" class="btn btn-outline-success">로그아웃</a>
	<a href="${pageContext.request.contextPath}/memberOne" class="btn btn-outline-success">회원정보</a>
	<a href="${pageContext.request.contextPath}/cashbook" class="btn btn-outline-success">캘린더</a>
</div>
	
<div class="container mypage">
	<div class="row">

		<div class="col-lg-6">
			<div class="profile center">
				<img src="${pageContext.request.contextPath}/img/pin2.png">
				<h1>내정보</h1>
				<img src="${pageContext.request.contextPath}/img/profile.png">
				<div class="title">${id}</div>
				<div class="password nonedeco">
					<div>
						<a href="${pageContext.request.contextPath}/modifyPassword" class="btn btn-success">비밀번호 수정</a>
						<a href="${pageContext.request.contextPath}/removeMember" class="btn btn-success">회원탈퇴</a>
					</div>
				</div>
			</div>
		</div>
		
		<div class="col-lg-6">
			<div class="chart center">
				<img src="${pageContext.request.contextPath}/img/pin2.png">
				<h2>${targetYear}년 ${targetMonth+1}월 수입/지출</h2>
					<div class="arrow nonedeco">
						<a href="${pageContext.request.contextPath}/memberOne?targetYear=${targetYear}&targetMonth=${targetMonth - 1}" class="arrow-left">
							<img src="${pageContext.request.contextPath}/img/arrow1.png" alt="이전달">
						</a>
						<a href="${pageContext.request.contextPath}/memberOne?targetYear=${targetYear}&targetMonth=${targetMonth + 1}" class="arrow-right">
							<img src="${pageContext.request.contextPath}/img/arrow1.png" alt="이전달">
						</a>
					</div>
				<canvas id="target" width="300" height="300" style="max-height: 350px"></canvas>
			</div>
		</div>
	</div>
</div>
<jsp:include page="/inc/copyright.jsp"></jsp:include>
</body>
</html>