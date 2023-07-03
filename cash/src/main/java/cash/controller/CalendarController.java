package cash.controller;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cash.model.CashbookDao;
import cash.vo.Cashbook;

@WebServlet("/calendar")
public class CalendarController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 로그인 유효성
		/*
		 * HttpSession session = request.getSession();
		 * if(session.getAttribute("loginMember") == null) {
		 * response.sendRedirect(request.getContextPath() + "/login"); return; }
		 * 
		 * String memberId = (String)session.getAttribute("loginMember");
		 */
		String memberId = "user2";
		// view에 넘겨줄 달력정보(모델값) - 기본값
		Calendar today = Calendar.getInstance(); //오늘날짜 
		Calendar firstDay = Calendar.getInstance(); // 월의 1일
		firstDay.set(Calendar.DATE, 1);
		
		int targetYear = firstDay.get(Calendar.YEAR);
		int targetMonth = firstDay.get(Calendar.MONTH);
		int todayYear = today.get(Calendar.YEAR);
		int todayMonth = today.get(Calendar.MONTH);
		int todayDate = today.get(Calendar.DATE);
			System.out.println(targetYear + "<-targetYear");
			System.out.println(targetMonth + "<-targetMonth");
			System.out.println(todayYear + "<-todayYear");
			System.out.println(todayMonth + "<-todayMonth");
			System.out.println(todayDate + "<-todayDate");
			
		// 출력하고자 하는 년,월이 매개값으로 넘어왔다면 = > 해당날짜 출력
		if(request.getParameter("targetYear") != null 
				&& request.getParameter("targetMonth") != null) {
			targetYear = Integer.parseInt(request.getParameter("targetYear"));
			targetMonth = Integer.parseInt(request.getParameter("targetMonth"));
			
			firstDay.set(Calendar.YEAR, targetYear);
			// API에서 자동으로 Calendar.MONTH값으로 12가 입력되면 월은 1, 년 +1
			// API에서 자동으로 Calendar.MONTH값으로 -1가 입력되면 월은 12, 년 -1
			firstDay.set(Calendar.MONTH, targetMonth); 
			
			targetYear = firstDay.get(Calendar.YEAR);
			targetMonth = firstDay.get(Calendar.MONTH);
		}
		
		// 달력출력시 시작공백
		// 1일 날짜를 통해서 (일1, 월2, 화3, ...) -1 => dayOfWeek 사용 
		int beginBlank = firstDay.get(Calendar.DAY_OF_WEEK) - 1;
			System.out.println(beginBlank + "<-beginBlank");
			
		// 출력되는 월의 마지막날짜
		int lastDate = firstDay.getActualMaximum(Calendar.DATE);
			System.out.println(lastDate + "<-lastDate");
			
		// 마지막 날짜 출력 후 공백 수 -> 전체 출력 셀의 수가 7로 나누어 떨어짐
		int endBlank = 0;
		
		if((beginBlank + lastDate) % 7 != 0) {
			endBlank = 7- ((beginBlank + lastDate) % 7);
			System.out.println(endBlank + "<-endBlank");
		}
		int totalCell = beginBlank + lastDate + endBlank;
			System.out.println(totalCell + "<-totalCell");
			
		// 모델을 호출(DAO 타켓 월의 수업/지출 데이터
		List<Cashbook> list = new CashbookDao().selectCashbookListByMonth(memberId, targetYear, targetMonth + 1);
		
		// 뷰에 값 넘기기(request속성)
		request.setAttribute("targetYear", targetYear);
		request.setAttribute("targetMonth", targetMonth);
		request.setAttribute("todayYear", todayYear);
		request.setAttribute("todayMonth", todayMonth);
		request.setAttribute("todayDate", todayDate);
		request.setAttribute("lastDate", lastDate);
		request.setAttribute("totalCell", totalCell);
		request.setAttribute("beginBlank", beginBlank);
		request.setAttribute("endBlank", endBlank);
		request.setAttribute("list", list);
		
		//달력을 출력하는 뷰
		request.getRequestDispatcher("/WEB-INF/view/calendar.jsp").forward(request, response);
	}

}