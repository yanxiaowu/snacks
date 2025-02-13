﻿<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

		<title>搜索页面</title>

		<link href="css/amazeui.css" rel="stylesheet" type="text/css" />
		<link href="css/admin.css" rel="stylesheet" type="text/css" />
		<link href="css/demo.css" rel="stylesheet" type="text/css" />
	   <link href="css/seastyle.css" rel="stylesheet" type="text/css" />

		<script type="text/javascript" src="js/jquery-1.7.min.js"></script>
		<script type="text/javascript" src="js/script.js"></script>
	</head>

	<body>
			<iframe src="header.jsp" width="100%" height="120px" frameborder="0"></iframe>
		
			<b class="line"></b>
           <div class="search">
			<div class="search-list">
			<div class="nav-table">
					   <div class="long-title"><span class="all-goods">全部分类</span></div>
					   <div class="nav-cont">
							<ul>
								<li class="index"><a href="#">首页</a></li>
                                <li class="qc"><a href="#">闪购</a></li>
                                <li class="qc"><a href="#">限时抢</a></li>
                                <li class="qc"><a href="#">团购</a></li>
                                <li class="qc last"><a href="#">大包装</a></li>
							</ul>
						   
						</div>
			</div>
			
				
					<div class="am-g am-g-fixed">
						<div class="am-u-sm-12 am-u-md-12">
	                  	<div class="theme-popover">														
							<ul class="select">
								<p class="title font-normal">
									<span class="fl">松子</span>
									<span class="total fl">搜索到<strong class="num">997</strong>件相关商品</span>
								</p>
								<div class="clear"></div>
								<li class="select-result">
									<dl>
										<dt>已选</dt>
										<dd class="select-no"></dd>
										<p class="eliminateCriteria">清除</p>
									</dl>
								</li>
								<div class="clear"></div>
								<li class="select-list">
									<dl id="select1">
										<dt class="am-badge am-round">品牌</dt>

										 <div class="dd-conent">										
											<%--<dd class="select-all selected"><a id="allbrand" href="javascript:findCommodityByBrandId()">全部</a></dd>--%>
											<c:forEach items="${requestScope.brandList}" var="brand">
												<dd><a id="brand_id" value="${brand.brand_id}" href="javascript:findCommodityByBrandId()">${brand.name}</a></dd>
											</c:forEach>

										 </div>
						
									</dl>
								</li>
								<li class="select-list">
									<dl id="select2">
										<dt class="am-badge am-round">种类</dt>
										<div class="dd-conent">
											<%--<dd class="select-all selected"><a id="allcategory" href="javascript:findCommodityByBrandId()">全部</a></dd>--%>
												<c:forEach items="${requestScope.categoryList}" var="category">
													<dd><a id = "category_id" value="${category.category_id}" href="javascript:findCommodityByBrandId()">${category.name}</a></dd>
												</c:forEach>

											<script>
												function findCommodityByBrandId(){
													// alert(document.getElementById("brand_id").innerHTML);
													// alert(document.getElementById("category_id").innerHTML);
													brand_name = document.getElementById("brand_id").innerHTML;
													category_name = document.getElementById("category_id").innerHTML;

													$.ajax({
														type:"post",
														dataType:"json",
														url:"FindCommodityByBrandIdAndCategoryIdServlet",
														data:"brand_name="+brand_name+"&category_name="+category_name,

														success:function(data){
															$("#aaa").html("");
															var aaa = "";

															for(i in data){
																aaa += "<li style=\" width:206px; height:306px\">" +
																"<div class=\"i-pic limit\">" +
																"<a href=\"detail.jsp\"><img src=\"/img/" + data[i].img + "\" /></a>" +
																"<p class=\"title fl\">" + data[i].cname + "</p>" +
																"<p class=\"price fl\">" +
																"<b>¥ </b>" +
																"<strong>" + data[i].promotional_price + "</strong>" +
																"</p>" +
																"</div>" +
																"</li>";
															}
															$("#aaa").html(aaa);
														}
													})
												}
											</script>
										</div>
									</dl>
								</li>
									        
							</ul>
							<div class="clear"></div>
                        </div>
							<div class="search-content">
								<div class="sort">
									<li class="first"><a title="综合">默认排序</a></li>
									<li><a title="销量">销量排序</a></li>
									<li><a title="价格">价格优先</a></li>

								</div>
								<div class="clear"></div>

								<ul id="aaa" class="am-avg-sm-2 am-avg-md-3 am-avg-lg-4 boxes">



								<c:forEach items="${requestScope.commodityList}" var="commodity">
									<li style=" width:206px; height:306px">
										<div class="i-pic limit">
											
											<a href="FindCommodityServlet?commodity_id=${commodity.commodity_id}">
												<img src="/img/${commodity.img}" />

											<p class="title fl">${commodity.cname}</p>
											<p class="price fl">
												<b>¥</b>
												<strong>${commodity.promotional_price}</strong>
											</p>
											</a>
										</div>
									</li>
								</c:forEach>




								</ul>
							</div>
							
							<div class="clear"></div>
							<!--分页 -->
							<ul class="am-pagination am-pagination-right">
								<li class="am-disabled"><a href="#">首页</a></li>
								<li><a href="#">上一页</a></li>
								<li><a href="#">下一页</a></li>
								<li><a href="#">尾页</a></li>
							</ul>

						</div>
					</div>
					
				</div>

			</div>

		<!--引导 -->
		<div class="navCir">
			<li><a href="home2.jsp"><i class="am-icon-home "></i>首页</a></li>
			<li><a href="sort.jsp"><i class="am-icon-list"></i>分类</a></li>
			<li><a href="shopcart.jsp"><i class="am-icon-shopping-basket"></i>购物车</a></li>	
			<li><a href="person/index.jsp"><i class="am-icon-user"></i>我的</a></li>					
		</div>

		
			<iframe src="footer.jsp" width="100%" height="150px" frameborder="0"></iframe>
	</body>

</html>