<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<title>eSmarket 1.0</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<!--Less styles -->
<!-- Other Less css file //different less files has different color scheam
	<link rel="stylesheet/less" type="text/css" href="themes/less/simplex.less">
	<link rel="stylesheet/less" type="text/css" href="themes/less/classified.less">
	<link rel="stylesheet/less" type="text/css" href="themes/less/amelia.less">  MOVE DOWN TO activate
	-->
<!--<link rel="stylesheet/less" type="text/css" href="themes/less/bootshop.less">
	<script src="themes/js/less.js" type="text/javascript"></script> -->

<!-- Bootstrap style -->
<link id="callCss" rel="stylesheet"
	href="themes/bootshop/bootstrap.min.css" media="screen" />
<link href="themes/css/base.css" rel="stylesheet" media="screen" />
<!-- Bootstrap style responsive -->
<link href="themes/css/bootstrap-responsive.min.css" rel="stylesheet" />
<link href="themes/css/font-awesome.css" rel="stylesheet"
	type="text/css">
<!-- Google-code-prettify -->
<link href="themes/js/google-code-prettify/prettify.css"
	rel="stylesheet" />
<!-- fav and touch icons -->
<link rel="shortcut icon" href="themes/images/ico/favicon.ico">
<link rel="apple-touch-icon-precomposed" sizes="144x144"
	href="themes/images/ico/apple-touch-icon-144-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="114x114"
	href="themes/images/ico/apple-touch-icon-114-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="72x72"
	href="themes/images/ico/apple-touch-icon-72-precomposed.png">
<link rel="apple-touch-icon-precomposed"
	href="themes/images/ico/apple-touch-icon-57-precomposed.png">
<style type="text/css" id="enject"></style>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


</head>

<body>
	<jsp:include page="../jspFragments/navbar.jsp"></jsp:include>
	<!-- Header End====================================================================== -->
	<div id="mainBody">
		<div class="container">
			<div class="row">
				<!-- Sidebar ================================================== -->
				<jsp:include page="../jspFragments/leftSidebar.jsp"></jsp:include>
				<!-- Sidebar end=============================================== -->
				<div class="span9">
					<ul class="breadcrumb">
						<li><a href="index.html">Home</a> <span class="divider">/</span></li>
						<li class="active">SHOPPING CART</li>
					</ul>
					<h3>
						SHOPPING CART [ <small>${fn:length(sessionScope.order.items)}
							Item(s) </small>]<a href='<c:url value="products?CATEGORY=all"></c:url>'
							class="btn btn-large pull-right"><i class="icon-arrow-left"></i>
							Continue Shopping </a>
					</h3>
					<hr class="soft" />
					<c:if test="${sessionScope.user eq null }">
						<table class="table table-bordered">
							<tr>
								<th>SIGN IN</th>
							</tr>
							<tr>
								<td>
									<form id="loginForm2" class="form-horizontal">
										<div class="control-group">
											<label class="control-label" for="inputEmail2">Email</label>
											<div class="controls">
												<input type="text" id="inputEmail2" placeholder="Email">
											</div>
										</div>
										<div class="control-group">
											<label class="control-label" for="inputPassword2">Password</label>
											<div class="controls">
												<input type="password" id="inputPassword2"
													placeholder="Password">
											</div>
										</div>
										<div style="color: red" id="errorMessage2"></div>
										<div class="control-group">
											<div class="controls">
												<button type="submit" class="btn">Sign in</button>
												OR <a href="register" class="btn">Register Now!</a>
											</div>
										</div>
									</form>
								</td>
							</tr>
						</table>
					</c:if>

					<table class="table table-bordered">
						<thead>
							<tr>
								<th>Product</th>
								<th>Description</th>
								<th>Quantity/Update</th>
								<th>Price</th>
								<th>Discount</th>
								<th>Total</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="item" items="${sessionScope.order.items}"
								varStatus="loop">
								<tr>
									<td><img width="60" src="${item.product.image}" alt="" /></td>
									<td>${item.product.name}<br />${fn:substring(item.product.description, 0, 30)}</td>
									<td>
										<div class="input-append">
											<input class="span1" style="max-width: 34px" placeholder="1"
												id="appendedInputButtons" size="16" type="text"
												value="${item.quantity}"> <a class="btn"
												type="button"
												href='<c:url value="/product_summary?QUANTITY=${item.quantity-1}&ITEM_POS=${loop.index}"></c:url>'>
												<i class="icon-minus"></i>
											</a> <a class="btn" type="button"
												href='<c:url value="/product_summary?QUANTITY=${item.quantity+1}&ITEM_POS=${loop.index}"></c:url>'>
												<i class="icon-plus"></i>
											</a> <a class="btn btn-danger" type="button"
												href='<c:url value="/product_summary?QUANTITY=${0}&ITEM_POS=${loop.index}"></c:url>'>
												<i class="icon-remove icon-white"></i>
											</a>
										</div>
									</td>
									<td>$${item.product.unitPrice}</td>
									<td>${item.product.promotion.discount}%</td>
									<td>$${item.product.getUnitPrice()*item.quantity}</td>
								</tr>
							</c:forEach>
							<tr>
								<td colspan="5" style="text-align: right">Total Price:</td>
								<td>$${sessionScope.order.totalPrice }</td>
							</tr>
							<tr>
								<td colspan="5" style="text-align: right">Total Discount:</td>
								<td>$${sessionScope.order.getTotalDiscount() }</td>
							</tr>
							<tr>
								<td colspan="5" style="text-align: right"><strong>TOTAL
										($${sessionScope.order.totalPrice } -
										$${sessionScope.order.getTotalDiscount()}) =</strong></td>
								<td class="label label-important" style="display: block"><strong>
										${sessionScope.order.totalPrice-sessionScope.order.getTotalDiscount()}
								</strong></td>
							</tr>
						</tbody>
					</table>
					<a href='<c:url value="products?CATEGORY=all"></c:url>'
						class="btn btn-large"><i class="icon-arrow-left"></i> Continue
						Shopping </a>
					<c:choose>
						<c:when test="${sessionScope.user eq null }">
							<c:choose>
								<c:when test="${fn:length(sessionScope.order.items)>0}">
									<a href='<c:url value="loginPage"></c:url>'
										class="btn btn-large pull-right">Next <i
										class="icon-arrow-right"></i>
									</a>
								</c:when>
								<c:otherwise>
									<a href='<c:url value="products?CATEGORY=all"></c:url>'
										class="btn btn-large pull-right"><i
										class="icon-arrow-left"></i> Continue Shopping </a>
								</c:otherwise>
							</c:choose>
						</c:when>
						<c:otherwise>
							<c:choose>
								<c:when test="${fn:length(sessionScope.order.items)>0 && sessionScope.user.getClass().name eq 'it.unical.esmarket.domain.account.Client'}">
									<a href='<c:url value="purchase"></c:url>'
										class="btn btn-large pull-right">Next <i
										class="icon-arrow-right"></i>
									</a>
								</c:when>
								<c:otherwise>
									<a href='<c:url value="products?CATEGORY=all"></c:url>'
										class="btn btn-large pull-right"><i
										class="icon-arrow-left"></i> Continue Shopping </a>
								</c:otherwise>
							</c:choose>
						</c:otherwise>
					</c:choose>

				</div>
			</div>
		</div>
	</div>
	<!-- Footer ================================================================== -->
	<jsp:include page="../jspFragments/footer.jsp"></jsp:include>
	<!-- Placed at the end of the document so the pages load faster ============================================= -->
	<script src="themes/js/jquery.js" type="text/javascript"></script>
	<script src="themes/js/bootstrap.min.js" type="text/javascript"></script>
	<script src="themes/js/google-code-prettify/prettify.js"></script>
	<script src="themes/js/bootshop.js"></script>
	<script src="themes/js/jquery.lightbox-0.5.js"></script>


	<script type="text/javascript">
		function getLoginResponse(response) {
			if (response == 'false') {
				$(errorMessage2).text(
						"There is no account with these credentials");
				$(inputPassword2).attr("value", "");
			} else {
				window.location = window.location;
			}
		}

		function submitLoginForm() {
			var mail = $(inputEmail2).attr("value");
			var password = $(inputPassword2).attr("value");
			$.post("login", "mail=" + mail + "&password=" + password,
					getLoginResponse);
		}

		$(document).ready(function() {
			$(loginForm2).submit(function(e) {
				e.preventDefault(e);
				submitLoginForm();
			});
		});
	</script>


	<span id="themesBtn"></span>
</body>
</html>