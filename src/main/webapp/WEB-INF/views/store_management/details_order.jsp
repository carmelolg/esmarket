<!DOCTYPE html>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
<link href="themes/css/default.css" rel="stylesheet" media="screen" />
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

<script type="text/javascript">
	function accept(obj) {
		if (document.getElementById(obj).style.display == 'none') {
			document.getElementById(obj).style.display = '';
		} else {
			document.getElementById(obj).style.display = 'none';
		}
	}
</script>
<script src="themes/js/jquery_default.js" type="text/javascript"></script>
<script src="themes/js/jquery.js" type="text/javascript"></script>
</head>

<body>
	<jsp:include page="../jspFragments/navbar.jsp"></jsp:include>

	<!-- Header End====================================================================== -->


	<div id="mainBody">
		<div class="container">
			<div class="row">

				<!-- Sidebar left -->
				<c:choose>
					<c:when
						test="${sessionScope.user.getClass().getName() ne 'it.unical.esmarket.domain.account.Client'}">
						<jsp:include page="menu_admin.jsp"></jsp:include>
					</c:when>
					<c:otherwise>
						<div id="sidebar" class="span3">
							<ul id="sideManu" class="nav nav-tabs nav-stacked">
								<li class="subMenu open"><a>ORDERS</a>
									<ul>
										<li><a href='<c:url value="listOfClientOrders"/>'><i
												class="icon-chevron-right"></i>My orders list</a></li>
									</ul></li>
							</ul>
							<br />
						</div>
					</c:otherwise>
				</c:choose>
				<!-- Siderbar left end
				
				 -->
				<div class="span9">

					<!-- cronologia pagine -->
					<ul class="breadcrumb">
						<li><a
							href="${list_of_orders}?idUser=${sessionScope.user.ID}">List
								of Orders</a><span class="divider"><i
								class="icon-arrow-right"></i></span></li>
						<li class="active">Details order ${order.ID}</li>
					</ul>
					<!-- end cronologia -->

					<div class="well well-small">
						<div class="store_table">
							<table>
								<tr>
									<td>Data</td>
									<td>Value</td>
									<td>Additional information</td>
								</tr>
								<tr>
									<td><i class="icon-inbox"></i>&nbsp;Order</td>
									<td>${order.ID}</td>
									<td></td>
								</tr>
								<tr>
									<td><i class="icon-user"></i>&nbsp;Client</td>
									<td>${order.orderOwner.username}</td>
									<td></td>
								</tr>
								<tr>
									<td><i class="icon-user"></i>&nbsp;Manager</td>
									<td>${order.orderManager.username}</td>
									<td></td>
								</tr>
								<tr>
									<td><i class="icon-time"></i>&nbsp;Request Date</td>
									<td>${order.requestDate}</td>
									<td></td>
								</tr>
								<tr>
									<td><i class="icon-wrench"></i>&nbsp;Status</td>
									<td>${order.status}</td>
									<td></td>
								</tr>
								<tr>
									<td><i class="icon-wrench"></i>&nbsp;Total price</td>
									<td>${order.totalPrice}</td>
									<td></td>
								</tr>
								<tr>
									<td><i class="icon-map-marker"></i>&nbsp;Position</td>
									<td>${order.position.address}<i class="icon-arrow-right"></i>
										${order.position.city} <i class="icon-arrow-right"></i>
										${order.position.region}
									</td>
									<td></td>
								</tr>
								<tr>
									<td><i class="icon-map-marker"></i>&nbsp;Destination
										Address</td>
									<td>${order.destinationAddress.address}<i
										class="icon-arrow-right"></i> ${order.destinationAddress.city}
										<i class="icon-arrow-right"></i>
										${order.destinationAddress.region} <i class="icon-arrow-right"></i>
										${order.destinationAddress.state} <i class="icon-arrow-right"></i>
										${order.destinationAddress.zipCode}
									</td>
									<td></td>
								</tr>
								<tr>
									<td><i class="icon-wrench"></i>&nbsp;List of items</td>
									<td>${order.items.size()}</td>
									<td><a href="javascript:void(0)"
										onclick="accept('animation');"><i class="icon-search"></i>Details</a></td>
								</tr>

							</table>
						</div>
						<!-- Content -->
					</div>
					<div id="animation" style="display: none;">
						<div class="well well-small">
							<div class="store_table">
								<table>
									<tr>
										<td>Product name</td>
										<td>Quantity</td>
										<td>Details</td>
									</tr>

									<c:forEach var="item" items="${order.items}">
										<tr>
											<td>${item.product.name }</td>
											<td>${item.quantity }</td>
											<c:choose>
												<c:when test="${sessionScope.user.getClass().getName() eq 'it.unical.esmarket.domain.account.Client'}">
													<td><c:url value="product_details" var="details_prod" />
														<a href="${details_prod}?PRODUCT_ID=${item.product.ID}"><i
															class="icon-search"></i> Details</a></td>
												</c:when>
												<c:otherwise>
													<td><c:url value="details_prod" var="details_prod" />
														<a
														href="${details_prod}?idProd=${item.ID}&storeID=${storeID}"><i
															class="icon-search"></i> Details</a></td>
												</c:otherwise>
											</c:choose>
										</tr>
									</c:forEach>
								</table>
							</div>
						</div>
					</div>
					<!-- Additionale info -->
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

</body>
</html>