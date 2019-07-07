package com.icss.snacks.controller;

import com.icss.snacks.entity.Orders;
import com.icss.snacks.entity.User;
import com.icss.snacks.service.OrderService;
import com.icss.snacks.service.PersonalCenterService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/OrderServlet")
public class OrderServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User user = (User) request.getSession().getAttribute("user");
        List<Orders> ordersList = null;
        OrderService orderService = new OrderService();
        try {
            ordersList = orderService.getOrdersListByUid(user.getUid());
        } catch (Exception e) {
            request.getRequestDispatcher("error.jsp").forward(request, response);
            return;
        }

        request.setAttribute("ordersList", ordersList);
        request.getRequestDispatcher("/person/order.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
