package com.kh.mcdonald.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.kh.mcdonald.model.dto.UserDTO;

public class UserDAO {
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public List<UserDTO> selectAll() {
		List<UserDTO> list = new ArrayList<UserDTO>();
		String url ="jdbc:oracle:thin:@127.0.0.1:12345:xe";
		String userName ="KH14_SDK";
		String password ="KH1234";
		String sql ="""
				SELECT USER_NO, USER_ID, USER_PW, USER_NAME, ENROLL_DATE
				FROM TB_USER
				ORDER BY ENROLL_DATE DESC
				""";
		
		try(Connection conn = DriverManager.getConnection(url, userName, password);
			PreparedStatement pstmt = conn.prepareStatement(sql);	
			ResultSet rset = pstmt.executeQuery();
		){
			while (rset.next()) {				
				list.add(new UserDTO(
						rset.getInt("USER_NO"),
						rset.getString("USER_ID"),
						rset.getString("USER_PW"),
						rset.getString("USER_NAME"),
						rset.getDate("ENROLL_DATE")));	
			}
	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;		
	}
}
