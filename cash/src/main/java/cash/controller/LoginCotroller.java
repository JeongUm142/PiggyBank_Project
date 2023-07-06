package cash.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cash.model.MemberDao;
import cash.vo.Member;

@WebServlet("/login")
public class LoginCotroller extends HttpServlet {
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// session 검사 코드
    	HttpSession session = request.getSession();
    	if(session.getAttribute("loginMember") != null) {
    		response.sendRedirect(request.getContextPath() + "/cashbook");
    		return;
    	}
		// 쿠키에 저장된 로그인 성공 id가 있다면 request속성에 저장
		Cookie[] cookies = request.getCookies();
		if(cookies != null) {
			for(Cookie c: cookies) {
				if(c.getName().equals("loginMember")) {
					request.setAttribute("loginMember", c.getValue());
						System.out.println();
				}
			}
		}
    	request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
	}
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String memberId = request.getParameter("memberId");
		String memberPw = request.getParameter("memberPw");
		
		//요청값을 넣어줌
		Member member = new Member(memberId, memberPw, null, null);
		
		MemberDao memberdao = new MemberDao();
		Member loginMember = memberdao.selectMemberById(member);
		
		//null로 로그인실패
		if(loginMember == null) {
			System.out.println("로그인 실패");
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}
		
		//로그인 성공
		HttpSession session = request.getSession();
		session.setAttribute("loginMember", loginMember);
		String idSave = request.getParameter("idSave");
			System.out.println(idSave);
		if(idSave != null){
			Cookie loginIdCookie = new Cookie("loginMember", memberId); // 맵의 한 종류로 프로포티즈의 자식
			loginIdCookie.setMaxAge(60); // 초단위로 계산값을 적어야함/ 결과 XX
			loginIdCookie.setPath("/");
			response.addCookie(loginIdCookie);
		}
		response.sendRedirect(request.getContextPath() + "/cashbook");
		}

}
