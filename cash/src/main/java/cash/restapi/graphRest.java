package cash.restapi;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import cash.model.CashbookDao;
import cash.vo.Member;

@WebServlet("/monthGraph")
public class graphRest extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
	
	request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");
		
	CashbookDao cashbookDao = new CashbookDao(); 
	
	String memberId = request.getParameter("memberId");
	int targetYear = Integer.parseInt(request.getParameter("targetYear"));
	int targetMonth = Integer.parseInt(request.getParameter("targetMonth"));
	System.out.println(targetYear);
	System.out.println(targetMonth);
	System.out.println(memberId);
	List<Map<String, Object>> categoryTotals = cashbookDao.sumCashByMonth(memberId, targetYear, targetMonth);
		System.out.println(categoryTotals.size()+ "<-categoryTotals.size");
		
	// json문자열로 변환 response.setContentType("application/json"); 
	PrintWriter out = response.getWriter();
	 
	Gson gson = new Gson(); 
	String jsonData = gson.toJson(categoryTotals); 
		System.out.println(jsonData + "jsonData");
	
	request.setAttribute("targetYear", targetYear);
	request.setAttribute("targetMonth", targetMonth);
	
	out.print(jsonData);
	
	}
}
