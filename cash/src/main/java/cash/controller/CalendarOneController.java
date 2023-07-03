package cash.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cash.model.CashbookDao;
import cash.vo.Cashbook;

@WebServlet("/calendarOne")
public class CalendarOneController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 로그인 유효성 검사
		/*
		 * HttpSession session = request.getSession();
		 * if(session.getAttribute("loginMember") == null) {
		 * response.sendRedirect(request.getContextPath() + "/login"); return; }
		 * 
		 * String memberId = (String)session.getAttribute("loginMember");
		 */
		String memberId = "user2";
		
		int targetYear = Integer.parseInt(request.getParameter("targetYear"));
		int targetMonth = Integer.parseInt(request.getParameter("targetMonth"));
		int date = Integer.parseInt(request.getParameter("date"));
		
		List<Cashbook> list = new CashbookDao().selectCashbookOne(memberId, targetYear, targetMonth, date);
		System.out.println(list + "<-list");
		
		request.setAttribute("targetYear", targetYear);
		request.setAttribute("targetMonth", targetMonth);
		request.setAttribute("list", list);
		
		request.getRequestDispatcher("/WEB-INF/view/calendarOne.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
