package cash.controller;

import java.io.IOException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cash.model.*;
import cash.vo.*;

@WebServlet("/cashbookTagList")
public class CashbookTagList extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//로그인 세션 
		HttpSession session = request.getSession();
		if(session.getAttribute("loginMember") == null) {
			response.sendRedirect(request.getContextPath()+"/login");
			return;
		}
		Member member = (Member)session.getAttribute("loginMember");
		
		String word = request.getParameter("word");
		int targetYear = Integer.parseInt(request.getParameter("targetYear"));
		int targetMonth = Integer.parseInt(request.getParameter("targetMonth"));
		
		CashbookDao cashbookDao = new CashbookDao();
		List<Cashbook> list = cashbookDao.selectCashbookListByTag(member.getMemberId(), word, targetYear, targetMonth);
		
		HashtagDao hashtagDao = new HashtagDao();
		List<Map<String, Object>> htList = hashtagDao.selectWordList(member.getMemberId());
		List<Map<String, Object>> htListByMonth = hashtagDao.selectWordCountByMonth(member.getMemberId(), targetYear, targetMonth);
		
		request.setAttribute("list", list);
		request.setAttribute("word", word);
		request.setAttribute("targetYear", targetYear);
		request.setAttribute("targetMonth", targetMonth);
		request.setAttribute("htList", htList);
		request.setAttribute("htListByMonth", htListByMonth);
		
		request.getRequestDispatcher("/WEB-INF/view/cashbookTagList.jsp").forward(request, response);
		
	}
}
