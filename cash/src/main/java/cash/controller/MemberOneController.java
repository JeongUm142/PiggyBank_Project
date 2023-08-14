package cash.controller;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cash.model.MemberDao;
import cash.vo.Member;

@WebServlet("/memberOne")
public class MemberOneController extends HttpServlet {
	@Override //부모가 가진 정보를 재정의
	// doget은 /memberOne를 주소창에서 검색해서 들어갈 수 있기 위해서 사용
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// session 유효성 검사
		HttpSession session = request.getSession();	
		if(session.getAttribute("loginMember") == null) {
    		response.sendRedirect(request.getContextPath() + "/login");
    		return;
    	}
		
		// view에 넘겨줄 달력정보(모델값) - 기본값
		Calendar firstDay = Calendar.getInstance(); // 월의 1일
		firstDay.set(Calendar.DATE, 1);
		
		int targetYear = firstDay.get(Calendar.YEAR);
		int targetMonth = firstDay.get(Calendar.MONTH);
			System.out.println(targetYear + "<-targetYear");
			System.out.println(targetMonth + "<-targetMonth");
			
		// 출력하고자 하는 년,월이 매개값으로 넘어왔다면 = > 해당날짜 출력
		if(request.getParameter("targetYear") != null 
				&& request.getParameter("targetMonth") != null) {
			targetYear = Integer.parseInt(request.getParameter("targetYear"));
			targetMonth = Integer.parseInt(request.getParameter("targetMonth"));
				System.out.println(targetYear + "년");
				System.out.println(targetMonth + "년");
			
			firstDay.set(Calendar.YEAR, targetYear);
			// API에서 자동으로 Calendar.MONTH값으로 12가 입력되면 월은 1, 년 +1
			// API에서 자동으로 Calendar.MONTH값으로 -1가 입력되면 월은 12, 년 -1
			firstDay.set(Calendar.MONTH, targetMonth); 
			
			targetYear = firstDay.get(Calendar.YEAR);
			targetMonth = firstDay.get(Calendar.MONTH);
		}
		
		Member loginMember = (Member)session.getAttribute("loginMember");
			//System.out.println(loginMember);
		
		// 모델 값 구하기
		MemberDao memberDao = new MemberDao();
		Member member = memberDao.selectMemberOne(loginMember.getMemberId());
		String id = member.getMemberId();
		
		// member 출력하는(포워딩대상) memberOne.jsp에도 공유되어야 한다
		// request가 공유되니까 request와 함께 공유
		request.setAttribute("id", id);
		request.setAttribute("targetYear", targetYear);
		request.setAttribute("targetMonth", targetMonth);
		
		// memberOne.jsp 포워딩
		request.getRequestDispatcher("/WEB-INF/view/memberOne.jsp").forward(request, response);
	}

}
