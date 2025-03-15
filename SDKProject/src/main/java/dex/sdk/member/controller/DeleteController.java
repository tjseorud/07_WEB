package dex.sdk.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dex.sdk.member.model.dto.UserDTO;
import dex.sdk.member.model.service.UserService;

@WebServlet("/delete")
public class DeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DeleteController() {
		super();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String userId = null;	//request.getSession()
		String userPw = request.getParameter("userPwd");	
		
		UserDTO user = new UserDTO(userId, userPw, null, null);
		int result = new UserService().delete(user);
		String path = request.getContextPath();
		response.sendRedirect(result != 0 ? path + "/logout" : path);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}