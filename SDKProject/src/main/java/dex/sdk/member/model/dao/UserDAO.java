package dex.sdk.member.model.dao;

import org.apache.ibatis.session.SqlSession;

import dex.sdk.member.model.dto.UserDTO;

public class UserDAO {	
	
	public UserDTO login(SqlSession sqlSession, UserDTO user) {
		return sqlSession.selectOne("memberMapper.login", user);
	}

	public boolean checkId(SqlSession sqlSession, String userId) {	
		return (Integer)sqlSession.selectOne("memberMapper.checkId", userId) > 0 ? true : false;
	}
	
	public boolean checkPw(SqlSession sqlSession, String userPw) {
		return (Integer)sqlSession.selectOne("memberMapper.checkPw", userPw) > 0 ? true : false;
	}
	
	public int signUp(SqlSession sqlSession, UserDTO user) {
		return sqlSession.insert("memberMapper.signUp", user);		
	}
	
	public int updatePw(SqlSession sqlSession, UserDTO user, String changePw) {
		return sqlSession.insert("memberMapper.updatePw", user);
	}
	
	public int delete(SqlSession sqlSession, UserDTO user) {		
		return sqlSession.insert("memberMapper.delete", user);		
	}

}
