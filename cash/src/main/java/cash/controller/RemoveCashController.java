package cash.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cash.model.CashbookDao;
import cash.model.MemberDao;
import cash.vo.Cashbook;
import cash.vo.Member;

@WebServlet("/removeCash")
public class RemoveCashController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("loginMember") == null) {
    		response.sendRedirect(request.getContextPath() + "/login");
    		return;
    	}
		Member member = (Member)session.getAttribute("loginMember");
			
		request.getRequestDispatcher("/WEB-INF/view/cashbookOne.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("loginMember") == null) {
    		response.sendRedirect(request.getContextPath() + "/login");
    		return;
    	}
		
		Member member = (Member)session.getAttribute("loginMember");
		int targetYear = Integer.parseInt(request.getParameter("targetYear"));
		int targetMonth = Integer.parseInt(request.getParameter("targetMonth"));
		int date = Integer.parseInt(request.getParameter("date"));
		int cashNo = Integer.parseInt(request.getParameter("cashNo"));		
		
		CashbookDao cashbookDao = new CashbookDao();
		int row = cashbookDao.removeCash(cashNo);
			System.out.println(row + "<-내역 삭제");
		if(row == 1) {// 성공
			System.out.println("삭제 성공");
			response.sendRedirect(request.getContextPath() + "/cashbookOne?targetYear="+ targetYear + "&targetMonth=" + targetMonth + "&date=" + date);
			return;
		} else{ // 실패
			System.out.println("삭제 실패");
			response.sendRedirect(request.getContextPath() + "/cashbookOne?targetYear="+ targetYear + "&targetMonth=" + targetMonth + "&date=" + date);
			return;
		} 
	}
}
