package cash.controller;

import java.io.IOException;
import java.net.URLEncoder;

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
			return;
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
			response.sendRedirect(request.getContextPath() + "/cashbook");	
		}
		
		// 유효성검사 
		if(request.getParameter("memberId") == null 
			|| request.getParameter("memberPw") == null
			|| request.getParameter("memberPwRe") == null
			|| request.getParameter("memberId").equals("")
			|| request.getParameter("memberPw").equals("")
			|| request.getParameter("memberPwRe").equals("")) {
			String errorMsg = "아이디, 비밀번호를 입력해주세요.";
			request.setAttribute("errorMsg", errorMsg);
			request.getRequestDispatcher("/WEB-INF/view/addMember.jsp").forward(request, response);
			return;
		}
		
		// request.getParameter()
		String memberId = request.getParameter("memberId");
		String memberPw = request.getParameter("memberPw");
		String memberPwRe = request.getParameter("memberPwRe");
		String idck = request.getParameter("idck");
		
		MemberDao memberdao = new MemberDao();
		Member member = new Member(memberId, memberPw, null, null);

		// 비밀번호 검사
		if(!memberPw.equals(memberPwRe)) {
			String errorMsg = "비밀번호를 확인해주세요.";
			request.setAttribute("errorMsg", errorMsg);
			request.getRequestDispatcher("/WEB-INF/view/addMember.jsp").forward(request, response);
			return;
		}
		
		// 회원가입 dao 호출
		MemberDao memberDao = new MemberDao();
		int row = memberDao.insertMember(member);
		if(row == 0) { // 실패시 회원가입으로
			// addMember.jsp view를 이동하는 controller를 리다이렉트
			String errorMsg = "비밀번호를 확인해주세요.";
			request.setAttribute("errorMsg", errorMsg);
			request.getRequestDispatcher("/WEB-INF/view/addMember.jsp").forward(request, response);
		} else if(row == 1) {// 성공시
			// login.jsp view를 이동하는 controller를 리다이렉트
			String msg = "회원가입 성공! 로그인 해주세요.";
			response.sendRedirect(request.getContextPath() + "/login?msg=" + URLEncoder.encode(msg, "UTF-8"));
			return;
		} else if(row == -1) {
			// addMember.jsp view를 이동하는 controller를 리다이렉트
			System.out.println("이미 존재하는 아이디");
			String errorMsg ="이미 존재하는 아이디입니다. 다른 아이디를 사용해주세요.";
			request.setAttribute("errorMsg", errorMsg); 
			request.getRequestDispatcher("/WEB-INF/view/addMember.jsp").forward(request, response);
			return;
		} else {
			System.out.println("add member error");
		}
	}

}
