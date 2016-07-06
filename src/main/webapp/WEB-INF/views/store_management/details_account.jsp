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
				<jsp:include page="menu_admin.jsp"></jsp:include>

				<!-- Sidebar end -->

				<div class="span9">

					<!-- cronologia pagine -->
					<ul class="breadcrumb">
						<li><c:url value="list_of_account" var="list_of_account" />
							<a href="${list_of_account}"><i class="icon-chevron-right"></i>List
								of accounts</a><span class="divider"><i class="icon-arrow-right"></i></span></li>
						<li><c:url value="list_of_manager" var="list_of_manager" />
							<a href="${list_of_manager}"><i class="icon-chevron-right"></i>List
								of managers</a><span class="divider"><i class="icon-arrow-right"></i></span></li>
						<li class="active"><i class="icon-zoom-in"></i>Details
							${account.username}</li>
					</ul>
					<!-- end cronologia -->

					<div class="well well-small">
						<div class="store_table">
							<table>
								<tr>
									<td>Account name</td>
									<td>${account.username}</td>
									<td>Additional Information</td>

								</tr>
								<tr>
									<td>Address</td>
									<td>${account.address.toString()}</td>
									<td>Google Maps</td>
								</tr>
								<tr>
									<td>e-Mail</td>
									<td>${account.eMail}</td>
									<td></td>
								</tr>
								<tr>
									<td>Manager</td>
									<td><c:if test="${account.getClass().getName() eq 'it.unical.esmarket.domain.account.ManagerShop'}">YES</c:if>
										<c:if test="${account.getClass().getName() eq 'it.unical.esmarket.domain.account.Client'}">NO</c:if></td>
									<td></td>
								</tr>
							</table>
						</div>
						<!-- Content -->
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