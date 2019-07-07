package com.icss.snacks.service;

import com.icss.snacks.dao.CategoryDao;
import com.icss.snacks.entity.Category;
import com.icss.snacks.util.DbFactory;

import java.util.ArrayList;
import java.util.List;

public class CategoryService {

    private CategoryDao categoryDao = new CategoryDao();

    public List<Category> findAllCategory() throws Exception {
        List<Category> categories = null;
        try {
            categories = categoryDao.findByParentId(0);
            for (Category c : categories) {
                c.setSubCategoryList(categoryDao.findByParentId(c.getCategory_id()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbFactory.closeConnection();
        }

        return categories;
    }

    public int findCategory_idByCategory_name(String category_name){
        int category_id = 0;
        try {
            category_id = categoryDao.findBrandIdByBrandName(category_name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return category_id;
    }


}
