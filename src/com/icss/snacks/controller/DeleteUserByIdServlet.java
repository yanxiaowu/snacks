package com.icss.snacks.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.snacks.entity.User;
import com.icss.snacks.service.UserService;

/**
 * Servlet implementation class DeleteUserByIdServlet
 */
@WebServlet("/DeleteUserByIdServlet")
public class DeleteUserByIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteUserByIdServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = request.getParameter("ID"); //用户勾选的购物车编号
		System.out.print(id);
        User user = null;
        Integer Ids = 0;
		Ids = Integer.parseInt(id);
		//调用业务层方法进行处理
		UserService userService = new UserService();

		try {
			userService.DeleteUserByUid(Ids);
		} catch (Exception e) {
			request.getRequestDispatcher("error.jsp").forward(request, response);
			return;
		}
		//根据处理结果跳转页面
		request.getRequestDispatcher("/back/userdetail.jsp").forward(request, response);
	}

}
