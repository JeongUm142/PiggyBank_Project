package cash.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/cashbook")
public class CashbookController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// session 검사 코드
    	HttpSession session = request.getSession();
    	if(session.getAttribute("loginMember") == null) {
    		response.sendRedirect(request.getContextPath() + "/login");
    		return;
    	}
    	
    	// 이번달 달력에 가계부목록의 모델값을 셋팅
    	request.getRequestDispatcher("/WEB-INF/view/cashbook.jsp").forward(request, response);
	}

}
