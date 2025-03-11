package com.kh.mcdonald.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.mcdonald.model.dto.UserDTO;
import com.kh.mcdonald.model.service.UserService;

@WebServlet("/jstl")
public class JstlController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public JstlController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int point = 500;
		String[] colors = {"red","orangered","orange","yellow","yellowgreen","green"};
		request.setAttribute("point", point);	
		request.setAttribute("colors", colors);
		
		//TB_USER 가지고 오기
		List<UserDTO> list = new UserService().selectAll();
		request.setAttribute("users", list);
		request.setAttribute("msg", "요청한 내용");
		request.getRequestDispatcher("/WEB-INF/views/jstl/JSTL.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
