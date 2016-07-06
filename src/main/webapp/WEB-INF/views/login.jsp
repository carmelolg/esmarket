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
	<jsp:include page="jspFragments/navbar.jsp"></jsp:include>
	<!-- Header End====================================================================== -->
	<div id="mainBody">
		<div class="container">
			<div class="row">
				<!-- Sidebar ================================================== -->
				<jsp:include page="jspFragments/leftSidebar.jsp"></jsp:include>
				<!-- Sidebar end=============================================== -->
				<div class="span9">
					<ul class="breadcrumb">
						<li><a href="index.html">Home</a> <span class="divider">/</span></li>
						<li class="active">Login</li>
					</ul>
					<h3>Login</h3>
					<hr class="soft" />

					<div class="row">
						<div class="span4">
							<div class="well">
								<h5>CREATE YOUR ACCOUNT</h5>
								<br /> <br /> <a class="btn"
									href='<c:url value="register"></c:url>'>SIGN UP</a>
							</div>
						</div>
						<div class="span1">&nbsp;</div>
						<div class="span4">
							<div class="well">
								<h5>ALREADY REGISTERED ?</h5>
								<form id="loginForm2">
									<div class="control-group">
										<label class="control-label" for="inputEmail2">Email</label>
										<div class="controls">
											<input class="span3" type="text" id="inputEmail2"
												placeholder="Email">
										</div>
									</div>
									<div class="control-group">
										<label class="control-label" for="inputPassword2">Password</label>
										<div class="controls">
											<input type="password" class="span3" id="inputPassword2"
												placeholder="Password">
										</div>
									</div>
									<div style="color: red" id="errorMessage2"></div>
									<div class="control-group">
										<div class="controls">
											<button type="submit" class="btn">Sign in</button>
										</div>
									</div>
								</form>
							</div>
						</div>
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


	<script type="text/javascript">
		function getLoginResponse(response) {
			if (response == 'false') {
				$(errorMessage2).text(
						"There is no account with these credentials");
				$(inputPassword2).attr("value", "");
			} else {
				window.location = "purchase";
			}
		}

		function submitLoginForm() {
			var mail = $(inputEmail2).attr("value");
			var password = $(inputPassword2).attr("value");
			$.post("login", "mail=" + mail + "&password=" + password,
					getLoginResponse);
		}

		$(document).ready(function() {
			$(loginForm2).submit(function(e) {
				e.preventDefault(e);
				submitLoginForm();
			});
		});
	</script>



	<span id="themesBtn"></span>
</body>
</html>