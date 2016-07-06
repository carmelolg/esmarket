<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<ul id="topMenu" class="nav pull-right">
	<li class=""><a href="<c:url value="/"/>">Home</a></li>
	<!-- <li class=""><a href="special_offer.html">Specials Offer</a></li>
	<li class=""><a href="normal.html">Delivery</a></li>
	<li class=""><a href="contact.html">Contact</a></li> -->



	<c:if test="${sessionScope.user.getClass().name eq 'it.unical.esmarket.domain.account.ManagerShop'}">
		<li class=""><c:url value="list_of_storage"
				var="store_management" /><a href="${store_management}?id=${sessionScope.user.ID}">Storage
				Management</a></li>
	</c:if>
	<c:if test="${sessionScope.user.getClass().name eq 'it.unical.esmarket.domain.account.Administrator'}">
		<li class=""><c:url value="list_of_storage"
				var="store_management" /><a href="${store_management}?id=${sessionScope.user.ID}">Storage
				Management</a></li>
	</c:if>


	<c:if test="${sessionScope.user ne null}">
		<li class=""><a href="<c:url value="accountDetails"/>">My
				Account</a></li>
	</c:if>
	<c:choose>
		<c:when test="${sessionScope.user eq null}">
			<li class=""><a href="#login" role="button" data-toggle="modal"
				style="padding-right: 0"><span class="btn btn-large btn-success">Login</span></a>
				<div id="login" class="modal hide fade in" tabindex="-1"
					role="dialog" aria-labelledby="login" aria-hidden="false">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">×</button>
						<h3>Login Block</h3>
					</div>
					<div class="modal-body">
						<form id="loginForm" class="form-horizontal loginFrm"
							method="post">
							<div class="control-group">
								<input type="text" id="inputEmail" placeholder="Email"
									name="mail">
							</div>
							<div class="control-group">
								<input type="password" id="inputPassword" placeholder="Password"
									name="password">
							</div>
							<div style="color: red" id="errorMessage"></div>
							<label></label> <label></label> <label></label>
							<button type="submit" class="btn btn-success">Sign in</button>
							<button class="btn" data-dismiss="modal" aria-hidden="true">Close</button>
						</form>
					</div>
				</div></li>
		</c:when>
		<c:otherwise>
			<li><a href='<c:url value="logout"></c:url>' role="button"
				style="padding-right: 0"><span class="btn btn-large btn-success">Logout</span></a>
			</li>
		</c:otherwise>
	</c:choose>
</ul>


<script src="themes/js/jquery.js" type="text/javascript"></script>
<script type="text/javascript">
	function getLoginResponse(response) {
		if (response == 'false') {
			$(errorMessage).text("There is no account with these credentials");
			$(inputPassword).attr("value", "");
		} else {
			if (window.location.href.indexOf("register") != -1) {
				window.location.href = '%20/';
			} else {
				window.location = window.location;
			}
		}
	}

	function submitLoginForm() {
		var mail = $(inputEmail).attr("value");
		var password = $(inputPassword).attr("value");
		$.post("login", "mail=" + mail + "&password=" + password,
				getLoginResponse);
	}

	$(document).ready(function() {
		$(loginForm).submit(function(e) {
			e.preventDefault(e);
			submitLoginForm();
		});
	});
</script>
