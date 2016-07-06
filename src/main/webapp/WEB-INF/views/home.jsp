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
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>


</head>

<body>
	<jsp:include page="jspFragments/navbar.jsp"></jsp:include>

	<!-- Header End====================================================================== -->
	<c:if test="${sessionScope.user eq null}">
		<div id="carouselBlk">
			<div id="myCarousel" class="carousel slide">
				<div class="carousel-inner">
					<div class="item active">
						<div class="container">
							<a href='<c:url value="register"></c:url>'><img
								style="width: 100%" src="themes/images/carousel/2.png" alt="" /></a>
						</div>
					</div>
					<div class="item">
						<div class="container">
							<a href='<c:url value="register"></c:url>'><img
								src="themes/images/carousel/3.png" alt="" /></a>
						</div>
					</div>
					<div class="item">
						<div class="container">
							<a href='<c:url value="register"></c:url>'><img
								src="themes/images/carousel/4.png" alt="" /></a>
						</div>
					</div>
					<div class="item">
						<div class="container">
							<a href='<c:url value="register"></c:url>'><img
								src="themes/images/carousel/5.png" alt="" /></a>
						</div>
					</div>
					<div class="item">
						<div class="container">
							<a href='<c:url value="register"></c:url>'><img
								src="themes/images/carousel/6.png" alt="" /></a>
						</div>
					</div>
				</div>
				<a class="left carousel-control" href="#myCarousel"
					data-slide="prev">&lsaquo;</a> <a class="right carousel-control"
					href="#myCarousel" data-slide="next">&rsaquo;</a>
			</div>
		</div>
	</c:if>
	<div id="mainBody">
		<div class="container">
			<div class="row">
				<!-- Sidebar ================================================== -->
				<jsp:include page="jspFragments/leftSidebar.jsp"></jsp:include>
				<!-- Sidebar end=============================================== -->
				<div class="span9">
					<div class="well well-small">
						<div class="row-fluid">
							<div id="featured" class="carousel slide">
								<div class="carousel-inner">
									<c:forEach var="productList" items="${featuredProducts}">
										<div class="item">
											<ul class="thumbnails">
												<c:forEach var="product" items="${productList}">
													<li class="span3">
														<div class="thumbnail">
															<i class="tag"></i> <a
																href="<s:url value="/product_details?PRODUCT_ID=${product.ID }"/>"><img
																src="${product.image}" alt=""></a>
															<div class="caption">
																<h5>${product.name}</h5>
																<h4>
																	<a class="btn"
																		href="<s:url value="/product_details?PRODUCT_ID=${product.ID }"/>">VIEW</a>
																	<span class="pull-right">${product.unitPrice }</span>
																</h4>
															</div>
														</div>
													</li>
												</c:forEach>
											</ul>
										</div>
									</c:forEach>
								</div>
								<a class="left carousel-control" href="#featured"
									data-slide="prev">‹</a> <a class="right carousel-control"
									href="#featured" data-slide="next">›</a>
							</div>
						</div>
					</div>
					<h4>Latest Products</h4>
					<ul class="thumbnails">
						<c:forEach var="lastProduct" items="${latestProducts}">
							<li class="span3">
								<div class="thumbnail">
									<a
										href="<s:url value="/product_details?PRODUCT_ID=${lastProduct.ID }"/>"><img
										src="${lastProduct.image }" alt="" /></a>
									<div class="caption">
										<h5>${lastProduct.name }</h5>
										<p>${fn:substring(lastProduct.description, 0, 30)}</p>

										<h4 style="text-align: center">
											<a class="btn"
												href="<s:url value="/product_details?PRODUCT_ID=${lastProduct.ID }"/>">
												<i class="icon-zoom-in"></i>
											</a> <a class="btn"
												href="<s:url value="/product_details?PRODUCT_ID=${lastProduct.ID }"/>">Add
												to <i class="icon-shopping-cart"></i>
											</a> <a class="btn btn-primary"
												href="<s:url value="/product_details?PRODUCT_ID=${lastProduct.ID }"/>">${lastProduct.unitPrice }</a>
										</h4>
									</div>
								</div>
							</li>
						</c:forEach>
					</ul>

				</div>
			</div>
		</div>
	</div>
	<!-- Footer ================================================================== -->
	<jsp:include page="jspFragments/footer.jsp"></jsp:include>
	<!-- Placed at the end of the document so the pages load faster ============================================= -->
	<script src="themes/js/jquery.js" type="text/javascript"></script>
	<script src="themes/js/bootstrap.min.js" type="text/javascript"></script>
	<script src="themes/js/google-code-prettify/prettify.js"></script>
	<script src="themes/js/bootshop.js"></script>
	<script src="themes/js/jquery.lightbox-0.5.js"></script>
	<span id="themesBtn"></span>
</body>
</html>