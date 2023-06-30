package cash.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cash.model.MemberDao;
import cash.vo.Member;

@WebServlet("/removeMember")
public class removeMemberController extends HttpServlet {
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
		request.setAttribute("member", member);
		request.getRequestDispatcher("/WEB-INF/view/removeMember.jsp").forward(request, response);
	}
	
	@Override
	// 탈퇴
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Member loginMember = (Member)session.getAttribute("loginMember");
			System.out.println(loginMember+"<--loginMember");
		
		String memberPw = request.getParameter("memberPw");
			System.out.println(memberPw+"<--memberPw");
		
		MemberDao memberDao = new MemberDao();
		int row = memberDao.removeMember(loginMember.getMemberId(), memberPw);
			System.out.println(row);
		if(row == 0) {// 탈퇴 실패
			System.out.println("탈퇴 실패");
			response.sendRedirect(request.getContextPath() + "/removeMember");
		} else if(row == 1){ // 성공
			System.out.println("탈퇴 성공");
			response.sendRedirect(request.getContextPath() + "/login");
			session.invalidate();
		} else {
			System.out.println("탈퇴 오류");
		}
	}

}
