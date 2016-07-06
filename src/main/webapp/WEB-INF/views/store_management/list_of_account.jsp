<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
</head>

<body>
	<jsp:include page="../jspFragments/navbar.jsp"></jsp:include>

	<!-- Header End====================================================================== -->


	<div id="mainBody">
		<div class="container">
			<div class="row">

				<!-- Sidebar left -->
				<jsp:include page="menu_admin.jsp"></jsp:include>
				<!-- Sidebar end -->

				<div class="span9">
				
				<!-- cronologia pagine -->
				<ul class="breadcrumb">
					<li><a href="<c:url value="/"/>">Home</a><span class="divider"><i class="icon-arrow-right"></i></span></li>
					<li class="active">List of accounts</li>
				</ul>
				
				<ul class="breadcrumb">
					<li>Legend</li>
					<li><i class="icon-arrow-right"></i></li>
					<li><i class="icon-home"></i> Manager <i class="icon-user"></i> Client</li>
				</ul>
				<!-- end cronologia -->
				
					<h4>List of accounts</h4>
					<ul class="thumbnails">
						<c:forEach var="account" items="${listOfAccount}">
							<li class="span3">
								<div class="thumbnail">
									<a href=""><img
										src="themes/images/ico/apple-touch-icon-57-precomposed.png"
										alt="" /></a>
									<div class="caption">
										<h5>${account.username}</h5>
										<h4 style="text-align: center">
											<c:url value="details_account" var="details_account" />
											<a href="${details_account}?id=${account.ID}"><i
												class="icon-search"></i></a>
											<c:if test="${account.getClass().name == 'it.unical.esmarket.domain.account.Client'}">
											<c:url value="remove_account" var="remove_account" />
											<a href="${remove_account}?id=${account.ID}"><i
												class="icon-trash"></i></a>
											</c:if>
											<c:url value="set_manager_account" var="set_manager_account" />
											<a href="${set_manager_account}?id=${account.ID}">
											
											<c:if test="${account.getClass().name == 'it.unical.esmarket.domain.account.Client'}">
											<i class="icon-user"></i>
											</c:if>
											<c:if test="${account.getClass().name == 'it.unical.esmarket.domain.account.ManagerShop'}">
											<i class="icon-home"></i>
											</c:if>
											</a>
										
										</h4>
									</div>
								</div>
							</li>
						</c:forEach>
						<c:if test="${fn:length(listOfAccount)==0}">
							<h4>
								<center>List of account empty</center>
							</h4>
						</c:if>

					</ul>
				</div>
				<!-- PRODUCT LIST -->
			</div>
			<!-- BEST OFFERS -->
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