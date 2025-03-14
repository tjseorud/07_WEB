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
		return new UserDAO().updatePw(userId, userPw, changePw);
	}

	public int delete(String userId, String userPw) {
		return new UserDAO().delete(userId, userPw);
	}

}
