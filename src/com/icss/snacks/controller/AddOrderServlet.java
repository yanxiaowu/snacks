package com.icss.snacks.controller;

import com.icss.snacks.entity.User;
import com.icss.snacks.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/AddOrderServlet")
public class AddOrderServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        User user = (User) request.getSession().getAttribute("user");
        Integer uid = user.getUid();
        Integer address_id = Integer.parseInt(request.getParameter("address_id"));
        String remark = request.getParameter("remark");
        Double total_price = Double.parseDouble(request.getParameter("total_price"));
        String cardIds = request.getParameter("cardIds");

        OrderService orderService = new OrderService();

        try {
            orderService.addOrder(address_id, remark, total_price, uid, cardIds);
        } catch (Exception e) {
            e.printStackTrace();
        }

        request.getRequestDispatcher("success.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doPost(request, response);

    }
}
