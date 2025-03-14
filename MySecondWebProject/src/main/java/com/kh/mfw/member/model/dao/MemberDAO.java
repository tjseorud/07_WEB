package com.kh.mfw.member.model.dao;

import org.apache.ibatis.session.SqlSession;

import com.kh.mfw.member.model.dto.MemberDTO;

public class MemberDAO {

	public MemberDTO login(SqlSession sqlSession, MemberDTO member) {
		/*
		 * sqlSession :PreparedStatement 역할도 같이함
		 * 
		 * sqlSession.selectList()	:List<DTO> 형태으로 반환
		 * sqlSession.selectOne()	:DTO
		 * 	1) SQL 값이 완성형이며 매핑값이 없을때 	:.selectOne("statement");
		 *  2) SQL 값이 미완성형이며 매핑값이 있을때	:.selectOne("statement", memberDTO);
		 */
		//SqlSession이 제공하는 메소드를 통해 SQL문을 찾아서 실행하고 결과값을 받을 수있음
		//sqlSession.sql문종류에맞는메서드("mapper파일의namespace.SQL문의id속성값");
//		MemberDTO memberDTO = sqlSession.selectOne("memberMapper.login", member);
//		System.out.println(memberDTO);
		return sqlSession.selectOne("memberMapper.login", member);
	}

	public boolean checkId(SqlSession sqlSession, String memberId) {
		// boolean 이거말고 int로 하면 편해
//		int result = sqlSession.selectOne("memberMapper.checkId", memberId);
//		if(result > 0) {
//			return true;
//		} else {
//			return false;
//		}
		return (Integer)sqlSession.selectOne("memberMapper.checkId", memberId) > 0 ? true : false;
	}

	public int signUp(SqlSession sqlSession, MemberDTO member) {
		return sqlSession.insert("memberMapper.signUp", member);		
	}

}
