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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//로그인 세션 
		HttpSession session = request.getSession();
		if(session.getAttribute("loginMember") == null) {
			response.sendRedirect(request.getContextPath()+"/login");
			return;
		}
		Member member = (Member)session.getAttribute("loginMember");
		
		String word = request.getParameter("word");
		
		//페이징 
		int currentPage = 1;
		int rowPerPage = 10;
		int beginRow = 0;
		
		CashbookDao cashbookDao = new CashbookDao();
		List<Cashbook> list = cashbookDao.selectCashbookListByTag(member.getMemberId(), word, beginRow, rowPerPage);
		
		HashtagDao hashtagDao = new HashtagDao();
		List<Map<String, Object>> htList = hashtagDao.selectWordList(member.getMemberId());
		
		request.setAttribute("list", list);
		request.setAttribute("word", word);
		request.setAttribute("htList", htList);
		
		request.getRequestDispatcher("/WEB-INF/view/cashbookTagList.jsp").forward(request, response);
		
	}
}
