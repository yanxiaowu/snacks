package com.icss.snacks.service;

import com.icss.snacks.dao.BrandDao;
import com.icss.snacks.entity.Brand;
import com.icss.snacks.util.DbFactory;

import java.util.ArrayList;
import java.util.List;

public class BrandService {
    private BrandDao brandDao = new BrandDao();

    public List<Brand> findAllBrandList() throws Exception {
        List<Brand> brandList = new ArrayList<Brand>();
        try {
            brandList = brandDao.findAllBrandList();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            DbFactory.closeConnection();
        }

        return brandList;
    }

    public int findBrand_idByBrand_name(String brand_name){
        int brand_id = 0;
        try {
            brand_id = brandDao.findBrandIdByBrandName(brand_name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return brand_id;
    }
}
