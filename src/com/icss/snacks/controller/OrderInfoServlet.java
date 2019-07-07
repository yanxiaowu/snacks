package com.icss.snacks.controller;

import com.icss.snacks.entity.*;
import com.icss.snacks.service.AddressService;
import com.icss.snacks.service.CommodityService;
import com.icss.snacks.service.FlavorService;
import com.icss.snacks.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/OrderInfoServlet")
public class OrderInfoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 接收页面提交的参数
        String oid = request.getParameter("oid");
        User user = (User) request.getSession().getAttribute("user");

        // 调用业务层方法进行处理
        OrderService orderService = new OrderService();
        AddressService addressService = new AddressService();
        CommodityService commodityService = new CommodityService();
        FlavorService flavorService = new FlavorService();
        Orders orders = null;
        Address address = null;
        List<Commodity> commodityList = new ArrayList<Commodity>();
        List<Flavor> flavorList = new ArrayList<Flavor>();

        try {
            orders = orderService.getOrdersByOid(oid);
            address = addressService.findAddressByAddressId(orders.getAddress_id());
            for (OrdersDetail ordersDetail : orders.getOrdersDetailList()) {
                commodityList.add(commodityService.findCommodityid(ordersDetail.getCommodity_id()));
                flavorList.add(flavorService.findFlavorByFid(ordersDetail.getFid()));
            }
        } catch (Exception e) {
            request.getRequestDispatcher("error.jsp").forward(request, response);
            return;
        }

        //根据处理返回结果
        request.setAttribute("orders", orders);
        request.setAttribute("address", address);
        request.setAttribute("commodityList", commodityList);
        request.setAttribute("flavorList", flavorList);
        request.getRequestDispatcher("person/orderinfo.jsp").forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
