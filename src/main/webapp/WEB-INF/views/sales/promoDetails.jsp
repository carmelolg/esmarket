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
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
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
						<li><c:url value="listofPromotion" var="listofPromotion" />
							<a href="${listofPromotion}"><i class="icon-chevron-right"></i>List
								of promotions</a><span class="divider"><i
								class="icon-arrow-right"></i></span></li>
						<li class="active">Details promotion</li>
					</ul>
					<!-- end cronologia -->

					<div class="span6">

						<hr class="soft" />


						<h4>Promotion Information</h4>
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
								<tr class="techSpecRow">
									<td class="techSpecTD1">Discount:</td>
									<td class="techSpecTD2">${promotion.discount }</td>
								</tr>
								<tr class="techSpecRow">
									<td class="techSpecTD1">Category:</td>
									<td class="techSpecTD2">${category}</td>
								</tr>

							</tbody>
						</table>

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
							<div class="tab-pane" id="listView"></div>
							<div class="tab-pane active" id="blockView">
								<ul class="thumbnails">
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

	<span id="themesBtn"></span>
</body>
</html>