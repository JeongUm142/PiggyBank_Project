package cash.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cash.model.CashbookDao;
import cash.model.HashtagDao;
import cash.vo.Cashbook;
import cash.vo.Member;

@WebServlet("/cashbookOne")
public class CashbookOneController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 로그인 유효성 검사
		HttpSession session = request.getSession();	
		if(session.getAttribute("loginMember") == null) {
    		response.sendRedirect(request.getContextPath() + "/login");
    		return;
    	}
		
		Member member = (Member)session.getAttribute("loginMember");
		
		int targetYear = Integer.parseInt(request.getParameter("targetYear"));
		int targetMonth = Integer.parseInt(request.getParameter("targetMonth"));
		int date = Integer.parseInt(request.getParameter("date"));
		
		CashbookDao cashbookDao = new CashbookDao();
		List<Cashbook> list = cashbookDao.selectCashbookOne(member.getMemberId(), targetYear, targetMonth, date);
		// 오늘의 전체 수입
		int incomeTotalToday = cashbookDao.sumIncomeCashByToday(member.getMemberId(), targetYear, targetMonth, date);
		// 오늘의 전체 지출
		int spendTotalToday = cashbookDao.sumSpendCashByToday(member.getMemberId(), targetYear, targetMonth, date);
			System.out.println(spendTotalToday);
		
		HashtagDao hashtagDao = new HashtagDao();
		List<Map<String, Object>> htList = hashtagDao.selectWordCountByMonth(member.getMemberId(), targetYear, targetMonth);
		
		request.setAttribute("targetYear", targetYear);
		request.setAttribute("targetMonth", targetMonth);
		request.setAttribute("date", date);
		request.setAttribute("list", list);
		request.setAttribute("htList", htList);
		request.setAttribute("incomeTotalToday", incomeTotalToday);
		request.setAttribute("spendTotalToday", spendTotalToday);
		
		request.getRequestDispatcher("/WEB-INF/view/cashbookOne.jsp").forward(request, response);
	}

}
