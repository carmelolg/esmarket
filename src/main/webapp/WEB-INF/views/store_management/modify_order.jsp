<!DOCTYPE html>
<html lang="en">

<head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
</head>

<body>
	<jsp:include page="../jspFragments/navbar.jsp"></jsp:include>

	<!-- Header End====================================================================== -->


	<div id="mainBody">
		<div class="container">
			<div class="row">

				<!-- Sidebar left -->
				<c:choose>
					<c:when test="${sessionScope.user.getClass().getName() ne 'it.unical.esmarket.domain.account.Client'}">
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
				<!-- Sidebar end -->

				<div class="span9">

					<!-- cronologia pagine -->
					<ul class="breadcrumb">
						<li><c:url value="list_of_orders" var="list_of_orders" /> <a
							href="${list_of_orders}?idUser=${sessionScope.user.ID}"><i
								class="icon-chevron-right"></i>List of orders</a><span
							class="divider"><i class="icon-arrow-right"></i></span></li>
						<li class="active">Modify order <span class="divider"></span>
							${idOrd}
						</li>
					</ul>
					<!-- end cronologia -->

					<div class="well well-small">
						<sf:form action="modify_order" modelAttribute="order"
							method="POST">

							<input type="hidden" name="idOrd" value="${order.ID}" />


							<sf:input path="ID" type="hidden" value="${order.ID}" />
							<sf:input path="status" type="hidden" value="${order.status}" />
							<sf:input path="totalPrice" type="hidden"
								value="${order.totalPrice}" />
							
								Address <br>
							<br>
							<sf:input path="destinationAddress.address" type="text"
								value="${destinationAddress.address}" />
							<br>
								City <br>
							<sf:input path="destinationAddress.city" type="text"
								value="${destinationAddress.city}" />
							<br>
								Region<br>
							<br>
							<sf:input path="destinationAddress.region" type="text"
								value="${destinationAddress.region}" />
							<br>
								State <br>
							<br>
							<sf:input path="destinationAddress.state" type="text"
								value="${destinationAddress.state}" />
							<br>
								Zip Code<br>
							<sf:errors path="destinationAddress.zipCode" style="color:red"></sf:errors>
							<br>
							<sf:input path="destinationAddress.zipCode" type="text"
								value="${destinationAddress.zipCode}" />
							<br>
							<sf:button type="submit" class="btn btn-primary">Modify Address</sf:button>
						</sf:form>
						<!-- PRODUCT LIST -->
					</div>
					<!-- BEST OFFERS -->
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