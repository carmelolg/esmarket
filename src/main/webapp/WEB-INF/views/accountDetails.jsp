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
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>



</head>

<body>
	<jsp:include page="jspFragments/navbar.jsp"></jsp:include>
	<!-- Header End====================================================================== -->
	<div id="mainBody">
		<div class="container">
			<div class="row">
				<!-- Sidebar ================================================== -->
				<div id="sidebar" class="span3">
					<c:if
						test="${sessionScope.user.getClass().getName() eq 'it.unical.esmarket.domain.account.Client'}">
						<ul id="sideManu" class="nav nav-tabs nav-stacked">
							<li class="subMenu open"><a>ORDERS</a>
								<ul>
									<li><a href="<s:url value="listOfClientOrders"/>"><i
											class="icon-chevron-right"></i>My orders list</a></li>
								</ul></li>
						</ul>
						<br />
					</c:if>
				</div>
				<!-- Sidebar end=============================================== -->
				<div class="span9">
					<ul class="breadcrumb">
						<li><a href='<c:url value="/"></c:url>'>Home</a> <span
							class="divider">/</span></li>
						<li class="active">Your personal information</li>
					</ul>
					<div class="well">
						<sf:form method="post" action="modifyUserInfo"
							class="form-horizontal" modelAttribute="currentUser">
							<sf:input path="eMail" value="${sessionScope.user.eMail}" type="hidden"/>
							<h4>Your personal information</h4>
								<div class="control-group">
									<label class="control-label" for="inputLnam">Username</label>
									<div class="controls">
										<sf:input path="username" type="text" id="inputLnam"
											placeholder="username"></sf:input>
										<div style="color: red">
											<sf:errors path="username" />
										</div>
									</div>
								</div>
								<div class="control-group">
									<label class="control-label" for="inputPassword1">Password

									</label>
									<div class="controls">
										<sf:input path="password" type="password" id="inputPassword1"
											placeholder="Password"></sf:input>
										<div style="color: red">
											<sf:errors path="password"></sf:errors>
										</div>
									</div>

								</div>

								<div class="control-group">
									<label class="control-label" for="address">Address</label>
									<div class="controls">
										<sf:input path="address.address" type="text" id="address"
											placeholder="Address"></sf:input>
										<div style="color: red">
											<sf:errors path="address.address"></sf:errors>
										</div>
									</div>

								</div>
								<div class="control-group">
									<label class="control-label" for="city">City</label>
									<div class="controls">
										<sf:input path="address.city" type="text" id="city"
											placeholder="city"></sf:input>
										<div style="color: red">
											<sf:errors path="address.city"></sf:errors>
										</div>
									</div>

								</div>
								<div class="control-group">
									<label class="control-label" for="State">State</label>
									<div class="controls">
										<sf:input path="address.state" type="text" id="state"
											placeholder="state"></sf:input>
										<div style="color: red">
											<sf:errors path="address.state"></sf:errors>
										</div>
									</div>

								</div>
								<div class="control-group">
									<label class="control-label" for="postcode">Zip /
										Postal Code </label>
									<div class="controls">
										<sf:input path="address.zipCode" type="text" id="postcode"
											placeholder="Zip / Postal Code"></sf:input>
										<div style="color: red">
											<sf:errors path="address.zipCode"></sf:errors>
										</div>
									</div>
								</div>
								<div class="control-group">
									<label class="control-label" for="region">Region </label>
									<div class="controls">
										<sf:input path="address.region" type="text" id="region"
											placeholder="Region"></sf:input>
										<div style="color: red">
											<sf:errors path="address.region"></sf:errors>
										</div>
									</div>

								</div>

								<div class="control-group">
									<div class="controls">

										<input class="btn btn-large btn-success" type="submit"
											value="Modify" />
									</div>
								</div>
						</sf:form>
					</div>
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