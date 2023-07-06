package cash.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.SendResult;
import javax.websocket.Session;

import cash.model.MemberDao;
import cash.vo.Member;

@WebServlet("/addMember")
public class AddMemberController extends HttpServlet {
	
	// addMember.jsp(회원가입)폼으로 갈때
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//세션 유효성 검사(로그인 x)
		HttpSession session = request.getSession();
		if(session.getAttribute("loginMember") != null) {
			response.sendRedirect(request.getContextPath() + "/login");	
		}
			
		// jsp페이지로 포어드(디스패치)
		request.getRequestDispatcher("/WEB-INF/view/addMember.jsp").forward(request, response);
		
	}
	
	// 회원가입 액션창
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//세션 유효성 검사(로그인 x)
		HttpSession session = request.getSession();
		if(session.getAttribute("loginMember") != null) {
			response.sendRedirect(request.getContextPath() + "/login");	
		}
		
		// 유효성검사 
		if(request.getParameter("memberId") == null 
			|| request.getParameter("memberPw") == null) {
			response.sendRedirect(request.getContextPath() + "/addMember");
			return;
		}
		
		// request.getParameter()
		String memberId = request.getParameter("memberId");
		String memberPw = request.getParameter("memberPw");
		Member member = new Member(memberId, memberPw, null, null);
		
		// 회원가입 dao 호출
		MemberDao memberDao = new MemberDao();
		int row = memberDao.insertMember(member);
		if(row == 0) { // 실패시 회원가입으로
			// addMember.jsp view를 이동하는 controller를 리다이렉트
			response.sendRedirect(request.getContextPath() + "/addMember");
		} else if(row == 1) {// 성공시
			// login.jsp view를 이동하는 controller를 리다이렉트
			response.sendRedirect(request.getContextPath() + "/login");	
		} else {
			System.out.println("add member error");
		}
	}

}
