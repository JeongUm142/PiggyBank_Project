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

@WebServlet("/removeMember")
public class RemoveMemberController extends HttpServlet {
	@Override
	// 비밀번호 입력 폼
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("loginMember") == null) {
    		response.sendRedirect(request.getContextPath() + "/login");
    		return;
    	}
		
		Member loginMember = (Member)session.getAttribute("loginMember");
		MemberDao memberDao = new MemberDao();
		Member member = memberDao.selectMemberOne(loginMember.getMemberId());
		String id = member.getMemberId();
		request.setAttribute("id", id);
		
		request.getRequestDispatcher("/WEB-INF/view/removeMember.jsp").forward(request, response);
	}
	
	@Override
	// 탈퇴
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("loginMember") == null) {
    		response.sendRedirect(request.getContextPath() + "/login");
    		return;
    	}
		
		// 유효성검사를 위해 먼저 선언
		Member loginMember = (Member)session.getAttribute("loginMember");
		System.out.println(loginMember+"<--loginMember");
	
		// 유효성검사 
		if(request.getParameter("memberPw") == null 
			|| request.getParameter("memberPw").equals("")) {
			System.out.println("비밀번호 값 부족");
			String errorMsg = "모든 값을 입력해주세요.";
			request.setAttribute("id", loginMember.getMemberId());
			request.setAttribute("errorMsg", errorMsg); // msg 값을 request 객체에 설정
	        request.getRequestDispatcher("/WEB-INF/view/removeMember.jsp").forward(request, response);
			return;
		}
		
		String memberPw = request.getParameter("memberPw");
			System.out.println(memberPw+"<--memberPw");
		
		MemberDao memberDao = new MemberDao();
		int row = memberDao.removeMember(loginMember.getMemberId(), memberPw);
			System.out.println(row);
			
		if(row == 0) {// 탈퇴 실패
			System.out.println("탈퇴 실패");
			String errorMsg = "비밀번호를 확인해주세요.";
			request.setAttribute("errorMsg", errorMsg);
			request.setAttribute("id", loginMember.getMemberId());
			request.getRequestDispatcher("/WEB-INF/view/removeMember.jsp").forward(request, response);
		} else if(row == 1){ // 성공
			System.out.println("탈퇴 성공");
			String msg = "저금통을 이용해주셔서 감사합니다.";
			session.invalidate();
			response.sendRedirect(request.getContextPath() + "/login?msg=" + URLEncoder.encode(msg, "UTF-8"));
			return;
		} else {
			System.out.println("탈퇴 오류");
		}
	}

}
