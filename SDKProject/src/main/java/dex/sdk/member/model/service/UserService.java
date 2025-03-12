package dex.sdk.member.model.service;

import java.sql.Connection;

import dex.sdk.member.model.dao.UserDAO;
import dex.sdk.member.model.dto.UserDTO;
import dex.sdk.util.SdkUtil;

public class UserService {
	private UserDAO userDAO = new UserDAO();
	
	public UserDTO login(UserDTO userDTO) {
		Connection conn = SdkUtil.getConnection();	
		UserDTO loginUser = new UserDAO().login(userDTO, conn);
		return loginUser;		
	}
}
