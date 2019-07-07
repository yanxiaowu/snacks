package com.icss.snacks.controller;

import com.icss.snacks.entity.Commodity;
import com.icss.snacks.service.BrandService;
import com.icss.snacks.service.CategoryService;
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

        String brand_name=request.getParameter("brand_name");
        String category_name = request.getParameter("category_name");

        BrandService brandService = new BrandService();
        CategoryService categoryService = new CategoryService();

        int brand_id = 0;
        int category_id = 0;

        CommodityService commodityService = new CommodityService();
        List<Commodity> commodityList = null;

        if(brand_name.equals("全部") & !category_name.equals("全部")){
            try {
                category_id = categoryService.findCategory_idByCategory_name(category_name);
                commodityList = commodityService.findByCategoryId(category_id);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if(category_name.equals("全部") & !brand_name.equals("全部")){
            try {
                brand_id = brandService.findBrand_idByBrand_name(brand_name);
                commodityList = commodityService.findByBrandId(brand_id);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if(category_name.equals("全部") & brand_name.equals("全部")){
            try {
                commodityList = commodityService.findAllCommodityList();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            try {
                brand_id = brandService.findBrand_idByBrand_name(brand_name);
                category_id = categoryService.findCategory_idByCategory_name(category_name);
                commodityList = commodityService.findByCategoryIdANDBrandId(category_id,brand_id);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        JSONArray a=JSONArray.fromObject(commodityList);
        response.getWriter().print(a);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
