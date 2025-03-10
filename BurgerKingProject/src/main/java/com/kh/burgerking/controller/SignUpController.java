package com.kh.burgerking.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.burgerking.model.dto.UserDTO;

/**
 * Servlet implementation class SignUpController
 */
@WebServlet("/sign-up.do")
public class SignUpController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public SignUpController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		System.out.println("히히모카");
		/*컨트롤러
		 * 1. request 객체로부터 값을 뽑아서 DTO로 가공
		 * 2. 요청처리 -->패스
		 * 3. View 처리
		 */
		/*Post방식일 경우 인코딩 설정 ISO-8859-1 방식으로 바뀌기 때문에
		 * 값을 뽑기전에 미리 UTF-8 방식으로 변경해야함
		 * */
		request.setCharacterEncoding("UTF-8");
		
		//1
		//request.getParameter("input 요소의 name 속성 값");
		String userId = request.getParameter("userId");
		System.out.println(userId);
		String userPw = request.getParameter("userPw");
		System.out.println(userPw);
		String userName = request.getParameter("userName");
		System.out.println(userName);
		
		UserDTO userDTO = new UserDTO(userId, userPw, userName);
		/* service.insertMember(userDTO);
		 * 	잘다녀왔다고 가정
		 * service -> DAO -> DB
		 * : int
		 */
		//View 처리
		//JSP를 이용해서 응답데이터 만들기
		//JSP :Java기반의 서버 사이드 스크립트 언어
		//ASP, PHP
		
		//응답화면(JSP)에서 필요한 데이터를 넘겨주기
		//Attribute => K:V 세트로 묶어서 값을 담을수 있음
		request.setAttribute("user", userDTO);	
		request.setAttribute("message", "요청 처리에 성공했나?");
		
		//응답페이지를 JSP에게 위임(배정)
		//RequestDispatcher
		RequestDispatcher view = request.getRequestDispatcher("/views/response_page.jsp");
		view.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("히히모카");		
		doGet(request, response);
	}

}
