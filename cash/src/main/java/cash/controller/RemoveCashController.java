package cash.controller;

import java.io.IOException;
import java.net.URLEncoder;

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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("loginMember") == null) {
    		response.sendRedirect(request.getContextPath() + "/login");
    		return;
    	}
		
		Member loginMember = (Member)session.getAttribute("loginMember");
		int targetYear = Integer.parseInt(request.getParameter("targetYear"));
		int targetMonth = Integer.parseInt(request.getParameter("targetMonth"));
		int date = Integer.parseInt(request.getParameter("date"));
		int cashbookNo = Integer.parseInt(request.getParameter("cashNo"));		
		String msg = null;
		String errorMsg = null;
		
		CashbookDao cashbookDao = new CashbookDao();
		
		int row = cashbookDao.removeCash(cashbookNo, loginMember.getMemberId());
			System.out.println(row + "<-내역 삭제");
			
		if(row == 1) {// 성공
			System.out.println("삭제 성공");
			msg = "선택한 수입/지출이 삭제되었습니다";
			response.sendRedirect(request.getContextPath() + "/cashbookOne?targetYear="+ targetYear + "&targetMonth=" + targetMonth + "&date=" + date + "&msg=" + URLEncoder.encode(msg, "UTF-8"));
			return;
		} else{ // 실패
			System.out.println("삭제 실패");
			errorMsg = "다시 한 번 시도해주세요";
			response.sendRedirect(request.getContextPath() + "/cashbookOne?targetYear="+ targetYear + "&targetMonth=" + targetMonth + "&date=" + date + "&errorMsg=" + URLEncoder.encode(errorMsg, "UTF-8"));
			return;
		} 
	}
}
