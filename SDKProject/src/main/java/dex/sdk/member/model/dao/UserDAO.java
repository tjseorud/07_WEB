package dex.sdk.member.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dex.sdk.member.model.dto.UserDTO;

public class UserDAO {
	static {
		try {
			Class.forName("oracle.jdbc.OracleDriver");	//FullClassName /패키지 경로부터 클래스까지
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public UserDTO login(UserDTO userDTO, Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql ="""
				SELECT USER_ID, USER_PW, USER_NAME, ENROLL_DATE
				FROM TB_USER
				WHERE USER_ID = ? AND USER_PW = ?
				""";
		UserDTO loginUser = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userDTO.getUserId());
			pstmt.setString(2, userDTO.getUserPw());
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				loginUser = new UserDTO(
						rset.getString("USER_ID"),
						rset.getString("USER_PW"),
						rset.getString("USER_NAME"),
						rset.getDate("ENROLL_DATE")
					);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rset != null) rset.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if(pstmt != null) pstmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if(conn != null) conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return loginUser;
	}

}
