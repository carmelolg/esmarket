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
				<jsp:include page="menu_admin.jsp"></jsp:include>
				<!-- Sidebar end -->

				<div class="span9">

					<!-- cronologia pagine -->
					<ul class="breadcrumb">
						<li><c:url value="list_of_product" var="list_of_product" />
							<a href="${list_of_product}?storeID=${storeID}"><i
								class="icon-chevron-right"></i>List of products</a><span
							class="divider"><i class="icon-arrow-right"></i></span></li>
						<li class="active">Modify product ${product.name}</li>
					</ul>
					<!-- end cronologia -->

					<div class="well well-small">
						<sf:form
							action="modify_prod?storeID=${storeID}&idProd=${product.ID}"
							modelAttribute="product" method="POST">
							<sf:input path="ID" type="hidden" value="${product.ID}" />
							
								Product Name <br>
							<sf:errors path="name" style="color:red"></sf:errors>
							<br>
							<sf:input path="name" type="text" value="${product.name}" />
							<br>
							<hr>
								Category <br>
							<sf:errors path="category" style="color:red"></sf:errors>
							<br>
							<sf:input path="category" type="text" value="${product.category}" />
							<br>
								Brand<br>
							<sf:errors path="brand" style="color:red"></sf:errors>
							<br>
							<sf:input path="brand" type="text" value="${product.brand}" />
							<br>
							<hr>
								Quantity <br>
							<sf:errors path="quantity" style="color:red"></sf:errors>
							<br>
							<sf:input path="quantity" type="text" value="${product.quantity}" />
							<br>
								Unit Price<br>
							<sf:errors path="unitPrice" style="color:red"></sf:errors>
							<br>
							<sf:input path="unitPrice" type="text"
								value="${product.unitPrice}" />
							<br>
								Image <br>
							<sf:errors path="image" style="color:red"></sf:errors>
							<br>
							<sf:input path="image" type="text" value="${product.image}" />
							<br>
								Description<br>
							<sf:errors path="description" style="color:red"></sf:errors>
							<br>
							<sf:input path="description" type="text"
								value="${product.description}" />
							<br>
								In sale <sf:checkbox path="forSale" value="${product.forSale}" />
							<br>
							<hr>

							<sf:button type="submit" class="btn btn-primary">Modify Product</sf:button>
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