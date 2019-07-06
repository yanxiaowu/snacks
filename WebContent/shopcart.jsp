<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

		<title>购物车页面</title>

		<link href="css/amazeui.css" rel="stylesheet" type="text/css" />
		<link href="css/demo.css" rel="stylesheet" type="text/css" />
		<link href="css/cartstyle.css" rel="stylesheet" type="text/css" />
		<link href="css/optstyle.css" rel="stylesheet" type="text/css" />

		<script type="text/javascript" src="js/jquery.js"></script>

	</head>

	<body>

					<iframe src="header.jsp" width="100%" height="120px" frameborder="0" scrolling="no"></iframe>

			<!--购物车 -->
			<div class="concent">
				<div id="cartTable">
					<div class="cart-table-th">
						<div class="wp">
							<div class="th th-chk">
								<div id="J_SelectAll1" class="select-all J_SelectAll">

								</div>
							</div>
							<div class="th th-item">
								<div class="td-inner">商品信息</div>
							</div>
							<div class="th th-price">
								<div class="td-inner">单价</div>
							</div>
							<div class="th th-amount">
								<div class="td-inner">数量</div>
							</div>
							<div class="th th-sum">
								<div class="td-inner">金额</div>
							</div>
							<div class="th th-op">
								<div class="td-inner">操作</div>
							</div>
						</div>
					</div>
					<div class="clear"></div>

					<tr class="item-list">
						<div class="bundle  bundle-last ">
							<div class="bundle-hd">
								<div class="bd-promos">
									<span class="list-change theme-login">编辑</span>
								</div>
							</div>
							<div class="clear"></div>
							<div class="bundle-main">
							<c:forEach items="${requestScope.cartVoList }" var="cartVo" varStatus="status">
								<ul class="item-content clearfix">
									<li class="td td-chk">
										<div class="cart-checkbox ">
											<input onclick="getTotalMoney(${status.index})" class="check" id="check${status.index}" name="items" value="${cartVo.quantity*cartVo.promotional_price }" type="checkbox">
											<input type="hidden" name="cartids" id="cartid${status.index}" value="${cartVo.cart_id}">
										</div>
									</li>
									<li class="td td-item">
										<div class="item-pic">
											<a href="#" target="_blank" class="J_MakePoint" data-point="tbcart.8.12">
												<img src="/img/${cartVo.img }" width="80" height="80"  class="itempic J_ItemImg"></a>
										</div>
										<div class="item-info">
											<div class="item-basic-info">
												<a href="#" target="_blank" class="item-title J_MakePoint" data-point="tbcart.8.11">${cartVo.cname }</a>
											</div>
										
											<div class="item-props">
												<span class="sku-line">口味：${cartVo.fname }</span>
											</div>
											
										</div>
									</li>
									<li class="td td-price">
										<div class="item-price price-promo-promo">
											<div class="price-content">
												<div class="price-line">
													<em class="price-original">${cartVo.original_price}</em>
												</div>
												<div class="price-line">
													<em class="J_Price price-now" id="price${status.index}" tabindex="0">${cartVo.promotional_price }</em>
												</div>
											</div>
										</div>
									</li>
									<li class="td td-amount">
										<div class="amount-wrapper ">
											<div class="item-amount ">
												<div class="sl"> <!-- 在JQuery.js中已定义类名为min和add的标签对类名为text_box的标签的触发事件 -->
													<input class="min am-btn" name="" type="button" value="-" onclick="minQuantity(${status.index})"/>
													<!-- oninput="value=value.replace(/[^\d]/g,'')"  限制只能输入数字 -->
													<input class="text_box" name="quantitys" id="quantity${status.index}" value="${cartVo.quantity }" style="width:30px;"
														   oncopy="return false" onpaste="return false" oninput="value=value.replace(/[^\d]/g,'')"/>
													<input class="add am-btn" name="" type="button" value="+" onclick="addQuantity(${status.index})"/>
												</div>
											</div>
										</div>
									</li>
									<li class="td td-sum">
										<div class="td-inner">
											<em tabindex="0" id="sum${status.index}" class="J_ItemSum number">${cartVo.quantity*cartVo.promotional_price }</em>
										</div>
									</li>
									<li class="td td-op">
										<div class="td-inner">
											<a href="javascript:deleteItem(${status.index});" data-point-url="#" class="delete">
                  删除</a>
										</div>
									</li>
								</ul>

							</c:forEach>
							<script type="text/javascript">
								var totalMoney = 0;
								var num = document.getElementsByName("cartids").length;//获取购物车内各条目总数
								function getTotalMoney(index){
									totalMoney = Number($("#J_Total").html());
									var select = document.getElementById("selectAll");
									if($("#check"+index).attr("checked")=="checked"){
										totalMoney = parseFloat(totalMoney)+parseFloat(Number(document.getElementById("sum"+index).innerHTML));
									}else{
										totalMoney = parseFloat(totalMoney)-parseFloat(Number(document.getElementById("sum"+index).innerHTML));
									}
									$("#J_Total").html(totalMoney.toFixed(2));//保留两位小数，防止精度问题
									$("#J_SelectedItemsCount").html($("[name='items']:checked").length);
									select.checked=false;
								}
								function js(){
									//先根据页面上购物车条目变化情况修改cart表
									var cartids = document.getElementsByName("cartids");//获取购物车内各条目编号的集合
									var quantitys = document.getElementsByName("quantitys");//获取购物车内各条目数量的集合
									if(cartids.length==quantitys.length && cartids.length!=0){
										for(var i=0;i<cartids.length;i++){
											location.href="UpdateCartServlet?cart_id="+cartids[i].value+"&quantity="+quantitys[i].value;
											//传递最终各购物车条目的编号与数量给servlet
										}
									}
									//根据最终情况提交参数
									var totalMoney = $("#J_Total").html();//总金额
									var cartId = "";//勾选的购物车编号
									$.each($("[name='items']:checked"),function(){
										cartId = cartId+$(this).next().val()+",";//购物车编号
									})
									if(totalMoney!=0){
										location.href="PayServlet?totalMoney="+totalMoney+"&cartId="+cartId;
									}else{
										alert("请选择商品后结算……")
									}
								}
								function selectAll(){
									totalMoney = 0;
									var select = document.getElementById("selectAll");
									var items = document.getElementsByName("items");//获取购物车内商品集合
									if(select.checked==true){
										if(items.length){
											for(var i=0;i<items.length;i++){
												items[i].checked = true;
												getTotalMoney(i);
											}
										}
										select.checked=true;
									}
									if(select.checked==false){
										if(items.length){
											for(var i=0;i<items.length;i++){
												items[i].checked = false;
												getTotalMoney(i);
											}
										}
										select.checked=false;
										totalMoney = 0;
										$("#J_Total").html(totalMoney.toFixed(2));
									}
								}
								//不同于detail.jsp，由于采用jQuery设定+-按钮与输入框的关系，点击事件函数也与其不同
								function addQuantity(i){
									var number = Number(document.getElementById("quantity"+i).value)+1;
									price = Number(document.getElementById("price"+i).innerHTML);//em标签获取内容方式不同于input
									$("#sum"+i).html((number*price).toFixed(2));//保留两位小数，防止精度问题
									if($("#check"+i).attr("checked")=="checked"){
										var totalsum = 0;
										for(var k=0;k<num;k++){
											if($("#check"+k).attr("checked")=="checked"){
												totalsum = totalsum+Number(document.getElementById("sum"+k).innerHTML);//遍历选中的金额小计，修改总计金额
											}
										}
										$("#J_Total").html(totalsum.toFixed(2));
									}
								}
								function minQuantity(i){
									var number = Number(document.getElementById("quantity"+i).value)-1;
									price = Number(document.getElementById("price"+i).innerHTML);
									if(number<=0){
										number=1;
									}
									$("#sum"+i).html((number*price).toFixed(2));
									if($("#check"+i).attr("checked")=="checked"){
										var totalsum = 0;
										for(var k=0;k<num;k++){
											if($("#check"+k).attr("checked")=="checked"){
												totalsum = totalsum+Number(document.getElementById("sum"+k).innerHTML);
											}
										}
										$("#J_Total").html(totalsum.toFixed(2));
									}
								}
								for(var j=0;j<num;j++){
									$("#quantity"+j).bind('input propertychange', function() {//循环为所有商品数量input标签绑定事件，发生更改时执行以下内容
										var order = this.id.charAt(this.id.length-1);//获取产生变化的input标签的id的status.index序号
										if($(this).val()<=0){
											document.getElementById("quantity"+order).value=1;//设置input值小于1时自动置为1
										}
										var number = $(this).val();//获取变化后的input标签的值
										var price = Number(document.getElementById("price"+order).innerHTML);
										$("#sum"+order).html((number*price).toFixed(2));
										if($("#check"+order).attr("checked")=="checked"){
											var totalsum = 0;
											for(var k=0;k<num;k++){
												if($("#check"+k).attr("checked")=="checked"){
													totalsum += Number(document.getElementById("sum"+k).innerHTML);
												}
											}
											$("#J_Total").html(totalsum);
										}
									});
								}
								function deleteItem(index){
									if(confirm("确认要删除该商品吗？")){
										location.href="DeleteCartServlet?cartId="+$("#cartid"+index).val()+",";
									}
								}
								function deleteItems(){
									if(confirm("确认要删除选中商品吗？")){
										var cartId = "";//勾选的购物车编号
										$.each($("[name='items']:checked"),function(){
											cartId = cartId+$(this).next().val()+",";//购物车编号
										})
										location.href="DeleteCartServlet?cartId="+cartId;
									}
								}
							</script>
							</div>
						</div>
					</tr>
					<div class="clear"></div>
				</div>
				<div class="clear"></div>

				<div class="float-bar-wrapper">
					<div id="J_SelectAll2" class="select-all J_SelectAll">
						<div class="cart-checkbox">
							<input onclick="selectAll()" class="check-all check" id="selectAll" name="select-all" type="checkbox">
							<label for="J_SelectAllCbx2"></label>
						</div>
						<span>全选</span>
					</div>
					<div class="operations">
						<a href="javascript:deleteItems();" hidefocus="true" class="deleteAll">删除</a>
					</div>
					<div class="float-bar-right">
						<div class="amount-sum">
							<span class="txt">已选商品</span>
							<em id="J_SelectedItemsCount">0</em><span class="txt">件</span>
							<div class="arrow-box">
								<span class="selected-items-arrow"></span>
								<span class="arrow"></span>
							</div>
						</div>
						<div class="price-sum">
							<span class="txt">合计:</span>
							<strong class="price">¥<em id="J_Total">0.00</em></strong>
						</div>
						<div class="btn-area">
							<a href="javascript:js()" id="J_Go" class="submit-btn submit-btn-disabled" aria-label="请注意如果没有选择宝贝，将无法结算">
								<span>结&nbsp;算</span></a>
						</div>
					</div>

				</div>

				

			</div>

			<!--操作页面-->

			<div class="theme-popover-mask"></div>

			<div class="theme-popover">
				<div class="theme-span"></div>
				<div class="theme-poptit h-title">
					<a href="javascript:;" title="关闭" class="close">×</a>
				</div>
				<div class="theme-popbod dform">
					<form class="theme-signin" name="loginform" action="" method="post">

						<div class="theme-signin-left">

							<li class="theme-options">
								<div class="cart-title">颜色：</div>
								<ul>
									<li class="sku-line selected">12#川南玛瑙<i></i></li>
									<li class="sku-line">10#蜜橘色+17#樱花粉<i></i></li>
								</ul>
							</li>
							<li class="theme-options">
								<div class="cart-title">包装：</div>
								<ul>
									<li class="sku-line selected">包装：裸装<i></i></li>
									<li class="sku-line">两支手袋装（送彩带）<i></i></li>
								</ul>
							</li>
							<div class="theme-options">
								<div class="cart-title number">数量</div>
								<dd>
									<input class="min am-btn am-btn-default" name="" type="button" value="-" />
									<input class="text_box" name="" type="text" value="1" style="width:30px;" />
									<input class="add am-btn am-btn-default" name="" type="button" value="+" />
									<span  class="tb-hidden">库存<span class="stock">1000</span>件</span>
								</dd>

							</div>
							<div class="clear"></div>
							<div class="btn-op">
								<div class="btn am-btn am-btn-warning">确认</div>
								<div class="btn close am-btn am-btn-warning">取消</div>
							</div>

						</div>
						<div class="theme-signin-right">
							<div class="img-info">
								<img src="images/kouhong.jpg_80x80.jpg" />
							</div>
							<div class="text-info">
								<span class="J_Price price-now">¥39.00</span>
								<span id="Stock" class="tb-hidden">库存<span class="stock">1000</span>件</span>
							</div>
						</div>

					</form>
				</div>
			</div>
		
        
        			<iframe src="footer.jsp" width="100%" height="150px" frameborder="0" scrolling="no"></iframe>
	</body>

</html>