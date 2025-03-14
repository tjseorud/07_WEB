package dex.sdk.member.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dex.sdk.member.model.dto.UserDTO;

public class UserDAO {
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver"); // FullClassName /패키지 경로부터 클래스까지
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	final String URL = "jdbc:oracle:thin:@112.221.156.34:12345:XE";
	final String USERNAME = "KH14_SDK";
	final String PASSWORD = "KH1234";		
	
	public UserDTO login(UserDTO userDTO) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = """
				SELECT USER_ID, USER_PW, USER_NAME, ENROLL_DATE
				FROM TB_USER
				WHERE USER_ID = ? AND USER_PW = ?
				""";
		UserDTO loginUser = null;
		try {
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userDTO.getUserId());
			pstmt.setString(2, userDTO.getUserPw());
			rset = pstmt.executeQuery();

			if (rset.next()) {
				loginUser = new UserDTO(
						rset.getString("USER_ID"),
						rset.getString("USER_PW"),
						rset.getString("USER_NAME"),
						rset.getDate("ENROLL_DATE"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rset != null) { rset.close(); }
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (pstmt != null) { pstmt.close(); }
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if(conn != null) { conn.close(); }
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return loginUser;
	}

	public int checkId(String userId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql ="""
					SELECT COUNT(*) 
					FROM TB_USER
					WHERE USER_ID = ?
					""";
		int result = 0;
		try {
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			rset = pstmt.executeQuery();
			rset.next();
			result = rset.getInt("COUNT(*)");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rset != null) rset.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if(pstmt != null) pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if(conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public void signUp(UserDTO userDTO) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql ="""
				INSERT INTO TB_USER
				VALUES(SEQ_USER_NO.NEXTVAL, ?, ?, ?, SYSDATE)
				""";
		try {
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userDTO.getUserId());
			pstmt.setString(2, userDTO.getUserPw());
			pstmt.setString(3, userDTO.getUserName());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {			
			try {
				if(pstmt != null) pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if(conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
	}

	public int updatePw(String userId, String userPw, String changePw) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql ="""
				UPDATE TB_USER
				SET USER_PW = ?
				WHERE USER_ID = ? AND USER_PW = ?
				""";
		try {
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, changePw);
			pstmt.setString(2, userId);
			pstmt.setString(3, userPw);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {			
			try {
				if(pstmt != null) pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if(conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 0;		
	}
	
	public int delete(String userId, String userPw) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql ="""
				DELETE FROM TB_USER
				WHERE USER_ID = ? AND USER_PW = ?
				""";
		try {
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.setString(2, userPw);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {			
			try {
				if(pstmt != null) pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if(conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 0;		
	}

}
