package dex.sdk.member.model.service;

import org.apache.ibatis.session.SqlSession;

import static dex.sdk.common.Template.getSqlSession;

import dex.sdk.member.model.dao.UserDAO;
import dex.sdk.member.model.dto.UserDTO;

public class UserService {

	public UserDTO login(UserDTO user) {		
		SqlSession sqlSession = getSqlSession();
		UserDTO loginUser = new UserDAO().login(sqlSession, user);
		sqlSession.close();
		return loginUser;
	}

	public int signUp(UserDTO user) {
		SqlSession sqlSession = getSqlSession();		
		if(new UserDAO().checkId(sqlSession, user.getUserId())) {
			sqlSession.close();
			return 0;
		}
		int result = new UserDAO().signUp(sqlSession, user);
		sqlSession.commit();
		sqlSession.close();
		return result;		
	}

	public int updatePw(UserDTO user, String changePw) {
		SqlSession sqlSession = getSqlSession();	
		if(new UserDAO().checkId(sqlSession, user.getUserId()) && new UserDAO().checkPw(sqlSession, user.getUserPw())) {
			sqlSession.close();
			return 0;
		}
		int result = new UserDAO().updatePw(sqlSession, user, changePw);
		sqlSession.commit();
		sqlSession.close();
		return result;
	}

	public int delete(UserDTO user) {
		SqlSession sqlSession = getSqlSession();	
		if(new UserDAO().checkId(sqlSession, user.getUserId()) && new UserDAO().checkPw(sqlSession, user.getUserPw())) {
			sqlSession.close();
			return 0;
		}
		int result = new UserDAO().delete(sqlSession, user);
		sqlSession.commit();
		sqlSession.close();
		return result;
	}

}
