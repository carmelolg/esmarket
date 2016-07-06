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
	<jsp:include page="../jspFragments/navbar.jsp"></jsp:include>
	<!-- Header End====================================================================== -->
	<div id="mainBody">
		<div class="container">
			<div class="row">
				<!-- Sidebar ================================================== -->
				<jsp:include page="../jspFragments/leftSidebar.jsp"></jsp:include>
				<!-- Sidebar end=============================================== -->
				<div class="span9">
					<div>
						<div>
							<h4>Payment method</h4>
							<select id="paymentMethod">
								<option value="creditCard">Credit card</option>
								<option value="bankTransfer">Bank transfer</option>
							</select>
						</div>
						<div id="creditCardDiv" class="well">
							<div class="control-group">
								<label class="control-label">Name </label>
								<div class="controls">
									<input name="name" type="text" placeholder="Name">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">Surname </label>
								<div class="controls">
									<input name="name" type="text" placeholder="Surname">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">Credit card number</label>
								<div class="controls">
									<input name="name" type="text" placeholder="Credit card number">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">Credit card ICV</label>
								<div class="controls">
									<input name="name" type="text" placeholder="ICV">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">Credit card expiration day</label>
								<div class="controls">
									<input name="name" type="text" placeholder="Expiration day">
								</div>
							</div>
						</div>
						<div id="bankTransferDiv" class="well">Bank transfer
							information</div>
						<sf:form method="POST" action="purchase"
							modelAttribute="submitForm">
							<div>
								<h4>Delivery mode</h4>
								<sf:select path="deliveryMode" id="deliveryMode">
									<sf:option value="delivery">Delivery</sf:option>
									<sf:option value="inStock">In stock</sf:option>
								</sf:select>
							</div>
							<div id="deliveryDiv" class="well">
								<div class="control-group">
									<label class="control-label">Delivery address</label>
									<div class="controls">
										<sf:select path="addressSelected" id="addressSelect">
											<sf:option value="registrationAddress">Registration
												address</sf:option>
											<sf:option value="anotherAddress">Another address</sf:option>
										</sf:select>
									</div>
								</div>
								<div id="addressDiv">
									<div class="control-group">
										<label class="control-label">Address</label>
										<div class="controls">
											<sf:input type="text" path="address.address"
												placeholder="Address"></sf:input>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">City</label>
										<div class="controls">
											<sf:input type="text" path="address.city" placeholder="City"></sf:input>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">Region</label>
										<div class="controls">
											<sf:input type="text" path="address.region"
												placeholder="Region"></sf:input>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">State</label>
										<div class="controls">
											<sf:input type="text" path="address.state"
												placeholder="State"></sf:input>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">Zip/Postal code</label>
										<div class="controls">
											<sf:input type="text" path="address.zipCode"
												placeholder="Postal code"></sf:input>
										</div>
									</div>
								</div>

							</div>
							<div id="inStockDiv" class="well">
								<div class="controls">
									<table>
										<tr>
											<td><label class="control-label">Select a stock</label>
												<sf:select path="storeID" id="storeSelect">
													<c:forEach var="store" items="${stores}">
														<option value="${store.ID}">${store.name}</option>
													</c:forEach>
												</sf:select></td>
											<td style="padding-left: 50px; padding-top: 5px;"><c:forEach
													var="store" items="${stores}">
													<div class="storeClass" id="store${store.ID}">${store.position}</div>
												</c:forEach></td>
										</tr>
									</table>
								</div>
							</div>
							<button type="submit" class="btn btn-primary">Submit
								order</button>
						</sf:form>
						<div style="color: red">${submitErrorMessage}</div>
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

	<script src="themes/js/purchase.js"></script>
	<script type="text/javascript">
		
	</script>

	<span id="themesBtn"></span>
</body>
</html>