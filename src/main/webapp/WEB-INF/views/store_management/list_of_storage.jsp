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
						<li><a href="<c:url value="/"/>">Home</a><span
							class="divider"><i class="icon-arrow-right"></i></span></li>
						<li class="active">List of storages</li>
					</ul>
					<!-- end cronologia -->

					<h4>List of storage</h4>
					<ul class="thumbnails">
						<c:forEach var="store" items="${listOfStores}">
							<li class="span3">
								<div class="thumbnail">
									<img
										src="themes/images/ico/apple-touch-icon-57-precomposed.png"
										alt="" />
									<div class="caption">
										<h5>${store.name}</h5>
										<h4 style="text-align: center">
											<c:url value="details" var="store_management" />
											<a href="${store_management}?id=${store.ID}"><i
												class="icon-search"></i></a>
											<%-- <c:url value="remove" var="remove" />
											<a href="${remove}?id=${store.ID}"><i class="icon-trash"></i></a> --%>
											<c:url value="modify" var="modify" />
												<a
												href="${modify}?id=${store.ID}&idAccount=${store.account.ID}"><i
												class="icon-edit"></i></a>
										</h4>
									</div>
								</div>
							</li>
						</c:forEach>
						<c:if test="${fn:length(listOfStores)==0}">
							<h4>
								<center>List of storage empty</center>
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