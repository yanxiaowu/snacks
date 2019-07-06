package com.icss.snacks.controller;

import com.icss.snacks.entity.User;
import com.icss.snacks.service.CartService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/DeleteCartServlet")
public class DeleteCartServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String cartId = request.getParameter("cartId");

        CartService cartService = new CartService();

        try {
            cartService.deleteCartByCartId(cartId);
        } catch (Exception e) {
            request.getRequestDispatcher("error.jsp").forward(request, response);
            return;
        }

        request.getRequestDispatcher("FindCartByUidServlet").forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
