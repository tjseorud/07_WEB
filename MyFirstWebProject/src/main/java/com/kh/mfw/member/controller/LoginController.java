package com.kh.mfw.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.mfw.member.model.dto.MemberDTO;
import com.kh.mfw.member.model.service.MemberService;

@WebServlet("/sign-in")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoginController() { 
    	super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//절차
		// 1) GET / POST => 요청 전송방식이 POST라면 인코딩 작업
		request.setCharacterEncoding("UTF-8");
		
		// 2) 요청값 확인 =>있다면 값을 뽑아서 가공
		//request.getParameter("??요소의 ??속성 값");
		String memberId = request.getParameter("memberId");
		String memberPw = request.getParameter("memberPw");
//		System.out.println("입력받은 id :"+memberId+"\n입력받은 pw :"+memberPw);
		
		// 3) 값이 두 개 이상일 경우 어딘가에 담아두기
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setMemberId(memberId);
		memberDTO.setMemberPw(memberPw);
		
		MemberDTO loginMember = new MemberService().login(memberDTO);
		/* case 1. 아이디와 비밀번호 값이 일치 했다면 필드값에 회원정보가 담겨있는 MemberDTO객체의 주소값
		/* case 2. 유효성 검증에 통과하지 못 했거나, 아이디와 비밀번호가 일치하지 않았다면 
		 */
		// 4) 응답화면 만들기
		request.setAttribute("loginMember", loginMember);
		/*
		 * 로그인에 성공시 로그인한 회원의 정보를 
		 * 로그아웃 요청이 들어오거나, 브라우저를 종료하기 전까지는
		 * 계속 사용할수 있어야 하기때문에,
		 * Session이 라는 저장소에 값을 담아둠
		 * 
		 * Session의 자료형 :HttpSession
		 */
		HttpSession session = request.getSession();
		session.setAttribute("loginMember",loginMember);		
//		request.getRequestDispatcher("index.jsp").forward(request, response);
		//http://127.0.0.1/mfw
		/* sendRedirect : Client에게 재요철할 URL을 알려주어서
		 * Client가 다시 요청을 보내게 만드는 방법
		 */
		String contextPath = request.getContextPath();
		response.sendRedirect(contextPath);	//재요청방식
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
