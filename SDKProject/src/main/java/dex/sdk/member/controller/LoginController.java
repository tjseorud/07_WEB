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

@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		// 절차
		// 1) GET / POST => 요청 전송방식이 POST라면 인코딩 작업
		request.setCharacterEncoding("UTF-8");

		// 2) 요청값 확인 =>있다면 값을 뽑아서 가공
		// request.getParameter("??요소의 ??속성 값");
		String userId = request.getParameter("userId");
		String userPw = request.getParameter("userPw");
		System.out.println("입력받은 id :" + userId + "\n입력받은 pw :" + userPw);

		// 3) 값이 두 개 이상일 경우 어딘가에 담아두기
		UserDTO userDTO = new UserDTO();
		userDTO.setUserId(userId);
		userDTO.setUserPw(userPw);

		UserDTO loginUser = new UserService().login(userDTO);

		// 4) 응답화면 만들기
		request.setAttribute("loginUser", loginUser);

		HttpSession session = request.getSession();
		session.setAttribute("loginUser", loginUser);

		String contextPath = request.getContextPath();
		response.sendRedirect(contextPath); // 재요청방식
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
