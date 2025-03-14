package com.kh.mfw.member.model.service;

import org.apache.ibatis.session.SqlSession;

import static com.kh.mfw.common.Template.getSqlSession;

import com.kh.mfw.member.model.dao.MemberDAO;
import com.kh.mfw.member.model.dto.MemberDTO;

public class MemberService {
	
	public MemberDTO login(MemberDTO member) {
		SqlSession sqlSession = getSqlSession();
		//유효성 검증필요 => 지금은 패스
		MemberDTO loginMember = new MemberDAO().login(sqlSession, member);
		sqlSession.close(); //자원 반납
		//결과 없으면 null		
		return loginMember;		
	}

	//의사 결정 코드
	public int signUp(MemberDTO member) {
		//3차 유효성 검증 Java
		//4차 데이터무결성을 위한 제약조건(DBMS)
		//아이디 중복검사
		SqlSession sqlSession = getSqlSession();
//		boolean result = new MemberDAO().checkId(sqlSession, member.getMemberId());
		if(new MemberDAO().checkId(sqlSession, member.getMemberId())) {
			sqlSession.close();
			return 0;
		}		
		int result = new MemberDAO().signUp(sqlSession, member);
		sqlSession.commit();	//중요!
		sqlSession.close();
		return result;
	}

}
