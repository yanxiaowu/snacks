package com.icss.snacks.service;

import com.icss.snacks.dao.AccountDao;
import com.icss.snacks.dao.OrdersDao;
import com.icss.snacks.dao.OrdersDetailDao;
import com.icss.snacks.entity.Account;
import com.icss.snacks.entity.Orders;
import com.icss.snacks.util.DbFactory;

import java.util.List;


public class PersonalCenterService {

    AccountDao accountDao = new AccountDao();
    OrdersDao ordersDao = new OrdersDao();
    OrdersDetailDao ordersDetailDao = new OrdersDetailDao();

    public Account findAccuoutByUid(Integer uid) throws Exception {

        Account account = null;

        try {
            account = accountDao.findAccountByUid(uid);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbFactory.closeConnection();
        }

        return account;
    }

    public List<Orders> findOrdersListByUid(Integer uid) throws Exception {
        List<Orders> ordersList = null;
        try {
            ordersList = ordersDao.findOrdersByUid(uid);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbFactory.closeConnection();
        }
        return ordersList;
    }


    public void getOrderListQuantity(List<Orders> ordersList) throws Exception {

        Integer quantity = 0;

        for (Orders orders : ordersList) {
            try {
                quantity = ordersDetailDao.getOrdersQuantity(orders.getOid());
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                DbFactory.closeConnection();
            }
            orders.setQuantity(quantity);
        }

    }


}
