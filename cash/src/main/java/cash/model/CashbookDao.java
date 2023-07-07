package cash.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

import org.mariadb.jdbc.Statement;

import cash.vo.Cashbook;
import cash.vo.Hashtag;

public class CashbookDao {
	public List<Cashbook> selectCashbookListByMonth(String memberId, int targetYear, int targetMonth) {
		
		List<Cashbook> list = new ArrayList<Cashbook>();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT cashbook_no cashbookNo, category, cashbook_date cashbookDate, price FROM cashbook WHERE member_id = ? AND YEAR(cashbook_date) = ? AND MONTH(cashbook_date) = ? ORDER BY cashbook_date ASC";
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/Cash", "root", "java1234");
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, memberId);
			stmt.setInt(2, targetYear);
			stmt.setInt(3, targetMonth);
				//System.out.println(stmt + "<-stmt");
			rs = stmt.executeQuery();
			while(rs.next()) {
				Cashbook c = new Cashbook();
				c.setCashbookNo(rs.getInt("cashbookNo"));
				c.setCategory(rs.getString("category"));
				c.setCashbookDate(rs.getString("cashbookDate"));
				c.setPrice(rs.getInt("price"));
				list.add(c);
			}
		} catch(Exception e){
			e.printStackTrace();
		} finally {
			try { // close는 역순
				rs.close();
				stmt.close();
				conn.close();
			} catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		return list;
	}
	// 캐시북에 있는 메모를 해시태그 클릭시 출력
	public List<Cashbook> selectCashbookListByTag(String memberId, String word, int beginRow, int rowPerPage) {
		
		List<Cashbook> list = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT c.cashbook_no cashbookNo, c.category, c.cashbook_date cashbookDate, c.price, c.memo FROM cashbook c  INNER JOIN hashtag h ON c.cashbook_no = h.cashbook_no WHERE c.member_id = ? AND h.word = ? ORDER BY c.cashbook_date DESC LIMIT ?, ?";
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/Cash", "root", "java1234");
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, memberId);
			stmt.setString(2, word);
			stmt.setInt(3, beginRow);
			stmt.setInt(4, rowPerPage);
				System.out.println(stmt + "<-stmt");
			rs = stmt.executeQuery();
			while(rs.next()) {
				Cashbook c = new Cashbook();
				c.setCashbookNo(rs.getInt("cashbookNo"));
				c.setCategory(rs.getString("category"));
				c.setCashbookDate(rs.getString("cashbookDate"));
				c.setPrice(rs.getInt("price"));
				c.setMemo(rs.getString("memo"));
				list.add(c);
			}
		} catch(Exception e){
			e.printStackTrace();
		} finally {
			try { // close는 역순
				rs.close();
				stmt.close();
				conn.close();
			} catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		return list;
	}
	
	//상세보기
	public List<Cashbook> selectCashbookOne(String memberId, int targetYear, int targetMonth, int date) {
		
		List<Cashbook> list = new ArrayList<Cashbook>();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT cashbook_no cashbookNo, category, cashbook_date cashbookDate, price, memo, updatedate, createdate FROM cashbook WHERE member_id = ? AND YEAR(cashbook_date) = ? AND MONTH(cashbook_date) = ? AND DAY(cashbook_date) = ?";
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/Cash", "root", "java1234");
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, memberId);
			stmt.setInt(2, targetYear);
			stmt.setInt(3, targetMonth);
			stmt.setInt(4, date);
				//System.out.println(stmt + "<-stmt");
			rs = stmt.executeQuery();
			while(rs.next()) {
				Cashbook c = new Cashbook();
				c.setCashbookNo(rs.getInt("cashbookNo"));
				c.setCategory(rs.getString("category"));
				c.setCashbookDate(rs.getString("cashbookDate"));
				c.setPrice(rs.getInt("price"));
				c.setMemo(rs.getString("memo"));
				c.setCreatedate(rs.getString("createdate"));
				c.setUpdatedate(rs.getString("updatedate"));
				list.add(c);
			}
		} catch(Exception e){
			e.printStackTrace();
		} finally {
			try { // close는 역순
				rs.close();
				stmt.close();
				conn.close();
			} catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		return list;
	}
	//오늘 수입
	public int sumIncomeCashByToday(String memberId, int targetYear, int targetMonth, int date) {
		int incomeTotalToday = 0;
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null; 
		String sql = "SELECT SUM(price) total FROM cashbook WHERE member_id = ? AND YEAR(cashbook_date) = ? AND MONTH(cashbook_date) = ? AND DAY(cashbook_date) = ?  AND category = '수입'";
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/Cash", "root", "java1234");
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, memberId);
			stmt.setInt(2, targetYear);
			stmt.setInt(3, targetMonth);
			stmt.setInt(4, date);
				//System.out.println(stmt + "<-stmt");
			rs = stmt.executeQuery();
			if(rs.next()) {
				incomeTotalToday = rs.getInt("total");
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
			rs.close();
			stmt.close();
			conn.close();
			} catch(Exception e2){
				e2.printStackTrace();
			}
		}
		return incomeTotalToday;
	}
	//오늘 지출
	public int sumSpendCashByToday(String memberId, int targetYear, int targetMonth, int date) {
		int spendTotalToday = 0;
			
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null; 
		String sql = "SELECT SUM(price) total FROM cashbook WHERE member_id = ? AND YEAR(cashbook_date) = ? AND MONTH(cashbook_date) = ? AND DAY(cashbook_date) = ? AND category = '지출'";
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/Cash", "root", "java1234");
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, memberId);
			stmt.setInt(2, targetYear);
			stmt.setInt(3, targetMonth);
			stmt.setInt(4, date);
				//System.out.println(stmt + "<-stmt");
			rs = stmt.executeQuery();
			if(rs.next()) {
				spendTotalToday = rs.getInt("total");
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
			rs.close();
			stmt.close();
			conn.close();
			} catch(Exception e2){
				e2.printStackTrace();
			}
		}
		return spendTotalToday;
	}
	//이번달 수입
	public int sumIncomeCashByMonth(String memberId, int targetYear, int targetMonth) {
		int incomeTotal = 0;
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null; 
		String sql = "SELECT SUM(price) total FROM cashbook WHERE member_id = ? AND YEAR(cashbook_date) = ? AND MONTH(cashbook_date) = ? AND category = '수입'";
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/Cash", "root", "java1234");
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, memberId);
			stmt.setInt(2, targetYear);
			stmt.setInt(3, targetMonth);
				//System.out.println(stmt + "<-stmt");
			rs = stmt.executeQuery();
			if(rs.next()) {
				incomeTotal = rs.getInt("total");
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
			rs.close();
			stmt.close();
			conn.close();
			} catch(Exception e2){
				e2.printStackTrace();
			}
		}
		return incomeTotal;
	}
	//이번달 지출
	public int sumSpendCashByMonth(String memberId, int targetYear, int targetMonth) {
		int spendTotal = 0;
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null; 
		String sql = "SELECT SUM(price) total FROM cashbook WHERE member_id = ? AND YEAR(cashbook_date) = ? AND MONTH(cashbook_date) = ? AND category = '지출'";
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/Cash", "root", "java1234");
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, memberId);
			stmt.setInt(2, targetYear);
			stmt.setInt(3, targetMonth);
				//System.out.println(stmt + "<-stmt");
			rs = stmt.executeQuery();
			if(rs.next()) {
				spendTotal = rs.getInt("total");
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
			rs.close();
			stmt.close();
			conn.close();
			} catch(Exception e2){
				e2.printStackTrace();
			}
		}
		return spendTotal;
	}
	
	//추가
	public int insertCash(Cashbook cashbook) {
		//반환값 cashbook_no 키값
		int cashbookNo = 0;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null; // 입력후 생성된 키 값 반환
		String sql = "INSERT INTO cashbook(member_id, category, cashbook_date, price, memo, updatedate, createdate) VALUE(?, ?, ?, ?, ?, now(), now())";
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/Cash", "root", "java1234");
			stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, cashbook.getMemberId());
			stmt.setString(2, cashbook.getCategory());
			stmt.setString(3, cashbook.getCashbookDate());
			stmt.setInt(4, cashbook.getPrice());
			stmt.setString(5, cashbook.getMemo());
			int row = stmt.executeUpdate();
			rs = stmt.getGeneratedKeys();
			if(rs.next()) {
				cashbookNo = rs.getInt(1);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
			rs.close();
			stmt.close();
			conn.close();
			} catch(Exception e2){
				e2.printStackTrace();
			}
		}
		return cashbookNo;
	}
	
	//삭제
	public int removeCash(int cashNo) {
		int row = 0;
		
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = "DELETE FROM cashbook WHERE cashbook_no = ?";
	
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/Cash", "root", "java1234");
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, cashNo);
				row = stmt.executeUpdate();
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					stmt.close();
					conn.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
			return row;
		}
}
