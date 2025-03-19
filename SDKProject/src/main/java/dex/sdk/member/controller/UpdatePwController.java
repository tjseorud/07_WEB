package dex.sdk.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dex.sdk.member.model.dto.UserDTO;
import dex.sdk.member.model.service.UserService;

@WebServlet("/update-pw")
public class UpdatePwController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UpdatePwController() {
		super();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");		
		String changePw = request.getParameter("changePwd");		
		
		HttpSession session = request.getSession();	
		String userId = ((UserDTO)session.getAttribute("loginUser")).getUserId();	
		
		UserDTO user = new UserDTO();
		user.setUserId(userId);
		user.setUserPw(request.getParameter("userPwd"));
		
		int result = new UserService().updatePw(user, changePw);		
		String path = request.getContextPath();
		response.sendRedirect(result != 0 ? path + "/logout" : path);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}