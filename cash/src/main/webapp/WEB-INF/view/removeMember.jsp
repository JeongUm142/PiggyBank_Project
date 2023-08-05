<%@page import="cash.vo.Member"%>
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
	
	<!-- SweetAlert2 스타일시트와 스크립트 -->
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@11.4.10/dist/sweetalert2.min.css">
	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.4.10/dist/sweetalert2.min.js"></script>
	
	<script>
	$(document).ready(function(){
		// URLSearchParams 객체를 사용하여 주소창의 값 가져오기
		const urlParams = new URLSearchParams(window.location.search);

        // msg가 주소창에 있으면 가져와서 출력
		var msg = urlParams.get('msg');
		var errorMsg = '${errorMsg}';
	  
	// msg 값이 비어있지 않으면 SweetAlert2 경고 팝업 표시 -> 성공
		if (msg != null) {
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
<div class="container center">
	<div class="login login-join">
		<h1>회원탈퇴</h1>
		<div class="form-wrapper">
			<form method="post" action="${pageContext.request.contextPath}/removeMember">
				<table>
					<tr>
					   <td><input type="text" name="memberId" value="${id}" readonly="readonly" class="form-check form-control"></td>
					</tr>
				    <tr>
				       <td><input type="password" name="memberPw" placeholder="비밀번호를 입력해주세요" class="form-check form-control"></td>
				    </tr>
				    <tr>
						<td>
							<button type="submit" class="btn btn-success">탈퇴</button>
						</td>	
					</tr>
					<tr>
						<td>
							<a href="${pageContext.request.contextPath}/memberOne" class="btn btn-success aBtn">돌아가기</a>
						</td>	
					</tr>
				</table>
			  
			</form>
		</div>
	</div>
</div>
</body>
</html>