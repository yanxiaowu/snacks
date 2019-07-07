package com.icss.snacks.controller;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "LoginFilter", urlPatterns = {"/FindCartByUidServlet", "/PersonalCenterServlet", "/OrderServlet", "/OrderInfoServlet"})
public class LoginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpServletRequest request = (HttpServletRequest) req;

        if (request.getSession().getAttribute("user") != null) {
            chain.doFilter(request, response);
            return;
        }

        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
