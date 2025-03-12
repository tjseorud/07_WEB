package com.kh.mfw.member.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.kh.mfw.member.model.dto.MemberDTO;

public class MemberDAO {
	static {
		try {
			Class.forName("oracle.jdbc.OracleDriver");	//FullClassName /패키지 경로부터 클래스까지
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	protected String url = "jdbc:oracle:thin:@112.221.156.34:12345:XE";
	protected String userName = "KH14_SDK";
	protected String password = "KH1234";		
	
	public MemberDTO login(MemberDTO memberDTO) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;		
		String sql ="""
						SELECT MEMBER_ID ,
							   MEMBER_PW , 
							   MEMBER_NAME ,
							   EMAIL ,
							   ENROLL_DATE
						  FROM KH_MEMBER
						 WHERE MEMBER_ID = ?
						   AND MEMBER_PW = ?
					""";	//왜? 이게 권장이지?	
		MemberDTO loginMember = null;
		try {
			conn = DriverManager.getConnection(url, userName, password);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberDTO.getMemberId());
			pstmt.setString(2, memberDTO.getMemberPw());	
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				loginMember = new MemberDTO(
						rset.getString("MEMBER_ID"),
						rset.getString("MEMBER_PW"),
						rset.getString("MEMBER_NAME"),
						rset.getString("EMAIL"),
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
		return loginMember;
	}
	
}
