<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


<div id="sidebar" class="span3">
	<div class="well well-small">
		<a id="myCart" href="<c:url value="product_summary"></c:url>"><img
			src="themes/images/ico-cart.png" alt="cart"> ${fn:length(sessionScope.order.items)} Items in your
			cart <span class="badge badge-warning pull-right">$${sessionScope.order.totalPrice}</span></a>
	</div>
	<ul id="sideManu" class="nav nav-tabs nav-stacked">
		<li class="subMenu open"><a>CATEGORIES</a>
			<ul>
				<c:forEach var="category" items="${categories}">
					<li><a href="<s:url value="/products?CATEGORY=${category.key}"/>"><i class="icon-chevron-right"></i>
							${category.key}(${category.value})</a></li>
				</c:forEach>
			</ul></li>
	</ul>
	<br />
</div>
