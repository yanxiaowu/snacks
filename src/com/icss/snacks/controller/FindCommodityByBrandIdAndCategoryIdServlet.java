package com.icss.snacks.controller;

import com.icss.snacks.entity.Commodity;
import com.icss.snacks.service.CommodityService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/FindCommodityByBrandIdAndCategoryIdServlet")
public class FindCommodityByBrandIdAndCategoryIdServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        int brand_id=Integer.parseInt(request.getParameter("brand_id"));
//        int category_id = Integer.parseInt(request.getParameter("category_id"));
        CommodityService commodityService = new CommodityService();
        List<Commodity> commodityList = null;

//        if(brand_id == 0){
//            try {
//                commodityList = commodityService.findByCategoryId(category_id);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }else if(category_id == 0){
            try {
                commodityList = commodityService.findByBrandId(brand_id);
            } catch (Exception e) {
                e.printStackTrace();
            }
//        }else{
//            try {
//                commodityList = commodityService.findByCategoryIdANDBrandId(category_id,brand_id);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
        JSONArray a=JSONArray.fromObject(commodityList);
        response.getWriter().print(a);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
