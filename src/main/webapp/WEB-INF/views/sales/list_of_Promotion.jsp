<!DOCTYPE html>
<html lang="en">


<head>
<meta charset="utf-8">
<title>eSmarket 1.0</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">

<link id="callCss" rel="stylesheet"
	href="themes/bootshop/bootstrap.min.css" media="screen" />
<link href="themes/css/base.css" rel="stylesheet" media="screen" />

<link href="themes/css/bootstrap-responsive.min.css" rel="stylesheet" />
<link href="themes/css/font-awesome.css" rel="stylesheet"
	type="text/css">

<link href="themes/js/google-code-prettify/prettify.css"
	rel="stylesheet" />
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
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
</head>

<body>
	<jsp:include page="../jspFragments/navbar.jsp"></jsp:include>

	<!-- Header End====================================================================== -->

	<div id="mainBody">
		<div class="container">
			<div class="row">
				<!-- Sidebar ================================================== -->
				<jsp:include page="../store_management/menu_admin.jsp"></jsp:include>
				<!-- Sidebar end=============================================== -->
				<div class="span9">

					<!-- cronologia pagine -->
					<ul class="breadcrumb">
						<li><a href="<c:url value="/"/>">Home</a><span
							class="divider"><i class="icon-arrow-right"></i></span></li>
						<li class="active">List of promotion</li>
					</ul>
					<!-- end cronologia -->


					<h4>List of Promotion</h4>
					<ul class="thumbnails">

						<c:forEach var="promotion" items="${promo}">


							<li class="span3">
								<div class="thumbnail">
									<table class="table table-bordered">
										<tbody>

											<tr class="techSpecRow">
												<td class="techSpecTD1">ID:</td>
												<td class="techSpecTD2">${promotion.ID }</td>
											</tr>
											<tr class="techSpecRow">
												<td class="techSpecTD1">Start Promo:</td>
												<td class="techSpecTD2"><fmt:formatDate
														value="${promotion.startPromo}" pattern="dd-MM-yyyy" /></td>
											</tr>
											<tr class="techSpecRow">
												<td class="techSpecTD1">End Promo:</td>
												<td class="techSpecTD2"><fmt:formatDate
														value="${promotion.endPromo}" pattern="dd-MM-yyyy" /></td>
											</tr>
										</tbody>
									</table>
									<h4 style="text-align: center">
										<a class="btn"
											href="<c:url value="detailsPromo?idPromo=${promotion.ID}"/>"><i
											class="icon-zoom-in"></i></a> <a class="btn"
											href="<c:url value="modifyPromotion?idPromo=${promotion.ID}"/>">
											<i class="icon-edit"></i>
										</a> <a class="btn"
											href="<c:url value="deletePromotion?idPromo=${promotion.ID}"/>">
											<i class="icon-trash"></i>
										</a>
									</h4>
								</div>
							</li>
						</c:forEach>

					</ul>

				</div>
			</div>
		</div>
	</div>
	<!-- Footer ================================================================== -->
	<div id="footerSection">
		<div class="container">
			<div class="row">
				<div class="span3">
					<h5>ACCOUNT</h5>
					<a href="login.html">YOUR ACCOUNT</a> <a href="login.html">PERSONAL
						INFORMATION</a> <a href="login.html">ADDRESSES</a> <a
						href="login.html">DISCOUNT</a> <a href="login.html">ORDER
						HISTORY</a>
				</div>
				<div class="span3">
					<h5>INFORMATION</h5>
					<a href="contact.html">CONTACT</a> <a href="register.html">REGISTRATION</a>
					<a href="legal_notice.html">LEGAL NOTICE</a> <a href="tac.html">TERMS
						AND CONDITIONS</a> <a href="faq.html">FAQ</a>
				</div>
				<div class="span3">
					<h5>OUR OFFERS</h5>
					<a href="#">NEW PRODUCTS</a> <a href="#">TOP SELLERS</a> <a
						href="special_offer.html">SPECIAL OFFERS</a> <a href="#">MANUFACTURERS</a>
					<a href="#">SUPPLIERS</a>
				</div>
				<div id="socialMedia" class="span3 pull-right">
					<h5>SOCIAL MEDIA</h5>
					<a href="#"><img width="60" height="60"
						src="themes/images/facebook.png" title="facebook" alt="facebook" /></a>
					<a href="#"><img width="60" height="60"
						src="themes/images/twitter.png" title="twitter" alt="twitter" /></a>
					<a href="#"><img width="60" height="60"
						src="themes/images/youtube.png" title="youtube" alt="youtube" /></a>
				</div>
			</div>
			<p class="pull-right">&copy; eSmarket</p>
		</div>
		<!-- Container End -->
	</div>
	<!-- Placed at the end of the document so the pages load faster ============================================= -->
	<script src="themes/js/jquery.js" type="text/javascript"></script>
	<script src="themes/js/bootstrap.min.js" type="text/javascript"></script>
	<script src="themes/js/google-code-prettify/prettify.js"></script>

	<script src="themes/js/bootshop.js"></script>
	<script src="themes/js/jquery.lightbox-0.5.js"></script>

	<!-- Themes switcher section ============================================================================================= -->

	<span id="themesBtn"></span>
</body>
</html>