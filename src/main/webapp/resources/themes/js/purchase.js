$(document).ready(function() {
	$(paymentMethod).attr("value", "creditCard");
	$(deliveryMode).attr("value", "delivery");
	$(addressSelect).attr("value", "registrationAddress");

	$(".storeClass").hide();

	$(storeSelect).attr("value", $(storeSelect).children().first().attr("value"));
	var selctedAddressId = "#store"+ $(storeSelect).children().first().attr("value");
	$(selctedAddressId).show();

	$(bankTransferDiv).hide();
	$(inStockDiv).hide();
	$(addressDiv).hide();
	addToggleEvent();
});

function addToggleEvent() {
	$(paymentMethod).change(function() {
		var selectedItem = $(paymentMethod).val();
		if (selectedItem == "creditCard") {
			$(creditCardDiv).show("slow");
			$(bankTransferDiv).hide("slow");
		} else {
			$(creditCardDiv).hide("slow");
			$(bankTransferDiv).show("slow");
		}
	});

	$(deliveryMode).change(function() {
		var selectedItem = $(deliveryMode).val();
		if (selectedItem == "delivery") {
			$(deliveryDiv).show("slow");
			$(inStockDiv).hide("slow");
		} else {
			$(deliveryDiv).hide("slow");
			$(inStockDiv).show("slow");
		}
	});

	$(addressSelect).change(function() {
		var selectedItem = $(addressSelect).val();
		if (selectedItem == "registrationAddress") {
			$(addressDiv).hide("slow");
		} else {
			$(addressDiv).show("slow");
		}
	});

	$(storeSelect).change(function() {
		var selctedAddressId = "#store"+ $(storeSelect).attr("value");
		$(".storeClass").hide();
		$(selctedAddressId).show();
	});

}