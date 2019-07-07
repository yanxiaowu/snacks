package com.icss.snacks.service;

import java.util.List;

import com.icss.snacks.dao.CartDao;
import com.icss.snacks.entity.Cart;
import com.icss.snacks.entity.CartVo;
import com.icss.snacks.util.DbFactory;

public class CartService {

	private CartDao cartDao = new CartDao();
	
	public Integer addCart(Cart cart) throws Exception {
		Integer row = 0;
		if(cartDao.findQuantity(cart) >= 1) {
			try {
				row = cartDao.updateQuantity(cart);
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				DbFactory.closeConnection();
			}
			return row;
		}else {
			try {
				row = cartDao.add(cart);
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				DbFactory.closeConnection();
			}
			return row;
		}
	}


	public Integer deleteCartByCartId(String cartId) throws Exception {

		Integer row = 0;
		cartId = cartId.substring(0, cartId.length() - 1);

		try {
			cartDao.deleteCart(cartId);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbFactory.closeConnection();
		}

		return row;
	}
	
	public List<CartVo> findCartListByUid(Integer uid) throws Exception {
		
		List<CartVo> list = null;
		try {
			list = cartDao.findCartListByUid(uid);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbFactory.closeConnection();
		}
		return list;
		
	}


	public List<CartVo> findCartListByUidAndCartId(Integer uid, String cartIds) throws Exception {

		List<CartVo> list = null;

		try {
			list = cartDao.findCartListByUidAndCartId(uid, cartIds);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbFactory.closeConnection();
		}

		return list;
	}
	
}
