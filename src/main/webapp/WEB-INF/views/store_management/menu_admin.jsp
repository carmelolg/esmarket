<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- SIDEBAR LEFT -->
<div id="sidebar" class="span3">

	<ul id="sideManu" class="nav nav-tabs nav-stacked">
		<li class="subMenu open"><a> STORE MANAGEMENT</a>
			<ul>
				<li><c:url value="list_of_storage" var="list_of_storage" /> <a
					href="${list_of_storage}?id=${sessionScope.user.ID}"><i
						class="icon-chevron-right"></i>List of storage</a></li>
			</ul></li>

		<li class="subMenu"><a> ORDER MANAGEMENT</a>
			<ul style="display: none">
				<li><c:url value="list_of_orders" var="list_of_orders" /> <a
					href="${list_of_orders}?idUser=${sessionScope.user.ID}"><i
						class="icon-chevron-right"></i>List of orders</a></li>
			</ul></li>
		<li class="subMenu"><a> PROMOTION MANAGEMENT</a>
			<ul style="display: none">
				<li><c:url value="listofPromotion" var="listofPromotion" /> <a
					href="${listofPromotion}"><i
						class="icon-chevron-right"></i>Promotions list</a>
				</li>
				<li><c:url value="addPromotion" var="addPromotion" /> <a
					href="${addPromotion}"><i
						class="icon-chevron-right"></i>Add promotion</a>
				</li>
			</ul></li>
		<c:if
			test="${sessionScope.user.getClass().name eq 'it.unical.esmarket.domain.account.Administrator'}">

			<li class="subMenu"><a> ACCOUNT MANAGEMENT </a>
				<ul style="display: none">
					<li><c:url value="list_of_account" var="list_of_account" /> <a
						href="${list_of_account}"><i class="icon-chevron-right"></i>List
							of account</a></li>
					<li><c:url value="list_of_manager" var="list_of_manager" /> <a
						href="${list_of_manager}"><i class="icon-chevron-right"></i>List
							of manager</a></li>
				</ul>
				</li>
		</c:if>
	</ul>

</div>

<!--  SIDEBAR END -->
