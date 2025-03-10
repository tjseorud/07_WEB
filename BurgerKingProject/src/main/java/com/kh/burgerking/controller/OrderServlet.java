package com.kh.burgerking.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class OrderServlet
 */
@WebServlet("/order")
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;	//버전 관리용
       
    public OrderServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("/bk/order doGet method 요청받음");
		/* 데이터를 받아 가공해서 Service로 넘겨주는 역할
		 * 응답받은 데이터를 View로 던짐
		 * 
		 * View에서 Get방식으로 요청시 doGet()가 호출됨
		 * 
		 * 두 개의 매개변수가 존재함
		 * 
		 * 첫 번째 매개변수 HttpServletRequest => 요청시 전달된 내용릏리 담김
		 *  =>요청 전송방식(Get),사용자의 IP주소, 어떤 URL을 통해서 왔는지, 사용자가 입력한 값 등등 ...
		 *  
		 * 두 번째 매개변수 HttpServletResponse => 요청처리 후 응답할때 사용
		 */
//		String query = request.getQueryString();
//		System.out.println(query);
		
		int num = Integer.parseInt(request.getParameter("num"));	//NumberFormatException: For input string: ""
//		String num = request.getParameter("num");
		System.out.println(num);
		String prductName = request.getParameter("prduct-name");
		System.out.println(prductName);
		/*자주보는 문제상황
		 * 404 :파일이나 요청을 받아주는 서블릿을 찾지 못했을때 발생 =>오타일 확률이 높음
		 * 500 :자바 소스코드상의 오류(예외발생) 
		 * */	
		/*Insert된 상태로 침
		 * 요청처리(Service의 메서드를 호출해서 DB와의 상호작용까지 끝난 상태)
		 */
		int totalPrice = num * 500;
		/*???(사용자가 입력한 제품명)의 총 가격은 ???(총 결제해야하는 금액)원 입니다.*/
		//1단계) 응답데이터 형식 지정	->문서형태의 HTML / 인코딩방식 UTF-8
		response.setContentType("text/html; charset=UTF-8");
		
		//2단계) 출력 스트림 생성
		PrintWriter writer = response.getWriter();
		
		//3단계) 스트림을 통해 HTML 출력
		writer.println("<html>");
		writer.println("<head>");
		writer.println("<title>qwerty</title>");
		writer.println("</head>");
		writer.println("<script>alert('읭??')</script>");
		writer.println(prductName+"의 총 가격은 "+totalPrice+"원 입니다.");
		writer.println("</html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("/bk/order doPost method 요청받음");
	}

}
