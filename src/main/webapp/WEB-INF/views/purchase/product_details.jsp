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
						<li><a href='<c:url value="/"></c:url>'>Home</a> <span
							class="divider">/</span></li>
						<li><a
							href='<c:url value="/products?CATEGORY=${product.category}"></c:url>'>Products</a>
							<span class="divider">/</span></li>
						<li class="active">product Details</li>
					</ul>
					<div class="row">
						<div id="gallery" class="span3">
							<a href="${product.image }" title="${product.name }"> <img
								src="${product.image }" style="width: 100%"
								alt="${product.name }" />
							</a>
						</div>
						<div class="span6">
							<h3>${product.name}</h3>
							<hr class="soft" />
							<form class="form-horizontal qtyFrm" method="POST"
								action='<c:url value="/product_details"></c:url>'>
								<div class="control-group">
									<label class="control-label"><span>
											$${product.unitPrice}</span></label>
									<div class="controls">
										<input type="number" class="span1" placeholder="Qty."
											value="1" name="QUANTITY" />
										<button type="submit"
											class="btn btn-large btn-primary pull-right">
											Add to cart <i class=" icon-shopping-cart"></i>
										</button>
									</div>
								</div>
								<div>Discount: ${product.promotion.discount}%</div>
								<div style="color: red;">${errorMessage}</div>
								<input type="hidden" name="PRODUCT_ID" value="${product.ID}">
							</form>

							<hr class="soft" />
							<h4>${product.quantity }itemsinstock</h4>
							<hr class="soft clr" />
							<p>${product.description }</p>
							<a class="btn btn-small pull-right" href="#detail">More
								Details</a> <br class="clr" /> <a href="#" name="detail"></a>
							<hr class="soft" />
						</div>
						<div class="span9">
							<ul id="productDetail" class="nav nav-tabs">
								<li class="active"><a href="#home" data-toggle="tab">Product
										Details</a></li>
								<li><a href="#profile" data-toggle="tab">Related
										Products</a></li>
							</ul>
							<div id="myTabContent" class="tab-content">
								<div class="tab-pane fade active in" id="home">
									<h4>Product Information</h4>
									<table class="table table-bordered">
										<tbody>
											<tr class="techSpecRow">
												<th colspan="2">Product Details</th>
											</tr>
											<tr class="techSpecRow">
												<td class="techSpecTD1">Brand:</td>
												<td class="techSpecTD2">${product.brand }</td>
											</tr>
											<tr class="techSpecRow">
												<td class="techSpecTD1">Model:</td>
												<td class="techSpecTD2">${product.name }</td>
											</tr>
											<tr class="techSpecRow">
												<td class="techSpecTD1">Released on:</td>
												<td class="techSpecTD2">${product.productDate }</td>
											</tr>
										</tbody>
									</table>

									<h4>
										Users Reviews
										<c:if test="${currentUserReview ne null}">
											<button class="btn btn-small pull-right" id="reviewButton">Review
												this product</button>
										</c:if>
									</h4>
									<c:if test="${currentUserReview ne null}">
										<div id="reviewTextArea">
											<form action="product_details" method="post">
												<textarea rows="5" style="width: 98%;" name="REVIEW_TEXT">${currentUserReview.reviewText}</textarea>
												<input type="hidden" name="PRODUCT_ID" value="${product.ID}">
												<input class="btn btn-large btn-success" type="submit"
													value="Submit review" />
											</form>
										</div>
									</c:if>
									<c:if test="${fn:length(productReviews)==0}">
										<table class="table table-bordered">
											<tbody>
												<tr class="techSpecRow">
													<th colspan="2">0 Reviews for this product</th>
												</tr>
											</tbody>
										</table>
									</c:if>
									<c:forEach var="review" items="${productReviews}">
										<table class="table table-bordered">
											<tbody>
												<tr class="techSpecRow">
													<th colspan="2">${review.author.username}<c:if
															test="${isAdmin ne null}">
															<form action="deleteReview" method="post">
																<input type="hidden" value="${review.ID}"
																	name="REVIEW_ID"> <input type="hidden"
																	name="PRODUCT_ID" value="${product.ID}"> <input
																	class="btn btn-small pull-right" type="submit"
																	value="Delete review">
															</form>
														</c:if>
													</th>
												</tr>
												<tr class="techSpecRow">
													<td class="techSpecTD1">${review.reviewText}</td>
												</tr>
											</tbody>
										</table>
									</c:forEach>
								</div>
								<div class="tab-pane fade" id="profile">
									<div id="myTab" class="pull-right">
										<a href="#listView" data-toggle="tab"><span
											class="btn btn-large"><i class="icon-list"></i></span></a> <a
											href="#blockView" data-toggle="tab"><span
											class="btn btn-large btn-primary"><i
												class="icon-th-large"></i></span></a>
									</div>
									<br class="clr" />
									<hr class="soft" />
									<div class="tab-content">
										<div class="tab-pane" id="listView">
											<c:forEach var="product" items="${relatedProducts }">
												<div class="row">
													<div class="span2">
														<img src="${product.image }" alt="" />
													</div>
													<div class="span4">
														<h3>New | Available</h3>
														<hr class="soft" />
														<h5>${product.name}</h5>
														<p>${product.description }</p>
														<a class="btn btn-small pull-right"
															href="<s:url value="/product_details?PRODUCT_ID=${product.ID }"/>">View
															Details</a> <br class="clr" />
													</div>
													<div class="span3 alignR">
														<form class="form-horizontal qtyFrm">
															<h3>$${product.unitPrice}</h3>
															<div class="btn-group">
																<a
																	href="<s:url value="/product_details?PRODUCT_ID=${product.ID }"/>"
																	class="btn btn-large btn-primary"> Add to <i
																	class=" icon-shopping-cart"></i></a> <a
																	href="<s:url value="/product_details?PRODUCT_ID=${product.ID }"/>"
																	class="btn btn-large"><i class="icon-zoom-in"></i></a>
															</div>
														</form>
													</div>
												</div>
												<hr class="soft" />
											</c:forEach>
										</div>
										<div class="tab-pane active" id="blockView">
											<ul class="thumbnails">
												<c:forEach var="product" items="${relatedProducts}">
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
																	</a> <a class="btn" href="#">Add to <i
																		class="icon-shopping-cart"></i>
																	</a> <a class="btn btn-primary" href="#">$${product.unitPrice
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
								</div>
								<br class="clr">
							</div>
						</div>
					</div>
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
	<script src="themes/js/review.js"></script>

	<span id="themesBtn"></span>
</body>
</html>