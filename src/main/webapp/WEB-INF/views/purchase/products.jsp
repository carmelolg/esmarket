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
						<li class="active">${category }</li>
					</ul>
					<h3>
						${category } <small class="pull-right">
							${categories[category]} products are available </small>
					</h3>
					<hr class="soft" />
					<hr class="soft" />
					<form class="form-horizontal span6"
						action="<s:url value="/products"/>">
						<div class="control-group">
							<label class="control-label alignL">Sort By </label> <select
								name="ORDER_OPTION" onchange="this.form.submit()">
								<c:forEach var="option" items="${comboBoxOptions}">
									<c:if test="${option.key!=orderOption}">
										<option value="${option.key}">${option.value}</option>
									</c:if>
									<c:if test="${option.key==orderOption}">
										<option value="${option.key}" selected="selected">${option.value}</option>
									</c:if>
								</c:forEach>
							</select>
						</div>
					</form>

					<div id="myTab" class="pull-right">
						<a href="#listView" data-toggle="tab"><span
							class="btn btn-large"><i class="icon-list"></i></span></a> <a
							href="#blockView" data-toggle="tab"><span
							class="btn btn-large btn-primary"><i class="icon-th-large"></i></span></a>
					</div>
					<br class="clr" />
					<div class="tab-content">
						<div class="tab-pane" id="listView">
							<c:forEach var="product" items="${products}">
								<hr class="soft" />
								<div class="row">
									<div class="span2">
										<img src="${product.image }" alt="" />
									</div>
									<div class="span4">
										<h3>New | Available</h3>
										<hr class="soft" />
										<h5>${product.name }</h5>
										<p>${product.description }</p>
										<a class="btn btn-small pull-right"
											href="<s:url value="/product_details?PRODUCT_ID=${product.ID }"/>">View
											Details</a> <br class="clr" />
									</div>
									<div class="span3 alignR">
										<form class="form-horizontal qtyFrm">
											<h3>$${product.unitPrice}</h3>
											<br /> <a
												href="<s:url value="/product_details?PRODUCT_ID=${product.ID }"/>"
												class="btn btn-large btn-primary"> Add to <i
												class=" icon-shopping-cart"></i></a> <a
												href="<s:url value="/product_details?PRODUCT_ID=${product.ID }"/>"
												class="btn btn-large"><i class="icon-zoom-in"></i></a>

										</form>
									</div>
								</div>
							</c:forEach>
							<hr class="soft" />
						</div>

						<div class="tab-pane  active" id="blockView">
							<ul class="thumbnails">
								<c:forEach var="product" items="${products }">
									<li class="span3">
										<div class="thumbnail">
											<a
												href="<s:url value="/product_details?PRODUCT_ID=${product.ID }"/>"><img
												src="${product.image }" alt="" /></a>
											<div class="caption">
												<h5>${product.name }</h5>
												<p>${fn:substring(product.description, 0, 30)}</p>
												<h4 style="text-align: center">
													<a class="btn"
														href="<s:url value="/product_details?PRODUCT_ID=${product.ID }"/>">
														<i class="icon-zoom-in"></i>
													</a> <a class="btn"
														href="<s:url value="/product_details?PRODUCT_ID=${product.ID }"/>">Add
														to <i class="icon-shopping-cart"></i>
													</a> <a class="btn btn-primary"
														href="<s:url value="/product_details?PRODUCT_ID=${product.ID }"/>">$${product.unitPrice
														}</a>
												</h4>
											</div>
										</div>
									</li>
								</c:forEach>
							</ul>
							<hr class="soft" />
						</div>
					</div>

					<div class="pagination">
						<ul>
							<li><a
								href="<s:url value="/products?PAGE_NUM=${backIndex}"/>">&lsaquo;</a></li>
							<c:forEach var="index" items="${pagesIndex}">
								<li><a href="<s:url value="/products?PAGE_NUM=${index}"/>">${index}</a></li>
							</c:forEach>
							<li><a
								href="<s:url value="/products?PAGE_NUM=${forwIndex}"/>">&rsaquo;</a></li>
						</ul>
					</div>
					<br class="clr" />
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

	<span id="themesBtn"></span>
</body>
</html>