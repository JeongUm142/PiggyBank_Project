# PiggyBank_Project

# 🖥 개요  
- 2023. 06 .30 ~ (진행중)
- Model 2 방식을 이용해 가계부
- 개인프로젝트
 
# 🛠️ 개발 환경
- OS(운영체제): `MAC`
- Library (라이브러리): `jQuery`, `Bootstrap`, `JSTL`
- Language (언어) : `HTML5` `CSS` `Java(JavaSE-17)`, `JavaScript`
- Database (데이터베이스) : `MariaDB` `Sequel Ace`
- WAS (Web Application Server) : `Apache Tomcat9`
- IDE (통합 개발 환경) : `Eclipse(4.26.0)`
  
# 📌 주요 기능

- 로그인 / 로그아웃
  - 로그인 / 로그아웃 시 **Session** 생성 및 삭제
  - **Cookie**를 사용하여 아이디 저장
- 회원가입
  - ID 중복 검사
  - 비밀번호 확인
- 마이페이지
  - 비밀번호 수정 -> 이전 비밀번호 사용불가 / 새 비밀번호 재확인
  - 회원탈퇴
- 가계부 캘린더
  - Calendar 클래스 사용하여 달력 출력
  - 달별 총 수입/지출 출력
- 가계부 일자별 상세
  - 일자별 수입/지출 표현
  - 상세페이지에서 추가, 읽기, 삭제
- 해시태그
  - 가계부 작성 시 #을 사용하여 해시태그 작성
  - **replace** 및 **split**을 사용하여 공백을 기준으로 #을 삭제 후 DB저장
- 성공/오류 메시지
  - **URLEncoder**와 **Alert**를 사용하여 성공과 오류 메시지 노출
- CSS
  - **부트스트랩**과 **CSS**를 활용하여 직접 디자인 작업 

# 🔎미리보기
|로그인 화면|메인화면|
|--|--|
||

|회원가입|회원탈퇴|
|--|--|
|

|가계부 상세|해시태그 상세|
|--|--|
|

|성공 Alert|실패 Alert|
|--|--|
|



