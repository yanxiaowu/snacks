package com.icss.snacks.entity;

public class CartVo {
	
	private Integer quantity;
	private String fname;
	private String cname;
	private Double original_price;
	private Double promotional_price;
	private String img;
	private Integer cart_id; //购物车编号

	@Override
	public String toString() {
		return "CartVo[" +
				"quantity=" + quantity +
				", fname='" + fname + '\'' +
				", cname='" + cname + '\'' +
				", original_price=" + original_price +
				", promotional_price=" + promotional_price +
				", img='" + img + '\'' +
				", cart_id=" + cart_id +
				']';
	}

	public Double getOriginal_price() {
		return original_price;
	}
	public Double getPromotional_price() {
		return promotional_price;
	}
	public void setOriginal_price(Double original_price) {
		this.original_price = original_price;
	}
	public void setPromotional_price(Double promotional_price) {
		this.promotional_price = promotional_price;
	}
	public Integer getCart_id() {
		return cart_id;
	}
	public void setCart_id(Integer cart_id) {
		this.cart_id = cart_id;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}

	
}
