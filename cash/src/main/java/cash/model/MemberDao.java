package cash.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import cash.vo.Member;

public class MemberDao {
	// 회원가입 메서드
	public int insertMember(Member member) {
		int row = 0;
		
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = "INSERT INTO Member(member_id, member_pw, createdate, updatedate) VALUE(?, password(?), now(), now())";
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/Cash", "root", "java1234");
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, member.getMemberId());
			stmt.setString(2, member.getMemberPw());
			row = stmt.executeUpdate();
		} catch(Exception e){
			e.printStackTrace();
		} finally {
			try { // close는 역순
				stmt.close();
				conn.close();
			} catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		return row;
	}
	
	// 로그인 메서드
	public Member selectMemberById(Member paramMember) {
		Member returnMember = null;
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT member_id memberId FROM member WHERE member_id=? AND member_pw = PASSWORD(?)";
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/Cash", "root", "java1234");
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, paramMember.getMemberId());
			stmt.setString(2, paramMember.getMemberPw());
			rs = stmt.executeQuery();
			if(rs.next()) {
				returnMember = new Member();
				returnMember.setMemberId(rs.getString("memberId"));
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch(Exception e2) {
				e2.printStackTrace();
			} 
		}
		return returnMember;
	}
	
	//회원 상세
	public Member selectMemberOne(String memberId) {
		Member returnMember = null;
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT member_id memberId, member_pw memberPw FROM member WHERE member_id=?";
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/Cash", "root", "java1234");
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, memberId);
				System.out.println(stmt);
			rs = stmt.executeQuery();
			if(rs.next()) {
				returnMember = new Member();
				returnMember.setMemberId(rs.getString("memberId"));
				returnMember.setMemberPw(rs.getString("memberPw"));
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try{
				rs.close();
				stmt.close();
				conn.close();
			}catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return returnMember;
	}
	
	// 회원정보 수정
	public int modifyMember(String memberId, String memberUpdatePw) {
		int row = 0;
		
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = "UPDATE member SET member_pw = PASSWORD(?) WHERE member_id=?";
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/Cash", "root", "java1234");
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, memberUpdatePw);
			stmt.setString(2, memberId);
			row = stmt.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		return row;
	}
	
	// 회원 탈퇴
	public int removeMember(String memberId, String memberPw) {
		int row = 0;
		
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = "DELETE FROM member WHERE member_id=? AND member_pw=PASSWORD(?)";
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/Cash", "root", "java1234");
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, memberId);
			stmt.setString(2, memberPw);
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
