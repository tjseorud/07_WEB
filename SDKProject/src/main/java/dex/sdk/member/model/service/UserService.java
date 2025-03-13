package dex.sdk.member.model.service;

import dex.sdk.member.model.dao.UserDAO;
import dex.sdk.member.model.dto.UserDTO;

public class UserService {

	public UserDTO login(UserDTO userDTO) {		
		UserDTO loginUser = new UserDAO().login(userDTO);
		return loginUser;
	}

	public int signUp(UserDTO userDTO) {
		int result = new UserDAO().checkId(userDTO.getUserId());
		if(result > 0) {
			return result;
		}
		new UserDAO().signUp(userDTO);
		return result;		
	}

	public int updatePw(String userId, String userPw, String changePw) {
		int result = new UserDAO().checkPw(userPw);
		if(result > 0) {
			return result;
		}
		new UserDAO().updatePw(userId, userPw, changePw);
		return result;
	}

	public void delete(String userId, String userPw) {
		new UserDAO().delete(userId, userPw);
	}

}
