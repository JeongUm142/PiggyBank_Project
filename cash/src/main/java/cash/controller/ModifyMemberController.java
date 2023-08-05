package cash.controller;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cash.model.MemberDao;
import cash.vo.Member;

@WebServlet("/modifyPassword")
public class ModifyMemberController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// session 유효성 검사
		HttpSession session = request.getSession();	
		if(session.getAttribute("loginMember") == null) {
    		response.sendRedirect(request.getContextPath() + "/login");
    		return;
    	}
		
		request.getRequestDispatcher("/WEB-INF/view/modifyPassword.jsp").forward(request, response);
	}
	
	// 수정
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// session 유효성 검사
		HttpSession session = request.getSession();	
		if(session.getAttribute("loginMember") == null) {
    		response.sendRedirect(request.getContextPath() + "/login");
    		return;
    	}
		
		// 유효성검사 
		if(request.getParameter("memberPw") == null 
			|| request.getParameter("memberUpdatePw") == null
			|| request.getParameter("memberUpdatePwRe") == null
			|| request.getParameter("memberPw").equals("")
			|| request.getParameter("memberUpdatePw").equals("")
			|| request.getParameter("memberUpdatePwRe").equals("")) {
			System.out.println("비밀번호 값 부족");
			String errorMsg = "모든 값을 입력해주세요.";
			request.setAttribute("errorMsg", errorMsg); // msg 값을 request 객체에 설정
	        request.getRequestDispatcher("/WEB-INF/view/modifyPassword.jsp").forward(request, response);
			return;
		}
				
		Member loginMember = (Member)session.getAttribute("loginMember");
		String memberPw = request.getParameter("memberPw");
		String memberUpdatePw = request.getParameter("memberUpdatePw");
		String memberUpdatePwRe = request.getParameter("memberUpdatePwRe");
		
		String errorMsg = null;
		
		// 비밀번호 재확인 맞는지 검사
		if(!memberUpdatePw.equals(memberUpdatePwRe)) {
			System.out.println("비밀번호 불일치");
			errorMsg = "새로운 비밀번호를 확인해주세요.";
			request.setAttribute("errorMsg", errorMsg); // msg 값을 request 객체에 설정
	        request.getRequestDispatcher("/WEB-INF/view/modifyPassword.jsp").forward(request, response);
			return;
		}
				
		MemberDao memberDao = new MemberDao();
		
		int row = memberDao.modifyPassword(loginMember.getMemberId(), memberPw, memberUpdatePw);
		System.out.println(row + "row");
		if(row == 1) {// 성공
			System.out.println("수정 성공");
			String msg = "비밀번호 변경으로 로그아웃됩니다.";
			session.invalidate();
			response.sendRedirect(request.getContextPath() + "/login?msg=" + URLEncoder.encode(msg, "UTF-8"));
			return;
		} else if(row == -1) { // 이전비밀번호와 동일
			System.out.println("이전 비밀번호와 동일");
			errorMsg = "최근에 사용한 비밀번호입니다. 다른 비밀번호를 입력해주세요.";
			request.setAttribute("errorMsg", errorMsg); // msg 값을 request 객체에 설정
			request.getRequestDispatcher("/WEB-INF/view/modifyPassword.jsp").forward(request, response);
			return;
		} else{ // 실패
			System.out.println("수정 실패");
			errorMsg = "현재 비밀번호를 확인해주세요.";
			request.setAttribute("errorMsg", errorMsg); // msg 값을 request 객체에 설정
			request.getRequestDispatcher("/WEB-INF/view/modifyPassword.jsp").forward(request, response);
			return;
		} 
	}

}
