package cash.controller;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cash.model.*;
import cash.vo.*;

@WebServlet("/addCash")
public class AddCashController extends HttpServlet {
	@Override
	// 입력폼으로
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 로그인 세션
		HttpSession session = request.getSession();	
		if(session.getAttribute("loginMember") == null) {
    		response.sendRedirect(request.getContextPath() + "/login");
    		return;
    	}
		
		// 나머지 데이터는 입력폼에서 사용자가 입력
		request.getRequestDispatcher("/WEB-INF/view/cashbookOne.jsp").forward(request, response);
	}

	@Override
	// 입력액션으로
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		HttpSession session = request.getSession();
		if(session.getAttribute("loginMember") == null) {
			response.sendRedirect(request.getContextPath()+"/login");
			return;
		}
		
		Member member = (Member)session.getAttribute("loginMember");
		
		// request 매개값 변수 날짜
		int targetYear = Integer.parseInt(request.getParameter("targetYear"));
		int targetMonth = Integer.parseInt(request.getParameter("targetMonth"));
		int date = Integer.parseInt(request.getParameter("date"));
		String cashbookdate = targetYear + "-" + targetMonth + "-" + date ;
			System.out.println(targetYear + "<-targetYear");
			System.out.println(targetMonth + "<-targetMonth");
			System.out.println(date + "<-date");
			System.out.println(cashbookdate + "<-cashbookdate");

		
		// 유효성검사 
		if(request.getParameter("price") == null 
			|| request.getParameter("memo") == null) {
			response.sendRedirect(request.getContextPath() + "/cashbookOne?targetYear=" + targetYear + "&targetMonth=" + targetMonth + "&date=" + date);
			return;
		}
		
		// 변수 입력값
		String category = request.getParameter("category");
		int price = Integer.parseInt(request.getParameter("price"));
		String memo = request.getParameter("memo");
			System.out.println(category + "<-category");
			System.out.println(memo + "<-memo");
			System.out.println(price + "<-price");
			
		//hashtag와 메모를 구분하기 위한 if
		String memo2 = memo.replace("#", " #"); // 메모에 있는 #를 공백#로 변경
		memo = "";//중복 방지를 위해 공백으로 시작
		for(String w : memo2.split(" ")) { // 공백을 기준으로 나누기
			if(w.startsWith("#")) { // 공백을 기준으로 나뉜 단어가 #으로 시작할 경우
				String word = w.replace("#", "");
				if(word.length() > 0) {
					memo += "#" + word + " "; 
				}
			} else {
				memo += w + " "; // 해시태그가 아닐경우
			}
		}
		
		Cashbook cashbook = new Cashbook();
		cashbook.setMemberId(member.getMemberId());
		cashbook.setCategory(category);
		cashbook.setCashbookDate(cashbookdate);
		cashbook.setMemo(memo);
		cashbook.setPrice(price);
		CashbookDao cashbookdao = new CashbookDao();
		int cashbookNo = cashbookdao.insertCash(cashbook); // 키값반환
		if(cashbookNo == 0 ) {
			System.out.println("입력 실패");
			response.sendRedirect(request.getContextPath() + "/cashbookOne?targetYear=" + targetYear + "&targetMonth=" + targetMonth + "&date=" + date);
			return;
		} 
		
        Set<String> set = new HashSet<String>(); // 중복된 해시태그방지를 위해 set자료구조를 사용

        // 입력성공시 -> 해시태그가 있다면 -> 해시태그 추출 -> 해시태그 입력(반복)
		// 해시태그 추출 알고리즘  // ##구디, #구디#자바  <-문제
		// 해시태그 여부 확인 및 해시태그가 여러개이면 반복해서 입력
		HashtagDao hashtagDao = new HashtagDao();
		for(String w : memo.split(" ")) { 
			if(w.startsWith("#")) {
				String word = w.replace("#", "");// 해시태그에서 #을 제외한 값을 word에 저장을 위한 변수
				if(word.length() > 0) {// word가 있다면 word에 저장
					set.add(word); // set은 중복된 값은 add되지 않는다
				}
			}		
		}
		
		for(String s : set) {
    		Hashtag hashtag = new Hashtag();
			hashtag.setCashbookNo(cashbookNo);
			hashtag.setWord(s);
			hashtagDao.insertHashtag(hashtag);
    }
		
		//redirect -> cashbookCon -> forward -> cashbook.jsp
		response.sendRedirect(request.getContextPath() + "/cashbookOne?targetYear=" + targetYear + "&targetMonth=" + targetMonth + "&date=" + date);
	}

}
