package it.carmelolagamba.esmarket.controller.purchase;

import org.springframework.stereotype.Component;

import it.carmelolagamba.esmarket.domain.account.Address;

@Component
public class SubmitForm {

	private String deliveryMode;
	private String addressSelected;
	private Address address;
	private Long storeID;

	public SubmitForm() {
		deliveryMode = new String();
		addressSelected = new String();
		address = new Address();
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}


	public Long getStoreID() {
		return storeID;
	}

	public void setStoreID(Long storeID) {
		this.storeID = storeID;
	}

	public String getDeliveryMode() {
		return deliveryMode;
	}

	public void setDeliveryMode(String deliveryMode) {
		this.deliveryMode = deliveryMode;
	}

	public String getAddressSelected() {
		return addressSelected;
	}

	public void setAddressSelected(String addressSelected) {
		this.addressSelected = addressSelected;
	}
}