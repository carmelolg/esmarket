<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>



<div id="header">

	<div class="container">
		<div id="welcomeLine" class="row">
			<div class="span6">
				Welcome!
				<c:choose>
					<c:when test="${sessionScope.user ne null}">
						<strong>${sessionScope.user.username}</strong>
					</c:when>
					<c:otherwise>
						<strong>User</strong>
						<a class="btn btn-mini" href='<c:url value="register"></c:url>'>SIGN UP</a>
					</c:otherwise>
				</c:choose>
			</div>
			<div class="span6">
				<div class="pull-right">
					<a class="btn btn-mini" onclick="toggleMenu()">ADVANCED SEARCH</a>
					<span class="btn btn-mini">$${sessionScope.order.totalPrice}</span>
					<!-- Prezzo totale carrello corrente  -->
					<a href='<c:url value="product_summary"></c:url>'><span
						class="btn btn-mini btn-primary"> <i
							class="icon-shopping-cart icon-white"></i> [
							${fn:length(sessionScope.order.items)} ] Itemes in your cart
					</span> </a>
				</div>
			</div>
		</div>
		<!-- Navbar ================================================== -->
		<div id="logoArea" class="navbar">
			<a id="smallScreen" data-target="#topMenu" data-toggle="collapse"
				class="btn btn-navbar"> <span class="icon-bar"></span> <span
				class="icon-bar"></span> <span class="icon-bar"></span>
			</a>
			<div class="navbar-inner">
				<a class="brand" href='<s:url value="/"></s:url>'><img
					src="themes/images/logo.png" alt="Bootsshop" /></a>
				<form class="form-inline navbar-search" method="post"
					action="products">
					<input class="srchTxt" type="text" name="PRODUCT_NAME" style="width: 180px" /> <select
						class="srchTxt" name="CATEGORY" style="width: 180px">
						<c:forEach var="category" items="${categories}">
							<option value="${category.key}">${category.key}</option>
						</c:forEach>
					</select>
					<button type="submit" id="submitButton" class="btn btn-primary">Go</button>
				</form>
				<jsp:include page="menu.jsp"></jsp:include>
			</div>
		</div>
		<div class="well well-small" id="searchForm">
			<sf:form class="form-inline" method="post" action="products"
				modelAttribute="searchForm">

				<table align="center" cellpadding="4">
					<tr>
						<th><label for="name">Product name: </label></th>
						<td><sf:input path="productName" class="srchTxt" type="text"
								name="PRODUCT_NAME" id="name"></sf:input></td>
						<th><label for="maxPrice">Max Price: </label></th>
						<td><sf:input path="maxPrice" class="srchTxt" type="text"
								name="MAX_PRICE" id="maxPrice"></sf:input></td>
						<th><label for="brand">Brand: </label></th>
						<td><sf:select path="brand" class="srchTxt" name="BRAND">
								<c:forEach var="brand" items="${brands}">
									<c:if test="${brand!=searchForm.brand}">
										<option value="${brand}">${brand}</option>
									</c:if>
									<c:if test="${brand==searchForm.brand}">
										<option value="${brand}" selected="selected">${brand}</option>
									</c:if>
								</c:forEach>
							</sf:select></td>
					</tr>
					<tr style="color: red">
						<td colspan="2"><sf:errors path="productName"></sf:errors></td>
						<td colspan="2"><sf:errors path="maxPrice"></sf:errors></td>
						<td colspan="2"></td>
					</tr>
					<tr>
						<td colspan="2"></td>
						<th><label for="minPrice">Min Price: </label></th>
						<td><sf:input path="minPrice" class="srchTxt" type="text"
								name="MIN_PRICE" id="minPrice"></sf:input></td>
						<th><label for="category">Category: </label></th>
						<td><sf:select path="category" id="category" class="srchTxt"
								name="CATEGORY">
								<c:forEach var="category" items="${categories}">
									<c:if test="${category.key!=searchForm.category}">
										<option value="${category.key}">${category.key}</option>
									</c:if>
									<c:if test="${category.key==searchForm.category}">
										<option value="${category.key}" selected="selected">${category.key}</option>
									</c:if>
								</c:forEach>

							</sf:select></td>
					</tr>
					<tr style="color: red">
						<td colspan="2"></td>
						<td colspan="2"><sf:errors path="minPrice"></sf:errors></td>
						<td colspan="2"></td>
					</tr>
					<tr>
						<td colspan="3"></td>
						<td>
							<button type="submit" class="btn btn-primary">Go</button>
						</td>
						<td></td>
					</tr>
				</table>
				<sf:input id="formIsOpen" path="open" type="hidden"
					value="${searchForm.open}"></sf:input>

			</sf:form>
		</div>
	</div>
</div>

<!-- Placed at the end of the document so the pages load faster ============================================= -->
<script src="themes/js/jquery.js" type="text/javascript"></script>
<script type="text/javascript">
	function toggleMenu() {
		if ($(formIsOpen).attr("value") == "false") {
			$.get("navbar", "FORM_IS_OPEN=true", null);
			$(formIsOpen).attr("value", "true");
		} else {
			$.get("navbar", "FORM_IS_OPEN=false", null);
			$(formIsOpen).attr("value", "false");
		}
		$(searchForm).toggle('normal');
	}

	$(document).ready(function() {
		if ($(formIsOpen).attr("value") == "false") {
			$(searchForm).hide();
		}
	});
</script>

